package com.Smartpay.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Smartpay.Constants.Constant;
import com.Smartpay.Response.TwoFactorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utility {

	private static final Logger logger = LoggerFactory.getLogger(Utility.class);

	public static long generateRandomfiveDigitNo() {
		Random r = new Random(System.currentTimeMillis());
		return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
	}

	public static Date convertStringToDate(String inputDate) {
		Date date = null;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse(inputDate);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		return date;
	}

	public static TwoFactorResponse sendLoginDetailsToUserMobno(String applicantName, String mobNo, String UserName) {
		TwoFactorResponse result = null;
		JSONObject json = new JSONObject();
		json.put("From", "PaySmt");
		json.put("To", mobNo);
		json.put("TemplateName", "SmartPay");
		json.put("VAR1", applicantName);
		json.put("VAR2", UserName);
		json.put("VAR3", StringUtil.generateDefaultPassword(applicantName));
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpPost request = new HttpPost(Constant.SENDLOGINDETAILSAPI);
			StringEntity params = new StringEntity(json.toString());
			request.addHeader("content-type", "application/json");
			request.setEntity(params);
			CloseableHttpResponse response = httpClient.execute(request);
			HttpEntity httpEntity = response.getEntity();
			String content = EntityUtils.toString(httpEntity);
			ObjectMapper objectMapper = new ObjectMapper();
			result = objectMapper.readValue(content, TwoFactorResponse.class);
			logger.info("2Factor Transaction API Response:----" + result);
			return result;
		} catch (Exception ex) {
			logger.error("2Factor Transaction API Response:----" + ex.getMessage());
			result.setDetails(null);
			result.setStatus("Failed");
			return result;
		}
	}

	public static TwoFactorResponse sendLoginOTP(String MobNo) {
		TwoFactorResponse result = null;
		String LOGINOTPSENDAPI = "https://2factor.in/API/V1/" + Constant.twoFactorAPIkey + "/SMS/" + MobNo
				+ "/AUTOGEN/SmartPay";
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpGet request = new HttpGet(LOGINOTPSENDAPI);
			request.addHeader("content-type", "application/json");
			CloseableHttpResponse response = httpClient.execute(request);
			HttpEntity httpEntity = response.getEntity();
			String content = EntityUtils.toString(httpEntity);
			ObjectMapper objectMapper = new ObjectMapper();
			result = objectMapper.readValue(content, TwoFactorResponse.class);
			logger.info("2Factor Transaction API Response:----" + result);
			return result;
		} catch (Exception ex) {
			logger.error("2Factor Transaction API Response:----" + ex.getMessage());
			result.setDetails(null);
			result.setStatus("Failed");
			return result;
		}
	}

	public static TwoFactorResponse verifyLoginOTP(String details, String inputOtp) {
		TwoFactorResponse result = null;
		String VERIFYOTPAPI = "https://2factor.in/API/V1/" + Constant.twoFactorAPIkey + "/SMS/VERIFY/" + details + "/"
				+ inputOtp;
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpGet request = new HttpGet(VERIFYOTPAPI);
			request.addHeader("content-type", "application/json");
			CloseableHttpResponse response = httpClient.execute(request);
			HttpEntity httpEntity = response.getEntity();
			String content = EntityUtils.toString(httpEntity);
			ObjectMapper objectMapper = new ObjectMapper();
			result = objectMapper.readValue(content, TwoFactorResponse.class);
			logger.info("2Factor Transaction API Response:----" + result);
			return result;
		} catch (Exception ex) {
			logger.error("2Factor Transaction API Response:----" + ex.getMessage());
			result.setDetails(null);
			result.setStatus("Failed");
			return result;
		}
	}
}
