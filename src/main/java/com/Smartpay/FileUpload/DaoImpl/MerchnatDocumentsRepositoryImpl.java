package com.Smartpay.FileUpload.DaoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.Smartpay.Exception.GlobalException;
import com.Smartpay.FileUpload.MerchantDocuments;
import com.Smartpay.FileUpload.Dao.MerchantDocumentsRepository;
import com.Smartpay.Model.Merchant;

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

	@Override
	public MerchantDocuments getDocDetailsByMerchantDetail(Merchant merchant) {
		Session session = entityManager.unwrap(Session.class);
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(MerchantDocuments.class, "docs");
			if (null != merchant) {
				criteria.add(Restrictions.eq("merchant", merchant));
			}
			criteria.setProjection(Projections.projectionList()
					.add(Projections.alias(Projections.property("docs.merchantDocumentsID"), "merchantDocumentsID"))
					.add(Projections.alias(Projections.property("docs.panCardImagePath"), "panCardImagePath"))
					.add(Projections.alias(Projections.property("docs.aadhaarCardImagePath"), "aadhaarCardImagePath"))
					.add(Projections.alias(Projections.property("docs.cancledCheckPath"), "cancledCheckPath"))
					.add(Projections.alias(Projections.property("docs.isApproved"), "isApproved")));
			criteria.setResultTransformer(Transformers.aliasToBean(MerchantDocuments.class));
			MerchantDocuments result = (MerchantDocuments) criteria.uniqueResult();
			logger.info("Documents Upload Details{}", result);
			transaction.commit();
			return result;
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			logger.debug("Exception Message{} " + e.getMessage());
			throw new GlobalException("Unbale To Fetch Documents Details");
		}

	}

	@Override
	public MerchantDocuments saveDocumentsDetail(MerchantDocuments docdetails) {
		Session session = entityManager.unwrap(Session.class);
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			MerchantDocuments savedResult = (MerchantDocuments) session.merge(docdetails);
			transaction.commit();
			return savedResult;
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			logger.debug("Exception Message{} " + e.getMessage());
			throw new GlobalException("Unbale To Save Documents Details");
		}
	}

}
