package ru.alikhano.cyberlife.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="address")
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="addressId")
	private int addressId;
	
	@Column(name="country")
	@NotNull
	private String country;
	
	@Column(name="city")
	@NotNull
	private String city;
	
	@Column(name="zipCode")
	@NotNull
	@Digits(integer=6, fraction=0)
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


	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getFlat() {
		return flat;
	}

	public void setFlat(String flat) {
		this.flat = flat;
	}
	
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
	
	public Address() {
		
	}

	public Address(int addressId, String country, String city, String zipCode, String street, String building,
			String flat) {
		super();
		this.addressId = addressId;
		this.country = country;
		this.city = city;
		this.zipCode = zipCode;
		this.street = street;
		this.building = building;
		this.flat = flat;
	}
	
	


}
