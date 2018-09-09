package ru.alikhano.cyberlife.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ru.alikhano.cyberlife.DTO.UserDTO;
import ru.alikhano.cyberlife.model.User;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserMapper {
	
	UserDTO userToUserDTO(User user);
	User userDTOtoUser(UserDTO userDTO);

}
