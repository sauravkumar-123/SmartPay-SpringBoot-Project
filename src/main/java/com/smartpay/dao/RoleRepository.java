package com.smartpay.dao;

import org.springframework.stereotype.Repository;

import com.smartpay.model.Role;

@Repository
public interface RoleRepository {

	public void saveRoles(Role role);

	public Role findRoleByName(String roleName);
}
