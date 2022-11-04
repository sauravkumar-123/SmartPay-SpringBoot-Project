package com.Smartpay.Controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Smartpay.Model.User;
import com.Smartpay.Response.RegistrationResponse;
import com.Smartpay.Response.Response;
import com.Smartpay.Service.AdminService;
import com.Smartpay.Service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@Validated
@RequestMapping("/v1/register")
public class RegistrationController {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	private AdminService adminService;

	@Autowired
	private UserService userService;

	@ApiOperation("Admin Registration API")
	@RequestMapping(value = "/saveAdmin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> registerAdmin(@Valid @RequestBody User user) {
		logger.info("Entred into RegistrationController::registerAdmin()");
		logger.info("Request Payload to registerAdmin {} ", user);
		RegistrationResponse result = adminService.registerAdmin(user);
		if (null != result) {
			logger.debug("Admin Registration Response {} " + result);
			return new ResponseEntity<Response>(new Response(true, "Admin Details Saved", result), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Response>(new Response(false, "Unable To Save Admin Details", result),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation("User Registration API")
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> registerUser(@Valid @RequestBody User user) {
		logger.info("Entred into RegistrationController::registerUser()");
		logger.info("Request Payload to registerUser {} ", user);
		RegistrationResponse result = userService.registerUser(user);
		if (null != result) {
			logger.debug("User Registration Response {} " + result);
			return new ResponseEntity<Response>(new Response(true, "User Details Saved", result), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Response>(new Response(false, "Unable To Save User Details", result),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
