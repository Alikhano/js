package ru.alikhano.cyberlife.service;

import org.springframework.stereotype.Service;

import ru.alikhano.cyberlife.DTO.RoleDTO;
import ru.alikhano.cyberlife.model.Role;

@Service
public interface RoleService {
	
	Role getRole();
	
	RoleDTO getRoleDTO();

}
