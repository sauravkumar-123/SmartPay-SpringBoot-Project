package com.smartpay.model;

import java.math.BigDecimal;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.smartpay.enums.EnumsStatus.IsActive;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
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
@Entity
@Table(name = "Main_Wallet")
@DynamicInsert
@DynamicUpdate
public class MainWallet extends BaseEntity {

    @Id
    @GeneratedValue(generator = "idGen")
    @GenericGenerator(name = "idGen", strategy = "uuid.hex")
    @Column(name = "Main_Wallet_Id", length = 200)
    @Size(min = 1, max = 200, message = "min 1 and max 200 character are allowed")
    private String mainWalletId;

    @Column(name = "User_Name", length = 10)
    private String userName;

    @Column(name = "Current_Balance")
    private BigDecimal currentBalance;

    @Column(name = "Charges")
    private BigDecimal charges;

    @Column(name = "Commission_Credit")
    private BigDecimal commissionCredit;

    @Column(name = "TDS")
    private BigDecimal tds;

    @Column(name = "Credit_Amount")
    private BigDecimal creditAmount;

    @Column(name = "Debit_Amount")
    private BigDecimal debitAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "IsActive", length = 10)
    private IsActive isActive;

    @Column(name = "Credit_Type", length = 100)
    private String creditType;

    @Column(name = "Debit_Type", length = 100)
    private String debitType;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    @JoinColumn(name = "userIdentificationNo")
    private User user;
}
