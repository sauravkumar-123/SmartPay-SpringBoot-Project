package com.smartpay.fileupload.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smartpay.dao.MerchantRepository;
import com.smartpay.enums.EnumsStatus;
import com.smartpay.enums.EnumsStatus.YesNO;
import com.smartpay.exception.FileStorageException;
import com.smartpay.exception.GlobalException;
import com.smartpay.exception.ResourceNotFoundException;
import com.smartpay.fileupload.util.FileStorageService;
import com.smartpay.fileupload.model.MerchantDocuments;
import com.smartpay.fileupload.dao.MerchantDocumentsRepository;
import com.smartpay.fileupload.service.DocumentsUploadService;
import com.smartpay.model.Merchant;
import com.smartpay.utility.StringUtil;

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
        Merchant merchant = merchantRepository.findMerchantByMerchantId(identificationNo);
        if (null != merchant) {
            MerchantDocuments docs = merchantDocumentsRepository
                    .getDocDetailsByMerchantId(merchant.getMerchantIdentificationNo());
            if (null == docs) {
                String[] filelocationArr = fileStorageService.storeFile(files,
                        StringUtil.getLast4digit(merchant.getAadhaarcardNo()));
                MerchantDocuments merchantDocuments = new MerchantDocuments();
                try {
                    merchantDocuments.setAadhaarCardImagePath(filelocationArr[0]);
                    merchantDocuments.setPanCardImagePath(filelocationArr[1]);
                    merchantDocuments.setCancledCheckPath(filelocationArr[2]);
                    merchantDocuments.setIsApproved(YesNO.NO);
                    merchantDocuments.setMerchantIdentificationNo(merchant.getMerchantIdentificationNo());
                    merchantRepository.updateDocumentsUploadStatus(merchant.getMerchantIdentificationNo(), EnumsStatus.DocumentsUploadStatus.SUCCESS);
                    MerchantDocuments savedResult = merchantDocumentsRepository.saveDocumentsDetail(merchantDocuments);
                    logger.debug("Merchant Uploaded documents: {}", savedResult);
                    return savedResult;
                } catch (Exception e) {
                    logger.error("Error while upload merchant documents ", e);
                    merchantRepository.updateDocumentsUploadStatus(merchant.getMerchantIdentificationNo(), EnumsStatus.DocumentsUploadStatus.FAILED);
                    throw new GlobalException("Error While Upload Documents!!");
                }
            } else {
                logger.debug("Documents Already Uploaded");
                throw new FileStorageException("Documents Already Uploaded");
            }
        } else {
            logger.error("Merchant Details Not Found To Uplaod Documents");
            throw new ResourceNotFoundException("Merchant Details Not Found");
        }
    }
}
