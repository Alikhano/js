package ru.alikhano.cyberlife.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ru.alikhano.cyberlife.DTO.AddressDTO;
import ru.alikhano.cyberlife.DTO.CustomerDTO;
import ru.alikhano.cyberlife.DTO.RoleDTO;
import ru.alikhano.cyberlife.DTO.UserDTO;
import ru.alikhano.cyberlife.model.Address;
import ru.alikhano.cyberlife.model.Customer;
import ru.alikhano.cyberlife.model.Role;
import ru.alikhano.cyberlife.model.User;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CustomerMapper {
	
	CustomerDTO customerToCustomerDTO(Customer customer);
	Customer customerDTOtoCustomer(CustomerDTO customerDTO);
	
	UserDTO userToUserDTO(User user);
	User userDTOtoUser(UserDTO userDTO);
	
	RoleDTO roleToRoleDTO(Role role);
	Role roleDTOtoRole(RoleDTO roleDTO);
	
	AddressDTO addressToAddressDTO(Address address);
	Address addressDTOtoAddress(AddressDTO addressDTO);

}
