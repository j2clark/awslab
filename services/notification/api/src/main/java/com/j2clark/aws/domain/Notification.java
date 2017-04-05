package com.j2clark.aws.domain;

import java.util.Map;
import java.util.Set;

public interface Notification {

    String getTransactionId();

    String getClientId();

    Long getTimestamp();

    Map<String, NotificationAttribute> getAttributes();

    Set<NotificationChannel> getChannels();

}
