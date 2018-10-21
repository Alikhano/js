package ru.alikhano.cyberlife.DTO;

import ru.alikhano.cyberlife.model.Role;

public class RoleDTO {
	
	private int roleId;
	private String type;
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public RoleDTO() {
		
	}
	
	public RoleDTO(Role role) {
		this.roleId=role.getRoleId();
		this.type=role.getType();
	}

}
