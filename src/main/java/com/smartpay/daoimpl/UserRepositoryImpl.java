package com.smartpay.daoimpl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.smartpay.dao.UserRepository;
import com.smartpay.enums.EnumsStatus;
import com.smartpay.enums.EnumsStatus.IsActive;
import com.smartpay.exception.GlobalException;
import com.smartpay.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.ws.rs.BadRequestException;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public User saveUserDetails(User user) {
        logger.info("Entred into UserRepository::saveUserDetails()");
        try {
            User savedResult = entityManager.merge(user);
            logger.debug("user saved data {} ", savedResult);
            return savedResult;
        } catch (Exception e) {
            logger.error("Exception {} ", e);
            throw new GlobalException("Unable To Save User Details!! Error:: " + e.getMessage());
        }

    }

    @Transactional(readOnly = true)
    @Override
    public User getAdminDetails() {
        logger.info("Entred into UserRepository::getAdminDetails()");
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> root = criteria.from(User.class);
            criteria.multiselect(root.get("userIdentificationNo").alias("userIdentificationNo"),
                    root.get("username").alias("username"));
            criteria.where(
                    builder.and(
                            builder.equal(root.get("isActive"), IsActive.ACTIVE),
                            builder.like(root.get("username"), "AD%")
                    )
            );
            List<User> result = entityManager.createQuery(criteria).getResultList();
            User admindata = (!result.isEmpty() && result.get(0) != null) ? result.get(0) : null;
            logger.debug("Admin Details{} ", admindata);
            return admindata;
        } catch (Exception e) {
            logger.error("Exception {} ", e);
            throw new GlobalException("Unbale To Fetch Admin Details");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserDetailsByEmailOrMobno(String emailId, String mobNo) {
        logger.info("Enter into UserRepository::getUserDetails()");
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> root = criteria.from(User.class);
            Predicate emailOrMobnoRestriction = null;
            Predicate isActiveRestriction = null;
            if (StringUtils.isNotBlank(emailId) && StringUtils.isNotBlank(mobNo)) {
                emailOrMobnoRestriction = builder.or(
                        builder.equal(root.get("emailId"), emailId),
                        builder.equal(root.get("mobileNo"), mobNo)
                );
                isActiveRestriction = builder.and(
                        builder.equal(root.get("isActive"), IsActive.ACTIVE)
                );
            } else {
                throw new BadRequestException("Invalid Email Id Or Mobile No");
            }
            criteria.multiselect(root.get("userIdentificationNo").alias("userIdentificationNo"),
                    root.get("customerId").alias("customerId"),
                    root.get("username").alias("username"),
                    root.get("role").alias("role"));
            //                   root.get("updatedDate").alias("updatedDate"),
            //                   root.get("createdDate").alias("createdDate"));
            criteria.where(emailOrMobnoRestriction, isActiveRestriction);
            List<User> result = entityManager.createQuery(criteria).getResultList();
            User userdata = (!result.isEmpty() && result.get(0) != null) ? result.get(0) : null;
            logger.debug("User Data{} ", userdata);
            return userdata;
        } catch (BadRequestException e) {
            logger.error("Exception {} ", e);
            throw new BadRequestException(e.getMessage());
        } catch (Exception e) {
            logger.error("Exception {} ", e);
            throw new GlobalException("Unable To Fetch User Details");
        }

    }

    @Transactional(readOnly = true)
    @Override
    public User findUserByUsername(String username) {
        logger.info("Entred into UserRepository::findUserByUsername()");
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> root = criteria.from(User.class);
            Predicate usernameRestriction = null;
            if (StringUtils.isNotBlank(username)) {
                usernameRestriction = builder.and(
                        builder.equal(root.get("username"), username),
                        builder.equal(root.get("isActive"), IsActive.ACTIVE)
                );
            } else {
                throw new BadRequestException("Invalid Username!!!");
            }
            criteria.multiselect(root.get("userIdentificationNo").alias("userIdentificationNo"),
                    root.get("customerId").alias("customerId"),
                    root.get("username").alias("username"),
                    root.get("firstName").alias("firstName"),
                    root.get("middleName").alias("middleName"),
                    root.get("lastName").alias("lastName"),
                    root.get("mobileNo").alias("mobileNo"),
                    root.get("emailId").alias("emailId"),
                    root.get("role").alias("role"),
                    root.get("parentUsername").alias("parentUsername"),
                    root.get("dateOfBirth").alias("dateOfBirth"),
                    root.get("isActive").alias("isActive"),
                    root.get("bankingServiceStatus").alias("bankingServiceStatus"),
                    root.get("password").alias("password")).where(usernameRestriction);
            List<User> result = entityManager.createQuery(criteria).getResultList();
            User userdata = (!result.isEmpty() && result.get(0) != null) ? result.get(0) : null;
            logger.debug("User Data{} ", userdata);
            return userdata;
        } catch (BadRequestException e) {
            logger.error("Exception {} ", e);
            throw new BadRequestException(e.getMessage());
        } catch (Exception e) {
            logger.error("Exception {} ", e);
            throw new GlobalException("Unable To Fetch User Details");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBankingServiceStatus(String userIdNo, EnumsStatus.YesNO status) {
        logger.info("Enter into UserRepository::updateBankingServiceStatus()");
        entityManager.createQuery("UPDATE User u SET u.bankingServiceStatus=:bankingStatus WHERE u.userIdentificationNo=:idNo AND u.isActive=:activeStatus")
                .setParameter("bankingStatus", status)
                .setParameter("idNo", userIdNo)
                .setParameter("activeStatus", IsActive.ACTIVE).executeUpdate();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUserLoginPassword(String userIdNo, String pwd) {
        logger.info("Enter into UserRepository::updateUserLoginPassword()");
        boolean result = false;
        int row = entityManager.createQuery("UPDATE User u SET u.password=:pswd WHERE u.userIdentificationNo=:idNo AND u.isActive=:activeStatus")
                .setParameter("pswd", pwd)
                .setParameter("idNo", userIdNo)
                .setParameter("activeStatus", IsActive.ACTIVE).executeUpdate();
        if (row > 0) {
            result = true;
        }
        return result;
    }

}
