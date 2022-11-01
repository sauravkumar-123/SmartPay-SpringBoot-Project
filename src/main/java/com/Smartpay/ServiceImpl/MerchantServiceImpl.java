package com.Smartpay.ServiceImpl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Smartpay.DAO.MerchantRepository;
import com.Smartpay.DAO.UserRepository;
import com.Smartpay.Enum.EnumsStatus.DocumentsUploadStatus;
import com.Smartpay.Enum.EnumsStatus.IsActive;
import com.Smartpay.Enum.EnumsStatus.YesNO;
import com.Smartpay.Exception.GlobalException;
import com.Smartpay.Exception.ResourceNotFoundException;
import com.Smartpay.Model.AEPSWallet;
import com.Smartpay.Model.Merchant;
import com.Smartpay.Model.User;
import com.Smartpay.Service.MerchantService;

@Service
public class MerchantServiceImpl implements MerchantService {

	private static final Logger logger = LoggerFactory.getLogger(MerchantServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MerchantRepository merchantRepository;

	@Override
	public Merchant updateUserProfileToMerchant(String userName, Merchant merchant) {
		logger.info("Enter Into updateUserProfileToMerchant");
		User user = userRepository.findUserByUsername(userName);
		if (null != user) {
			Merchant merchantDetails = merchantRepository.findMerchantByUserDetails(user);
			if (null == merchantDetails) {
				Merchant merchantProfile = new Merchant();
				user.setBankingServiceStatus(YesNO.YES);
				merchantProfile.setUser(user);
				merchantProfile.setBankDetails(merchant.getBankDetails());
				merchantProfile.setAddresses(merchant.getAddresses());
				merchantProfile.setGuardianName(merchant.getGuardianName());
				merchantProfile.setMaritalStatus(merchant.getMaritalStatus());
				merchantProfile.setGender(merchant.getGender());
				merchantProfile.setBusinessType(merchant.getBusinessType());
				merchantProfile.setAepsServiceStatus(YesNO.NO);
				merchantProfile.setEKYCstatus(YesNO.NO);
				merchantProfile.setBankOnboardStatus(YesNO.NO);
				merchantProfile.setDocumentsUploadStatus(DocumentsUploadStatus.Pending);
				merchantProfile.setIsActive(IsActive.ACTIVE);
				merchantProfile.setPanCardNo(merchant.getPanCardNo());
				merchantProfile.setAadhaarcardNo(merchant.getAadhaarcardNo());
				merchantProfile.setBusinesspanno(merchant.getBusinesspanno());
				merchantProfile.setGstNo(merchant.getGstNo());
				merchantProfile.setTanNo(merchant.getTanNo());

				AEPSWallet aepsWallet = new AEPSWallet();
				aepsWallet.setCurrentBalance(BigDecimal.ZERO);
				aepsWallet.setCommissionCredit(BigDecimal.ZERO);
				aepsWallet.setCharges(BigDecimal.ZERO);
				aepsWallet.setTds(BigDecimal.ZERO);
				aepsWallet.setCreditAmount(BigDecimal.ZERO);
				aepsWallet.setDebitAmount(BigDecimal.ZERO);
				aepsWallet.setIsActive(IsActive.ACTIVE);
				aepsWallet.setCreditType(null);
				aepsWallet.setDebitType(null);
				merchantProfile.setAepsWallet(aepsWallet);
				Merchant result = merchantRepository.saveMerchantProfile(merchantProfile);
				logger.info("Merchant Details{} ", result);
				return result;
			} else {
				logger.debug("User Profile Already Upgraded To Merchant Type.....");
				throw new GlobalException("User Alreday Updgraded To Merchant Profile");
			}
		} else {
			logger.debug("User Registration Not Found.....");
			throw new ResourceNotFoundException("No User Registred With: " + userName);
		}

	}

}
