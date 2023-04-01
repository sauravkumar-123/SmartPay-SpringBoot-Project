/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartpay.request;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author saura
 */
@Setter
@Getter
public class MerchantOnboardingRequest {

    private String onboardingServiceIdentificationNo;
    private String merchantIdentificationNo;
    private String aadhaarcardNo;
    private String panCardNo;
    private String userName;
    private String bankOnboardStatus;
    private String gstNo;
}
