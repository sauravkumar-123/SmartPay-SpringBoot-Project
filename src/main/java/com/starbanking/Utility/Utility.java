package com.starbanking.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.starbanking.Exception.GlobalException;

public class Utility {

	private static final Logger logger = LoggerFactory.getLogger(Utility.class);

//	public static SessionFactory buildSessionFactory() {
//		try {
//			Configuration cfg = new Configuration();
//			cfg.configure();
//			SessionFactory factory = cfg.buildSessionFactory();
//			logger.info("Session Factory Object Created", factory);
//			return factory;
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new GlobalException("Falied To Create Session Factory Object");
//		}
//	}

	public static long generateRandomfiveDigitNo() {
		Random r = new Random(System.currentTimeMillis());
		return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
	}

	public static Date convertStringToDate(String inputDate) {
		Date date = null;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse(inputDate);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		return date;
	}
}
