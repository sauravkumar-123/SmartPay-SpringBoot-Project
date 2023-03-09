package com.smartpay.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.smartpay.model.Privileges;
import com.smartpay.model.Role;

@Service
public interface RolesAndPrivilegesService {

	public Privileges createWritePrivileges();

	public Privileges createReadPrivileges();

	public Privileges createUpdatePrivileges();

	public Privileges createDeletePrivileges();

	public Role createAdminRole(Set<Privileges> adminPrivilegesSet);

	public Role createMerchantRole(Set<Privileges> merchantPrivilegesSet);

	public Role createDistributorRole(Set<Privileges> distributorPrivilegesSet);

	public Role createMasterDistributor(Set<Privileges> masterDistributorPrivilegesSet);

}
