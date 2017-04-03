package com.j2clark.aws.controllers;

import com.amazonaws.services.sqs.AmazonSQS;
import com.j2clark.aws.domain.WelcomeRequest;
import com.j2clark.aws.service.EventRequestService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
@RequestMapping(value = "/event")
public class EventController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final AmazonSQS sqs;
    private final EventRequestService someService;

    @Autowired
    public EventController(final AmazonSQS sqs,
                           final EventRequestService someService) {
        this.sqs = sqs;
        this.someService = someService;
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    UUID onmRequest(@RequestBody WelcomeRequestModel model) {
        UUID transactionId = UUID.randomUUID();

        // exception if required data missing or malformed
        WelcomeRequest welcomeRequest = WelcomeRequestAdapter.of(transactionId)
            .with(model)
            .build();

        someService.onRequest(welcomeRequest);

        return transactionId;
    }


    /*@RequestMapping(value = "/push", method = RequestMethod.POST, consumes = "application/json")
    public
    @ResponseBody
    String pushToQueue(@RequestParam("qname") String queueName,
                       @RequestParam("msg") String msg) {

        //sqs.

        //logger.info("find queueName["+queueName+"]");
        //GetQueueUrlResult getQueueUrlResult = sqs.getQueueUrl(queueName);
        //if (getQueueUrlResult.)
        logger.info("create SQS[" + queueName + "]");

        CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName);
        String myQueueUrl = sqs.createQueue(createQueueRequest).getQueueUrl();

        logger.info("SQS[" + queueName + "] push [" + msg + "]");
        SendMessageResult result = sqs.sendMessage(new SendMessageRequest(myQueueUrl, msg));

        return result.toString();
    }

    @RequestMapping(value="/pull", method = RequestMethod.GET)
    public
    @ResponseBody
    Message pullFromQueue(@RequestParam("qname") String queueName) {

        // Receive messages
        logger.info("Receiving messages from SQS[" + queueName + "]");
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueName);
        List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();

        Message message = null;
        if (!messages.isEmpty()) {
        for (Message message : messages) {
            System.out.println("  Message");
            System.out.println("    MessageId:     " + message.getMessageId());
            System.out.println("    ReceiptHandle: " + message.getReceiptHandle());
            System.out.println("    MD5OfBody:     " + message.getMD5OfBody());
            System.out.println("    Body:          " + message.getBody());
            for (Map.Entry<String, String> entry : message.getAttributes().entrySet()) {
                System.out.println("  Attribute");
                System.out.println("    Name:  " + entry.getKey());
                System.out.println("    Value: " + entry.getValue());
            }
        }
        System.out.println();

            // Delete a message
            System.out.println("Deleting a message.\n");
            message = messages.get(0);
            String messageReceiptHandle = message.getReceiptHandle();
            sqs.deleteMessage(new DeleteMessageRequest(queueName, messageReceiptHandle));
        }

        return message;
    }*/
}