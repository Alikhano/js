package ru.alikhano.cyberlife.DTO;

import ru.alikhano.cyberlife.model.Customer;

public class CustomerDTO {
	
	private int customerId;
	private String firstName;
	private String lastName;
	private String birthDate;
	private String email;
	private AddressDTO address;
	private UserDTO user;
	
	
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public AddressDTO getAddress() {
		return address;
	}
	public void setAddress(AddressDTO address) {
		this.address = address;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	public CustomerDTO() {
		
	}
	
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
