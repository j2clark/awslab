package com.j2clark.aws.domain;

import java.util.UUID;

public interface Request {
    UUID getTransactionId();
    String getClientId();
    long getRequestDateTime();
    String getEmail();
}
