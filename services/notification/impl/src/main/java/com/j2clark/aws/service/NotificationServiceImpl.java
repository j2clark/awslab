package com.j2clark.aws.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.j2clark.aws.domain.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationServiceImpl implements NotificationService {

    // http://docs.aws.amazon.com/ses/latest/DeveloperGuide/send-using-sdk-java.html

    private final AmazonSimpleEmailService emailService;
    private final AmazonSNS snsService;

    @Autowired
    public NotificationServiceImpl(final AmazonSimpleEmailService emailService,
                                   final AmazonSNS snsService) {
        this.emailService = emailService;
        this.snsService = snsService;
    }

    @Override
    public void send(Notification notification) throws NotificationException {

        throw new UnsupportedOperationException("TO BE IMPLEMENTED");

        /*sendSMS(notification);

        sendEmail(notification);*/
    }

    /**
     * @return the message id
     */
    protected String sendSMS(Notification notification) /*throws SMSException*/ {

        String message = "My SMS message";
        String phoneNumber = "+1XXX5550100";
        Map<String, MessageAttributeValue> smsAttributes = new HashMap<>();

        PublishResult result = snsService.publish(new PublishRequest()
                                                     .withMessage(message)
                                                     .withPhoneNumber(phoneNumber)
                                                     .withMessageAttributes(smsAttributes));

        return result.getMessageId();
    }


    protected String sendEmail(Notification notification) /*throws EMailException*/ {

        String TO = "";
        String SUBJECT = "";
        String BODY = "";
        String FROM = "";

        // is this an sms message?
        // is this an email service?
        // if both, send to both

        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(new String[]{TO});

        // Create the subject and body of the message.
        Content subject = new Content().withData(SUBJECT);
        Content textBody = new Content().withData(BODY);
        Body body = new Body().withText(textBody);

        // Create a message with the specified subject and body.
        Message message = new Message().withSubject(subject).withBody(body);

        // Assemble the email.
        SendEmailRequest request = new SendEmailRequest()
            .withSource(FROM)
            .withDestination(destination)
            .withMessage(message);

        try
        {
            System.out.println("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");

            // Instantiate an Amazon SES client, which will make the service call. The service call requires your AWS credentials.
            // Because we're not providing an argument when instantiating the client, the SDK will attempt to find your AWS credentials
            // using the default credential provider chain. The first place the chain looks for the credentials is in environment variables
            // AWS_ACCESS_KEY_ID and AWS_SECRET_KEY.
            // For more information, see http://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html
            //AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient();

            // Choose the AWS region of the Amazon SES endpoint you want to connect to. Note that your sandbox
            // status, sending limits, and Amazon SES identity-related settings are specific to a given AWS
            // region, so be sure to select an AWS region in which you set up Amazon SES. Here, we are using
            // the US West (Oregon) region. Examples of other regions that Amazon SES supports are US_EAST_1
            // and EU_WEST_1. For a complete list, see http://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html
            //Region REGION = Region.getRegion(Regions.US_WEST_2);
            //client.setRegion(REGION);

            // Send the email.
            SendEmailResult result = emailService.sendEmail(request);
            return result.getMessageId();

        }
        catch (Exception ex)
        {

            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }

        return null;
    }
}
