package com.Smartpay.ServiceImpl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Smartpay.DAO.PrivilegeRepository;
import com.Smartpay.DAO.RoleRepository;
import com.Smartpay.Enum.EnumsStatus.UserPrivilege;
import com.Smartpay.Enum.EnumsStatus.UserRole;
import com.Smartpay.Model.Privileges;
import com.Smartpay.Model.Role;
import com.Smartpay.Service.RolesAndPrivilegesService;

@Service
public class RolesAndPrivilegesServiceImpl implements RolesAndPrivilegesService {

	private static final Logger logger = LoggerFactory.getLogger(RolesAndPrivilegesServiceImpl.class);

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Privileges createWritePrivileges() {
		String autheritie = UserPrivilege.CREATE.getPrivilegeName();
		Privileges privilege = privilegeRepository.findPrivilegeByName(autheritie);
		if (null == privilege) {
			logger.info("Set CREATE Privilege");
			privilege = new Privileges();
			privilege.setPrivilegeName(autheritie);
			privilegeRepository.savePrivilege(privilege);
		}
		return privilege;
	}

	@Override
	public Privileges createReadPrivileges() {
		String autheritie = UserPrivilege.READ.getPrivilegeName();
		Privileges privilege = privilegeRepository.findPrivilegeByName(autheritie);
		if (null == privilege) {
			logger.info("Set READ Privilege");
			privilege = new Privileges();
			privilege.setPrivilegeName(autheritie);
			privilegeRepository.savePrivilege(privilege);
		}
		return privilege;
	}

	@Override
	public Privileges createUpdatePrivileges() {
		String autheritie = UserPrivilege.UPDATE.getPrivilegeName();
		Privileges privilege = privilegeRepository.findPrivilegeByName(autheritie);
		if (null == privilege) {
			logger.info("Set UPDATE Privilege");
			privilege = new Privileges();
			privilege.setPrivilegeName(autheritie);
			privilegeRepository.savePrivilege(privilege);
		}
		return privilege;
	}

	@Override
	public Privileges createDeletePrivileges() {
		String autheritie = UserPrivilege.DELETE.getPrivilegeName();
		Privileges privilege = privilegeRepository.findPrivilegeByName(autheritie);
		if (null == privilege) {
			logger.info("Set DELETE Privilege");
			privilege = new Privileges();
			privilege.setPrivilegeName(autheritie);
			privilegeRepository.savePrivilege(privilege);
		}
		return privilege;
	}

	@Override
	public Role createAdminRole(Set<Privileges> adminPrivilegesSet) {
		String roleName = UserRole.ADMIN.getRoleName();
		Role role = roleRepository.findRoleByName(roleName);
		if (null == role) {
			logger.info("Set ADMIN role and its privileges");
			role = new Role();
			role.setRoleName(roleName);
			role.setPrivileges(adminPrivilegesSet);
			roleRepository.saveRoles(role);
		}
		return role;
	}

	@Override
	public Role createMerchantRole(Set<Privileges> merchantPrivilegesSet) {
		String roleName = UserRole.MERCHANT.getRoleName();
		Role role = roleRepository.findRoleByName(roleName);
		if (null == role) {
			logger.info("Set MERCHANT role and its privileges");
			role = new Role();
			role.setRoleName(roleName);
			role.setPrivileges(merchantPrivilegesSet);
			roleRepository.saveRoles(role);
		}
		return role;
	}

	@Override
	public Role createDistributorRole(Set<Privileges> distributorPrivilegesSet) {
		String roleName = UserRole.DISTRIBUTOR.getRoleName();
		Role role = roleRepository.findRoleByName(roleName);
		if (null == role) {
			logger.info("Set DISTRIBUTOR role and its privileges");
			role = new Role();
			role.setRoleName(roleName);
			role.setPrivileges(distributorPrivilegesSet);
			roleRepository.saveRoles(role);
		}
		return role;
	}

	@Override
	public Role createMasterDistributor(Set<Privileges> masterDistributorPrivilegesSet) {
		String roleName = UserRole.MASTERDISTRIBUTOR.getRoleName();
		Role role = roleRepository.findRoleByName(roleName);
		if (null == role) {
			logger.info("Set MASTERDISTRIBUTOR role and its privileges");
			role = new Role();
			role.setRoleName(roleName);
			role.setPrivileges(masterDistributorPrivilegesSet);
			roleRepository.saveRoles(role);
		}
		return role;
	}
}
