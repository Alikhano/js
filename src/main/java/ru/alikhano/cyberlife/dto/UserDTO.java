package ru.alikhano.cyberlife.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.alikhano.cyberlife.model.User;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private int userId;
	private String username;
	private String password;
	private Set<RoleDTO> roles;
	private boolean enabled;
	
	public UserDTO(User user) {
		this.userId=user.getUserId();
		this.username=user.getUsername();
		this.password=user.getPassword();
	}
	

}
