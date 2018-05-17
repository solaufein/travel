package com.radek.travelplanet.controller;

import com.radek.travelplanet.exception.OfferException;
import com.radek.travelplanet.model.auth.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class OfferExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfferExceptionHandler.class);

    @ExceptionHandler(OfferException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleAuthenticationException(OfferException ex) {
        LOGGER.debug("Internal OfferService exception occured.");
        return new ErrorMessage(ex.getMessage(), "offer.error.internal");
    }

}