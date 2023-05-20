/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartpay.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author saura
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestHeader {

    @JsonProperty(value = "request-source")
    private String requestSource;
    @JsonProperty(value = "request-id")
    private String requestId;
    @JsonProperty(value = "service-name")
    private String serviceName;
    @JsonProperty(value = "request-time")
    private String requestTime;

    public RequestHeader() {
    }

    public RequestHeader(String requestSource, String requestId, String serviceName, String requestTime) {
        this.requestSource = requestSource;
        this.requestId = requestId;
        this.serviceName = serviceName;
        this.requestTime = requestTime;
    }

    public String getRequestSource() {
        return requestSource;
    }

    public void setRequestSource(String requestSource) {
        this.requestSource = requestSource;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

}
