package ru.alikhano.cyberlife.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
	
	private Integer addressId;
	private String country;
	private String city;
	private String zipCode;
	private String street;
	private String building;
	private String flat;

}
