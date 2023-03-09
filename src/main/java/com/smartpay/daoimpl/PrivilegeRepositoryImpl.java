package com.smartpay.daoimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.smartpay.dao.PrivilegeRepository;
import com.smartpay.model.Privileges;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void savePrivilege(Privileges privilege) {
        logger.info("Entred into PrivilegeRepository::savePrivilege()");
        // Session session = entityManager.unwrap(Session.class);
        // session.saveOrUpdate(privilege);
        entityManager.persist(privilege);
    }

    @Transactional(readOnly = true)
    @Override
    public Privileges findPrivilegeByName(String privilegeName) {
        logger.info("Entred into PrivilegeRepository::findPrivilegeByName()");
        /*Session session = entityManager.unwrap(Session.class);
        String qry = "SELECT p FROM Privileges p WHERE p.privilegeName=:name";
        Query query = session.createQuery(qry);
        query.setParameter("name", privilegeName);
        logger.debug("Query " + query);
        Privileges privilege = (Privileges) query.uniqueResult();
        return privilege;*/
        Privileges privilege = null;
        try {
            privilege = entityManager.createQuery("SELECT p FROM Privileges p WHERE p.privilegeName=:name", Privileges.class)
                    .setParameter("name", privilegeName).getSingleResult();
            logger.debug("Query Result " + privilege);
        } catch (Exception ex) {
            logger.error("result from query " + ex.getMessage());
        }
        return privilege;
    }

}
