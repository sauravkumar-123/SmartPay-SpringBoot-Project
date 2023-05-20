/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartpay.exception;

import org.springframework.web.client.HttpStatusCodeException;

/**
 *
 * @author saura
 */
public class RestClientCallException extends RuntimeException {

    private HttpStatusCodeException httpStatusCodeException;

    public RestClientCallException() {
    }

    public RestClientCallException(HttpStatusCodeException httpStatusCodeException) {
        this.httpStatusCodeException = httpStatusCodeException;
    }

    public RestClientCallException(String message) {
        super(message);
    }

    public RestClientCallException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestClientCallException(Throwable cause) {
        super(cause);
    }

    public RestClientCallException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public HttpStatusCodeException getHttpStatusCodeException() {
        return httpStatusCodeException;
    }

    public void setHttpStatusCodeException(HttpStatusCodeException httpStatusCodeException) {
        this.httpStatusCodeException = httpStatusCodeException;
    }

}
