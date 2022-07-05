package com.starbanking.DAO;

import org.springframework.stereotype.Repository;

import com.starbanking.Model.Role;

@Repository
public interface RoleRepository {

	public void saveRoles(Role role);

	public Role findRoleByName(String roleName);
}
