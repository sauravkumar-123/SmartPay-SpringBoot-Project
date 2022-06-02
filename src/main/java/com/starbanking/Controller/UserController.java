package com.starbanking.Controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.starbanking.Model.User;
import com.starbanking.Response.Response;
import com.starbanking.Service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> registerUser(@Valid @RequestBody User user) {
		User result = userService.registerUser(user);
		logger.info("User Details{} " + result);
		if (null != result) {
			return new ResponseEntity<Response>(new Response(true, "User Details Saved", result), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Response>(new Response(false, "Unable To Save User Details", result),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
