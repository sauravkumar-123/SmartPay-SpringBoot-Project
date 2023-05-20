/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartpay.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 *
 * @author saura
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MerchantOnboardServiceInput {

    private List<MerchantOnboardingDto> onboardServiceInput;

    public MerchantOnboardServiceInput() {
    }

    public MerchantOnboardServiceInput(List<MerchantOnboardingDto> onboardServiceInput) {
        this.onboardServiceInput = onboardServiceInput;
    }

    public List<MerchantOnboardingDto> getOnboardServiceInput() {
        return onboardServiceInput;
    }

    public void setOnboardServiceInput(List<MerchantOnboardingDto> onboardServiceInput) {
        this.onboardServiceInput = onboardServiceInput;
    }

}
