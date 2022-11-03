package com.Smartpay.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Roles")
@DynamicInsert
@DynamicUpdate
@XmlRootElement
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
	@JoinTable(name = "roles_privileges", joinColumns = @JoinColumn(name = "Role_Id"), inverseJoinColumns = @JoinColumn(name = "Privilege_Id"))
	private Set<Privileges> privileges = new HashSet<>();

}
