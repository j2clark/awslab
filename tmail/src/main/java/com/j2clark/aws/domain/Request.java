package com.j2clark.aws.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.joda.time.DateTime;

import java.util.UUID;

public interface Request {
    UUID getTransactionId();
    String getClientId();
    long getRequestDateTime();
    String getEmail();
}
