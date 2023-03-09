package com.smartpay.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.smartpay.constants.Constant;
import com.smartpay.enums.EnumsStatus.IsActive;
import com.smartpay.enums.EnumsStatus.YesNO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Basic;
import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "User")
@Entity
@DynamicInsert
@DynamicUpdate
public class User extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "idGen")
    @GenericGenerator(name = "idGen", strategy = "uuid.hex")
    @Column(name = "User_Identification_No", length = 200)
    @Size(min = 1, max = 200, message = "min 1 and max 200 character are allowed")
    private String userIdentificationNo;

    @Column(name = "Customer_Id")
    private long customerId;

//    @JsonIgnore
//    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
//    @JsonManagedReference
//    private Merchant merchant;
    @JsonIgnore
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private MainWallet mainWallet;

    @Column(name = "Username", length = 10)
    private String username;

    @NotBlank(message = "Invalid First Name")
    @Size(min = 1, max = 150, message = "Invalid First Name entry")
    @Column(name = "First_Name", length = 150)
    private String firstName;

    @Column(name = "Middle_Name", length = 150)
    @Size(max = 150, message = "Invalid Middle Name entry")
    private String middleName;

    @NotBlank(message = "Invalid Last Name")
    @Size(min = 1, max = 150, message = "Invalid Last Name entry")
    @Column(name = "Last_Name", length = 150)
    private String lastName;

    @NotBlank(message = "Invalid User MobileNo")
    @Pattern(regexp = Constant.mobileNumberPattern, message = "Mobile Number is invalid")
    @Column(name = "User_Mobile_No", length = 20)
    private String mobileNo;

    @NotBlank(message = "Invalid User EmailId")
    @Pattern(regexp = Constant.emailIdPattern, message = "Email-Id is invalid")
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

    @JsonIgnore
    @Basic(optional = false)
    @Column(name = "Password")
    private String password;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = {
        @JoinColumn(name = "User_Identification_No")}, inverseJoinColumns = {
        @JoinColumn(name = "Role_Id")})
    private Set<Role> roles = new HashSet<>();

}
