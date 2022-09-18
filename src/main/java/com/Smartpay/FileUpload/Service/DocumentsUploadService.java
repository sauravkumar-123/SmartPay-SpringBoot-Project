package com.Smartpay.FileUpload.Service;

import org.springframework.stereotype.Service;

import com.Smartpay.FileUpload.MerchantDocuments;
import com.Smartpay.Request.MerchantDocumentsRequest;

@Service
public interface DocumentsUploadService {

	public MerchantDocuments uploadDocumentsForBankingService(String username,
			MerchantDocumentsRequest merchantDocumentsRequest);

}
