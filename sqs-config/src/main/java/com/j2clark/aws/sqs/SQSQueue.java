package com.j2clark.aws.sqs;

import com.amazonaws.services.sqs.model.Message;

import java.util.Collection;

public interface SQSQueue {

    String name();

    String send(String body);

    Collection<Message> retrieveMessages(int maxEvents);

    Message deleteMessage(Message message);
}