package com.starbanking.Model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starbanking.Constants.Constant;
import com.starbanking.Enum.EnumsStatus.YesNO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "User")
@Entity
public class User extends BaseEntity {

	@Id
	@Column(name = "UserIdentificationNo")
	private Long UserIdentificationNo;

//	@OneToOne(mappedBy = "users", fetch = FetchType.LAZY)
//	private Merchant merchant;

	@Column(name = "Username", length = 10)
	private String Username;

	@NotNull(message = "Invalid Applicant Name")
	@NotBlank(message = "Invalid Applicant Name")
	@NotEmpty(message = "Invalid Applicant Name")
	@Size(min = 1, max = 70, message = "Minimum 1 Or Maximum 70 Character are Allowed")
	@Column(name = "Applicant_Name", length = 70)
	private String applicantName;

	@NotNull(message = "Invalid User MobileNo")
	@NotBlank(message = "Invalid User MobileNo")
	@NotEmpty(message = "Invalid User MobileNo")
	@Pattern(regexp = Constant.mobileNumberPattern, message = "Mobile Number Is Invalid")
	@Column(name = "User_MobileNo", length = 20)
	private String mobileno;

	@NotNull(message = "Invalid User EmailId")
	@NotBlank(message = "Invalid User EmailId")
	@NotEmpty(message = "Invalid User EmailId")
	@Pattern(regexp = Constant.emailIdPattern, message = "Email-Id Is Invalid")
	@Column(name = "Email_Id", length = 50)
	private String emailid;

	@Column(name = "User_Role", length = 20)
	private String role;

	@Column(name = "Parent_Username", length = 50)
	private String parentUsername;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "DOB")
	private Date dateOfBirth;

	@Column(name = "IsActive")
	private char isActive;

	@Enumerated(EnumType.STRING)
	@Column(name = "BankingService_Status")
	private YesNO bankingServiceStatus;

	@Basic(optional = false)
	@Column(name = "Password")
	private String password;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "UserIdentificationNo") }, inverseJoinColumns = {
			@JoinColumn(name = "Role_ID") })
	private Set<Role> roles = new HashSet<>();
}
