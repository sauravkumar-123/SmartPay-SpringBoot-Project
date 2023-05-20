/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartpay.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author saura
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MerchantOnboardingDto {

    private String merchantIdentificationNo;
    private String aadhaarcardNo;
    private String panCardNo;
    private String userName;
    private String gstNo;

    public MerchantOnboardingDto() {
    }

    public MerchantOnboardingDto(String merchantIdentificationNo, String aadhaarcardNo, String panCardNo, String userName, String gstNo) {
        this.merchantIdentificationNo = merchantIdentificationNo;
        this.aadhaarcardNo = aadhaarcardNo;
        this.panCardNo = panCardNo;
        this.userName = userName;
        this.gstNo = gstNo;
    }

    public String getMerchantIdentificationNo() {
        return merchantIdentificationNo;
    }

    public void setMerchantIdentificationNo(String merchantIdentificationNo) {
        this.merchantIdentificationNo = merchantIdentificationNo;
    }

    public String getAadhaarcardNo() {
        return aadhaarcardNo;
    }

    public void setAadhaarcardNo(String aadhaarcardNo) {
        this.aadhaarcardNo = aadhaarcardNo;
    }

    public String getPanCardNo() {
        return panCardNo;
    }

    public void setPanCardNo(String panCardNo) {
        this.panCardNo = panCardNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

}
