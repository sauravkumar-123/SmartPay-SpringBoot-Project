package com.Smartpay.Security;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// @default security configuration.

		// http.authorizeRequests().anyRequest().denyAll().and().formLogin().and().httpBasic();
		// http.authorizeRequests().anyRequest().permitAll().and().formLogin().and().httpBasic();

		http.cors().configurationSource(new CorsConfigurationSource() {
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedOrigins(Collections.singletonList("*"));
				config.setAllowedMethods(Collections.singletonList("*"));
				config.setAllowCredentials(true);
				config.setAllowedHeaders(Collections.singletonList("*"));
				config.setMaxAge(3600L);
				return config;
			}
		}).and().csrf().disable().authorizeRequests().antMatchers("/v1/register/**").permitAll()
				.antMatchers("/v1/user/**").authenticated().antMatchers("/swagger-ui.html#").permitAll().and()
				.formLogin().permitAll().and().logout().invalidateHttpSession(true).clearAuthentication(true)
				.permitAll();
	}

//@ InMemeoryAuthencation with passwordEncode.

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("admin").password("12345").authorities("admin").and().withUser("user")
//				.password("12345").authorities("read").and().passwordEncoder(NoOpPasswordEncoder.getInstance());
//	}

//@ Create user using InMemoryUserDetailsManager which is managed by container.	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		InMemoryUserDetailsManager userDetailsManager=new InMemoryUserDetailsManager();
//		UserDetails user1= User.withUsername("admin").password("12345").authorities("admin").build();
//		UserDetails user2= User.withUsername("user").password("12345").authorities("read").build();
//		
//		userDetailsManager.createUser(user1);
//		userDetailsManager.createUser(user2);
//		auth.userDetailsService(userDetailsManager);
//	}

//@ Userdetails get using default JdbcUserDetailsManager; (Not Recommended)
//	@Bean
//	public UserDetailsService userDetailsService(DataSource dataSource) {
//		return new JdbcUserDetailsManager(dataSource);
//	}

//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
}
