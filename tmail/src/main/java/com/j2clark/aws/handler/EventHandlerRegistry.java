package com.j2clark.aws.handler;

import com.j2clark.aws.domain.EventMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component
public class EventHandlerRegistry {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Map<EventMessage.Type,EventHandler> handlers = new HashMap<>();

    public EventHandlerRegistry register(EventHandler eventHandler) {
        Set<EventMessage.Type> supportedTypes = eventHandler.supports();
        for (EventMessage.Type type : supportedTypes) {
            if (handlers.containsKey(type)) {
                throw new IllegalStateException(
                    "Configuration Exception. Attempting to reassign eventHandler of type[" + type
                    + "] from[" + handlers.get(type).getClass() + "] to [" + eventHandler.getClass()
                    + "]");
            } else {
                logger.info("Registered type["+type+"] with eventHandler["+eventHandler.getClass()+"]");
                handlers.put(type, eventHandler);
            }
        }
        return this;
    }

    public Optional<EventHandler> findEventHandler(EventMessage eventMessage) {
        Optional<EventHandler> handler = Optional.empty();
        if (handlers.containsKey(eventMessage.getType())) {
            handler = Optional.of(handlers.get(eventMessage.getType()));
        }
        return handler;
    }



}
