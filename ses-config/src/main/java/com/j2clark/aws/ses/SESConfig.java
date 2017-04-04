package com.j2clark.aws.ses;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsyncClientBuilder;

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
public class SESConfig {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Environment environment;

    @Bean
    public AmazonSimpleEmailService amazonSQSClient(@Value("${aws.sqs.region:us-west-1}") String awsRegion) {

        AmazonSimpleEmailService amazonSES;
        Regions region;
        if(isDev()) {
            logger.warn("Dev Environment!!! Using MockAmazonSQS implementation!");
            amazonSES = new MockAmazonSimpleEmailService();
        } else {
            // aothough Regions is an enum, Regions.valueOf() throws IllegalArgumentException. Use fromName() instead
            region = Regions.fromName(awsRegion);
            amazonSES = AmazonSimpleEmailServiceAsyncClientBuilder.standard().withRegion(region).build();
        }

        return amazonSES;
    }

    protected boolean isDev() {
        Collection<String> activeProfiles = Arrays.asList(environment.getActiveProfiles());
        return !CollectionUtils.isEmpty(activeProfiles) && activeProfiles.contains("dev");
    }
}
