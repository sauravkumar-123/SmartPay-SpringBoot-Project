package com.Smartpay.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Smartpay.DAO.MerchantRepository;
import com.Smartpay.DAO.UserRepository;
import com.Smartpay.Enum.EnumsStatus.DocumentsUploadStatus;
import com.Smartpay.Enum.EnumsStatus.YesNO;
import com.Smartpay.Exception.GlobalException;
import com.Smartpay.Exception.ResourceNotFoundException;
import com.Smartpay.FileUpload.FileStorageService;
import com.Smartpay.Model.AEPSWallet;
import com.Smartpay.Model.Merchant;
import com.Smartpay.Model.User;
import com.Smartpay.Service.MerchantService;

@Service
public class MerchantServiceImpl implements MerchantService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MerchantRepository merchantRepository;

	@Override
	public Merchant updateUserProfileToMerchant(String userName, Merchant merchant) {
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
				merchantProfile.setIsActive('Y');
				merchantProfile.setPanCardNo(merchant.getPanCardNo());
				merchantProfile.setAadhaarcardNo(merchant.getAadhaarcardNo());
				merchantProfile.setBusinesspanno(merchant.getBusinesspanno());
				merchantProfile.setGstNo(merchant.getGstNo());
				merchantProfile.setTanNo(merchant.getTanNo());

				AEPSWallet aepsWallet = new AEPSWallet();
				aepsWallet.setCurrentBalance(0.00);
				aepsWallet.setCommissionCredit(0.00);
				aepsWallet.setCharges(0.00);
				aepsWallet.setTds(0.00);
				aepsWallet.setCreditAmount(0.00);
				aepsWallet.setDebitAmount(0.00);
				aepsWallet.setIsActive('Y');
				aepsWallet.setCreditType(null);
				aepsWallet.setDebitType(null);

				merchantProfile.setAepsWallet(aepsWallet);
				Merchant result = merchantRepository.saveMerchantProfile(merchantProfile);
				return result;
			} else {
				throw new GlobalException("User Alreday Updated To Merchant Profile");
			}
		} else {
			throw new ResourceNotFoundException("No User Registred With: " + userName);
		}

	}

}
