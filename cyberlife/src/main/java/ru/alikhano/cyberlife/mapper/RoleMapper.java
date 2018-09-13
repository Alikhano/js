package ru.alikhano.cyberlife.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ru.alikhano.cyberlife.DTO.RoleDTO;
import ru.alikhano.cyberlife.model.Role;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface RoleMapper {
	
	RoleDTO roleToRoleDTO(Role role);
	Role roleDTOtoRole(RoleDTO roleDTO);
}
