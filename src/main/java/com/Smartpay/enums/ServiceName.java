/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.smartpay.enums;

/**
 *
 * @author saura
 */
public enum ServiceName {

    BANKING_ONBOARD_SERVICE("Bank_Onboarding");

    private String value;

    ServiceName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String getName(String value) {
        ServiceName[] serviceNames = ServiceName.values();
        for (ServiceName serviceName : serviceNames) {
            if (serviceName.getValue().equals(value)) {
                return serviceName.name();
            }
        }
        return "";
    }

    public static String getValue(String name) {
        ServiceName[] serviceNames = ServiceName.values();
        for (ServiceName serviceName : serviceNames) {
            if (serviceName.name().equals(name)) {
                return serviceName.getValue();
            }
        }
        return "";
    }
}
