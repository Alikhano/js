package ru.alikhano.cyberlife.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="addressId")
	private int addressId;
	
	@Column(name="country")
	@NotNull
	@Pattern(regexp = "[A-Za-z\\s]+")
	private String country;
	
	@Column(name="city")
	@NotNull
	@Pattern(regexp = "[A-Za-z\\s]+")
	private String city;
	
	@Column(name="zipCode")
	@NotNull
	@Pattern(regexp="[\\d]{6}")
	private String zipCode;
	
	@Column(name="street")
	@NotNull
	private String street;
	
	@Column(name="building")
	@Min(value=1)
	private String building;
	
	@Column(name="flat")
	@Min(value=1)
	private String flat;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + addressId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (addressId != other.addressId)
			return false;
		return true;
	}
}
