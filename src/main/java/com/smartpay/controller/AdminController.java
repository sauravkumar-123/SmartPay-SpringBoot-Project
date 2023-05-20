package com.smartpay.controller;

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

import com.smartpay.model.User;
import com.smartpay.response.Response;
import com.smartpay.service.AdminService;

import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@Validated
@RequestMapping("/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @PreAuthorize("permitAll()")
    @ApiOperation("Admin Registration API")
    @RequestMapping(value = "/saveAdmin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> registerAdmin(@Valid @RequestBody User user) {
        logger.info("Entred into RegistrationController::registerAdmin()");
        logger.info("Request Payload to registerAdmin {} ", user);
        User result = adminService.registerAdmin(user);
        if (null != result) {
            logger.debug("Admin Registration Response {} " + result);
            return new ResponseEntity<Response>(new Response(LocalDateTime.now(), true, "Admin Details Saved", result), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<Response>(new Response(LocalDateTime.now(), false, "Unable To Save Admin Details", result),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
