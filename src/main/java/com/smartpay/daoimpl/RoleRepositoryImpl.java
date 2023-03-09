package com.smartpay.daoimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.smartpay.dao.RoleRepository;
import com.smartpay.model.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveRoles(Role role) {
        logger.info("Entred into RoleRepository::saveRoles()");
        //Session session = entityManager.unwrap(Session.class);
        //session.saveOrUpdate(role);
        entityManager.persist(role);
    }

    @Transactional(readOnly = true)
    @Override
    public Role findRoleByName(String roleName) {
        logger.info("Entred into RoleRepository::findRoleByName()");
        /*Session session = entityManager.unwrap(Session.class);
        String qry = "SELECT r FROM Role r WHERE r.roleName=:name";
        Query query = session.createQuery(qry);
        query.setParameter("name", roleName);
        logger.debug("Query " + query);
        Role role = (Role) query.uniqueResult();
        return role;*/
        Role role = null;
        try {
            role = entityManager.createQuery("SELECT r FROM Role r WHERE r.roleName=:name", Role.class)
                    .setParameter("name", roleName).getSingleResult();
            logger.debug("Query Result " + role);
        } catch (Exception ex) {
            logger.error("result from query " + ex.getMessage());
        }
        return role;
    }

}
