package ru.alikhano.cyberlife.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.alikhano.cyberlife.model.Address;

@Getter
@Setter
@NoArgsConstructor
public class AddressDTO {
	
	private int addressId;
	private String country;
	private String city;
	private String zipCode;
	private String street;
	private String building;
	private String flat;
	
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
