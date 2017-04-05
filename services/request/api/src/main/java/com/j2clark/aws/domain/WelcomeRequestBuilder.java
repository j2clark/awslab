package com.j2clark.aws.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

public class WelcomeRequestBuilder {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private UUID transactionId;
    private String clientId;
    private Long requestDateTime;
    private String uid;
    private String firstName;
    private String lastName;
    private Set<RequestChannel> channels = new LinkedHashSet<>();

    public WelcomeRequestBuilder withTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public WelcomeRequestBuilder withClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public WelcomeRequestBuilder withRequestDateTime(DateTime requestDateTime) {
        this.requestDateTime = requestDateTime.getMillis();
        return this;
    }

    public WelcomeRequestBuilder withUid(String uid) {
        this.uid = uid;
        return this;
    }

    public WelcomeRequestBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public WelcomeRequestBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /*public WelcomeRequestBuilder withEmail(String email) {
        if (!StringUtils.isEmpty(email)) {

        }
        return this;
    }

    public WelcomeRequestBuilder withSms(String sms) {
        if (!StringUtils.isEmpty(sms)) {

        }
        return this;
    }*/

    public WelcomeRequestBuilder withChannel(RequestChannel channel) {
        if (channel != null) {
            if (this.channels.contains(channel)) {
                logger.warn("Builder already contains channel["+channel+"]. Check your code.");
            }
            this.channels.add(channel);
        }
        return this;
    }

    public void validate() {
        // validate required variables
        if (transactionId == null) {
            throw new IllegalStateException("Missing required attribute[transactionId]");
        }
        if (requestDateTime == null) {
            throw new IllegalStateException("Missing required attribute[requestDateTime]");
        }
        if (StringUtils.isEmpty(uid)) {
            throw new IllegalStateException("Missing required attribute[uid]");
        }
        /*if (CollectionUtils.isEmpty(channels)) {
            throw new IllegalStateException("No requestChannels have been added");
        }*/
    }

    public Request.Welcome build() {

        validate();

        return new WelcomeImpl(transactionId,
                                      clientId,
                                      uid,
                                      requestDateTime,
                                      firstName,
                                      lastName,
                                      channels);
    }

    public static class WelcomeImpl implements Request.Welcome {
        private final UUID transactionId;
        private final String clientId;
        private final long requestTimestamp;
        private final String uid;
        private final String firstName;
        private final String lastName;
        private final Set<RequestChannel> channels;


        @JsonCreator
        public WelcomeImpl(
            @JsonProperty("transactionId") UUID transactionId,
            @JsonProperty("clientId") String clientId,
            @JsonProperty("uid") String uid,
            @JsonProperty("requestTimestanmp") long requestTimestamp,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("channels") Set<RequestChannel> channels) {
            this.transactionId = transactionId;
            this.clientId = clientId;
            this.uid = uid;
            this.requestTimestamp = requestTimestamp;
            this.firstName = firstName;
            this.lastName = lastName;
            if (CollectionUtils.isEmpty(channels)) {
                this.channels = Collections.emptySet();
            } else {
                this.channels = new LinkedHashSet<>(channels);
            }

        }

        /*@Override
        public String getType() {
            return type;
        }*/
        @Override
        public UUID getTransactionId() {
            return transactionId;
        }

        @Override
        public String getClientId() {
            return clientId;
        }

        @Override
        public long getRequestTimestamp() {
            return requestTimestamp;
        }

        @Override
        public String getUid() {
            return uid;
        }

        @Override
        public String getFirstName() {
            return firstName;
        }

        @Override
        public String getLastName() {
            return lastName;
        }

        @Override
        public Set<RequestChannel> getChannels() {
            return channels;
        }
    }
}
