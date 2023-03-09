package com.smartpay.serviceimpl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartpay.dao.PrivilegeRepository;
import com.smartpay.dao.RoleRepository;
import com.smartpay.enums.EnumsStatus.UserPrivilege;
import com.smartpay.enums.EnumsStatus.UserRole;
import com.smartpay.model.Privileges;
import com.smartpay.model.Role;
import com.smartpay.service.RolesAndPrivilegesService;

@Service
public class RolesAndPrivilegesServiceImpl implements RolesAndPrivilegesService {

	private static final Logger logger = LoggerFactory.getLogger(RolesAndPrivilegesServiceImpl.class);

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Privileges createWritePrivileges() {
		logger.info("Enter into RolesAndPrivilegesService::createWritePrivileges()");
		String autheritie = UserPrivilege.CREATE.getPrivilegeName();
		Privileges privilege = privilegeRepository.findPrivilegeByName(autheritie);
		if (null == privilege) {
			logger.debug("Set CREATE Privilege");
			privilege = new Privileges();
			privilege.setPrivilegeName(autheritie);
			privilegeRepository.savePrivilege(privilege);
		}
		return privilege;
	}

	@Override
	public Privileges createReadPrivileges() {
		logger.info("Enter into RolesAndPrivilegesService::createReadPrivileges()");
		String autheritie = UserPrivilege.READ.getPrivilegeName();
		Privileges privilege = privilegeRepository.findPrivilegeByName(autheritie);
		if (null == privilege) {
			logger.debug("Set READ Privilege");
			privilege = new Privileges();
			privilege.setPrivilegeName(autheritie);
			privilegeRepository.savePrivilege(privilege);
		}
		return privilege;
	}

	@Override
	public Privileges createUpdatePrivileges() {
		logger.info("Enter into RolesAndPrivilegesService::createUpdatePrivileges()");
		String autheritie = UserPrivilege.UPDATE.getPrivilegeName();
		Privileges privilege = privilegeRepository.findPrivilegeByName(autheritie);
		if (null == privilege) {
			logger.debug("Set UPDATE Privilege");
			privilege = new Privileges();
			privilege.setPrivilegeName(autheritie);
			privilegeRepository.savePrivilege(privilege);
		}
		return privilege;
	}

	@Override
	public Privileges createDeletePrivileges() {
		logger.info("Enter into RolesAndPrivilegesService::createDeletePrivileges()");
		String autheritie = UserPrivilege.DELETE.getPrivilegeName();
		Privileges privilege = privilegeRepository.findPrivilegeByName(autheritie);
		if (null == privilege) {
			logger.debug("Set DELETE Privilege");
			privilege = new Privileges();
			privilege.setPrivilegeName(autheritie);
			privilegeRepository.savePrivilege(privilege);
		}
		return privilege;
	}

	@Override
	public Role createAdminRole(Set<Privileges> adminPrivilegesSet) {
		logger.info("Enter into RolesAndPrivilegesService::createAdminRole()");
		String roleName = UserRole.ADMIN.getRoleName();
		Role role = roleRepository.findRoleByName(roleName);
		if (null == role) {
			logger.debug("Set ADMIN role and its privileges");
			role = new Role();
			role.setRoleName(roleName);
			role.setPrivileges(adminPrivilegesSet);
			roleRepository.saveRoles(role);
		}
		return role;
	}

	@Override
	public Role createMerchantRole(Set<Privileges> merchantPrivilegesSet) {
		logger.info("Enter into RolesAndPrivilegesService::createMerchantRole()");
		String roleName = UserRole.MERCHANT.getRoleName();
		Role role = roleRepository.findRoleByName(roleName);
		if (null == role) {
			logger.debug("Set MERCHANT role and its privileges");
			role = new Role();
			role.setRoleName(roleName);
			role.setPrivileges(merchantPrivilegesSet);
			roleRepository.saveRoles(role);
		}
		return role;
	}

	@Override
	public Role createDistributorRole(Set<Privileges> distributorPrivilegesSet) {
		logger.info("Enter into RolesAndPrivilegesService::createDistributorRole()");
		String roleName = UserRole.DISTRIBUTOR.getRoleName();
		Role role = roleRepository.findRoleByName(roleName);
		if (null == role) {
			logger.debug("Set DISTRIBUTOR role and its privileges");
			role = new Role();
			role.setRoleName(roleName);
			role.setPrivileges(distributorPrivilegesSet);
			roleRepository.saveRoles(role);
		}
		return role;
	}

	@Override
	public Role createMasterDistributor(Set<Privileges> masterDistributorPrivilegesSet) {
		logger.info("Enter into RolesAndPrivilegesService::createMasterDistributor()");
		String roleName = UserRole.MASTERDISTRIBUTOR.getRoleName();
		Role role = roleRepository.findRoleByName(roleName);
		if (null == role) {
			logger.debug("Set MASTERDISTRIBUTOR role and its privileges");
			role = new Role();
			role.setRoleName(roleName);
			role.setPrivileges(masterDistributorPrivilegesSet);
			roleRepository.saveRoles(role);
		}
		return role;
	}
}
