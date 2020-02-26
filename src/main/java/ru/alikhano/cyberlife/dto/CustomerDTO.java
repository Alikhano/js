package ru.alikhano.cyberlife.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
	
	private Integer customerId;
	private String firstName;
	private String lastName;
	private String birthDate;
	private String email;
	private AddressDTO address;
	private UserDTO user;

}
