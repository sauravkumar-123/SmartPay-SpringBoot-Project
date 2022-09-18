package com.Smartpay.FileUpload;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import com.Smartpay.Enum.EnumsStatus.YesNO;
import com.Smartpay.Model.BaseEntity;
import com.Smartpay.Model.Merchant;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
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
@Table(name = "Merchant_Documents")
public class MerchantDocuments extends BaseEntity {

	@Id
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "uuid.hex")
	@Column(name = "MerchantDocumentsID", length = 200)
	@Size(min = 1, max = 200, message = "min 1 and max 200 character are allowed")
	private String merchantDocumentsID;

	@Column(name = "PanCardImagePath", length = 200)
	private String panCardImagePath;

	@Column(name = "AadhaarCardImagePath", length = 200)
	private String aadhaarCardImagePath;

	@Column(name = "CancledCheckImagePath", length = 200)
	private String cancledCheckPath;

	@Enumerated(EnumType.STRING)
	@Column(name = "IsApproved", length = 4)
	private YesNO isApproved;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name = "Username")
	private Merchant merchant;
}
