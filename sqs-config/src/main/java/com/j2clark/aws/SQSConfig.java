package com.j2clark.aws;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

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

@Configuration
public class SQSConfig {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Environment environment;

    @Bean
    public AmazonSQS amazonSQSClient(@Value("${aws.sqs.region:us-west-1}") String awsRegion) {

        AmazonSQS amazonSQS;

        Regions region;
        if(isDev()) {
            region = Regions.DEFAULT_REGION;
            logger.warn("Dev Environment!!! Using MockAmazonSQS implementation!");
            amazonSQS = new MockAmazonSQS();
        } else {
            // aothough Regions is an enum, Regions.valueOf() throws IllegalArgumentException. Use fromName() instead
            region = Regions.fromName(awsRegion);
            amazonSQS = AmazonSQSClientBuilder.standard().withRegion(region).build();
        }

        logger.info("SQS ]" + region.getName() + "] queueUrls:");
        for (String queueUrl : amazonSQS.listQueues().getQueueUrls()) {
            logger.info("SQS[" + queueUrl + "]");
        }

        return amazonSQS;
    }


    protected boolean isDev() {
        Collection<String> activeProfiles = Arrays.asList(environment.getActiveProfiles());
        return !CollectionUtils.isEmpty(activeProfiles) && activeProfiles.contains("dev");
    }

}
