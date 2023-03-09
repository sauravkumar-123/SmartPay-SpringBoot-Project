package com.smartpay.fileupload.dao;

import org.springframework.stereotype.Repository;

import com.smartpay.fileupload.model.MerchantDocuments;

@Repository
public interface MerchantDocumentsRepository {

    public MerchantDocuments getDocDetailsByMerchantId(String identificationNo);

    public MerchantDocuments saveDocumentsDetail(MerchantDocuments docdetails);

}
