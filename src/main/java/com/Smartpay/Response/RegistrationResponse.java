package com.Smartpay.Response;

import java.util.Date;

import com.Smartpay.Enum.EnumsStatus.IsActive;
import com.Smartpay.Enum.EnumsStatus.YesNO;

import lombok.Data;

@Data
public class RegistrationResponse {

	private Long customerId;
	private String username;
	private String applicantName;
	private String role;
	private String parentUsername;
	private Date dateOfBirth;
	private IsActive isActive;
	private YesNO bankingServiceStatus;
}
