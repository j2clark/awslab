package com.j2clark.aws;

import com.amazonaws.services.sqs.AbstractAmazonSQS;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteMessageResult;
import com.amazonaws.services.sqs.model.GetQueueUrlRequest;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class MockAmazonSQS extends AbstractAmazonSQS {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    //private Stack<Message> stack = new Stack<>();
    private Map<String,String> queue = new LinkedHashMap<>();
    // todo: will adapt this to an in-memroy impl of AmazonSQS

    @Override
    public ListQueuesResult listQueues() {
        return new ListQueuesResult().withQueueUrls("");
    }

    public GetQueueUrlResult getQueueUrl(GetQueueUrlRequest getQueueUrlRequest) {
        return new GetQueueUrlResult().withQueueUrl(getQueueUrlRequest.getQueueName());
    }

    public ReceiveMessageResult receiveMessage(ReceiveMessageRequest receiveMessageRequest) {
        /*return new ReceiveMessageResult().withMessages(
            Collections.singletonList(
                new Message().withBody(
                    "{\"type\":\"welcome\",\"version\":\"1\",\"json\":\"{\\\"transactionId\\\":\\\"418ad30e-417d-4c37-9f54-d31ad3c18d81\\\",\\\"clientId\\\":null,\\\"requestDateTime\\\":1491202038442,\\\"uid\\\":\\\"d7bfccab-3ff3-47ad-8d20-04732a068995\\\",\\\"firstName\\\":null,\\\"lastName\\\":null,\\\"email\\\":\\\"xyz@shutterfly.com\\\"}\"}")
            )
        );*/

        Integer maxCount = receiveMessageRequest.getMaxNumberOfMessages();
        if (maxCount == null) {
            maxCount = 1;
        }
        Set<String> keys = queue.keySet().stream().limit(maxCount).collect(Collectors.toSet());
        List<Message> results = new ArrayList<>();
        for (String key : keys) {
            if (queue.containsKey(key)) {
                results.add(new Message().withMessageId(key).withBody(queue.get(key)));
            }
        }

        if(!results.isEmpty()) {
            logger.info("SQS Message " + keys + " received");
        }

        return new ReceiveMessageResult().withMessages(
            results
        );
    }

    public DeleteMessageResult deleteMessage(DeleteMessageRequest request) {
        if (queue.containsKey(request.getReceiptHandle())) {
            logger.info("SQS Message ["+request.getReceiptHandle()+"] deleted");
            queue.remove(request.getReceiptHandle());
        } else {
            logger.warn("MessageHandle["+request.getReceiptHandle()+"] not found in queue");
        }
        return new DeleteMessageResult();
    }

    public SendMessageResult sendMessage(SendMessageRequest request) {

        String id = UUID.randomUUID().toString();

        logger.info("SQS Message ["+id+"] sent");

        queue.put(id, request.getMessageBody());
        return new SendMessageResult().withMessageId(id);
    }
}
