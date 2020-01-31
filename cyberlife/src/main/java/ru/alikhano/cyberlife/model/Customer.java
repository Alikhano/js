package ru.alikhano.cyberlife.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="customer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customerId")
	private Integer customerId;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="birthDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Pattern(regexp="^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")
	private String birthDate;
	
	@Column(name="email", unique = true)
	@Pattern(regexp="^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$")
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="addressId")
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userId")
	@NotNull
	User user;

}
