package com.Smartpay.FileUpload.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Smartpay.FileUpload.MerchantDocuments;

@Service
public interface DocumentsUploadService {

	public MerchantDocuments uploadDocumentsForBankingService(String username, MultipartFile[] files);

}
