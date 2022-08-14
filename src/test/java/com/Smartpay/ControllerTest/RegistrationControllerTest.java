package com.Smartpay.ControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.Smartpay.Model.User;
import com.Smartpay.Response.Response;
import com.Smartpay.Utility.Utility;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegistrationControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationControllerTest.class);

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeAll
	public void initalSetUp() {
		logger.info("RegistrationController Test Started");
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@DisplayName("Test UserRegistration and their mainWalet Service")
	@Test
	public void testUserRegistration() throws Exception {
		logger.info("UserRegistration");
		User user = new User();
		user.setApplicantName("Sarvesh Rai");
		user.setMobileno("6852102065");
		user.setEmailid("sarvesh.rai@gmail.com");
		user.setDateOfBirth(Utility.convertStringToDate("1996-05-11"));
		String jsonRequest = objectMapper.writeValueAsString(user);
		MvcResult result = mockMvc.perform(
				post("/v1/register/saveUser").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated()).andReturn();
		String contentString = result.getResponse().getContentAsString();
		Response response = objectMapper.readValue(contentString, Response.class);
		Assertions.assertTrue(response.isProcessStatus());
	}
}
