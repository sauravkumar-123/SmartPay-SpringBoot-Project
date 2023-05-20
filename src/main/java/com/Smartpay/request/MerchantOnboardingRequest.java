/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartpay.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.smartpay.dto.MerchantOnboardServiceInput;

/**
 *
 * @author saura
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MerchantOnboardingRequest {

    private RequestHeader requestHeader;
    private MerchantOnboardServiceInput serviceInput;

    public MerchantOnboardingRequest() {
    }

    public MerchantOnboardingRequest(RequestHeader requestHeader, MerchantOnboardServiceInput serviceInput) {
        this.requestHeader = requestHeader;
        this.serviceInput = serviceInput;
    }

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(RequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }

    public MerchantOnboardServiceInput getServiceInput() {
        return serviceInput;
    }

    public void setServiceInput(MerchantOnboardServiceInput serviceInput) {
        this.serviceInput = serviceInput;
    }

}
