package com.j2clark.aws.domain;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

@Ignore
public class RequestSerializationTest {

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void eventMessageSerialization() throws IOException {

        /*Request.Welcome welcome = new WelcomeRequestBuilder()
            .withTransactionId(UUID.randomUUID())
            .withRequestDateTime(DateTime.now())
            .withUid(UUID.randomUUID().toString())
            //
                // .withEmail("xyz@shutterfly.com")
            .build();

        EventMessage eventMsg = EventMessageBuilder.of(welcome).build();

        String json = objectMapper.writeValueAsString(eventMsg);
        System.out.println(json);

        eventMsg = objectMapper.readValue(json, EventMessage.class);*/

        // todo: DELETE ME
    }
}
