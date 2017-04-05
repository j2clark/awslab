package com.j2clark.aws.sqs;

import com.amazonaws.services.sqs.model.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class MockSQSFactory extends SQSFactory {

    private Map<String,SQSQueue> queueMap = new HashMap<>();
    private final int[] semaphore = new int[0];

    public MockSQSFactory() {
        super(true, null);
    }

    @Override
    public SQSQueue of(String queueName) {
        SQSQueue queue = null;
        if(queueMap.containsKey(queueName)) {
            queue = queueMap.get(queueName);
        }

        if (queue == null) {
            synchronized (semaphore) {
                queue = new MockSQSQueue(queueName);
                queueMap.put(queueName, queue);
            }
        }

        return queue;
    }

    private static class MockSQSQueue implements SQSQueue {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private final String name;
        private Map<String,String> queue = new LinkedHashMap<>();


        public MockSQSQueue(String name) {
            this.name = name;
        }


        @Override
        public String name() {
            return name;
        }

        @Override
        public String send(String body) {
            String messageId = UUID.randomUUID().toString();
            queue.put(messageId, body);
            return messageId;
        }

        @Override
        public Collection<Message> retrieveMessages(int maxCount) {
            Set<String> keys = queue.keySet().stream().limit(maxCount).collect(Collectors.toSet());
            return
                keys.stream().filter(queue::containsKey).map(key -> new Message()
                    .withMessageId(key)
                    .withReceiptHandle(key) // NOTE: in AWS, these keys are never the same, and a new requestHandle is generated each request
                    .withBody(queue.get(key))).collect(Collectors.toList());
        }

        @Override
        public Message deleteMessage(Message message) {
            String handle = message.getReceiptHandle();
            if (queue.containsKey(handle)) {
                logger.info("SQSQueue Message ["+message.getMessageId()+"] deleted");
                queue.remove(handle);
            } else {
                logger.warn("MessageHandle["+handle+"] not found in queue");
            }
            return message;
        }
    }

}
