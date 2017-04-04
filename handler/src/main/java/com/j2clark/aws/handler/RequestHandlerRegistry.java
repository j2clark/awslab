package com.j2clark.aws.handler;

import com.j2clark.aws.domain.Request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component
public class RequestHandlerRegistry {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Map<Class<? extends Request>,RequestHandler> handlers = new HashMap<>();

    public RequestHandlerRegistry register(RequestHandler requestHandler) {
        Set<Class<? extends Request>> supportedTypes = requestHandler.supports();
        for (Class<? extends Request> type : supportedTypes) {
            if (handlers.containsKey(type)) {
                throw new IllegalStateException(
                    "Configuration Exception. Attempting to reassign requestHandler of type[" + type
                    + "] from[" + handlers.get(type).getClass() + "] to [" + requestHandler.getClass()
                    + "]");
            } else {
                logger.info("Registered type["+type+"] with requestHandler["+ requestHandler.getClass()+"]");
                handlers.put(type, requestHandler);
            }
        }
        return this;
    }

    public Optional<RequestHandler> findEventHandler(Request request) {
        for (Class<? extends Request> type : handlers.keySet()) {
            if (type.isAssignableFrom(request.getClass())) {
                return Optional.of(handlers.get(type));
            }
        }
        return Optional.empty();

        /*Optional<RequestHandler> handler = Optional.empty();
        if (handlers.containsKey(request.getClass())) {
            handler = Optional.of(handlers.get(request.getClass()));
        }
        return handler;*/
    }



}
