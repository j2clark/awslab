package com.j2clark.aws.service;

public interface NotificationService {

    void send(Notification notification) throws NotificationException;

    class NotificationException extends Exception {

    }

}
