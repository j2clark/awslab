package com.j2clark.aws.service;

import com.j2clark.aws.domain.Notification;

public interface NotificationService {

    void send(Notification notification) throws NotificationException;

    class NotificationException extends Exception {

    }

}
