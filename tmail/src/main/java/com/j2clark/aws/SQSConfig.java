package com.j2clark.aws;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AbstractAmazonSQS;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageBatchResult;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteMessageResult;
import com.amazonaws.services.sqs.model.GetQueueUrlRequest;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Configuration
public class SQSConfig {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Environment environment;


    @Bean
    public AmazonSQS amazonSQSClient(@Value("${aws.sqs.region:us-west-1}") String awsRegion) {

        AmazonSQS amazonSQS;

        if(isDev()) {
            logger.warn("Dev Environment!!! Using MockAmazonSQS implementation!");
            amazonSQS = new MockAmazonSQS();
        } else {
            amazonSQS = AmazonSQSClientBuilder.standard().withRegion(Regions.valueOf(awsRegion)).build();
        }

        logger.info("SQS ]" + Regions.US_WEST_1.getName() + "] queueUrls:");
        for (String queueUrl : amazonSQS.listQueues().getQueueUrls()) {
            logger.info("SQS[" + queueUrl + "]");
        }

        return amazonSQS;
    }

    protected boolean isDev() {
        Collection<String> activeProfiles = Arrays.asList(environment.getActiveProfiles());
        return !CollectionUtils.isEmpty(activeProfiles) && activeProfiles.contains("dev");
    }

    public static class MockAmazonSQS extends AbstractAmazonSQS {

        @Override
        public ListQueuesResult listQueues() {
            return new ListQueuesResult().withQueueUrls("");
        }

        public GetQueueUrlResult getQueueUrl(GetQueueUrlRequest getQueueUrlRequest) {
            return new GetQueueUrlResult().withQueueUrl(getQueueUrlRequest.getQueueName());
        }

        public ReceiveMessageResult receiveMessage(ReceiveMessageRequest receiveMessageRequest) {
            return new ReceiveMessageResult().withMessages(
                Collections.singletonList(
                    new Message().withBody(
                        "{\"type\":\"welcome\",\"version\":\"1\",\"json\":\"{\\\"transactionId\\\":\\\"418ad30e-417d-4c37-9f54-d31ad3c18d81\\\",\\\"clientId\\\":null,\\\"requestDateTime\\\":1491202038442,\\\"uid\\\":\\\"d7bfccab-3ff3-47ad-8d20-04732a068995\\\",\\\"firstName\\\":null,\\\"lastName\\\":null,\\\"email\\\":\\\"xyz@shutterfly.com\\\"}\"}")
                )
            );
        }

        public DeleteMessageResult deleteMessage(DeleteMessageRequest request) {
            return new DeleteMessageResult();
        }
    }

}
