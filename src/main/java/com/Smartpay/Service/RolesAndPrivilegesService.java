package com.Smartpay.Service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.Smartpay.Model.Privileges;
import com.Smartpay.Model.Role;

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