package com.j2clark.aws.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.j2clark.aws.sqs.SQS;
import com.j2clark.aws.sqs.SQSFactory;
import com.j2clark.aws.domain.EventMessage;
import com.j2clark.aws.domain.EventMessageBuilder;
import com.j2clark.aws.domain.Request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EventRequestServiceImpl implements EventRequestService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final SQS sqs;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public EventRequestServiceImpl(
        @Value("${resource.queuename:tmail-resource}") final String queueName,
        final SQSFactory sqsFactory) {

        // fail fast - sqsFactgory will throw an exception if queue is not found, and force create not enabled
        this.sqs = sqsFactory.of(queueName);
    }

    /**
     * publish request to queue for asynch processing
     */
    public void onRequest(Request request) {

        logger.info("Request["+request.getTransactionId()+"] onRequest");

        try {
            EventMessage message = EventMessageBuilder.of(request).build();
            String json = objectMapper.writeValueAsString(message);
            String messageId = sqs.send(json);

            logger.info("Request["+request.getTransactionId()+"] SQS["+sqs.name()+"] pushed. MessageId[" + messageId + "]");
        } catch (IOException e) {
            // fatal exception - this request is dead
            // todo: create a nicr exception which we can handle more gracefully
            throw new RuntimeException(e);
        }
    }
}
