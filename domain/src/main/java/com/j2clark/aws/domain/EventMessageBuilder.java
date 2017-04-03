package com.j2clark.aws.domain;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class EventMessageBuilder {

    private final ObjectMapper objectMapper = new ObjectMapper();


    public static EventMessageBuilder of(Request request) throws IOException {
        if (request instanceof WelcomeRequest) {
            return new EventMessageBuilder().with((WelcomeRequest) request);
        } else {
            throw new IllegalArgumentException("Unsupported request class["+request.getClass().getSimpleName()+"]");
        }
    }

    private EventMessage.Type type;
    private String version;
    private String json;

    public EventMessageBuilder with(WelcomeRequest welcomeRequest) throws IOException {
        this.type = EventMessage.Type.welcome;
        this.version = "1";
        this.json = objectMapper.writeValueAsString(welcomeRequest);
        return this;
    }

    public EventMessage build() {
        return new EventMessage(type, version, json);
    }

}
