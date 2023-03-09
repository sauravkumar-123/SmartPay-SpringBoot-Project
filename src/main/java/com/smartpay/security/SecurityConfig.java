package com.smartpay.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.smartpay.security.jwt.JWTTokenFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig /*extends WebSecurityConfigurerAdapter*/ {

    @Autowired
    private LoadUserDetails loadUserDetails;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // auth.authenticationProvider(null);
//        auth.userDetailsService(username -> loadUserDetails.loadUserByUsername(username));
//    
    @Bean
    public UserDetailsService userDetailsService() {
        return new LoadUserDetails();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().cors()
                .configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("*"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Arrays.asList("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }).and().csrf().disable();

        httpSecurity.authorizeHttpRequests().requestMatchers("/v1/user/saveUser", "/v1/admin/saveAdmin").permitAll().requestMatchers("/v1/auth/**").permitAll()
                .requestMatchers("/v1/admin/**").permitAll().requestMatchers("/v1/user/**").permitAll().requestMatchers("/swagger-ui.html#").permitAll().anyRequest()
                .authenticated().and().oauth2Login().and().exceptionHandling().accessDeniedPage("/403").and()
                .formLogin().permitAll().and().logout().invalidateHttpSession(true).clearAuthentication(true)
                .permitAll();

        httpSecurity.exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
        });

        httpSecurity.addFilterBefore(new JWTTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
        dap.setUserDetailsService(userDetailsService());
        dap.setPasswordEncoder(passwordEncoder());
        return dap;
    }

//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().anyRequest().authenticated().and().oauth2Login();
//	}
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
    // @default security configuration.
    // http.authorizeRequests().anyRequest().denyAll().and().formLogin().and().httpBasic();
//		// http.authorizeRequests().anyRequest().permitAll().and().formLogin().and().httpBasic();
//
/*        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().cors()
                .configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("*"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Arrays.asList("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }).and().csrf().disable();

        /*
		 * .addFilterBefore(new JWTTokenGeneratorFilter(),
		 * BasicAuthenticationFilter.class) .addFilterAfter(new
		 * JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
		 * .addFilterBefore(new AutherizationBeforeFilter(),
		 * BasicAuthenticationFilter.class). .addFilterAt(new AutherizationAtFilter(),
		 * BasicAuthenticationFilter.class) .addFilterAfter(new
		 * AutherizationAfterFilter(), BasicAuthenticationFilter.class)
		 * 
		 * 
     */
    // http.authorizeRequests().antMatchers("/v1/Oauth/**").permitAll().and().oauth2Login();
//        http.authorizeRequests().antMatchers("/v1/user/**").permitAll().antMatchers("/v1/auth/**").permitAll()
//                .antMatchers("/v1/admin/**").permitAll().antMatchers("/swagger-ui.html#").permitAll().anyRequest()
//                .authenticated().and().oauth2Login().and().exceptionHandling().accessDeniedPage("/403").and()
//                .formLogin().permitAll().and().logout().invalidateHttpSession(true).clearAuthentication(true)
//                .permitAll();
//
//        http.exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
//        });
//
//        http.addFilterBefore(new JWTTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
    /**
     * ************************************************************************************************************************************************************************************************************************************************************
     */
//	private ClientRegistration clientRegistration() {
//		return CommonOAuth2Provider.GOOGLE.getBuilder("google")
//				.clientId("65324304311-tvh4snq709m3abctjfs83g1arlu06t83.apps.googleusercontent.com")
//				.clientSecret("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").build();
//	}
//private ClientRegistration clientRegistration() {
//	ClientRegistration cr = ClientRegistration.withRegistrationId("github")
//			.clientId("65324304311-tvh4snq709m3abctjfs83g1arlu06t83.apps.googleusercontent.com")
//			.clientSecret("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").scope(new String[] { "read:user" })
//			.authorizationUri("https://accounts.google.com/o/oauth2/auth")
//			.tokenUri("https://oauth2.googleapis.com/token").userInfoUri("https://www.googleapis.com/oauth2/v1/certs")
//			.userNameAttributeName("id").clientName("Google")
//			.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//			.redirectUriTemplate("{baseUrl}/{action}/oauth2/code/{registrationId}").build();
//	return cr;
//}
//	@Bean
//	public ClientRegistrationRepository clientRepository() {
//		ClientRegistration clientReg = clientRegistration();
//		return new InMemoryClientRegistrationRepository(clientReg);
//	}
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
    /**
     * ***************************************************************************************************************************************************************************************************************************************************************************
     */
}
