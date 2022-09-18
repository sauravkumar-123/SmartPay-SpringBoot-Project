package com.Smartpay.Model;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
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
@Table(name = "Address")
public class Address extends BaseEntity {

	@Id
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "uuid.hex")
	@Column(name = "AddressDetailsID", length = 200)
	@Size(min = 1, max = 200, message = "min 1 and max 200 character are allowed")
	private String AddressDetailsID;

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
	@JsonBackReference
	@JoinColumn(name = "MerchantIdentificationNo")
	private Merchant merchant;
}
