package com.j2clark.aws.handler;

import com.j2clark.aws.domain.EventMessage;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class AbstractEventHandler implements EventHandler {


    private final Set<EventMessage.Type> supports;

    @Autowired
    public AbstractEventHandler(EventHandlerRegistry registry, EventMessage.Type type) {
        // set supports first
        this.supports = new LinkedHashSet<>(Collections.singletonList(type));

        registry.register(this);
    }

    @Override
    public Set<EventMessage.Type> supports() {
        return supports;
    }

}
