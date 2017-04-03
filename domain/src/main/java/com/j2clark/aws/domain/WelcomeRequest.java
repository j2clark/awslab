package com.j2clark.aws.domain;

public interface WelcomeRequest extends Request {
    String getUid();
    String getFirstName();
    String getLastName();
}
