package ru.alikhano.cyberlife.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.dto.UserDTO;
import ru.alikhano.cyberlife.dao.UserDao;
import ru.alikhano.cyberlife.mapper.UserMapper;
import ru.alikhano.cyberlife.model.Role;
import ru.alikhano.cyberlife.model.User;
import ru.alikhano.cyberlife.service.RoleService;
import ru.alikhano.cyberlife.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void create(User user) {

		userDao.create(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void delete(User user) {

		userDao.delete(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public User getById(int id) {

		return userDao.getById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public UserDTO getDTOById(int id) {

		return userMapper.userToUserDTO(getById(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<User> getAll() {

		return userDao.getAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void changePassword(String password, UserDTO userDTO) {

		userDTO.setPassword(encoder.encode(password));
		update(userDTO);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void update(UserDTO userDTO) {

		userDao.update(userMapper.userDTOtoUser(userDTO));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public User getByUsername(String username) {

		return userDao.getByUsername(username);
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public UserDTO getByUsernameDTO(String username) {

		return userMapper.userToUserDTO(getByUsername(username));
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public boolean verifyPassword(String password, int id) {
		UserDTO userDTO = getDTOById(id);

		return BCrypt.checkpw(password, userDTO.getPassword());
	}

}
