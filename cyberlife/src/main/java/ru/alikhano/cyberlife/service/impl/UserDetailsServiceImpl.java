package ru.alikhano.cyberlife.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.model.Role;
import ru.alikhano.cyberlife.model.User;
import ru.alikhano.cyberlife.service.UserService;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserService userService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getByUsername(username);
		
		Set<GrantedAuthority> authorities = new HashSet<>();
		
		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getType()));	
		}
	
		 return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}

}
