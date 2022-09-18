package com.Smartpay.FileUpload.Dao;

import org.springframework.stereotype.Repository;

import com.Smartpay.FileUpload.MerchantDocuments;
import com.Smartpay.Model.Merchant;

@Repository
public interface MerchantDocumentsRepository {

	public MerchantDocuments getDocDetailsByMerchantDetail(Merchant merchant);

	public MerchantDocuments saveDocumentsDetail(MerchantDocuments docdetails);

}
