package com.j2clark.aws.controllers;

import com.j2clark.aws.domain.Request;
import com.j2clark.aws.service.RequestService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
@RequestMapping(value = "/event")
public class RequestController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final RequestService someService;

    @Autowired
    public RequestController(final RequestService someService) {
        this.someService = someService;
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    ResponseEntity<?> onmRequest(@RequestHeader(value = "app-id", required = false) String clientId,
                                 @RequestBody WelcomeRequestModel model) {
        UUID transactionId = UUID.randomUUID();

        if (StringUtils.isEmpty(clientId)) {
            logger.warn("Unauthorized. Reason[missing app-id]: " + model);
            return new ResponseEntity<Object>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        // exception if required data missing or malformed
        Request.Welcome welcomeRequest = WelcomeRequestAdapter.of(transactionId)
            .with(model)
            .build();

        someService.onRequest(welcomeRequest);

        return new ResponseEntity<Object>(transactionId, HttpStatus.OK);
    }
}