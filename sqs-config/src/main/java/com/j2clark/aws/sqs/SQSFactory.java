package com.j2clark.aws.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteMessageResult;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.QueueDoesNotExistException;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.util.CollectionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Component
public class SQSFactory {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final boolean forceCreate;
    private final AmazonSQS amazonSQS;

    @Autowired
    public SQSFactory(@Value("${sqsfactory.forceCreate:false}") final boolean forceCreate,
                      final AmazonSQS amazonSQS) {
        this.forceCreate = forceCreate;
        this.amazonSQS = amazonSQS;
    }

    public SQS of(String queueName) {

        Optional<String> queueUrl = findQueueUrl(queueName);
        if (!queueUrl.isPresent()) {
            if (forceCreate) {
                logger.warn("force-create SQS queue for queueName["+queueName+"]");
                CreateQueueResult result = amazonSQS.createQueue(new CreateQueueRequest(queueName));
                queueUrl = Optional.of(result.getQueueUrl());
            } else {
                throw new IllegalStateException("SQS QueueName["+queueName+"] does not exist");
            }
        }

        logger.warn("queueUrl["+queueUrl+"] found for SQS queue with queueName["+queueName+"]");

        return new SQSImpl(amazonSQS, queueUrl.get(), queueName);
    }

    private Optional<String> findQueueUrl(String queueName) {
        try {
            GetQueueUrlResult result = amazonSQS.getQueueUrl(queueName);
            return Optional.of(result.getQueueUrl());
        } catch (QueueDoesNotExistException e) {
            return Optional.empty();
        }
    }

    private static class SQSImpl implements SQS {

        private final AmazonSQS sqs;
        private final String queueName;
        private final String queueUrl;

        public SQSImpl(AmazonSQS sqs, String queueUrl, String queueName) {
            this.sqs = sqs;
            this.queueUrl = queueUrl;
            this.queueName = queueName;
        }

        public String name() {
            return queueName;
        }

        public String send(String body) {
            return sqs.sendMessage(new SendMessageRequest(queueUrl, body)).getMessageId();
        }

        public Collection<Message> retrieveMessages(int maxEvents) {
            ReceiveMessageResult result = sqs.receiveMessage(
                new ReceiveMessageRequest().withQueueUrl(queueUrl)
                    .withMaxNumberOfMessages(maxEvents));

            if(!CollectionUtils.isNullOrEmpty(result.getMessages())) {
                return Collections.unmodifiableCollection(result.getMessages());
            } else {
                return Collections.emptyList();
            }
        }

        public String deleteMessage(Message message) {
            String messageId = message.getMessageId();
            deleteMessage(message.getReceiptHandle());
            return messageId;
        }

        public void deleteMessage(String messageHandle) {

            DeleteMessageResult
                deleteResult =
                sqs.deleteMessage(new DeleteMessageRequest().withQueueUrl(queueUrl)
                                      .withReceiptHandle(messageHandle));
        }
    }
}
