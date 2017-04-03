package com.j2clark.aws.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EventMessage {

    public enum Type {
        welcome
    }

    private final Type type;
    private final String version;
    private String json;

    @JsonCreator
    public EventMessage(@JsonProperty("type") Type type,
                        @JsonProperty("version") String version,
                        @JsonProperty("json") String json) {
        this.type = type;
        this.version = version;
        this.json = json;
    }

    public Type getType() {
        return type;
    }

    public String getVersion() {
        return version;
    }

    public String getJson() {
        return json;
    }
}
