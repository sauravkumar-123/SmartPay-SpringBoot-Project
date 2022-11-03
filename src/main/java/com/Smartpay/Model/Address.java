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
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.Smartpay.Enum.EnumsStatus.AddressType;
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
@Table(name = "Address")
@DynamicInsert
@DynamicUpdate
public class Address extends BaseEntity {

	@Id
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "uuid.hex")
	@Column(name = "Address_Details_ID", length = 200)
	@Size(min = 1, max = 200, message = "min 1 and max 200 character are allowed")
	private String addressDetailsID;

	@NotBlank(message = "Invalid Address Data")
	@Size(min = 1, max = 250, message = "Maximum 250 character are allowed")
	@Column(name = "Address", length = 250)
	private String address;

	@NotBlank(message = "Invalid Input")
	@Size(min = 1, max = 200, message = "Maximum 200 character are allowed")
	@Column(name = "Village_Or_City", length = 200)
	private String villageCity;

	@NotBlank(message = "Invalid District")
	@Size(min = 1, max = 200, message = "Maximum 200 character are allowed")
	@Column(name = "District", length = 200)
	private String district;

	@NotBlank(message = "Invalid Pincode")
	@Size(min = 6, max = 6, message = "Pincode Should be 6 Character")
	@Column(name = "Pincode", length = 6)
	private String pincode;

	@NotBlank(message = "Invalid State")
	@Size(min = 1, max = 200, message = "Maximum 150 character are allowed")
	@Column(name = "State", length = 150)
	private String state;

	@NotBlank(message = "Invalid Country")
	@Size(min = 1, max = 200, message = "Maximum 150 character are allowed")
	@Column(name = "Country", length = 150)
	private String country;

	@Enumerated(EnumType.STRING)
	@Column(name = "Address_Type", length = 50)
	private AddressType addressType;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonBackReference
	@JoinColumn(name = "merchantIdentificationNo")
	private Merchant merchant;
}
