package com.Smartpay.Model;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.Smartpay.Enum.EnumsStatus.BusinessType;
import com.Smartpay.Enum.EnumsStatus.Gender;
import com.Smartpay.Enum.EnumsStatus.MaritalStatus;
import com.Smartpay.Enum.EnumsStatus.YesNO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "Merchant")
public class Merchant extends BaseEntity {

	@Id
	@Column(name = "MerchantIdentificationNo")
	private Long MerchantIdentificationNo;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "Username")
	private User user;

	@OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY)
	private List<MerchantBankDetails> bankDetails;

	@OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY)
	private List<Address> addresses;

	@OneToOne(mappedBy = "merchant", fetch = FetchType.LAZY)
	private AEPSWallet aepsWallet;

	@NotBlank(message = "Invalid Guardian Name")
	@Size(min = 1, max = 200, message = "Minimum 1 Or Maximum 200 Character are Allowed")
	@Column(name = "FatherName", length = 200)
	private String guardianName;

	@Enumerated(EnumType.STRING)
	@Column(name = "MaritalStatus", length = 15)
	private MaritalStatus maritalStatus;

	@Enumerated(EnumType.STRING)
	@Column(name = "Gender", length = 15)
	private Gender gender;

	@Enumerated(EnumType.STRING)
	@Column(name = "BusinessType", length = 50)
	private BusinessType businessType;

	@Enumerated(EnumType.STRING)
	@Column(name = "AEPSServiceStatus", length = 4)
	private YesNO aepsServiceStatus;

	@Enumerated(EnumType.STRING)
	@Column(name = "EKYC_Status", length = 4)
	private YesNO EKYCstatus;

	@Enumerated(EnumType.STRING)
	@Column(name = "BankOnboardStatus", length = 4)
	private YesNO bankOnboardStatus;

	@Column(name = "PancardNumber", length = 10)
	@Size(min = 10, max = 10, message = "PanCard should be 10 Character")
	private String panCardNo;

	@Column(name = "AadhaarcardNumber", length = 12)
	@Size(min = 12, max = 12, message = "Aadhaar Number should be 12 Character")
	private String aadhaarcardNo;

	@Column(name = "PanCardImagePath", length = 200)
	private String panCardImagePath;

	@Column(name = "AadhaarCardImagePath", length = 200)
	private String aadhaarCardImagePath;

	@Column(name = "BusinessPanNo", length = 100)
	private String businesspanno;

	@NotBlank(message = "Invalid GST Nummber")
	@Column(name = "GSTNo", length = 100)
	private String gstNo;

	@NotBlank(message = "Invalid TAN Nummber")
	@Column(name = "TANNo", length = 100)
	private String tanNo;

}