package com.j2clark.aws.sns;

import com.amazonaws.client.builder.ExecutorFactory;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;

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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  API Reference
 *  http://docs.aws.amazon.com/sns/latest/api/Welcome.html
 *
 *  Developer Guide
 *  http://docs.aws.amazon.com/sns/latest/dg/welcome.html
 *
 *  SNS seems like a traditional PubSub model
 */
@Configuration
public class SNSConfig {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Environment environment;

    @Bean
    public AmazonSNS amazonSNSClient(@Value("${aws.sns.region:us-west-1}") String awsRegion) {

        AmazonSNS amazonSNS;

        if(isDev()) {
            logger.warn("Dev Environment!!! Using MockAmazonSQS implementation!");
            amazonSNS = new MockAmazonSNS();
        } else {
            // although Regions is an enum, Regions.valueOf() throws IllegalArgumentException. Use fromName() instead
            amazonSNS = AmazonSNSAsyncClientBuilder.standard()
                    .withRegion(awsRegion)
                    //.withExecutorFactory(new ExecutorFactoryImpl())
                    .build();
        }

        return amazonSNS;
    }

    protected boolean isDev() {
        Collection<String> activeProfiles = Arrays.asList(environment.getActiveProfiles());
        return !CollectionUtils.isEmpty(activeProfiles) && activeProfiles.contains("dev");
    }

    public static class ExecutorFactoryImpl implements ExecutorFactory {

        @Override
        public ExecutorService newExecutor() {
            return Executors.newSingleThreadExecutor();
        }
    }
}
