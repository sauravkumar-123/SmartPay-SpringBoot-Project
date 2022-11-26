package com.Smartpay.DAO;

import org.springframework.stereotype.Repository;

import com.Smartpay.Model.Merchant;

@Repository
public interface MerchantRepository {

	public Merchant saveMerchantProfile(Merchant merchant);

	public Merchant findMerchantByUserId(String identificationNo);

	public Merchant findMerchantByMerchantId(String identificationNo);
}
