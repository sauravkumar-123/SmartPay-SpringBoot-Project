package com.Smartpay.Model;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "AEPSWallet")
public class AEPSWallet extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AepsWalletId")
	private Long aepsWalletId;

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

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "MerchantIdentificationNo")
	private Merchant merchant;
}
