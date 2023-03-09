package com.smartpay.model;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.AllArgsConstructor;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "Privileges")
@DynamicInsert
@DynamicUpdate
public class Privileges {

    @Id
    @Column(name = "Privilege_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int privilegeId;

    @Column(name = "Privilege_Name", length = 20)
    private String privilegeName;

    @ManyToMany(mappedBy = "privileges")
    private Set<Role> roles;
}
