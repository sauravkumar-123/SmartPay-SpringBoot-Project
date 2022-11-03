package com.Smartpay.Model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.Smartpay.Enum.EnumsStatus.IsActive;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
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
}
