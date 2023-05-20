/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartpay.restcall.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartpay.constants.Constant;
import com.smartpay.dao.MerchantRepository;
import com.smartpay.dto.MerchantOnboardServiceInput;
import com.smartpay.dto.MerchantOnboardingDto;
import com.smartpay.enums.EnumsStatus;
import com.smartpay.enums.ServiceName;
import com.smartpay.exception.GlobalException;
import com.smartpay.request.MerchantOnboardingRequest;
import com.smartpay.request.RequestHeader;
import com.smartpay.response.Response;
import com.smartpay.restcall.service.BankingServiceApiCall;
import com.smartpay.utility.Utility;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author saura
 */
@Service(value = "bankingServiceApiCall")
public class BankingServiceApiCallImpl implements BankingServiceApiCall {

    private static final Logger logger = LoggerFactory.getLogger(BankingServiceApiCallImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper ObjectMapper;

    @Autowired
    private MerchantRepository merchantRepository;

//    @Autowired
//    private BankingServiceFingClient bankingServiceApi;
    @Override
    public Response merchantOnboardingApiCall() {
        Response result = null;
        List<MerchantOnboardingDto> onboardingDtos = merchantRepository.fetchMerchantByOnboardingStatus(EnumsStatus.YesNO.NO);
        if (null != onboardingDtos && !onboardingDtos.isEmpty()) {
            try {

                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                headers.set("User-Agent", "test");

                RequestHeader requestHeader = new RequestHeader(Constant.bankingServiceRequestSourceName, Utility.requestIdGen(), ServiceName.BANKING_ONBOARD_SERVICE.getValue(), Constant.simpleDateFormat.format(new Date()));
                MerchantOnboardServiceInput serviceInput = new MerchantOnboardServiceInput(onboardingDtos);
                MerchantOnboardingRequest merchantOnboardingRequest = new MerchantOnboardingRequest(requestHeader, serviceInput);

                HttpEntity<MerchantOnboardingRequest> entity = new HttpEntity<>(merchantOnboardingRequest, headers);
                ResponseEntity<String> responseEntity = restTemplate.exchange(Constant.onboardingApi, HttpMethod.POST, entity, String.class);

                String data = responseEntity.getBody();
                logger.info("Onboarding service response data{} ", data);
                if (null != data) {
                    result = ObjectMapper.readValue(data, Response.class);
                }
            } catch (HttpClientErrorException | HttpServerErrorException hx) {
                logger.error("Exception from bank server.. while pushing merchant onboarding data to banking system{} ", hx);
                if (hx.getStatusCode().is4xxClientError() || hx.getStatusCode().is5xxServerError()) {
                    logger.error("Geting exception from Bank Server...!!!");
                    String data = hx.getResponseBodyAsString();
                    try {
                        result = ObjectMapper.readValue(data, Response.class);
                    } catch (Exception ex) {
                        logger.error("Exception while parsing bank server response {} ", ex);
                        throw new GlobalException(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
                    }
                }
            } catch (Exception ex) {
                logger.error("Error while pushing merchant onboarding data to banking system{} ", ex);
                throw new GlobalException("Fail to push merchant data in banking system!!Please Try again", HttpStatus.EXPECTATION_FAILED);

            }
        }
        /**
         * ***************************************************************************************************************************************************************************************************************************************************************
         */
        /* try {
            RequestEntity<MerchantOnboardingRequest> request = RequestEntity.post(new URI(Constant.onboardingApi)).
                    header("User-Agent", "test")
                    .accept(MediaType.APPLICATION_JSON).body(merchantOnboardingRequest);

            ResponseEntity<Response> response = restTemplate.exchange(request, Response.class);
            logger.info("Onboarding API response{} :", response);
            result = response.getBody();
        } catch (HttpClientErrorException hx) {
            String str = hx.getResponseBodyAsString();
            logger.error("Failure Response{} :", str);
        } catch (Exception ex) {
            logger.error("Error while pushing merchant onboarding data to banking system{} ", ex);
            throw new GlobalException("Fail to push merchant data in banking system!!Please Try again", HttpStatus.EXPECTATION_FAILED);

        }*/
        /**
         * ********************************************************************************************************************************************************************************************************************************************************************************
         */
        return result;
    }

    /**
     * ********************************************************************************************************************************************************************************************************************************
     */
    /* public Response merchantOnboardingApiCall() {
        List<MerchantOnboardingDto> onboardingDtos = merchantRepository.fetchMerchantByOnboardingStatus(EnumsStatus.YesNO.NO);
        Response response = null;
        String responseBody = null;
        if (null != onboardingDtos && !onboardingDtos.isEmpty()) {
            
        }
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.set("User-Agent", "test");

                RequestHeader requestHeader = new RequestHeader(Constant.bankingServiceRequestSourceName, Utility.requestIdGen(), ServiceName.BANKING_ONBOARD_SERVICE.getValue(), Constant.simpleDateFormat.format(new Date()));
                MerchantOnboardServiceInput serviceInput = new MerchantOnboardServiceInput(onboardingDtos);
                MerchantOnboardingRequest merchantOnboardingRequest = new MerchantOnboardingRequest(requestHeader, serviceInput);
                HttpEntity<MerchantOnboardingRequest> entity = new HttpEntity<>(merchantOnboardingRequest, headers);
                ResponseEntity<String> responseEntity = restTemplate.exchange(Constant.onboardingApi, HttpMethod.POST, entity, String.class);
//                Response data = bankingServiceApi.pushMerchantOnboardingDataToBankingSystem(merchantOnboardInput);
                String data = responseEntity.getBody();
                logger.info("Onboarding service response data{} ", data);
                if (null != data) {
                    response = ObjectMapper.readValue(data, Response.class);
                }
            } catch (HttpClientErrorException ex) {
                responseBody = ex.getResponseBodyAsString();
            } catch (Exception ex) {
                logger.error("Error while pushing merchant onboarding data to banking system{} ", ex);
                throw new GlobalException("Fail to push merchant data in banking system!!Please Try again", HttpStatus.EXPECTATION_FAILED);
            }
        }
        //   }
        return response;
        
    }*/
    /**
     * ***************************************************************************************************************************************************************************************************************************************************************************
     */
}
