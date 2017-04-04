package com.j2clark.aws.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.j2clark.aws.domain.EventMessage;
import com.j2clark.aws.domain.WelcomeRequest;
import com.j2clark.aws.domain.WelcomeRequestBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WelcomeEventHandler extends AbstractEventHandler implements EventHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final NotifyService messagingService;

    @Autowired
    public WelcomeEventHandler(EventHandlerRegistry registry,
                               NotifyService messagingService) {
        super(registry, EventMessage.Type.welcome);

        this.messagingService = messagingService;
    }

    @Override
    public void onEvent(String messageId, EventMessage event) {
        try {
            WelcomeRequest
                welcomeRequest =
                objectMapper.readValue(event.getJson(), WelcomeRequestBuilder.WelcomeRequestImpl.class);

            logger.info("handle WELCOME request["+welcomeRequest.getTransactionId()+"]");

            try {
                messagingService.send(NotifyService.NotificationBuilder.instance()

                                          .build());
            } catch (Throwable t) {
                // failed to send message
                // either mark for retry and republish,
                // or log to failed and log warning
            }
            /*// format to pinpoint standards
            PinpointRequest pinpointRequest = PinpointRequest.of()
                .build();

            // push to pinpoint queue
            pinpointService.sendToPinpoint(pinpointRequest);*/


        } catch (IOException e) {
            // todo: handle failed parsing
            logger.error("", e);
        }
    }


}
