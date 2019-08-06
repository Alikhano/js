package ru.alikhano.cyberlife.dto;

import ru.alikhano.cyberlife.model.Address;

public class AddressDTO {
	
	private int addressId;
	private String country;
	private String city;
	private String zipCode;
	private String street;
	private String building;
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
	
	public AddressDTO() {
		
	}
	
	public AddressDTO(Address address) {
		this.addressId = address.getAddressId();
		this.building = address.getBuilding();
		this.city = address.getCity();
		this.country = address.getCountry();
		this.flat = address.getFlat();
		this.street = address.getStreet();
		this.zipCode = address.getZipCode();
	}

}
