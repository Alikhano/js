package ru.alikhano.cyberlife.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ru.alikhano.cyberlife.dto.AddressDTO;
import ru.alikhano.cyberlife.model.Address;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AddressMapper {
	
	AddressDTO addressToAddressDTO(Address address);
	Address addressDTOtoAddress(AddressDTO addressDTO);

}
