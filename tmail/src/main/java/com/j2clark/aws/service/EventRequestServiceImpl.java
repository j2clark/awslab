package com.j2clark.aws.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final AmazonSQS sqs;
    private final String queueName;
    private final String queueUrl;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public EventRequestServiceImpl(
        @Value("${resource.queuename:tmail-resource}") final String queueName,
        final AmazonSQS sqs) {

        this.queueName = queueName;

        // fail fast - if we do not find the queue, we should throw up
        logger.info("look up SQS QueueURL for queueName["+queueName+"]...");
        this.queueUrl = getQueueUrl(sqs, queueName);
        logger.info("SQS["+queueUrl+"] found for queueName["+queueName+"]");
        this.sqs = sqs;
    }

    /**
     * publish request to queue for asynch processing
     */
    public void onRequest(Request request) {

        logger.info("Request["+request.getTransactionId()+"] onRequest");

        try {
            EventMessage message = EventMessageBuilder.of(request).build();

            String json = objectMapper.writeValueAsString(message);

            SendMessageResult result = sqs.sendMessage(new SendMessageRequest(queueUrl, json));

            logger.info("Request["+request.getTransactionId()+"] SQS["+queueName+"] pushed. MessageId[" + result.getMessageId() + "]");
        } catch (IOException e) {
            // fatal exception - this request is dead
            // todo: create a nicr exception which we can handle more gracefully
            throw new RuntimeException(e);
        }
    }

    protected String getQueueUrl(final AmazonSQS sqs, String queueName){
        GetQueueUrlRequest getQueueUrlRequest = new GetQueueUrlRequest(queueName);
        return sqs.getQueueUrl(getQueueUrlRequest).getQueueUrl();
    }

}
