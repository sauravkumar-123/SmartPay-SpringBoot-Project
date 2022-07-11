package com.starbanking.Model;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
@XmlRootElement
public class Role {

	@Id
	@Column(name = "Role_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;

	@Column(name = "Role_Name", length = 50)
	private String name;

	@ManyToMany(mappedBy = "roles")
	private Set<User> user;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "roles_privileges", joinColumns = @JoinColumn(name = "Role_ID"), inverseJoinColumns = @JoinColumn(name = "Privilege_Id"))
	private Set<Privileges> privileges = new HashSet<>();

}
