package com.Smartpay.Model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Privileges")
@XmlRootElement
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
