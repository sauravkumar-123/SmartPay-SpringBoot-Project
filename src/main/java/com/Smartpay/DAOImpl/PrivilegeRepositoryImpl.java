package com.Smartpay.DAOImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.Smartpay.DAO.PrivilegeRepository;
import com.Smartpay.Model.Privileges;

@Repository
public class PrivilegeRepositoryImpl implements PrivilegeRepository {

	private static final Logger logger = LoggerFactory.getLogger(PrivilegeRepositoryImpl.class);

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public PrivilegeRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public PrivilegeRepositoryImpl() {

	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public void savePrivilege(Privileges privilege) {
		Session session = entityManager.unwrap(Session.class);
		session.save(privilege);
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public Privileges findPrivilegeByName(String privilegeName) {
		Session session = entityManager.unwrap(Session.class);
		String qry = "SELECT p FROM Privileges p WHERE p.privilegeName=:name";
		Query query = session.createQuery(qry);
		query.setParameter("name", privilegeName);
		logger.debug("Query " + query);
		Privileges privilege = (Privileges) query.uniqueResult();
		return privilege;
	}

}
