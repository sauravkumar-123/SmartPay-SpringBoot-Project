package com.Smartpay.DAOImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.ws.rs.BadRequestException;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.Smartpay.DAO.UserRepository;
import com.Smartpay.Enum.EnumsStatus.IsActive;
import com.Smartpay.Exception.GlobalException;
import com.Smartpay.Model.MainWallet;
import com.Smartpay.Model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public UserRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public UserRepositoryImpl() {

	}

	@Override
	public boolean saveUserDetails(User user, MainWallet mainWallet) {
		Session session = entityManager.unwrap(Session.class);
		Transaction transaction = null;
		boolean status = false;
		try {
			transaction = session.beginTransaction();
			session.merge(user);
			session.save(mainWallet);
			transaction.commit();
			status = true;
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			logger.debug("Exception Message{} " + e.getMessage());
			throw new GlobalException("Unable To Save User Details");
		}
//		finally {
//		  session.close();
//		}
		return status;
	}

	@Override
	public User getAdminDetails() {
		Session session = entityManager.unwrap(Session.class);
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(User.class, "user");
			criteria.add(Restrictions.ilike("user.username", "AD%"));
			criteria.add(Restrictions.eq("user.isActive", IsActive.ACTIVE));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.alias(Projections.property("user.userIdentificationNo"), "userIdentificationNo"))
					.add(Projections.alias(Projections.property("user.customerId"), "customerId"))
					.add(Projections.alias(Projections.property("user.username"), "username"))
					.add(Projections.alias(Projections.property("user.applicantName"), "applicantName"))
					.add(Projections.alias(Projections.property("user.role"), "role"))
					.add(Projections.alias(Projections.property("user.createdDate"), "createdDate")));
			criteria.setResultTransformer(Transformers.aliasToBean(User.class));
			User userdata = (User) criteria.uniqueResult();
			logger.info("Admin Details{} " + userdata);
			transaction.commit();
			return userdata;
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			logger.debug("Exception Message{} " + e.getMessage());
			throw new GlobalException("Unbale To Fetch Admin Details");
		}
	}

	@Override
	public User getUserDetails(String emailId, String mobNo) {
		Session session = entityManager.unwrap(Session.class);
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(User.class, "user");
			if (StringUtils.isNotEmpty(emailId) && StringUtils.isNotEmpty(mobNo)) {
				Criterion emailAdd = Restrictions.eq("user.emailId", emailId);
				Criterion mobileNumber = Restrictions.eq("user.mobileNo", mobNo);
				Criterion status = Restrictions.eq("user.isActive", IsActive.ACTIVE);

				criteria.add(Restrictions.or(emailAdd, mobileNumber));
				criteria.add(Restrictions.and(status));
			} else {
				throw new BadRequestException("Invalid Parameter Email Id Or Mobile No");
			}
			criteria.setProjection(Projections.projectionList()
					.add(Projections.alias(Projections.property("user.userIdentificationNo"), "userIdentificationNo"))
					.add(Projections.alias(Projections.property("user.customerId"), "customerId"))
					.add(Projections.alias(Projections.property("user.username"), "username"))
					.add(Projections.alias(Projections.property("user.applicantName"), "applicantName"))
					.add(Projections.alias(Projections.property("user.role"), "role"))
					.add(Projections.alias(Projections.property("user.createdDate"), "createdDate")));
			criteria.setResultTransformer(Transformers.aliasToBean(User.class));
			User userdata = (User) criteria.uniqueResult();
			logger.info("User Data{} " + userdata);
			transaction.commit();
			return userdata;
		} catch (BadRequestException e) {
			if (transaction != null)
				transaction.rollback();
			logger.debug("Exception Message{} " + e.getMessage());
			throw new BadRequestException(e.getMessage());
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			logger.debug("Exception Message{} " + e.getMessage());
			throw new GlobalException("Unable To Fetch User Details");
		}
//		finally {
//			session.close();
//		}
	}

	@Override
	public User findUserByUsername(String username) {
		Session session = entityManager.unwrap(Session.class);
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(User.class, "user");
			if (StringUtils.isNotEmpty(username)) {
				criteria.add(Restrictions.and(Restrictions.eq("user.username", username),
						Restrictions.eq("user.isActive", IsActive.ACTIVE)));
			} else {
				throw new BadRequestException("Invalid Username!!!");
			}
			criteria.setProjection(Projections.projectionList()
					.add(Projections.alias(Projections.property("user.userIdentificationNo"), "userIdentificationNo"))
					.add(Projections.alias(Projections.property("user.customerId"), "customerId"))
					.add(Projections.alias(Projections.property("user.username"), "username"))
					.add(Projections.alias(Projections.property("user.applicantName"), "applicantName"))
					.add(Projections.alias(Projections.property("user.mobileNo"), "mobileNo"))
					.add(Projections.alias(Projections.property("user.emailId"), "emailId"))
					.add(Projections.alias(Projections.property("user.role"), "role"))
					.add(Projections.alias(Projections.property("user.password"), "password"))
					.add(Projections.alias(Projections.property("user.parentUsername"), "parentUsername"))
					.add(Projections.alias(Projections.property("user.dateOfBirth"), "dateOfBirth"))
					.add(Projections.alias(Projections.property("user.createdDate"), "createdDate")));
			criteria.setResultTransformer(Transformers.aliasToBean(User.class));
			User userdata = (User) criteria.uniqueResult();
			logger.info("User Data{} " + userdata);
			transaction.commit();
			return userdata;
		} catch (BadRequestException e) {
			if (transaction != null)
				transaction.rollback();
			logger.debug("Exception Message{} " + e.getMessage());
			throw new BadRequestException(e.getMessage());
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			logger.debug("Exception Message{} " + e.getMessage());
			throw new GlobalException("Unable To Fetch User Details");
		}
//		finally {
//			session.close();
//		}
	}

}
