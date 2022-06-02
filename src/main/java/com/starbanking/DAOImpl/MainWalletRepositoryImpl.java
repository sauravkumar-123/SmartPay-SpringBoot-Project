package com.starbanking.DAOImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.starbanking.DAO.MainWalletRepository;

@Repository
public class MainWalletRepositoryImpl implements MainWalletRepository {

	private static final Logger logger = LoggerFactory.getLogger(MainWalletRepositoryImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

}
