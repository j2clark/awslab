package com.j2clark.aws.handler;

import com.j2clark.aws.domain.EventMessage;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface EventHandler {

    Set<EventMessage.Type> supports();


    void onEvent(String messageId, EventMessage event);
        // todo:
        // based on type,
        // unpack and deseriaize payload
        // format
        // push to Pinpoint for templatization and emailing


}
