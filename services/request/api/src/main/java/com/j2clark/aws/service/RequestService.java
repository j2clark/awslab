package com.j2clark.aws.service;

import com.j2clark.aws.domain.Request;

public interface RequestService {

    /**
     * publish request to queue for async processing
     */
    void onRequest(Request request);
}
