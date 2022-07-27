package com.Smartpay.DAO;

import org.springframework.stereotype.Repository;

import com.Smartpay.Model.Privileges;

@Repository
public interface PrivilegeRepository {

	public void savePrivilege(Privileges privilege);

	public Privileges findPrivilegeByName(String privilegeName);
}
