package com.smartpay.serviceimpl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartpay.dao.MerchantRepository;
import com.smartpay.dao.UserRepository;
import com.smartpay.enums.EnumsStatus.DocumentsUploadStatus;
import com.smartpay.enums.EnumsStatus.IsActive;
import com.smartpay.enums.EnumsStatus.YesNO;
import com.smartpay.exception.GlobalException;
import com.smartpay.exception.ResourceNotFoundException;
import com.smartpay.model.AEPSWallet;
import com.smartpay.model.Merchant;
import com.smartpay.model.User;
import com.smartpay.service.MerchantService;

@Service
public class MerchantServiceImpl implements MerchantService {

    private static final Logger logger = LoggerFactory.getLogger(MerchantServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public Merchant updateUserProfileToMerchant(String userName, Merchant merchant) {
        logger.info("Enter into MerchantService::updateUserProfileToMerchant()");
        User user = userRepository.findUserByUsername(userName);
        if (null != user) {
            Merchant merchantDetails = merchantRepository.findMerchantByUserId(user.getUserIdentificationNo());
            if (null == merchantDetails) {
                try {
                    Merchant merchantProfile = new Merchant();
                    // @ now going to set Merchant Data.
                    merchantProfile.setBankDetails(merchant.getBankDetails());
                    merchantProfile.setAddresses(merchant.getAddresses());
                    merchantProfile.setGuardianName(merchant.getGuardianName());
                    merchantProfile.setMaritalStatus(merchant.getMaritalStatus());
                    merchantProfile.setGender(merchant.getGender());
                    merchantProfile.setBusinessType(merchant.getBusinessType());
                    merchantProfile.setAepsServiceStatus(YesNO.NO);
                    merchantProfile.setEKYCstatus(YesNO.NO);
                    merchantProfile.setBankOnboardStatus(YesNO.NO);
                    merchantProfile.setDocumentsUploadStatus(DocumentsUploadStatus.PENDING);
                    merchantProfile.setIsActive(IsActive.ACTIVE);
                    merchantProfile.setPanCardNo(merchant.getPanCardNo());
                    merchantProfile.setAadhaarcardNo(merchant.getAadhaarcardNo());
                    merchantProfile.setBusinesspanno(merchant.getBusinesspanno());
                    merchantProfile.setGstNo(merchant.getGstNo());
                    merchantProfile.setTanNo(merchant.getTanNo());
                    merchantProfile.setUserIdentificationNo(user.getUserIdentificationNo());

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
                    aepsWallet.setMerchant(merchantProfile);
                    merchantProfile.setAepsWallet(aepsWallet);
                    userRepository.updateBankingServiceStatus(user.getUserIdentificationNo(), YesNO.YES);
                    Merchant savedResult = merchantRepository.saveMerchantProfile(merchantProfile);
                    logger.debug("Merchant Details{} ", savedResult);
                    return savedResult;
                } catch (Exception e) {
                    logger.error("Unable to save merchant detail ", e);
                    userRepository.updateBankingServiceStatus(user.getUserIdentificationNo(), YesNO.NO);
                    throw new GlobalException("Unable to update merchnat profile for username " + userName);
                }
            } else {
                logger.error("User Profile Already Upgraded To Merchant Type.....");
                throw new GlobalException("User Alreday Updgraded To Merchant Profile");
            }
        } else {
            logger.error("User Registration Not Found.....");
            throw new ResourceNotFoundException("No User Registred With: " + userName);
        }

    }
}