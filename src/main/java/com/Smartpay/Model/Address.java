package com.Smartpay.Model;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.Smartpay.Enum.EnumsStatus.AddressType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "Address")
public class Address extends BaseEntity{

	@Id
	@Column(name = "AddressDetailsID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long AddressDetailsID;

	@NotBlank(message = "Invalid Address Data")
	@Column(name = "Address", length = 250)
	private String address;

	@NotBlank(message = "Invalid Input")
	@Column(name = "VillageOrCity", length = 150)
	private String villageCity;

	@NotBlank(message = "Invalid District")
	@Column(name = "District", length = 150)
	private String district;

	@NotBlank(message = "Invalid Pincode")
	@Column(name = "Pincode", length = 10)
	private String pincode;

	@NotBlank(message = "Invalid State")
	@Column(name = "State", length = 150)
	private String state;

	@NotBlank(message = "Invalid Country")
	@Column(name = "Country", length = 150)
	private String country;

	@Enumerated(EnumType.STRING)
	@Column(name = "AddressType", length = 30)
	private AddressType addressType;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "MerchantIdentificationNo")
	private Merchant merchant;
}
