package com.smartpay.dao;

import org.springframework.stereotype.Repository;

import com.smartpay.model.Privileges;

@Repository
public interface PrivilegeRepository {

	public void savePrivilege(Privileges privilege);

	public Privileges findPrivilegeByName(String privilegeName);
}
