package ru.alikhano.cyberlife.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private Integer userId;
	private String username;
	private String password;
	private Set<RoleDTO> roles;
	private Boolean enabled;

}
