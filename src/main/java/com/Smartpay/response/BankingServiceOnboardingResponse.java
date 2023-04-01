/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartpay.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author saura
 */
@Setter
@Getter
public class BankingServiceOnboardingResponse {

    private List<MerchantOnboardingResponse> datasource;
}
