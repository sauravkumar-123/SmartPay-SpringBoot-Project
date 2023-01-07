package com.Smartpay;

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
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.client.RestTemplate;

import com.Smartpay.Service.RolesAndPrivilegesService;
import com.Smartpay.ServiceImpl.RolesAndPrivilegesServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;

import brave.sampler.Sampler;

@EnableDiscoveryClient
@SpringBootApplication
@EnableWebSecurity(debug = true)
public class SmartPayApplication {

	private static final Logger logger = LoggerFactory.getLogger(SmartPayApplication.class);

	@Value("${server.port}")
	private String portno;

	@Value("${app.message}")
	private String appMessage;

	@Value("${spring.profiles.active}")
	private String profile;

	private static final String dateTimeFormat = "dd-MM-yyyy HH:mm:ss";
	private static final String dateFormat = "dd-MM-yyyy";

	public static void main(String[] args) {
		logger.info("<---------SmartPay Application Start------------>");
		SpringApplication.run(SmartPayApplication.class, args);
		logger.info("<---------SmartPay Application End------------>");
	}

	@PostConstruct
	public void init() {
		logger.info("<---------StarBankingandInsurance Application Server Run On PortNo:------------>" + portno);
	}

	@Profile(value = { "dev", "test", "uat", "prod" })
	@Bean
	public void getEnvironmentConfig() {
		logger.info("<--- " + appMessage + " --->");
		logger.info(
				"<---------SmartPay Application Run On " + profile + " environment and PortNo:------------>" + portno);
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
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

//	@Bean(value = "jwtValid")
//	public JWTokenValadation jwtValidation() {
//		return new JWTokenValadation();
//	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@PostConstruct
	public void setTimeZone() {
		// Setting Spring Boot SetTimeZone
		TimeZone.setDefault(TimeZone.getTimeZone("IST"));
	}

}
