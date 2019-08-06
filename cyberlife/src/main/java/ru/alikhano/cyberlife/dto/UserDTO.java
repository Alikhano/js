package ru.alikhano.cyberlife.dto;

import java.util.Set;

import ru.alikhano.cyberlife.model.User;


public class UserDTO {
	
	private int userId;
	private String username;
	private String password;
	private Set<RoleDTO> roles;
	private boolean enabled;
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Set<RoleDTO> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
	}
	
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserDTO() {
		
	}
	
	public UserDTO(User user) {
		this.userId=user.getUserId();
		this.username=user.getUsername();
		this.password=user.getPassword();
	}
	

}
