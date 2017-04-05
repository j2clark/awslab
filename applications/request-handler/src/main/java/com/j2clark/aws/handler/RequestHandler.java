package com.j2clark.aws.handler;

import com.j2clark.aws.domain.Request;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface RequestHandler {

    Set<Class<? extends Request>> supports();

    void onEvent(String messageId, Request request);

}
