package com.starbanking.DAO;

import org.springframework.stereotype.Repository;

import com.starbanking.Model.Privileges;

@Repository
public interface PrivilegeRepository {

	public void savePrivilege(Privileges privilege);

	public Privileges findPrivilegeByName(String privilegeName);
}
