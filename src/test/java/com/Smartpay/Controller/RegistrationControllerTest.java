package com.Smartpay.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.Smartpay.Base.BaseTest;
import com.Smartpay.Model.User;
import com.Smartpay.Response.Response;
import com.Smartpay.Utility.Utility;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegistrationControllerTest extends BaseTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeAll
	public void init() {
		logger.info("RegistrationController API's Test Start.......");
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@DisplayName("Test RegisterAdmin")
	@Test
	public void testRegisterAdmin() throws Exception {
		logger.info("testRegisterAdmin");
		User user = new User();
		user.setApplicantName("Saurav Kumar");
		user.setMobileNo("969109874");
		user.setEmailId("krsaurav.097@gmail.com");
		user.setDateOfBirth(Utility.convertStringToDate("1997-05-10"));
		String jsonRequest = objectMapper.writeValueAsString(user);
		MvcResult result = mockMvc.perform(
				post("/v1/register/saveAdmin").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated()).andReturn();
		String contentString = result.getResponse().getContentAsString();
		Response response = objectMapper.readValue(contentString, Response.class);
		Assertions.assertTrue(response.isProcessStatus());
	}

	@DisplayName("Test RegisterUser")
	@Test
	public void testRegisterUser() throws Exception {
		logger.info("testRegisterUser");
		User user = new User();
		user.setApplicantName("Surbhi Kumari");
		user.setMobileNo("7565201087");
		user.setEmailId("kumari.surbhi1999@hotmail.com");
		user.setDateOfBirth(Utility.convertStringToDate("1999-04-14"));
		String jsonRequest = objectMapper.writeValueAsString(user);
		MvcResult result = mockMvc.perform(
				post("/v1/register/saveUser").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated()).andReturn();
		String contentString = result.getResponse().getContentAsString();
		Response response = objectMapper.readValue(contentString, Response.class);
		Assertions.assertTrue(response.isProcessStatus());
	}

	@AfterAll
	public void finish() {
		logger.info("RegistrationController API's Test  Finished....");
		mockMvc = null;
	}

}
