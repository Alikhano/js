package ru.alikhano.cyberlife.service;

import org.springframework.stereotype.Service;

import ru.alikhano.cyberlife.DTO.RoleDTO;
import ru.alikhano.cyberlife.model.Role;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Service
public interface RoleService {
	
	/**
	 * @return Role instance (always USER_ROLE)
	 */
	Role getRole();
	
	/**
	 * @return RoleDTO instance (always USER_ROLE)
	 */
	RoleDTO getRoleDTO();

}
