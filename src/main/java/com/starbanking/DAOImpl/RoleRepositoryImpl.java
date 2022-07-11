package com.starbanking.DAOImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.starbanking.DAO.RoleRepository;
import com.starbanking.Model.Role;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

	private static final Logger logger = LoggerFactory.getLogger(RoleRepositoryImpl.class);

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public RoleRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public RoleRepositoryImpl() {

	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public void saveRoles(Role role) {
		Session session = entityManager.unwrap(Session.class);
		session.save(role);
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public Role findRoleByName(String roleName) {
		Session session = entityManager.unwrap(Session.class);
		String qry = "SELECT r FROM Role r WHERE r.name=:roleName";
		Query query = session.createQuery(qry);
		query.setParameter("roleName", roleName);
		Role role = (Role) query.uniqueResult();
		return role;
	}

}
