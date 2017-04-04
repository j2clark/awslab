package com.j2clark.aws.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Set;
import java.util.UUID;

/**
 * http://www.baeldung.com/jackson-annotations
 *
 * https://fasterxml.github.io/jackson-annotations/javadoc/2.2.0/com/fasterxml/jackson/annotation/JsonTypeInfo.html
 *
 *
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = WelcomeRequestBuilder.WelcomeImpl.class, name = "welcome")
})
public interface Request {
    UUID getTransactionId();
    String getClientId();
    long getRequestTimestamp();
    Set<RequestChannel> getChannels();

    interface Welcome extends Request {
        String getUid();
        String getFirstName();
        String getLastName();
    }
}
