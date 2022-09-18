package com.Smartpay.Service;

import org.springframework.stereotype.Service;

import com.Smartpay.Model.Merchant;

@Service
public interface MerchantService {

	public Merchant updateUserProfileToMerchant(String userName, Merchant merchantRequest);
}
