/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartpay.response;

/**
 *
 * @author saura
 */
public class MerchantOnboardingResponse {

    private String onboardingServiceIdentificationNo;
    private String merchantIdentificationNo;
    private String aadhaarcardNo;
    private String panCardNo;
    private String userName;
    private String bankOnboardStatus;
    private String gstNo;

    public MerchantOnboardingResponse() {
    }

    public MerchantOnboardingResponse(String onboardingServiceIdentificationNo, String merchantIdentificationNo, String aadhaarcardNo, String panCardNo, String userName, String bankOnboardStatus, String gstNo) {
        this.onboardingServiceIdentificationNo = onboardingServiceIdentificationNo;
        this.merchantIdentificationNo = merchantIdentificationNo;
        this.aadhaarcardNo = aadhaarcardNo;
        this.panCardNo = panCardNo;
        this.userName = userName;
        this.bankOnboardStatus = bankOnboardStatus;
        this.gstNo = gstNo;
    }

    public String getOnboardingServiceIdentificationNo() {
        return onboardingServiceIdentificationNo;
    }

    public void setOnboardingServiceIdentificationNo(String onboardingServiceIdentificationNo) {
        this.onboardingServiceIdentificationNo = onboardingServiceIdentificationNo;
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

    public String getBankOnboardStatus() {
        return bankOnboardStatus;
    }

    public void setBankOnboardStatus(String bankOnboardStatus) {
        this.bankOnboardStatus = bankOnboardStatus;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

}
