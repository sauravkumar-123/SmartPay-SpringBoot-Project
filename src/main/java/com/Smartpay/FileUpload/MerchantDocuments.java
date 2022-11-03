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
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
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
@DynamicInsert
@DynamicUpdate
public class MerchantDocuments extends BaseEntity {

	@Id
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "uuid.hex")
	@Column(name = "Merchant_Documents_ID", length = 200)
	@Size(min = 1, max = 200, message = "min 1 and max 200 character are allowed")
	private String merchantDocumentsID;

	@Column(name = "PanCard_Image_Path", length = 250)
	private String panCardImagePath;

	@Column(name = "AadhaarCard_Image_Path", length = 250)
	private String aadhaarCardImagePath;

	@Column(name = "CancledCheck_Image_Path", length = 250)
	private String cancledCheckPath;

	@Enumerated(EnumType.STRING)
	@Column(name = "IsApproved", length = 4)
	private YesNO isApproved;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonBackReference
	@JoinColumn(name = "merchantIdentificationNo")
	private Merchant merchant;
}
