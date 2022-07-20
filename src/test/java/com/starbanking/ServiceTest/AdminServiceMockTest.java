package com.starbanking.ServiceTest;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.starbanking.Enum.EnumsStatus.UserRole;
import com.starbanking.Enum.EnumsStatus.YesNO;
import com.starbanking.Model.User;
import com.starbanking.Service.AdminService;
import com.starbanking.Utility.StringUtil;
import com.starbanking.Utility.Utility;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdminServiceMockTest {

	private static final Logger logger = LoggerFactory.getLogger(AdminServiceMockTest.class);

//	@Autowired
//	private PasswordEncoder passwordEncoder;

//	@Autowired
//	private RoleRepository roleRepository;

	@Mock
	private AdminService adminService;

	@DisplayName("Test registerAdminDetails method")
	@Test
	public void testRegisterAdmin() {
		logger.info("RegisterAdmin");
		User admin = new User();
//		Role merchantRole = roleRepository.findRoleByName(UserRole.ADMIN.getRoleName());
//		List<Role> roleList = Arrays.asList(merchantRole);
		admin.setApplicantName("Nirmal Kumar Aalnkar");
		admin.setEmailid("nirmal.kumar045@gmail.com");
		admin.setMobileno("6520102360");
		admin.setDateOfBirth(Utility.convertStringToDate("11-06-1974"));
		admin.setBankingServiceStatus(YesNO.NO);
		admin.setIsActive('Y');
		admin.setRole(UserRole.ADMIN.getRoleName());
//		admin.setRoles(roleList.stream().collect(Collectors.toSet()));
//		admin.setPassword(passwordEncoder.encode(StringUtil.generateDefaultPassword(admin.getApplicantName())));
		admin.setUserIdentificationNo(Utility.generateRandomfiveDigitNo());
		admin.setUsername("AD" + StringUtil.getLastSixDigitOfMobileNo(admin.getMobileno()));
		admin.setParentUsername("Parents Details Not Avaliable");

		given(adminService.registerAdmin(admin)).willReturn(admin);
		Assertions.assertNotNull(adminService.registerAdmin(admin));
	}
	
	@AfterAll
	public void endSetUp() {
		logger.info("UserService MockTest SetUp Ended");
	}
}