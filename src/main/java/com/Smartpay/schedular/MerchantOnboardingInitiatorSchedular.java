///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.smartpay.schedular;
//
//import com.smartpay.service.MerchantService;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.Trigger;
//import org.springframework.scheduling.TriggerContext;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//
///**
// *
// * @author saura
// */
//@Configuration(value = "OnboardingInitiatorSchedular")
//public class MerchantOnboardingInitiatorSchedular implements SchedulingConfigurer {
//
//    private static final Logger logger = LoggerFactory.getLogger(MerchantOnboardingInitiatorSchedular.class);
//
//    @Value("${merchant.onboarding.inital.schedular.enable}")
//    private boolean onboardingSchedularEnable;
//
//    @Autowired
//    private MerchantService merchantService;
//
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        logger.info("Merchant onboarding schedular started{} ", new Date());
//
//        if (null == taskRegistrar.getScheduler()) {
//            taskRegistrar.setScheduler(poolScheduler());
//        }
//
//        taskRegistrar.addTriggerTask(() -> {
//            if (onboardingSchedularEnable) {
//                try {
//                    logger.debug("Initialize the merchant onboarding data push process : ", new Date());
//                    merchantService.pushMerchantOnboardingDataToBankingService();
//                    logger.debug("Finiliaze the merchant onboarding data push process : ", new Date());
//                } catch (Exception ex) {
//                    logger.error("Error while processing the merchant onboarding data push process : ", ex);
//                }
//            }
//
//        }, new Trigger() {
//            @Override
//            public Date nextExecutionTime(TriggerContext triggerContext) {
//                logger.debug("Calculating the next trigger timing");
//                Calendar nextExecutionTime = new GregorianCalendar();
//                Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
//                Date lastActualExecutionCompletedTime = triggerContext.lastCompletionTime();
//
//                logger.info("Merchant onboarding data move lastActualExecutionTime : " + lastActualExecutionTime);
//                logger.debug("Merchant onboarding data move lastActualExecutionCompletedTime : " + lastActualExecutionCompletedTime);
////                nextExecutionTime.setTime(lastActualExecutionCompletedTime != null ? lastActualExecutionCompletedTime : new Date());
//                if (lastActualExecutionCompletedTime == null) {
//                    Calendar calendar = Calendar.getInstance();
//                    String startTime = ApplicationContextProvider.getValue("merchant.onboard.start.time");
//                    calendar.add(Calendar.MINUTE, Integer.parseInt(startTime));
//                    nextExecutionTime.setTime(calendar.getTime());
//                    logger.debug("Merchant onboard data move lastActualExecutionCompletedTime : " + lastActualExecutionCompletedTime);
//                    logger.info("Merchant onboard data move lastActualExecutionTime : " + lastActualExecutionTime);
//                    logger.debug("Actual trigger time : " + nextExecutionTime.getTime());
//
//                } else {
//                    Calendar calendar = Calendar.getInstance();
//                    String startTime = ApplicationContextProvider.getValue("merchant.onboard.nextstart.time");
//                    calendar.add(Calendar.MINUTE, Integer.parseInt(startTime));
//                    nextExecutionTime.setTime(calendar.getTime());
//                    logger.debug("Merchant onboard data move lastActualExecutionCompletedTime : " + lastActualExecutionCompletedTime);
//                    logger.info("Merchant onboard data move lastActualExecutionTime : " + lastActualExecutionTime);
//                    logger.debug("Actual trigger time in else : " + nextExecutionTime.getTime());
//                }
//                logger.debug("******************************************** : " + nextExecutionTime.getTime());
//                return nextExecutionTime.getTime();
//            }
//        });
//        logger.debug("Merchant onboard Initiator scheduler completed : " + new Date());
//
//    }
//}
//
//@Bean(destroyMethod = "shutdown")
//public Executor taskExecutor() {
//        return Executors.newScheduledThreadPool(100);
//    }
//
//@Bean
//public TaskScheduler poolScheduler() {
//        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
//        scheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
//        scheduler.setPoolSize(1);
//        scheduler.initialize();
//        return scheduler;
//    }
//}
