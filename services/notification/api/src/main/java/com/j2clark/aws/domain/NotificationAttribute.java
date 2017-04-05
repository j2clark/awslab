package com.j2clark.aws.domain;

public class NotificationAttribute<T> {

    private final T t;

    public NotificationAttribute(T t) {
        this.t = t;
    }

    public T getValue() {
        return t;
    }

}
