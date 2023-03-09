/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartpay.dto;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author saura
 */
@Data
public class UserDto {

    private String userIdentificationNo;
    private long customerId;
    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private String mobileNo;
    private String emailId;
    private String role;
    private String parentUsername;
    private Date dateOfBirth;
    private String isActive;
    private String bankingServiceStatus;
    private String password;

}
