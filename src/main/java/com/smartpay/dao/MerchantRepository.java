package com.smartpay.dao;

import com.smartpay.enums.EnumsStatus;
import org.springframework.stereotype.Repository;

import com.smartpay.model.Merchant;

@Repository
public interface MerchantRepository {

    public Merchant saveMerchantProfile(Merchant merchant);

    public Merchant findMerchantByUserId(String userIdentificationNo);

    public Merchant findMerchantByMerchantId(String identificationNo);

    public void updateDocumentsUploadStatus(String identificationNo, EnumsStatus.DocumentsUploadStatus uploadStatus);

}
