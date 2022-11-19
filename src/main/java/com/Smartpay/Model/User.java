package com.Smartpay.Model;

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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.Smartpay.Constants.Constant;
import com.Smartpay.Enum.EnumsStatus.IsActive;
import com.Smartpay.Enum.EnumsStatus.YesNO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@DynamicInsert
@DynamicUpdate
public class User extends BaseEntity {

//	@PrePersist
//	void preInsert() {
//		userIdentificationNo = "402880ea83e092e70163e093aa280009";
//	}

	@Id
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "uuid.hex")
	@Column(name = "User_Identification_No", length = 200)
	@Size(min = 1, max = 200, message = "min 1 and max 200 character are allowed")
	private String userIdentificationNo;

	@Column(name = "Customer_Id")
	private Long customerId;

//	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
//	@JsonManagedReference
//	private Merchant merchant;

	@Column(name = "Username", length = 10)
	private String username;

	@NotBlank(message = "Invalid Applicant Name")
	@Size(min = 1, max = 200, message = "Minimum 1 Or Maximum 200 Character are Allowed")
	@Column(name = "Applicant_Name", length = 200)
	private String applicantName;

	@NotBlank(message = "Invalid User MobileNo")
	@Pattern(regexp = Constant.mobileNumberPattern, message = "Mobile Number Is Invalid")
	@Column(name = "User_MobileNo", length = 20)
	private String mobileNo;

	@NotBlank(message = "Invalid User EmailId")
	@Pattern(regexp = Constant.emailIdPattern, message = "Email-Id Is Invalid")
	@Column(name = "Email_Id", length = 150)
	private String emailId;

	@Column(name = "User_Role", length = 20)
	private String role;

	@Column(name = "Parent_User_Name", length = 50)
	private String parentUsername;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DOB")
	private Date dateOfBirth;

	@Enumerated(EnumType.STRING)
	@Column(name = "IsActive", length = 10)
	private IsActive isActive;

	@Enumerated(EnumType.STRING)
	@Column(name = "BankingService_Status")
	private YesNO bankingServiceStatus;

	@Basic(optional = false)
	@Column(name = "Password")
	private String password;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = {
			@JoinColumn(name = "User_Identification_No") }, inverseJoinColumns = { @JoinColumn(name = "Role_Id") })
	private Set<Role> roles = new HashSet<>();

}
