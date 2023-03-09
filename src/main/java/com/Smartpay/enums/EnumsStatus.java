package com.smartpay.enums;

public class EnumsStatus {

    public enum YesNO {
        NO, YES
    }

    public enum IsActive {
        ACTIVE, INACTIVE
    }

    public enum UserRole {

        MERCHANT(1, "ROLE_MERCHANT"), ADMIN(2, "ROLE_ADMIN"), DISTRIBUTOR(3, "ROLE_DISTRIBUTOR"),
        MASTERDISTRIBUTOR(4, "ROLE_MASTERDISTRIBUTOR");

        private final Integer roleId;
        private final String roleName;

        private UserRole(Integer roleid, String rolename) {
            this.roleId = roleid;
            this.roleName = rolename;
        }

        public Integer getRoleId() {
            return roleId;
        }

        public String getRoleName() {
            return roleName;
        }
    }

    public enum UserPrivilege {
        CREATE(1, "CREATE"), READ(2, "READ"), UPDATE(3, "UPDATE"), DELETE(4, "DELETE");

        private final Integer privilegeId;
        private final String privilegeName;

        private UserPrivilege(Integer privilegeId, String privilegeName) {
            this.privilegeId = privilegeId;
            this.privilegeName = privilegeName;
        }

        public Integer getPrivilegeId() {
            return privilegeId;
        }

        public String getPrivilegeName() {
            return privilegeName;
        }

    }

    public enum MaritalStatus {
        UNMARRIED, MARRIED, WIDOW, WIDOWER
    }

    public enum Gender {
        MALE, FEMALE, TRANSGENDER
    }

    public enum BusinessType {
        INDIVIDUAL, SOLEPROPRIETORSHIP, PARTNERSHIPFIRM
    }

    public enum AccountType {
        CURRENT, SAVING
    }

    public enum AddressType {
        CURRENTADDRESS, PERMANENTADDRESS
    }

    public enum DocumentsUploadStatus {
        SUCCESS, PENDING, FAILED
    }
}
