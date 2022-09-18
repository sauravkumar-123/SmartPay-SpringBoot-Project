package com.Smartpay.Model;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.Smartpay.Enum.EnumsStatus.AddressType;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "AEPSWallet")
public class AEPSWallet extends BaseEntity {

	@Id
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "uuid.hex")
	@Column(name = "AepsWalletId", length = 200)
	@Size(min = 1, max = 200, message = "min 1 and max 200 character are allowed")
	private String aepsWalletId;

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
	@JsonBackReference
	@JoinColumn(name = "MerchantIdentificationNo")
	private Merchant merchant;
}
