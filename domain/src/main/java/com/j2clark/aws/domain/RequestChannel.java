package com.j2clark.aws.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * method of communication
 * email, sms, http
 */


// https://gist.github.com/sscovil/8788339

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = RequestChannel.SMSChannel.class, name = "sms"),
    @JsonSubTypes.Type(value = RequestChannel.EmailChannel.class, name = "email"),
    @JsonSubTypes.Type(value = RequestChannel.HttpCallbackChannel.class, name = "http")
})
public interface RequestChannel {

    class SMSChannel implements RequestChannel {

        private final String phoneNumber;

        @JsonCreator
        public SMSChannel(@JsonProperty("phoneNumber") String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }
    }

    class EmailChannel implements RequestChannel {

        private final String email;

        @JsonCreator
        public EmailChannel(@JsonProperty("email") String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }
    }

    class HttpCallbackChannel implements RequestChannel {

    }
}
