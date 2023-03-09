package com.smartpay.daoimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.smartpay.dao.MainWalletRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class MainWalletRepositoryImpl implements MainWalletRepository {

    private static final Logger logger = LoggerFactory.getLogger(MainWalletRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

}
