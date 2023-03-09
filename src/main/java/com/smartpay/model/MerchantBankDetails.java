package com.smartpay.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.smartpay.enums.EnumsStatus.AccountType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "Merchant_Bank_Details")
@DynamicInsert
@DynamicUpdate
public class MerchantBankDetails extends BaseEntity {

    @Id
    @GeneratedValue(generator = "idGen")
    @GenericGenerator(name = "idGen", strategy = "uuid.hex")
    @Column(name = "Bank_Details_ID", length = 200)
    @Size(min = 1, max = 200, message = "min 1 and max 200 character are allowed")
    private String bankDetailsID;

    @NotBlank(message = "Invalid AccountHolder Name")
    @Column(name = "Account_Holder_Name", length = 200)
    @Size(min = 1, max = 200, message = "Invalid account holder name entry")
    private String accountHolderName;

    @NotBlank(message = "Invalid Account Number")
    @Size(max = 20, message = "Invalid account number entry")
    @Column(name = "Account_Number", length = 20)
    private String accountNumber;

    @NotBlank(message = "Invalid IFSC Code")
    @Column(name = "IFSC_Code", length = 11)
    @Size(min = 11, max = 11, message = "IFSC Code should be 11 Character")
    private String ifscCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "AccountType", length = 15)
    private AccountType accountType;

    @NotBlank(message = "Invalid Bank Name")
    @Size(min = 1, max = 150, message = "Invalid BanK Name entry")
    @Column(name = "Bank_Name", length = 150)
    private String bankName;

    @NotBlank(message = "Invalid Branch Name")
    @Size(min = 1, max = 150, message = "Invalid Branch Name entry")
    @Column(name = "Branch_Name", length = 150)
    private String branchName;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "merchantIdentificationNo")
    private Merchant merchant;
}
