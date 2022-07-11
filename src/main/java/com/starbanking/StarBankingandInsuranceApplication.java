package com.starbanking;

import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import com.starbanking.Service.RolesAndPrivilegesService;
import com.starbanking.ServiceImpl.RolesAndPrivilegesServiceImpl;

import brave.sampler.Sampler;

@EnableDiscoveryClient
@SpringBootApplication
public class StarBankingandInsuranceApplication {

	private static final Logger logger = LoggerFactory.getLogger(StarBankingandInsuranceApplication.class);

	@Value("${server.port}")
	private String portno;

	private static final String dateTimeFormat = "dd-MM-yyyy HH:mm:ss";
	private static final String dateFormat = "dd-MM-yyyy";

	public static void main(String[] args) {
		logger.info("<---------StarBankingandInsurance Application Start------------>");
		SpringApplication.run(StarBankingandInsuranceApplication.class, args);
		logger.info("<---------StarBankingandInsurance Application End------------>");
	}

	@PostConstruct
	public void init() {
		logger.info("<---------StarBankingandInsurance Application Server Run On PortNo:------------>" + portno);
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
		return builder -> {
			builder.simpleDateFormat(dateFormat);
			builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
			builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
			builder.serializers(new ZonedDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
		};
	}

	@Bean
	public RolesAndPrivilegesService rolesAndPrivileges() {
		return new RolesAndPrivilegesServiceImpl();
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

	@PostConstruct
	public void setTimeZone() {
		// Setting Spring Boot SetTimeZone
		TimeZone.setDefault(TimeZone.getTimeZone("IST"));
	}

}
