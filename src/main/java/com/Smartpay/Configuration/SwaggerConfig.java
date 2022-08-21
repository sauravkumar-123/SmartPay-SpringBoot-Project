package com.Smartpay.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@Configuration
@EnableSwagger2
@ConfigurationProperties("app.api")
@ConditionalOnProperty(name = "app.api.swagger.enable", havingValue = "true", matchIfMissing = false)
public class SwaggerConfig {

	public static final Contact DEFAULT_CONTACT = new Contact("StarBanking", "http://localhost:8080/v1/starbankingAPI",
			"saurav.tit@gmail.com");

	public static final ApiInfo API_DEFAULT = new ApiInfo("StarBanking Api Documentation",
			"API Documentation Of StarBanking", "1.0", "urn:tos", DEFAULT_CONTACT, "StarBankingAPI 2.0",
			"www.starbanking.com", new ArrayList<VendorExtension>());

	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(
			Arrays.asList("application/json", "application/xml"));

	// @ http://localhost:8080/swagger-ui.html#
	// @ http://localhost:8080/v2/api-docs
	@Bean
	public Docket swagerAPI() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.Smartpay.Controller")).paths(PathSelectors.any()).build()
				.directModelSubstitute(LocalDate.class, java.sql.Date.class)
				.directModelSubstitute(LocalDateTime.class, java.util.Date.class).apiInfo(API_DEFAULT)
				.produces(DEFAULT_PRODUCES_AND_CONSUMES).consumes(DEFAULT_PRODUCES_AND_CONSUMES);
	}
}
