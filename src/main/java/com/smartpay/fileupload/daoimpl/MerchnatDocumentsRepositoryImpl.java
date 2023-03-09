package com.smartpay.fileupload.daoimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.smartpay.exception.GlobalException;
import com.smartpay.fileupload.model.MerchantDocuments;
import com.smartpay.fileupload.dao.MerchantDocumentsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MerchnatDocumentsRepositoryImpl implements MerchantDocumentsRepository {

    private static final Logger logger = LoggerFactory.getLogger(MerchnatDocumentsRepositoryImpl.class);

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    public MerchnatDocumentsRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public MerchnatDocumentsRepositoryImpl() {

    }

    @Transactional(readOnly = true)
    @Override
    public MerchantDocuments getDocDetailsByMerchantId(String identificationNo) {
        logger.info("Entred into MerchantDocumentsRepository::getDocDetailsByMerchantDetail()");
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<MerchantDocuments> criteria = builder.createQuery(MerchantDocuments.class);
            Root<MerchantDocuments> root = criteria.from(MerchantDocuments.class);

            criteria.multiselect(root.get("merchantDocumentsID").alias("merchantDocumentsID"),
                    root.get("panCardImagePath").alias("panCardImagePath"),
                    root.get("aadhaarCardImagePath").alias("aadhaarCardImagePath"),
                    root.get("cancledCheckPath").alias("cancledCheckPath"),
                    root.get("isApproved").alias("isApproved"))
                    .where(
                            builder.equal(root.get("merchantIdentificationNo"), identificationNo)
                    );
            List<MerchantDocuments> result = entityManager.createQuery(criteria).getResultList();
            MerchantDocuments docResult = (!result.isEmpty() && result.get(0) != null) ? result.get(0) : null;
            logger.debug("Documents Upload Details{}", docResult);
            return docResult;
        } catch (Exception e) {
            logger.error("Exception {} ", e);
            throw new GlobalException("Unable To Fetch Documents Details");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public MerchantDocuments saveDocumentsDetail(MerchantDocuments docdetails) {
        logger.info("Entred into MerchantDocumentsRepository::saveDocumentsDetail()");
        try {
            MerchantDocuments savedResult = entityManager.merge(docdetails);
            logger.debug("merchant documents saved data {} ", savedResult);
            return savedResult;
        } catch (Exception e) {
            logger.error("Exception {} ", e);
            throw new GlobalException("Unable To Save Documents Details");
        }
    }
}
