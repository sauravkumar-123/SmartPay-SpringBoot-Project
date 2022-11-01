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
import com.Smartpay.Enum.EnumsStatus.IsActive;
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
			criteria.add(Restrictions.and(Restrictions.eq("user", user), Restrictions.eq("isActive", IsActive.ACTIVE)));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.alias(Projections.property("merchant.merchantIdentificationNo"),
							"merchantIdentificationNo"))
					.add(Projections.alias(Projections.property("merchant.aepsServiceStatus"), "aepsServiceStatus"))
					.add(Projections.alias(Projections.property("merchant.EKYCstatus"), "EKYCstatus"))
					.add(Projections.alias(Projections.property("merchant.bankOnboardStatus"), "bankOnboardStatus"))
					.add(Projections.alias(Projections.property("merchant.isActive"), "isActive"))
					.add(Projections.alias(Projections.property("merchant.documentsUploadStatus"),
							"documentsUploadStatus"))
					.add(Projections.alias(Projections.property("merchant.createdDate"), "createdDate"))
					.add(Projections.alias(Projections.property("merchant.updatedDate"), "updatedDate")));
			criteria.setResultTransformer(Transformers.aliasToBean(Merchant.class));
			Merchant merchantData = (Merchant) criteria.uniqueResult();
			logger.info("Merchant Details{} ", merchantData);
			transaction.commit();
			return merchantData;
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			logger.debug("Exception Message{} " + e.getMessage());
			throw new GlobalException("Unbale To Fetch Merchant Details");
		}
	}

	@Override
	public Merchant findMerchantById(String identificationNo) {
		Session session = entityManager.unwrap(Session.class);
		String qry = "SELECT m.merchantIdentificationNo AS merchantIdentificationNo, m.aadhaarcardNo AS aadhaarcardNo, m.isActive AS isActive FROM Merchant m WHERE m.merchantIdentificationNo=:id";
		Query query = session.createQuery(qry);
		query.setParameter("id", identificationNo);
		logger.debug("Query", query);
		Merchant merchant = (Merchant) query.uniqueResult();
		return merchant;
	}

}
