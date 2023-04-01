package com.smartpay.daoimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.smartpay.dao.MerchantRepository;
import com.smartpay.dto.MerchantOnboardingDto;
import com.smartpay.enums.EnumsStatus;
import com.smartpay.enums.EnumsStatus.IsActive;
import com.smartpay.exception.GlobalException;
import com.smartpay.model.Merchant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MerchantRepositoryImpl implements MerchantRepository {

    private static final Logger logger = LoggerFactory.getLogger(MerchantRepositoryImpl.class);

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    public MerchantRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public MerchantRepositoryImpl() {

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Merchant saveMerchantProfile(Merchant merchant) {
        logger.info("Entred into MerchantRepository::saveMerchantProfile()");
        try {
            Merchant saveResult = entityManager.merge(merchant);
            logger.debug("merchant saved data {} ", saveResult);
            return saveResult;
        } catch (Exception e) {
            logger.error("Exception {} ", e);
            throw new GlobalException("Unable To Save Merchant Details!! Error:: " + e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Merchant findMerchantByUserId(String userIdentificationNo) {
        logger.info("Entred into MerchantRepository::findMerchantByUserId()");
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Merchant> criteria = builder.createQuery(Merchant.class);
            Root<Merchant> root = criteria.from(Merchant.class);
            Predicate userIdPredicate = builder
                    .and(
                            builder.equal(root.get("userIdentificationNo"), userIdentificationNo),
                            builder.equal(root.get("isActive"), IsActive.ACTIVE)
                    );
            criteria.multiselect(root.get("merchantIdentificationNo").alias("merchantIdentificationNo"),
                    root.get("aepsServiceStatus").alias("aepsServiceStatus"),
                    root.get("EKYCstatus").alias("EKYCstatus"),
                    root.get("bankOnboardStatus").alias("bankOnboardStatus"),
                    root.get("isActive").alias("isActive"),
                    root.get("documentsUploadStatus").alias("documentsUploadStatus")
            //    root.get("createdDate").alias("createdDate"),
            //    root.get("updatedDate").alias("updatedDate")
            ).where(userIdPredicate);
            List<Merchant> result = entityManager.createQuery(criteria).getResultList();
            Merchant merchantData = (!result.isEmpty() && result.get(0) != null) ? result.get(0) : null;
            logger.debug("Merchant Details{} ", merchantData);
            return merchantData;
        } catch (Exception e) {
            logger.error("Exception {} ", e);
            throw new GlobalException("Unable To Check Merchant Records!!Try Again..");
        }

    }

    @Transactional(readOnly = true)
    @Override
    public Merchant findMerchantByMerchantId(String identificationNo) {
        logger.info("Entred into MerchantRepository::findMerchantByMerchantId()");
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Merchant> criteria = builder.createQuery(Merchant.class);
            Root<Merchant> root = criteria.from(Merchant.class);

            criteria.multiselect(root.get("merchantIdentificationNo").alias("merchantIdentificationNo"),
                    root.get("userIdentificationNo").alias("userIdentificationNo"),
                    root.get("aadhaarcardNo").alias("aadhaarcardNo"))
                    .where(builder.and(
                            builder.equal(root.get("merchantIdentificationNo"), identificationNo),
                            builder.equal(root.get("isActive"), IsActive.ACTIVE)
                    )
                    );
            List<Merchant> result = entityManager.createQuery(criteria).getResultList();
            Merchant merchantData = (!result.isEmpty() && result.get(0) != null) ? result.get(0) : null;
            logger.debug("Merchant Details{} ", merchantData);
            return merchantData;
        } catch (Exception e) {
            logger.error("Exception {} ", e);
            throw new GlobalException("Unable To Check Merchant Records!!Try Again..");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDocumentsUploadStatus(String identificationNo, EnumsStatus.DocumentsUploadStatus status) {
        logger.info("Enter into MerchantRepository::updateDocumentsUploadStatus()");
        entityManager.createQuery("UPDATE Merchant mt SET mt.documentsUploadStatus=:uploadStatus WHERE mt.merchantIdentificationNo=:idNo AND mt.isActive=:activeStatus")
                .setParameter("uploadStatus", status)
                .setParameter("idNo", identificationNo)
                .setParameter("activeStatus", IsActive.ACTIVE).executeUpdate();
    }

    @Override
    public List<MerchantOnboardingDto> fecthMerchnatByOnboardingStatus(EnumsStatus.YesNO status) {
        logger.info("Entred into MerchantRepository::fecthMerchnatByOnboardingStatus()");
        try {
            String qry = "SELECT m.merchantIdentificationNo AS merchantIdentificationNo, "
                    + "m.aadhaarcardNo AS aadhaarcardNo, "
                    + "m.panCardNo AS panCardNo, "
                    + "m.userName AS userName, "
                    + "m.gstNo AS gstNo "
                    + "FROM Merchant m WHERE m.bankOnboardStatus=:onboardStatus AND m.isActive=:activeStatus";
            List<MerchantOnboardingDto> result = entityManager.createQuery(qry)
                    .setParameter("onboardStatus", status)
                    .setParameter("activeStatus", IsActive.ACTIVE).getResultList();
            return result;
        } catch (Exception ex) {
            logger.error("Unable to get merchant onboard data{} ", ex);
            throw new GlobalException("Unable to get merchant onboard data!!");
        }
    }

    @Override
    public void updateOnboardAndaepsStatus(String merchantId, String onboardId, String onboardStatus, EnumsStatus.YesNO aepsStatus) {
        logger.info("Entred into MerchantRepository::updateOnboardAndaepsStatus()");
        String qry = "UPDATE Merchant mt SET mt.onboardingServiceIdentificationNo=:onboardId, mt.bankOnboardStatus=:onbaordStatus, "
                + "mt.aepsServiceStatus=:aepsStatus WHERE mt.merchantIdentificationNo=:merchantId";
        entityManager.createQuery(qry)
                .setParameter("onboardId", onboardId)
                .setParameter("merchantId", merchantId)
                .setParameter("onbaordStatus", onboardStatus.equals(EnumsStatus.YesNO.YES.toString()) ? EnumsStatus.YesNO.YES : EnumsStatus.YesNO.NO)
                .setParameter("aepsStatus", aepsStatus).executeUpdate();
    }

}
