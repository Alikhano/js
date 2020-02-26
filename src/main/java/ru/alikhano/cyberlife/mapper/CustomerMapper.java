package ru.alikhano.cyberlife.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ru.alikhano.cyberlife.dto.CustomerDTO;
import ru.alikhano.cyberlife.model.Customer;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {UserMapper.class,
		RoleMapper.class, AddressMapper.class})
public interface CustomerMapper extends BiConverter<Customer, CustomerDTO> {

}
