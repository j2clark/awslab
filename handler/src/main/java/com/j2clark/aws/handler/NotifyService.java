package com.j2clark.aws.handler;

public interface NotifyService {

    void send(Notification notification) throws NotificationException;

    interface Notification {

        boolean isSMS();

        boolean isEmail();

    }

    class NotificationException extends Exception {

    }

    class NotificationBuilder {

        private boolean sms;
        private boolean email;

        public static NotificationBuilder instance() {
            return new NotificationBuilder();
        }

        public Notification build () {

            return new NotificationImpl(sms, email);
        }

        private static class NotificationImpl implements Notification {

            private final boolean sms;
            private final boolean email;

            private NotificationImpl(boolean sms, boolean email) {
                this.sms = sms;
                this.email = email;
            }

            @Override
            public boolean isSMS() {
                return sms;
            }

            @Override
            public boolean isEmail() {
                return email;
            }
        }
    }
}
