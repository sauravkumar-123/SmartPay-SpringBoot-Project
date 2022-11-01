package com.Smartpay.DAO;

import org.springframework.stereotype.Repository;

import com.Smartpay.Model.Merchant;
import com.Smartpay.Model.User;

@Repository
public interface MerchantRepository {

	public Merchant saveMerchantProfile(Merchant merchant);

	public Merchant findMerchantByUserDetails(User user);

	public Merchant findMerchantById(String identificationNo);
}
