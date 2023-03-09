package com.smartpay.response;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OAuthResponse {

	private boolean isAuthenciated;
	private String clientRegestrationId;
	private Collection<GrantedAuthority> authorities;
	private Object credentials;
	private Object details;
	private String name;
	private OAuth2User principal;

}
