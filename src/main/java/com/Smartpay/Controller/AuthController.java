package com.Smartpay.Controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Smartpay.DAO.UserRepository;
import com.Smartpay.Model.User;
import com.Smartpay.Response.JWTAuthResponse;
import com.Smartpay.Response.OAuthResponse;
import com.Smartpay.Response.Response;
import com.Smartpay.Response.TwoFactorResponse;
import com.Smartpay.Security.JWTUtils;
import com.Smartpay.Security.SmartPayAuthencationProvider;
import com.Smartpay.Utility.Utility;

import io.swagger.annotations.ApiOperation;

@RestController
@Validated
@RequestMapping("/v1/auth")
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private SmartPayAuthencationProvider authProvider;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JWTUtils jwtUtils;

	@ApiOperation("Login OTP Send API")
	@RequestMapping(value = "/send-login-OTP", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> sendLoginOTP(
			@Valid @RequestParam("Username") @NotBlank(message = "Invalid Username") String Username,
			@Valid @RequestParam("Password") @NotBlank(message = "Invalid Password") String Password) {
		Authentication authentication = authProvider
				.authenticate(new UsernamePasswordAuthenticationToken(Username, Password));
		logger.debug("Authentication Details{} " + authentication);
		if (null != authentication) {
			User user = userRepository.findUserByUsername(authentication.getName());
			TwoFactorResponse twoFactorResponse = Utility.sendLoginOTP(user.getMobileno());
			if (twoFactorResponse.getStatus().equalsIgnoreCase("Success")) {
				return new ResponseEntity<Response>(new Response(true, "OTP sent", twoFactorResponse), HttpStatus.OK);
			} else {
				return new ResponseEntity<Response>(new Response(false, "Unable To Send OTP", twoFactorResponse),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<Response>(new Response(false, "Authencation Failed", null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation("User Login And OTP Verfication API")
	@RequestMapping(value = "/user-login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> loginUser(
			@Valid @RequestParam("Username") @NotBlank(message = "Invalid Username") String Username,
			@Valid @RequestParam("Password") @NotBlank(message = "Invalid Password") String Password,
			@Valid @RequestParam("OTPdetails") @NotBlank(message = "Invalid OTP Details") String OTPdetails,
			@Valid @RequestParam("InputOTP") @NotBlank(message = "Invalid OTP") String InputOTP) {
		Authentication authentication = authProvider
				.authenticate(new UsernamePasswordAuthenticationToken(Username, Password));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		logger.debug("Authentication Details{} " + authentication);
		if (null != authentication) {
			TwoFactorResponse twoFactorResponse = Utility.verifyLoginOTP(OTPdetails, InputOTP);
			if (twoFactorResponse.getStatus().equalsIgnoreCase("Success")) {
				User user = userRepository.findUserByUsername(authentication.getName());
				String token = jwtUtils.generateAccessToken(user);
				JWTAuthResponse authResponse = new JWTAuthResponse();
				authResponse.setAuthToken(token);
				authResponse.setMessage("Authencation Success");
				authResponse.setStatus("Token Generated");
				authResponse.setUsername(authentication.getName());
				return new ResponseEntity<Response>(new Response(true, "Login Success", authResponse), HttpStatus.OK);
			} else {
				return new ResponseEntity<Response>(new Response(false, "Wrong OTP", twoFactorResponse),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<Response>(new Response(false, "Authencation Failed", null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation("Oauth Information")
	@GetMapping("/info")
	public ResponseEntity<Response> getOAuthDetails(OAuth2AuthenticationToken token) {
		logger.info("OAuth Details ", token);
		if (null != token) {
			OAuthResponse oAuthResponse = new OAuthResponse();
			oAuthResponse.setAuthenciated(token.isAuthenticated());
			oAuthResponse.setAuthorities(token.getAuthorities());
			oAuthResponse.setClientRegestrationId(token.getAuthorizedClientRegistrationId());
			oAuthResponse.setCredentials(token.getCredentials());
			oAuthResponse.setDetails(token.getDetails());
			oAuthResponse.setName(token.getName());
			oAuthResponse.setPrincipal(token.getPrincipal());
			return new ResponseEntity<Response>(new Response(true, "Success", oAuthResponse), HttpStatus.OK);
		} else {
			return new ResponseEntity<Response>(new Response(false, "Authencation Failed", null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
