package com.j2clark.aws;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.ListQueuesResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Setting up AWS EC2 - this helped get me moving: http://briansjavablog.blogspot.com/2016/05/spring-boot-angular-amazon-web-services.html
 *
 */

@SpringBootApplication
@EnableScheduling   // todo: remember to move this to handler application
public class Application {


    // working with AWS IAM roles on EC2 instance
    //see http://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/java-dg-roles.html

    // example of overriding sqs endpoint/regio
    //see https://www.javacodegeeks.com/2013/06/working-with-amazon-simple-queue-service-using-java.html

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }


}
