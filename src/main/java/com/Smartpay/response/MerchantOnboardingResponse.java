/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartpay.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author saura
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MerchantOnboardingResponse {

    private String onboardingServiceIdentificationNo;
    private String merchantIdentificationNo;
    private String aadhaarcardNo;
    private String panCardNo;
    private String userName;
    private String bankOnboardStatus;
    private String gstNo;
}
