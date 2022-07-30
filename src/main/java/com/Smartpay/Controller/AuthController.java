package com.Smartpay.Controller;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Smartpay.DAO.UserRepository;
import com.Smartpay.Model.User;
import com.Smartpay.Response.Response;
import com.Smartpay.Response.TwoFactorResponse;
import com.Smartpay.Security.SmartPayAuthencationProvider;
import com.Smartpay.Utility.Utility;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/auth")
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private SmartPayAuthencationProvider authProvider;

	@Autowired
	private UserRepository userRepository;

	@ApiOperation("Login OTP Send API")
	@RequestMapping(value = "/send-login-OTP", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> sendLoginOTP(
			@RequestParam("Username") @NotNull(message = "Invalid Username") String Username,
			@RequestParam("Password") @NotNull(message = "Invalid Password") String Password) {
		Authentication authentication = authProvider
				.authenticate(new UsernamePasswordAuthenticationToken(Username, Password));
		logger.debug("Authentication Details{} " + authentication);
		if (null != authentication) {
			User user = userRepository.findUserByUsername(authentication.getName());
			TwoFactorResponse result = Utility.sendLoginOTP(user.getMobileno());
			return new ResponseEntity<Response>(new Response(true, "OTP sent", result), HttpStatus.OK);
		} else {
			return new ResponseEntity<Response>(new Response(false, "Authencation Failed", null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation("User Login API")
	@RequestMapping(value = "/user-login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> loginUser(
			@RequestParam("Username") @NotNull(message = "Invalid Username") String Username,
			@RequestParam("Password") @NotNull(message = "Invalid Password") String Password,
			@RequestParam("OTPdetails") @NotNull(message = "Invalid OTP Details") String OTPdetails,
			@RequestParam("inputOTP") @NotNull(message = "Invalid OTP") String inputOTP) {
		Authentication authentication = authProvider
				.authenticate(new UsernamePasswordAuthenticationToken(Username, Password));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		logger.debug("Authentication Details{} " + authentication);
		if (null != authentication) {
			TwoFactorResponse twoFactorResponse = Utility.verifyLoginOTP(OTPdetails, inputOTP);
			if (twoFactorResponse.getStatus().equalsIgnoreCase("Success")) {
				User user = userRepository.findUserByUsername(authentication.getName());
				return new ResponseEntity<Response>(new Response(true, "Login Success", user), HttpStatus.OK);
			} else {
				return new ResponseEntity<Response>(new Response(true, "Wrong OTP", null),
						HttpStatus.EXPECTATION_FAILED);
			}
		} else {
			return new ResponseEntity<Response>(new Response(false, "Authencation Failed", null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
