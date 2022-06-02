package com.starbanking.Enum;

public class EnumsStatus {

	public enum YesNO {
		NO, YES
	}

	public enum UserRole {

		MERCHANT(1, "merchant"), ADMIN(2, "admin"), DISTRIBUTOR(3, "distributor"),
		MASTERDISTRIBUTOR(4, "masterdistributor");

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
}
