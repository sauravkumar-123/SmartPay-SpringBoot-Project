package com.starbanking.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/auth")
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
//	@Autowired
//	private AuthenticationManager authenticationManager;

}
