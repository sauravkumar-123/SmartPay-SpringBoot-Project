package com.starbanking.DAOImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

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

import com.starbanking.DAO.UserRepository;
import com.starbanking.Exception.GlobalException;
import com.starbanking.Exception.ResourceNotFoundException;
import com.starbanking.Model.MainWallet;
import com.starbanking.Model.User;

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
			session.save(user);
			session.save(mainWallet);
			transaction.commit();
			status = true;
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
			logger.info("Exception Message{} " + e.getMessage());
			throw new GlobalException("Unbale To Save User Details");
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
			criteria.add(Restrictions.ilike("Username", "AD%"));
			criteria.add(Restrictions.eq("isActive", 'Y'));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.alias(Projections.property("user.UserIdentificationNo"), "UserIdentificationNo"))
					.add(Projections.alias(Projections.property("user.Username"), "Username"))
					.add(Projections.alias(Projections.property("user.applicantName"), "applicantName"))
					.add(Projections.alias(Projections.property("user.mobileno"), "mobileno"))
					.add(Projections.alias(Projections.property("user.emailid"), "emailid"))
					.add(Projections.alias(Projections.property("user.role"), "role"))
					.add(Projections.alias(Projections.property("user.parentUsername"), "parentUsername"))
					.add(Projections.alias(Projections.property("user.dateOfBirth"), "dateOfBirth"))
					.add(Projections.alias(Projections.property("user.isActive"), "isActive"))
					.add(Projections.alias(Projections.property("user.bankingServiceStatus"), "bankingServiceStatus"))
					.add(Projections.alias(Projections.property("user.createdDate"), "createdDate"))
					.add(Projections.alias(Projections.property("user.updatedDate"), "updatedDate")));
			criteria.setResultTransformer(Transformers.aliasToBean(User.class));
			User userdata = (User) criteria.uniqueResult();
			logger.info("Admin Data{} " + userdata);
			transaction.commit();
			return userdata;
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
			logger.info("Exception Message{} " + e.getMessage());
			throw new GlobalException("Unbale To Fetch Admin Details");
		}
	}

	@Override
	public User getUserDetails(String emailId, String mobno, char isActive) {
		Session session = entityManager.unwrap(Session.class);
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(User.class, "user");
			if (null != emailId && null != mobno && 'N' != isActive) {
				Criterion emailAdd = Restrictions.eq("emailid", emailId);
				Criterion mobileNumber = Restrictions.eq("mobileno", mobno);
				Criterion status = Restrictions.eq("isActive", isActive);

				criteria.add(Restrictions.or(emailAdd, mobileNumber));
				criteria.add(Restrictions.and(status));
			}
			criteria.setProjection(Projections.projectionList()
					.add(Projections.alias(Projections.property("user.UserIdentificationNo"), "UserIdentificationNo"))
					.add(Projections.alias(Projections.property("user.Username"), "Username"))
					.add(Projections.alias(Projections.property("user.applicantName"), "applicantName"))
					.add(Projections.alias(Projections.property("user.mobileno"), "mobileno"))
					.add(Projections.alias(Projections.property("user.emailid"), "emailid"))
					.add(Projections.alias(Projections.property("user.role"), "role"))
					.add(Projections.alias(Projections.property("user.parentUsername"), "parentUsername"))
					.add(Projections.alias(Projections.property("user.dateOfBirth"), "dateOfBirth"))
					.add(Projections.alias(Projections.property("user.isActive"), "isActive"))
					.add(Projections.alias(Projections.property("user.bankingServiceStatus"), "bankingServiceStatus"))
					.add(Projections.alias(Projections.property("user.createdDate"), "createdDate"))
					.add(Projections.alias(Projections.property("user.updatedDate"), "updatedDate")));
			criteria.setResultTransformer(Transformers.aliasToBean(User.class));
			User userdata = (User) criteria.uniqueResult();
			logger.info("User Data{} " + userdata);
			transaction.commit();
			return userdata;
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
			logger.info("Exception Message{} " + e.getMessage());
			throw new GlobalException("Unbale To Fetch User Details");
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
			if (null != username && !username.isEmpty() && !username.isBlank()) {
				criteria.add(Restrictions.and(Restrictions.eq("Username", username), Restrictions.eq("isActive", 'Y')));
			} else {
				throw new ResourceNotFoundException("Invalid Username!!!");
			}
			criteria.setProjection(Projections.projectionList()
					.add(Projections.alias(Projections.property("user.UserIdentificationNo"), "UserIdentificationNo"))
					.add(Projections.alias(Projections.property("user.Username"), "Username"))
					.add(Projections.alias(Projections.property("user.applicantName"), "applicantName"))
					.add(Projections.alias(Projections.property("user.mobileno"), "mobileno"))
					.add(Projections.alias(Projections.property("user.emailid"), "emailid"))
					.add(Projections.alias(Projections.property("user.role"), "role"))
					.add(Projections.alias(Projections.property("user.password"), "password"))
					.add(Projections.alias(Projections.property("user.parentUsername"), "parentUsername"))
					.add(Projections.alias(Projections.property("user.dateOfBirth"), "dateOfBirth"))
					.add(Projections.alias(Projections.property("user.isActive"), "isActive"))
					.add(Projections.alias(Projections.property("user.bankingServiceStatus"), "bankingServiceStatus"))
					.add(Projections.alias(Projections.property("user.createdDate"), "createdDate"))
					.add(Projections.alias(Projections.property("user.updatedDate"), "updatedDate")));
			criteria.setResultTransformer(Transformers.aliasToBean(User.class));
			User userdata = (User) criteria.uniqueResult();
			logger.info("User Data{} " + userdata);
			transaction.commit();
			return userdata;
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
			logger.info("Exception Message{} " + e.getMessage());
			throw new GlobalException("Unbale To Fetch User Details");
		}
//		finally {
//			session.close();
//		}
	}

}
