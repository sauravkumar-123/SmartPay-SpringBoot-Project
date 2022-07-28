package com.Smartpay.Constants;

public class Constant {

	// @Pattern Matchers For Attribute Validation.
	public static final String mobileNumberPattern = "^(\\+91[\\-\\s]?)?[0]?(91)?[6789]\\d{9}$";
	public static final String emailIdPattern = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

	// @2Factor API's.
	public static final String twoFactorAPIkey = "99d3f5f5-09ec-11ed-9c12-0200cd936042";
	public static final String SENDLOGINDETAILSAPI = "http://2factor.in/API/V1/" + twoFactorAPIkey
			+ "/ADDON_SERVICES/SEND/TSMS";

}
