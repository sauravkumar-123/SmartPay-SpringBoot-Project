package com.smartpay.security;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.smartpay.model.Privileges;
import com.smartpay.model.Role;
import com.smartpay.service.RolesAndPrivilegesService;
import jakarta.transaction.Transactional;

@Component
public class RolePrivilegeConfig implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private RolesAndPrivilegesService rolesAndPrivileges;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup) {
            return;
        }

        Privileges writePrivileges = rolesAndPrivileges.createWritePrivileges();
        Privileges readPrivileges = rolesAndPrivileges.createReadPrivileges();
        Privileges updatePrivileges = rolesAndPrivileges.createUpdatePrivileges();
        Privileges deletePrivileges = rolesAndPrivileges.createDeletePrivileges();

        List<Privileges> adminPrivilegesList = Arrays.asList(writePrivileges, readPrivileges, updatePrivileges,
                deletePrivileges);
        Set<Privileges> adminPrivilegesSet = adminPrivilegesList.stream().collect(Collectors.toSet());

        List<Privileges> merchantPrivilegesList = Arrays.asList(readPrivileges, updatePrivileges);
        Set<Privileges> merchantPrivilegesSet = merchantPrivilegesList.stream().collect(Collectors.toSet());

        List<Privileges> distributorPrivilegesList = Arrays.asList(writePrivileges, readPrivileges, updatePrivileges);
        Set<Privileges> distributorPrivilegesSet = distributorPrivilegesList.stream().collect(Collectors.toSet());

        List<Privileges> masterDistributorPrivilegesList = Arrays.asList(writePrivileges, readPrivileges,
                updatePrivileges);
        Set<Privileges> masterDistributorPrivilegesSet = masterDistributorPrivilegesList.stream()
                .collect(Collectors.toSet());

        Role adminRole = rolesAndPrivileges.createAdminRole(adminPrivilegesSet);
        Role merchantRole = rolesAndPrivileges.createMerchantRole(merchantPrivilegesSet);
        Role distributorRole = rolesAndPrivileges.createDistributorRole(distributorPrivilegesSet);
        Role masterdistributorRole = rolesAndPrivileges.createMasterDistributor(masterDistributorPrivilegesSet);

        alreadySetup = true;
    }

}
