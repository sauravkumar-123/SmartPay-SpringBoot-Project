/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartpay.dto;

import java.io.Serializable;
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
public class MerchantOnboardingDto implements Serializable {

    private String merchantIdentificationNo;
    private String aadhaarcardNo;
    private String panCardNo;
    private String userName;
    private String gstNo;
}
