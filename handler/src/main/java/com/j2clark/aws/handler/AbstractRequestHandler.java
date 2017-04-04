package com.j2clark.aws.handler;

import com.j2clark.aws.domain.Request;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class AbstractRequestHandler implements RequestHandler {


    private final Set<Class<? extends Request>> supports;

    @Autowired
    public AbstractRequestHandler(RequestHandlerRegistry registry, Class<? extends Request> type) {
        // set supports first
        this.supports = new LinkedHashSet<>(Collections.singletonList(type));

        registry.register(this);
    }

    @Override
    public Set<Class<? extends Request>> supports() {
        return supports;
    }

}
