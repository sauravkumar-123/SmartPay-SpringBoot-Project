package com.smartpay.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

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
@Table(name = "Roles")
@DynamicInsert
@DynamicUpdate
public class Role {

    @Id
    @Column(name = "Role_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @Column(name = "Role_Name", length = 50)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> user;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "roles_privileges",
            joinColumns = {
                @JoinColumn(name = "Role_Id")}, inverseJoinColumns = {
                @JoinColumn(name = "Privilege_Id")})
    private Set<Privileges> privileges = new HashSet<>();

}
