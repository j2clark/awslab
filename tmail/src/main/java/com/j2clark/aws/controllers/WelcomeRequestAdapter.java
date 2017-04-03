package com.j2clark.aws.controllers;

import com.j2clark.aws.domain.WelcomeRequestBuilder;

import org.joda.time.DateTime;
import org.springframework.util.StringUtils;

import java.util.UUID;

public class WelcomeRequestAdapter extends WelcomeRequestBuilder {

    public static WelcomeRequestAdapter of(UUID transactionId) {
        return new WelcomeRequestAdapter().withTransactionId(transactionId);
    }

    @Override
    public WelcomeRequestAdapter withTransactionId(UUID transactionId) {
        return (WelcomeRequestAdapter) super.withTransactionId(transactionId);
    }

    public WelcomeRequestAdapter with(WelcomeRequestModel model) {
        withClientId(model.getClientId());
        DateTime requestDateTime = DateTime.now();
        if (StringUtils.isEmpty(model.getRequestDateTime())) {
            // todo: throws exception if not formatted properly - catch and handle nicely
            requestDateTime = DateTime.parse(model.getRequestDateTime());
        } else {
            // todo: log the fact that we were not given a request time
        }
        withRequestDateTime(requestDateTime);
        withUid(model.getUid());
        withFirstName(model.getFirstName());
        withLastName(model.getLastName());
        withEmail(model.getEmail());

        return this;
    }
}
