package com.j2clark.aws.handler;

import com.j2clark.aws.domain.Request;
import com.j2clark.aws.domain.NotificationBuilder;
import com.j2clark.aws.service.NotificationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WelcomeRequestHandler extends AbstractRequestHandler implements RequestHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final NotificationService notificationService;

    @Autowired
    public WelcomeRequestHandler(final RequestHandlerRegistry registry,
                                 final NotificationService notificationService) {
        // register this handler into central registry
        super(registry, Request.Welcome.class);

        this.notificationService = notificationService;
    }

    @Override
    public void onEvent(String messageId, Request request) {

        logger.info("Event["+messageId+"] Request["+request.getTransactionId()+"] welcomeRequest");

        // adapt all common request attributes to notification domain
        NotificationBuilder builder = NotificationAdapter.of(request);

        try {
            Request.Welcome welcomeRequest = (Request.Welcome) request;

            // todo: adapt request specifics to notification payload
            {
                builder.withPayloadEntry("UID", welcomeRequest.getUid());
                builder.withPayloadEntry("LNAME", welcomeRequest.getLastName());
                builder.withPayloadEntry("FNAME", welcomeRequest.getFirstName());
            }

            notificationService.send(builder.build());
        } catch (NotificationService.NotificationException e) {
            // todo: handle failed parsing
            logger.error("", e);
        }
    }
}
