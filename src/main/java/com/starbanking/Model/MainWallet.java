package com.starbanking.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "MainWallet")
public class MainWallet extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Main_WalletId")
	private Integer mainWalletId;

	@Column(name = "userName", length = 10)
	private String userName;

	@Column(name = "Current_Balance")
	private Double currentBalance;

	@Column(name = "Charges")
	private Double charges;

	@Column(name = "Commission_Credit")
	private Double commissionCredit;

	@Column(name = "tds")
	private Double tds;

	@Column(name = "Credit_Amount")
	private Double creditAmount;

	@Column(name = "debit_Amount")
	private Double debitAmount;
	
	@Column(name = "IsActive")
	private char isActive;

	@Column(name = "credit_type", length = 20)
	private String creditType;

	@Column(name = "debit_type", length = 20)
	private String debitType;
}
