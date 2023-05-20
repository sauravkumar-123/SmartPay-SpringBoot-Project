package com.smartpay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartpay.dao.UserRepository;
import com.smartpay.model.User;
import com.smartpay.response.JWTAuthResponse;
import com.smartpay.response.OAuthResponse;
import com.smartpay.response.Response;
import com.smartpay.response.TwoFactorResponse;
import com.smartpay.security.SmartPayAuthencationProvider;
import com.smartpay.security.jwt.JWTUtils;
import com.smartpay.utility.Utility;

import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation("Login OTP Send API")
    @RequestMapping(value = "/send-login-OTP", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> sendLoginOTP(
            @Valid @RequestParam("username") @NotBlank(message = "Invalid Username") String username,
            @Valid @RequestParam("password") @NotBlank(message = "Invalid Password") String password) {
        Authentication authentication = authProvider
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        logger.info("Authentication Details{} " + authentication);
        if (null != authentication) {
            User user = userRepository.findUserByUsername(authentication.getName());
            TwoFactorResponse twoFactorResponse = Utility.sendLoginOTP(user.getMobileNo());
            if (twoFactorResponse.getStatus().equalsIgnoreCase("Success")) {
                return new ResponseEntity<Response>(new Response(LocalDateTime.now(), true, "OTP Sent", twoFactorResponse), HttpStatus.OK);
            } else {
                return new ResponseEntity<Response>(new Response(LocalDateTime.now(), false, "Unable To Send OTP", twoFactorResponse),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<Response>(new Response(LocalDateTime.now(), false, "Authencation Failed", null),
                    HttpStatus.FORBIDDEN);
        }
    }

    @ApiOperation("User Login And OTP Verfication API")
    @RequestMapping(value = "/user-login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> loginUser(
            @Valid @RequestParam("username") @NotBlank(message = "Invalid Username") String username,
            @Valid @RequestParam("password") @NotBlank(message = "Invalid Password") String password,
            @Valid @RequestParam("sessionId") @NotBlank(message = "Invalid SessionId") String sessionId,
            @Valid @RequestParam("inputOtp") @NotBlank(message = "Invalid OTP") String inputOtp) {
        Authentication authentication = authProvider
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        logger.info("Authentication Details{} ", authentication);
        if (null != authentication) {
            TwoFactorResponse twoFactorResponse = Utility.verifyLoginOTP(sessionId, inputOtp);
            if (twoFactorResponse.getStatus().equalsIgnoreCase("Success")) {
                User user = userRepository.findUserByUsername(authentication.getName());
                String token = jwtUtils.generateAccessToken(user);
                JWTAuthResponse authResponse = new JWTAuthResponse();
                authResponse.setAuthToken(token);
                authResponse.setMessage("Authencation Successfully done!!");
                authResponse.setStatus("success");
                authResponse.setUsername(authentication.getName());
                return new ResponseEntity<Response>(new Response(LocalDateTime.now(), true, "Login Success", authResponse), HttpStatus.OK);
            } else {
                return new ResponseEntity<Response>(new Response(LocalDateTime.now(), false, "Wrong OTP", twoFactorResponse),
                        HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<Response>(new Response(LocalDateTime.now(), false, "Authencation Failed", null),
                    HttpStatus.FORBIDDEN);
        }
    }

    @ApiOperation("API for send forgot password OTP")
    @PostMapping("/forgotPassword/OTP/send")
    public ResponseEntity<Response> forgotPasswordOTPsent(
            @Valid @RequestParam("username") @NotBlank(message = "Invalid Username") String username) {
        logger.info("Enter into AuthController::forgotPasswordOTPsent()");
        User user = userRepository.findUserByUsername(username);
        if (null != user) {
            TwoFactorResponse twoFactorResponse = Utility.sendLoginOTP(user.getMobileNo());
            if (twoFactorResponse.getStatus().equalsIgnoreCase("Success")) {
                return new ResponseEntity<Response>(new Response(LocalDateTime.now(), true, "OTP Sent", twoFactorResponse), HttpStatus.OK);
            } else {
                return new ResponseEntity<Response>(
                        new Response(LocalDateTime.now(), false, "OTP Send Failed..Try Again!!", twoFactorResponse),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<Response>(new Response(LocalDateTime.now(), false, "User not found with username: " + username, null),
                    HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("API for verify forgot password OTP and change user password")
    @PostMapping("/forgotPassword/OTP/verify")
    public ResponseEntity<Response> forgotPasswordOTPverify(
            @Valid @RequestParam("username") @NotBlank(message = "Invalid Username") String username,
            @Valid @RequestParam("sessionId") @NotBlank(message = "Invalid SessionId") String sessionId,
            @Valid @RequestParam("inputOtp") @NotBlank(message = "Invalid OTP") String inputOtp,
            @Valid @RequestParam("password") @NotBlank(message = "Invalid Password") String password) {
        logger.info("Enter into AuthController::forgotPasswordOTPverify()");
        User user = userRepository.findUserByUsername(username);
        if (null != user) {
            TwoFactorResponse twoFactorResponse = Utility.verifyLoginOTP(sessionId, inputOtp);
            if (twoFactorResponse.getStatus().equalsIgnoreCase("Success")) {
                String pswd = passwordEncoder.encode(password);
                boolean result = userRepository.updateUserLoginPassword(user.getUserIdentificationNo(), pswd);
                if (result) {
                    return new ResponseEntity<Response>(new Response(LocalDateTime.now(), true, "Password Successfully Updated", null),
                            HttpStatus.OK);
                } else {
                    return new ResponseEntity<Response>(new Response(LocalDateTime.now(), false, "Fail to update password!!Try Again..", null),
                            HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity<Response>(new Response(LocalDateTime.now(), false, "OTP verification failed!!", twoFactorResponse),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<Response>(new Response(LocalDateTime.now(), false, "User not found with username: " + username, null),
                    HttpStatus.NOT_FOUND);
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
            return new ResponseEntity<Response>(new Response(LocalDateTime.now(), true, "Success", oAuthResponse), HttpStatus.OK);
        } else {
            return new ResponseEntity<Response>(new Response(LocalDateTime.now(), false, "Authencation Failed", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
