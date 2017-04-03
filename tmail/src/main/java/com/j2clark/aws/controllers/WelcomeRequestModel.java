package com.j2clark.aws.controllers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WelcomeRequestModel {

    private final String clientId;
    private final String requestDateTime;
    private final String uid;
    private final String fName;
    private final String lName;
    private final String email;

    @JsonCreator
    public WelcomeRequestModel(@JsonProperty("clientId") final String clientId,
                               @JsonProperty("requestedDateTime") final String requestedDateTime,
                               @JsonProperty("uid") final String uid,
                               @JsonProperty("lname") final String fName,
                               @JsonProperty("fname") final String lName,
                               @JsonProperty("email") final String email) {
        this.clientId = clientId;
        this.requestDateTime = requestedDateTime;
        this.uid = uid;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
    }

    public String getFirstName() {
        return fName;
    }

    public String getLastName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public String getClientId() {
        return clientId;
    }

    public String getRequestDateTime() {
        return requestDateTime;
    }

    public String getUid() {
        return uid;
    }
}
