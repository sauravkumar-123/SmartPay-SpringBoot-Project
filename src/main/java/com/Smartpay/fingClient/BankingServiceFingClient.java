/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.smartpay.fingClient;

import com.smartpay.request.MerchantOnboardingRequest;
import com.smartpay.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author saura
 */
@FeignClient(name = "BANKSERVICEPROVIDER")
public interface BankingServiceFingClient {

    @PostMapping(value = "/v1/bankingService/onboarding")
    Response pushMerchantOnboardingDataToBankingSystem(MerchantOnboardingRequest merchantOnboardInput);
}
