package com.j2clark.aws.service;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class NotificationBuilder {

    private String transactionId;
    private String clientId;
    private Long timestamp;
    private Map<String,NotificationAttribute> attributes = new LinkedHashMap<>();
    private Set<NotificationChannel> channels = new LinkedHashSet<>();

    public NotificationBuilder withTransactionId(UUID transactionId) {
        if (transactionId != null) {
            return withTransactionId(transactionId.toString());
        }
        return this;
    }

    public NotificationBuilder withTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public NotificationBuilder withReqeustTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public NotificationBuilder withClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public NotificationBuilder withChannels(Collection<NotificationChannel> channels) {
        if (!CollectionUtils.isEmpty(channels)) {
            this.channels.addAll(channels);
        }
        return this;
    }

    public NotificationBuilder withPayloadEntry(String key, String attribute) {
        if (!StringUtils.isEmpty(attribute)) {
            return withPayloadEntry(key, new NotificationAttribute<String>(attribute));
        }
        return this;
    }

    public NotificationBuilder withPayloadEntry(String key, NotificationAttribute attribute) {
        if (attribute != null) {
            this.attributes.put(key, attribute);
        }
        return this;
    }

    public Notification build () {

        return new NotificationImpl(transactionId, clientId, timestamp, attributes, channels);
    }

    private static class NotificationImpl implements Notification {

        private final String transactionId;
        private final String clientId;
        private final Long timestamp;
        private final Map<String,NotificationAttribute> attributes;
        private final Set<NotificationChannel> channels;


        private NotificationImpl(final String transactionId,
                                 final String clientId,
                                 final Long timestamp,
                                 Map<String, NotificationAttribute> attributes,
                                 Set<NotificationChannel> channels) {
            this.transactionId = transactionId;
            this.clientId = clientId;
            this.timestamp = timestamp;
            if (!CollectionUtils.isEmpty(attributes)) {
                this.attributes = attributes;
            } else {
                this.attributes = Collections.emptyMap();
            }
            if (!CollectionUtils.isEmpty(channels)) {
                this.channels = channels;
            } else {
                this.channels = Collections.emptySet();
            }
        }

        public String getTransactionId() {
            return transactionId;
        }

        public String getClientId() {
            return clientId;
        }

        public Long getTimestamp() {
            return timestamp;
        }

        public Map<String, NotificationAttribute> getAttributes() {
            return attributes;
        }

        public Set<NotificationChannel> getChannels() {
            return channels;
        }
    }
}
