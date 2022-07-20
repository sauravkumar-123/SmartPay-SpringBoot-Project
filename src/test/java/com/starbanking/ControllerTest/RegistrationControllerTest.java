package com.starbanking.ControllerTest;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starbanking.Model.User;
import com.starbanking.Response.Response;
import com.starbanking.Utility.Utility;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegistrationControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationControllerTest.class);

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	ObjectMapper objectMapper = new ObjectMapper();

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
		user.setApplicantName("Shivani Kumari");
		user.setMobileno("8956258410");
		user.setEmailid("shivani.kumari654@gmail.com");
		user.setDateOfBirth(Utility.convertStringToDate("10-02-1996"));
		String jsonRequest = objectMapper.writeValueAsString(user);
		MvcResult result = mockMvc.perform(
				post("/v1/register/saveUser").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated()).andReturn();
		String contentString = result.getResponse().getContentAsString();
		Response response = objectMapper.readValue(contentString, Response.class);
		Assertions.assertTrue(response.isProcessStatus());
	}
}
