package com.j2clark.aws.ses;

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

/**
 * http://docs.aws.amazon.com/sns/latest/dg/welcome.html
 *
 *
 * http://docs.aws.amazon.com/ses/latest/DeveloperGuide/send-using-sdk-java.html
 *
 * http://docs.aws.amazon.com/ses/latest/DeveloperGuide/send-using-sdk-java.html
 *
 * Test
 * http://docs.aws.amazon.com/ses/latest/DeveloperGuide/mailbox-simulator.html
 */
@Configuration
public class SESConfig {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Environment environment;

    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService(@Value("${aws.ses.region:us-west-1}") String awsRegion) {

        AmazonSimpleEmailService amazonSES;
        if(isDev()) {
            logger.warn("Dev Environment!!! Using MockAmazonSQS implementation!");
            amazonSES = new MockAmazonSimpleEmailService();
        } else {
            // although Regions is an enum, Regions.valueOf() throws IllegalArgumentException. Use fromName() instead
            amazonSES = AmazonSimpleEmailServiceAsyncClientBuilder.standard()
                .withRegion(awsRegion)
                .build();
        }

        return amazonSES;
    }

    protected boolean isDev() {
        Collection<String> activeProfiles = Arrays.asList(environment.getActiveProfiles());
        return !CollectionUtils.isEmpty(activeProfiles) && activeProfiles.contains("dev");
    }
}
