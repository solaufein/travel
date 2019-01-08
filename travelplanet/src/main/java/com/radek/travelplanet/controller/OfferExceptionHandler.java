package com.radek.travelplanet.controller;

import com.radek.travelplanet.controller.model.ErrorMessage;
import com.radek.travelplanet.exception.OfferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class OfferExceptionHandler {

    @ExceptionHandler(OfferException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleAuthenticationException(OfferException ex) {
        log.debug("Internal OfferService exception occured.");
        return new ErrorMessage(ex.getMessage(), "offer.error.internal");
    }

}