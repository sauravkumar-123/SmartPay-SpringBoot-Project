package com.smartpay.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.smartpay.enums.EnumsStatus.AddressType;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
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
