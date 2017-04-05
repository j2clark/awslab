package com.j2clark.aws.domain;

public interface NotificationChannel {


    class SMSChannel implements NotificationChannel {

    }

    class EmailChannel implements NotificationChannel {

    }

    class HttpCallbackChannel implements NotificationChannel {

    }
}
