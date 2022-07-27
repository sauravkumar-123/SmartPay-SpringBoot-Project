package com.Smartpay.DAO;

import org.springframework.stereotype.Repository;

import com.Smartpay.Model.Role;

@Repository
public interface RoleRepository {

	public void saveRoles(Role role);

	public Role findRoleByName(String roleName);
}
