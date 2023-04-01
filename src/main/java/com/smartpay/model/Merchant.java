package com.smartpay.model;

import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.smartpay.enums.EnumsStatus.BusinessType;
import com.smartpay.enums.EnumsStatus.DocumentsUploadStatus;
import com.smartpay.enums.EnumsStatus.Gender;
import com.smartpay.enums.EnumsStatus.IsActive;
import com.smartpay.enums.EnumsStatus.MaritalStatus;
import com.smartpay.enums.EnumsStatus.YesNO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "Merchant")
@DynamicInsert
@DynamicUpdate
public class Merchant extends BaseEntity {

    @Id
    @GeneratedValue(generator = "idGen")
    @GenericGenerator(name = "idGen", strategy = "uuid.hex")
    @Column(name = "Merchant_Identification_No", length = 200)
    @Size(min = 1, max = 200, message = "min 1 and max 200 character are allowed")
    private String merchantIdentificationNo;

//    @JsonIgnore
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
//    @JsonBackReference
//    @JoinColumn(name = "userIdentificationNo")
//    private User user;
    @Column(name = "User_Identification_No", length = 200)
    @Size(max = 200)
    private String userIdentificationNo;

    @Size(min = 1, max = 10)
    @Column(name = "User_Name", length = 10)
    private String userName;

    @JsonIgnore
    @OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<MerchantBankDetails> bankDetails;

    @OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Address> addresses;

    @JsonIgnore
    @OneToOne(mappedBy = "merchant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private AEPSWallet aepsWallet;

//    @JsonIgnore
//    @OneToOne(mappedBy = "merchant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private MerchantDocuments merchantDocuments;
    @NotBlank(message = "Invalid Father/Husband Name")
    @Size(min = 1, max = 200, message = "Invalid entry of guardian name")
    @Column(name = "Father_Husband_Name", length = 200)
    private String guardianName;

    @Enumerated(EnumType.STRING)
    @Column(name = "Marital_Status", length = 15)
    private MaritalStatus maritalStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "Gender", length = 15)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "Business_Type", length = 50)
    private BusinessType businessType;

    @Enumerated(EnumType.STRING)
    @Column(name = "AEPS_Service_Status", length = 4)
    private YesNO aepsServiceStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "EKYC_Status", length = 4)
    private YesNO EKYCstatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "Bank_Onboard_Status", length = 4)
    private YesNO bankOnboardStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "Documents_Upload_Status", length = 25)
    private DocumentsUploadStatus documentsUploadStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "isActive", length = 10)
    private IsActive isActive;

    @Column(name = "Pancard_Number", length = 10)
    @Size(min = 10, max = 10, message = "PanCard No should be 10 Character")
    private String panCardNo;

    @Column(name = "Aadhaarcard_Number", length = 12)
    @Size(min = 12, max = 12, message = "Aadhaar Number should be 12 Digit")
    private String aadhaarcardNo;

    @Column(name = "Business_PanNo", length = 100)
    @Size(max = 100, message = "Max 100 Character are allowed for Business Pan No")
    private String businesspanno;

    @NotBlank(message = "Invalid GST Number")
    @Size(max = 100, message = "Max 100 Character are allowed for GST No")
    @Column(name = "GST_No", length = 100)
    private String gstNo;

    @NotBlank(message = "Invalid TAN Number")
    @Size(max = 100, message = "Max 100 Character are allowed for TAN No")
    @Column(name = "TANNo", length = 100)
    private String tanNo;

    @Column(name = "Merchant_Onboard_Id", length = 150)
    private String onboardingServiceIdentificationNo;

}
