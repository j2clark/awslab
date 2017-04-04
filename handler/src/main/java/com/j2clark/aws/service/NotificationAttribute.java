package com.j2clark.aws.service;

public class NotificationAttribute<T> {

    private final T t;

    public NotificationAttribute(T t) {
        this.t = t;
    }

    public T getValue() {
        return t;
    }

}
