package com.smartpay.dao;

import com.smartpay.dto.MerchantOnboardingDto;
import com.smartpay.enums.EnumsStatus;
import com.smartpay.enums.EnumsStatus.YesNO;
import org.springframework.stereotype.Repository;

import com.smartpay.model.Merchant;
import java.util.List;

@Repository
public interface MerchantRepository {

    public Merchant saveMerchantProfile(Merchant merchant);

    public Merchant findMerchantByUserId(String userIdentificationNo);

    public Merchant findMerchantByMerchantId(String identificationNo);

    public void updateDocumentsUploadStatus(String identificationNo, EnumsStatus.DocumentsUploadStatus uploadStatus);

    public List<MerchantOnboardingDto> fetchMerchantByOnboardingStatus(YesNO status);

    public void updateOnboardAndaepsStatus(String merchantId, String onboardId, String onboardStatus, YesNO aepsStatus);

}
