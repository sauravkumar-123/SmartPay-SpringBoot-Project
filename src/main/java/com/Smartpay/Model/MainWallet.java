package com.Smartpay.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

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
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "uuid.hex")
	@Column(name = "MainWalletId", length = 200)
	@Size(min = 1, max = 200, message = "min 1 and max 200 character are allowed")
	private String mainWalletId;

	@Column(name = "userName", length = 10)
	private String userName;

	@Column(name = "CurrentBalance")
	private Double currentBalance;

	@Column(name = "Charges")
	private Double charges;

	@Column(name = "CommissionCredit")
	private Double commissionCredit;

	@Column(name = "TDS")
	private Double tds;

	@Column(name = "CreditAmount")
	private Double creditAmount;

	@Column(name = "DebitAmount")
	private Double debitAmount;

	@Column(name = "IsActive")
	private char isActive;

	@Column(name = "CreditType", length = 80)
	private String creditType;

	@Column(name = "DebitType", length = 80)
	private String debitType;
}
