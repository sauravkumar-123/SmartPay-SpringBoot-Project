package com.smartpay.fileupload.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.smartpay.enums.EnumsStatus.YesNO;
import com.smartpay.model.BaseEntity;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
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

    @Column(name = "Merchant_Identification_No", length = 200)
    @Size(max = 200)
    private String merchantIdentificationNo;

//    @JsonIgnore
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonBackReference
//    @JoinColumn(name = "merchantIdentificationNo")
//    private Merchant merchant;
}
