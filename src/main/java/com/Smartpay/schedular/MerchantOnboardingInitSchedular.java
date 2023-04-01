/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartpay.schedular;

import com.smartpay.service.MerchantService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author saura
 */
@Configuration(value = "onboardingInitSchedular")
public class MerchantOnboardingInitSchedular {

    private static final Logger logger = LoggerFactory.getLogger(MerchantOnboardingInitSchedular.class);

    public final Lock merchantOnboardingServiceLock = new ReentrantLock();

    @Value("${merchant.onboarding.inital.schedular.enable}")
    private boolean onboardingSchedularEnable;

    @Autowired
    private MerchantService merchantService;

    @Scheduled(fixedDelayString = "${merchant.onboarding.api.timer}", initialDelay = 3000)
    // @Scheduled(cron = "0 0 10 1/1 * ? *", zone = "Asia/Kolkata ")
    public void trracsTransactionalAPIScheduler() {
        if (onboardingSchedularEnable) {
            merchantOnboardingServiceLock.lock();
            try {
                logger.trace("Merchant onboarding API Scheduler started");
                merchantService.pushMerchantOnboardingDataToBankingService();
            } catch (Exception e) {
                logger.error("Exception occurred in Merchant onboarding API Scheduler", e);
            } finally {
                logger.trace("Merchant onboarding API Scheduler ended");
                merchantOnboardingServiceLock.unlock();
            }
        }
    }
}
