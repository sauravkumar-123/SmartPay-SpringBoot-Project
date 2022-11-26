package com.Smartpay.Controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.Smartpay.Enum.EnumsStatus.AccountType;
import com.Smartpay.Enum.EnumsStatus.AddressType;
import com.Smartpay.Enum.EnumsStatus.BusinessType;
import com.Smartpay.Enum.EnumsStatus.Gender;
import com.Smartpay.Enum.EnumsStatus.MaritalStatus;
import com.Smartpay.Model.Address;
import com.Smartpay.Model.Merchant;
import com.Smartpay.Model.MerchantBankDetails;
import com.Smartpay.Response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest extends BaseTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeAll
	public void init() {
		logger.info("UserController API's Test Start.......");
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@DisplayName("Test UpdateUserTOMerchantProfile")
	@Test
	void testUpdateUserTOMerchantProfile() throws Exception {
		logger.info("testUpdateUserTOMerchantProfile");

		MerchantBankDetails bankDetails1 = new MerchantBankDetails();
		bankDetails1.setAccountHolderName("Surya Kanti");
		bankDetails1.setAccountNumber("058698410025146");
		bankDetails1.setIfscCode("SBI00004512");
		bankDetails1.setAccountType(AccountType.Current);
		bankDetails1.setBankName("State Bank Of India");
		bankDetails1.setBranchName("Whitefield Bangalore");

		MerchantBankDetails bankDetails2 = new MerchantBankDetails();
		bankDetails2.setAccountHolderName("Surya Kanti");
		bankDetails2.setAccountNumber("651008742100156");
		bankDetails2.setIfscCode("PNB00001512");
		bankDetails2.setAccountType(AccountType.Saving);
		bankDetails2.setBankName("Punjab National Bank");
		bankDetails2.setBranchName("Digha Patna");

		Set<MerchantBankDetails> bankDetails = new HashSet<>();
		bankDetails.add(bankDetails1);
		bankDetails.add(bankDetails2);

		Address address1 = new Address();
		address1.setAddress("Near Police Station Whitefield, Bangalore");
		address1.setVillageCity("Bangalore");
		address1.setDistrict("Bangalore Urban");
		address1.setPincode("560087");
		address1.setState("karnatka");
		address1.setCountry("India");
		address1.setAddressType(AddressType.CurrentAddress);

		Address address2 = new Address();
		address1.setAddress("Dinkar Chowk Patna-01");
		address1.setVillageCity("Patna");
		address1.setDistrict("Patna");
		address1.setPincode("800001");
		address1.setState("Bihar");
		address1.setCountry("India");
		address1.setAddressType(AddressType.PermanentAddress);

		List<Address> addresses = Arrays.asList(address1, address2);

		Merchant merchant = new Merchant();
		merchant.setBankDetails(bankDetails);
		merchant.setAddresses(addresses);
		merchant.setGuardianName("Shri Gyanendra kumar");
		merchant.setMaritalStatus(MaritalStatus.MARRIED);
		merchant.setGender(Gender.MALE);
		merchant.setBusinessType(BusinessType.Individual);
		merchant.setPanCardNo("SXZPA7076J");
		merchant.setAadhaarcardNo("562045129614");
		merchant.setBusinesspanno("JAPOA0230D");
		merchant.setGstNo("PSVH7950KJ");
		merchant.setTanNo("FASOPRTRPC87550TA");
		String jsonRequest = objectMapper.writeValueAsString(merchant);
		MvcResult result = mockMvc.perform(put("/v1/user/updateProfile/{userName}", "IR104510").content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		String contentString = result.getResponse().getContentAsString();
		Response response = objectMapper.readValue(contentString, Response.class);
		Assertions.assertTrue(response.isProcessStatus());
	}

	@Test
	void testUploadMerchantDocuments() {
		fail("Not yet implemented");
	}

	@Test
	void testDownloadImageFile() {
		fail("Not yet implemented");
	}

	@AfterAll
	public void finish() {
		logger.info("UserController API's Test  Finished....");
		mockMvc = null;
	}
}
