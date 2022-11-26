package com.Smartpay.FileUpload.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Smartpay.DAO.MerchantRepository;
import com.Smartpay.Enum.EnumsStatus.YesNO;
import com.Smartpay.Exception.FileStorageException;
import com.Smartpay.Exception.ResourceNotFoundException;
import com.Smartpay.FileUpload.FileStorageService;
import com.Smartpay.FileUpload.MerchantDocuments;
import com.Smartpay.FileUpload.Dao.MerchantDocumentsRepository;
import com.Smartpay.FileUpload.Service.DocumentsUploadService;
import com.Smartpay.Model.Merchant;
import com.Smartpay.Request.MerchantDocumentsRequest;
import com.Smartpay.Utility.StringUtil;

import net.bytebuddy.asm.Advice.Return;

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
		logger.info("Enter Into uploadDocumentsForBankingService");
		Merchant merchant = merchantRepository.findMerchantByMerchantId(identificationNo);
		if (null != merchant) {
			MerchantDocuments docs = merchantDocumentsRepository
					.getDocDetailsByMerchantId(merchant.getMerchantIdentificationNo());
			if (null == docs) {
				String[] filelocationArr = fileStorageService.storeFile(files,
						StringUtil.getLast4digit(merchant.getAadhaarcardNo()));
				MerchantDocuments merchantDocuments = new MerchantDocuments();
				merchantDocuments.setMerchant(merchant);
				merchantDocuments.setAadhaarCardImagePath(filelocationArr[0]);
				merchantDocuments.setPanCardImagePath(filelocationArr[1]);
				merchantDocuments.setCancledCheckPath(filelocationArr[2]);
				merchantDocuments.setIsApproved(YesNO.NO);
				return merchantDocumentsRepository.saveDocumentsDetail(docs);
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
