package com.Smartpay.FileUpload.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Smartpay.DAO.MerchantRepository;
import com.Smartpay.Enum.EnumsStatus.DocumentsUploadStatus;
import com.Smartpay.Enum.EnumsStatus.YesNO;
import com.Smartpay.Exception.FileStorageException;
import com.Smartpay.Exception.GlobalException;
import com.Smartpay.Exception.ResourceNotFoundException;
import com.Smartpay.FileUpload.FileStorageService;
import com.Smartpay.FileUpload.MerchantDocuments;
import com.Smartpay.FileUpload.Dao.MerchantDocumentsRepository;
import com.Smartpay.FileUpload.Service.DocumentsUploadService;
import com.Smartpay.Model.Merchant;
import com.Smartpay.Utility.StringUtil;

@Service
public class DocumentsUploadServiceImpl implements DocumentsUploadService {

	private static final Logger logger = LoggerFactory.getLogger(DocumentsUploadServiceImpl.class);

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private MerchantRepository merchantRepository;

	@Autowired
	private MerchantDocumentsRepository merchantDocumentsRepository;

	@Override
	public MerchantDocuments uploadDocumentsForBankingService(String identificationNo, MultipartFile[] files) {
		logger.info("Enter Into DocumentsUploadService::uploadDocumentsForBankingService()");
		MerchantDocuments result = null;
		Merchant merchant = merchantRepository.findMerchantByMerchantId(identificationNo);
		if (null != merchant) {
			MerchantDocuments docs = merchantDocumentsRepository
					.getDocDetailsByMerchantId(merchant.getMerchantIdentificationNo());
			if (null == docs) {
				String[] filelocationArr = fileStorageService.storeFile(files,
						StringUtil.getLast4digit(merchant.getAadhaarcardNo()));
				MerchantDocuments merchantDocuments = new MerchantDocuments();

				// @ changing documents upload status in merchant.
				merchant.setDocumentsUploadStatus(DocumentsUploadStatus.SUCCESS);

				merchantDocuments.setAadhaarCardImagePath(filelocationArr[0]);
				merchantDocuments.setPanCardImagePath(filelocationArr[1]);
				merchantDocuments.setCancledCheckPath(filelocationArr[2]);
				merchantDocuments.setIsApproved(YesNO.NO);
				merchantDocuments.setMerchantIdentificationNo(merchant.getMerchantIdentificationNo());
				if (merchantRepository.updateMerchantDetails(merchant)) {
					result = merchantDocumentsRepository.saveDocumentsDetail(docs);
					logger.debug("Merchant Uploaded documents: {}", result);
				} else {
					logger.error("merchant documents upload status not updated..");
					throw new GlobalException("Unable To Update merchant documents upload Status..!!");
				}
				return result;
			} else {
				logger.debug("Documents Already Uploaded");
				throw new FileStorageException("Documents Alreday Uploaded");
			}
		} else {
			logger.error("Merchant Details Not Found To Uplaod Documents");
			throw new ResourceNotFoundException("Merchant Details Not Found");
		}

	}

}
