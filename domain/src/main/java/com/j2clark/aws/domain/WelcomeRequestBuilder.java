package com.j2clark.aws.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.joda.time.DateTime;
import org.springframework.util.StringUtils;

import java.util.UUID;

public class WelcomeRequestBuilder {
    private UUID transactionId;
    private String clientId;
    private Long requestDateTime;
    private String uid;
    private String firstName;
    private String lastName;
    private String email;

    public WelcomeRequestBuilder withTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public WelcomeRequestBuilder withClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public WelcomeRequestBuilder withRequestDateTime(DateTime requestDateTime) {
        this.requestDateTime = requestDateTime.getMillis();
        return this;
    }

    public WelcomeRequestBuilder withUid(String uid) {
        this.uid = uid;
        return this;
    }

    public WelcomeRequestBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public WelcomeRequestBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public WelcomeRequestBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public void validate() {
        // validate required variables
        if (transactionId == null) {
            throw new IllegalStateException("Missing required attribute[transactionId]");
        }
        if (requestDateTime == null) {
            throw new IllegalStateException("Missing required attribute[requestDateTime]");
        }
        if (StringUtils.isEmpty(uid)) {
            throw new IllegalStateException("Missing required attribute[uid]");
        }
        if (StringUtils.isEmpty(email)) {
            throw new IllegalStateException("Missing required attribute[email]");
        }
    }

    public WelcomeRequest build() {

        validate();

        return new WelcomeRequestImpl(transactionId,
                                  clientId,
                                  uid,
                                  requestDateTime,
                                  firstName,
                                  lastName,
                                  email);
    }

    public static class WelcomeRequestImpl implements WelcomeRequest {
        private final UUID transactionId;
        private final String clientId;
        private final long requestDateTime;
        private final String uid;
        private final String firstName;
        private final String lastName;
        private final String email;

        @JsonCreator
        public WelcomeRequestImpl(
            @JsonProperty("transactionId") UUID transactionId,
            @JsonProperty("clientId") String clientId,
            @JsonProperty("uid") String uid,
            @JsonProperty("requestDateTime") long requestDateTime,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("email") String email) {
            this.transactionId = transactionId;
            this.clientId = clientId;
            this.uid = uid;
            this.requestDateTime = requestDateTime;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }

        /*@Override
        public String getType() {
            return type;
        }*/
        @Override
        public UUID getTransactionId() {
            return transactionId;
        }

        @Override
        public String getClientId() {
            return clientId;
        }

        @Override
        public long getRequestDateTime() {
            return requestDateTime;
        }

        public String getUid() {
            return uid;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        @Override
        public String getEmail() {
            return email;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof WelcomeRequestImpl)) {
                return false;
            }

            WelcomeRequestImpl that = (WelcomeRequestImpl) o;

            if (requestDateTime != that.requestDateTime) {
                return false;
            }
            if (transactionId != null ? !transactionId.equals(that.transactionId)
                                      : that.transactionId != null) {
                return false;
            }
            if (clientId != null ? !clientId.equals(that.clientId) : that.clientId != null) {
                return false;
            }
            if (uid != null ? !uid.equals(that.uid) : that.uid != null) {
                return false;
            }
            if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) {
                return false;
            }
            if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) {
                return false;
            }
            return !(email != null ? !email.equals(that.email) : that.email != null);

        }

        @Override
        public int hashCode() {
            int result = transactionId != null ? transactionId.hashCode() : 0;
            result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
            result = 31 * result + (int) (requestDateTime ^ (requestDateTime >>> 32));
            result = 31 * result + (uid != null ? uid.hashCode() : 0);
            result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
            result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
            result = 31 * result + (email != null ? email.hashCode() : 0);
            return result;
        }
    }
}
