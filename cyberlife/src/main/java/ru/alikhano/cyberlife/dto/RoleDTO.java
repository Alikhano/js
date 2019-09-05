package ru.alikhano.cyberlife.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.alikhano.cyberlife.model.Role;

@Getter
@Setter
@NoArgsConstructor
public class RoleDTO {
	
	private int roleId;
	private String type;
	
	public RoleDTO(Role role) {
		this.roleId=role.getRoleId();
		this.type=role.getType();
	}

}
