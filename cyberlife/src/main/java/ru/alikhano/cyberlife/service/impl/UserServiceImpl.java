package ru.alikhano.cyberlife.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.DTO.UserDTO;
import ru.alikhano.cyberlife.dao.UserDao;
import ru.alikhano.cyberlife.mapper.UserMapper;
import ru.alikhano.cyberlife.model.Role;
import ru.alikhano.cyberlife.model.User;
import ru.alikhano.cyberlife.service.RoleService;
import ru.alikhano.cyberlife.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	@Transactional
	public void create(User user) {
		userDao.create(user);
	}

	@Override
	@Transactional
	public void delete(User user) {
		userDao.delete(user);
	}

	@Override
	@Transactional
	public User getById(int id) {
		return userDao.getById(id);
	}

	@Override
	@Transactional
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	@Transactional
	public void changePassword(String password, UserDTO userDTO) {
		userDTO.setPassword(encoder.encode(password));
		update(userDTO);
		
	}

	@Override
	@Transactional
	public void update(UserDTO userDTO) {
		userDao.update(userMapper.userDTOtoUser(userDTO));
	}

	@Override
	@Transactional
	public User getByUsername(String username) {
		return userDao.getByUsername(username);
	}

	@Override
	@Transactional
	public void register(UserDTO userDTO) {
		//check not null
		//check unique username
		Role role = roleService.getRole();
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		User user = new User();
		user.setEnabled(true);
		user.setUsername(userDTO.getUsername());
		user.setPassword(encoder.encode(userDTO.getPassword()));
		user.setRoles(roles);
		create(user);
			
	}

	@Override
	@Transactional
	public UserDTO getByUsernameDTO(String username) {
		return userMapper.userToUserDTO(getByUsername(username));
	}


	@Override
	@Transactional
	public boolean verifyPassword(String password, int id) {
		UserDTO userDTO = userMapper.userToUserDTO(userDao.getById(id));

		return BCrypt.checkpw(password, userDTO.getPassword());
	}

}
