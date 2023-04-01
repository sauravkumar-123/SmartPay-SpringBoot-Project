package com.smartpay.service;

import org.springframework.stereotype.Service;

import com.smartpay.model.Merchant;

@Service
public interface MerchantService {

    public Merchant updateUserProfileToMerchant(String userName, Merchant merchantRequest);

    public void pushMerchantOnboardingDataToBankingService();
}
