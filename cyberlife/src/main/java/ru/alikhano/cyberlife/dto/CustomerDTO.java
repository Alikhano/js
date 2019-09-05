package ru.alikhano.cyberlife.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.alikhano.cyberlife.model.Customer;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO {
	
	private int customerId;
	private String firstName;
	private String lastName;
	private String birthDate;
	private String email;
	private AddressDTO address;
	private UserDTO user;
	
	public CustomerDTO(Customer customer) {
		this.address = new AddressDTO();
		address.setAddressId(customer.getAddress().getAddressId());
		address.setCountry(customer.getAddress().getCountry());
		address.setCity(customer.getAddress().getCity());
		address.setZipCode(customer.getAddress().getZipCode());
		address.setStreet(customer.getAddress().getStreet());
		address.setBuilding(customer.getAddress().getBuilding());
		address.setFlat(customer.getAddress().getFlat());
		this.birthDate = customer.getBirthDate();
		this.email = customer.getEmail();
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.customerId = customer.getCustomerId();
	}
}
