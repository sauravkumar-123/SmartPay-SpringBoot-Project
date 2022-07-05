package com.starbanking.Security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.starbanking.Model.Role;
import com.starbanking.Model.User;

public class UserSecurity implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final User user;

	public UserSecurity(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = user.getRoles();
		List<GrantedAuthority> authorities = new ArrayList<>();
		roles.stream().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		if (user.getIsActive() == 'Y') {
			return true;
		} else {
			return false;
		}
	}

}
