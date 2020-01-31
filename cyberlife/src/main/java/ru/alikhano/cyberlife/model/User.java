package ru.alikhano.cyberlife.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userId")
	private Integer userId;
	
	@Column(name="username", unique = true)
	@NotNull
	private String username;
	
	@Column(name="password")
	@NotNull
	private String password;
	
	@Column(name="enabled")
	private Boolean enabled;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="user_role", joinColumns= {
			@JoinColumn(name="userId")},
			inverseJoinColumns= {
					@JoinColumn(name="roleId")})
	private Set<Role> roles;

}
