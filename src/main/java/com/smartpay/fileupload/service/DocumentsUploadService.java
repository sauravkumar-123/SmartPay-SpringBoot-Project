package com.smartpay.fileupload.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smartpay.fileupload.model.MerchantDocuments;

@Service
public interface DocumentsUploadService {

	public MerchantDocuments uploadDocumentsForBankingService(String username, MultipartFile[] files);

}
