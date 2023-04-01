/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartpay.restcall;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartpay.constants.Constant;
import com.smartpay.dao.MerchantRepository;
import com.smartpay.dto.MerchantOnboardingDto;
import com.smartpay.enums.EnumsStatus;
import com.smartpay.exception.GlobalException;
import com.smartpay.response.Response;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author saura
 */
@Component(value = "bankingServiceApiCall")
public class BankingServiceApiCall {

    private static final Logger logger = LoggerFactory.getLogger(BankingServiceApiCall.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper ObjectMapper;

    @Autowired
    private MerchantRepository merchantRepository;

    public Response merchantOnboadingApiCall() {
        List<MerchantOnboardingDto> onboardingDto = merchantRepository.fecthMerchnatByOnboardingStatus(EnumsStatus.YesNO.NO);
        Response response = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<List<MerchantOnboardingDto>> entity = new HttpEntity<>(onboardingDto);
            ResponseEntity<String> responseEntity = restTemplate.exchange(Constant.onboardingApi, HttpMethod.POST, entity, String.class);
            String data = responseEntity.getBody();
            if (null != data) {
                response = ObjectMapper.readValue(data, Response.class);
            }
        } catch (Exception ex) {
            logger.error("Error while pushing merchant onboarding data to banking system{} ", ex);
            throw new GlobalException("Fail to push merchant data in banking system!!Please Try again");
        }
        return response;
    }
}
