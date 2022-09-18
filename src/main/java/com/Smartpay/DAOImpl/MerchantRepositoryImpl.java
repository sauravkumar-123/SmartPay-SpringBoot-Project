package com.Smartpay.DAOImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.Smartpay.DAO.MerchantRepository;
import com.Smartpay.Exception.GlobalException;
import com.Smartpay.Model.Merchant;
import com.Smartpay.Model.User;

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

	@Override
	public Merchant saveMerchantProfile(Merchant merchant) {
		Session session = entityManager.unwrap(Session.class);
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Merchant saveResult = (Merchant) session.merge(merchant);
			transaction.commit();
			return saveResult;
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			logger.info("Exception Message{} " + e.getMessage());
			throw new GlobalException("Unbale To Save Merchant Details");
		}
	}

	@Override
	public Merchant findMerchantByUserDetails(User user) {
		Session session = entityManager.unwrap(Session.class);
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Merchant.class, "merchant");
			criteria.add(Restrictions.and(Restrictions.eq("user", user), Restrictions.eq("isActive", 'Y')));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.alias(Projections.property("merchant.MerchantIdentificationNo"),
							"MerchantIdentificationNo"))
					.add(Projections.alias(Projections.property("merchant.user"), "user"))
					.add(Projections.alias(Projections.property("merchant.bankDetails"), "bankDetails"))
					.add(Projections.alias(Projections.property("merchant.addresses"), "addresses"))
					.add(Projections.alias(Projections.property("merchant.guardianName"), "guardianName"))
					.add(Projections.alias(Projections.property("merchant.maritalStatus"), "maritalStatus"))
					.add(Projections.alias(Projections.property("merchant.gender"), "gender"))
					.add(Projections.alias(Projections.property("merchant.businessType"), "businessType"))
					.add(Projections.alias(Projections.property("merchant.aepsServiceStatus"), "aepsServiceStatus"))
					.add(Projections.alias(Projections.property("merchant.EKYCstatus"), "EKYCstatus"))
					.add(Projections.alias(Projections.property("merchant.bankOnboardStatus"), "bankOnboardStatus"))
					.add(Projections.alias(Projections.property("merchant.isActive"), "isActive"))
					.add(Projections.alias(Projections.property("merchant.documentsUploadStatus"),
							"documentsUploadStatus"))
					.add(Projections.alias(Projections.property("merchant.panCardNo"), "panCardNo"))
					.add(Projections.alias(Projections.property("merchant.aadhaarcardNo"), "aadhaarcardNo"))
					.add(Projections.alias(Projections.property("merchant.merchantDocuments"), "merchantDocuments"))
					.add(Projections.alias(Projections.property("merchant.businesspanno"), "businesspanno"))
					.add(Projections.alias(Projections.property("merchant.gstNo"), "gstNo"))
					.add(Projections.alias(Projections.property("merchant.tanNo"), "tanNo"))
					.add(Projections.alias(Projections.property("merchant.createdDate"), "createdDate"))
					.add(Projections.alias(Projections.property("merchant.updatedDate"), "updatedDate")));
			criteria.setResultTransformer(Transformers.aliasToBean(Merchant.class));
			Merchant merchantData = (Merchant) criteria.uniqueResult();
			transaction.commit();
			return merchantData;
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			logger.info("Exception Message{} " + e.getMessage());
			throw new GlobalException("Unbale To Fetch Merchnat Details");
		}
	}

	@Override
	public Merchant findMerchantByUsername(String username) {
		Session session = entityManager.unwrap(Session.class);
		String qry = "SELECT m.merchant_identification_no AS MerchantId, m.username AS Username FROM Merchant m WHERE m.username=:userName";
		Query query = session.createQuery(qry);
		query.setParameter("userName", username);
		logger.debug("Query", query);
		Merchant merchant = (Merchant) query.uniqueResult();
		return merchant;
	}

}
