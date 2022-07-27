package com.Smartpay.ServiceTest;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Smartpay.DAO.UserRepository;
import com.Smartpay.Enum.EnumsStatus.UserRole;
import com.Smartpay.Enum.EnumsStatus.YesNO;
import com.Smartpay.Model.User;
import com.Smartpay.Service.AdminService;
import com.Smartpay.Utility.StringUtil;
import com.Smartpay.Utility.Utility;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdminServiceMockTest {

	private static final Logger logger = LoggerFactory.getLogger(AdminServiceMockTest.class);

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private AdminService adminService;

//	@Autowired
//	private PasswordEncoder passwordEncoder;

//	@Autowired
//	private RoleRepository roleRepository;

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

		given(userRepository.getUserDetails(admin.getEmailid(), admin.getMobileno(), 'Y')).willReturn(null);
		Assertions.assertNotNull(adminService.registerAdmin(admin));
	}

	@AfterAll
	public void endSetUp() {
		logger.info("UserService MockTest SetUp Ended");
	}
}
