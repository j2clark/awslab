package com.j2clark.aws.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.amazonaws.services.sqs.buffered.AmazonSQSBufferedAsyncClient;

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

/**
 * API Reference
 * http://docs.aws.amazon.com/AWSSimpleQueueService/latest/APIReference/Welcome.html
 *
 * Developer Guide
 * http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/Welcome.html
 *
 * http://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/index.html?com/amazonaws/services/sqs/AmazonSQS.html
 *
 * http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/sqs-message-attributes.html
 *
 * General: creating service clients
 * http://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/creating-clients.html
 */
@Configuration
public class SQSConfig {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Environment environment;

    @Bean
    public SQSFactory amazonSQSFactory (@Value("${aws.sqs.region:us-west-1}") String awsRegion,
                                        @Value("${sqsfactory.forceCreate:false}") final boolean forceCreate) {
        if (isDev()) {
            return new MockSQSFactory();
        } else {
            AmazonSQS amazonSQS = new AmazonSQSBufferedAsyncClient(
                AmazonSQSAsyncClientBuilder.standard()
                    .withRegion(awsRegion)
                    .build()
            );

            return new SQSFactory(forceCreate, amazonSQS);
        }
    }

    protected boolean isDev() {
        Collection<String> activeProfiles = Arrays.asList(environment.getActiveProfiles());
        return !CollectionUtils.isEmpty(activeProfiles) && activeProfiles.contains("dev");
    }

}
