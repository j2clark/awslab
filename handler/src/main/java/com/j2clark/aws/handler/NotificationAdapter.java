package com.j2clark.aws.handler;

import com.j2clark.aws.domain.Request;
import com.j2clark.aws.domain.RequestChannel;
import com.j2clark.aws.service.NotificationBuilder;
import com.j2clark.aws.service.NotificationChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class NotificationAdapter extends NotificationBuilder {

    private static final Logger logger = LoggerFactory.getLogger(NotificationAdapter.class);

    public static NotificationAdapter of(Request request) {
        return (NotificationAdapter) new NotificationAdapter()
                .withTransactionId(request.getTransactionId())
                .withReqeustTimestamp(request.getRequestTimestamp())
                .withClientId(request.getClientId())
                .withChannels(adapt(request.getChannels()));
    }

    public static Collection<NotificationChannel> adapt(Collection<RequestChannel> requestChannels) {
        Set<NotificationChannel> channels = new LinkedHashSet<>();
        if (!CollectionUtils.isEmpty(requestChannels)) {
            for (RequestChannel channel : requestChannels) {
                if (channel instanceof RequestChannel.SMSChannel) {
                    channels.add(new NotificationChannel.SMSChannel());
                } else if (channel instanceof RequestChannel.EmailChannel) {
                    channels.add(new NotificationChannel.EmailChannel());
                } else if (channel instanceof RequestChannel.HttpCallbackChannel) {

                } else {channels.add(new NotificationChannel.HttpCallbackChannel());
                    logger.warn("Unsupported RequestChannel[" + channel.getClass() + "]: ignoring");
                }
            }
        }
        return channels;

    }

}
