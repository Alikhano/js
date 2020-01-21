package ru.alikhano.cyberlife.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.alikhano.cyberlife.dto.UserDTO;
import ru.alikhano.cyberlife.dao.UserDao;
import ru.alikhano.cyberlife.mapper.UserMapper;
import ru.alikhano.cyberlife.model.User;
import ru.alikhano.cyberlife.service.impl.UserServiceImpl;
import ru.alikhano.cyberlife.supplier.UserSupplier;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	@Mock
	private UserDao userDao;
	@Mock
	private UserMapper userMapper;
	@Mock
	private RoleService roleService;
	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@InjectMocks
	private  UserServiceImpl userService;

	private User user;
	private UserDTO userDTO;

	private static final String TEST_PASSWORD = "1234";
	private static final String TEST_USERNAME = "user";

	@Before
	public void init() {
		user = UserSupplier.getAdminUser();
		userDTO = UserSupplier.getAdminUserDTO();
		
		Mockito.doNothing().when(userDao).create(user);
		Mockito.doNothing().when(userDao).delete(user);
		Mockito.doNothing().when(userDao).update(user);
		
		Mockito.when(userDao.getByUsername(TEST_USERNAME)).thenReturn(user);
		Mockito.when(userDao.getById(1)).thenReturn(user);
		Mockito.when(userDao.getAll()).thenReturn(Collections.singletonList(user));
		Mockito.when(userMapper.userDTOtoUser(userDTO)).thenReturn(user);
		Mockito.when(userMapper.userToUserDTO(user)).thenReturn(userDTO);

	}
	
	@Test
	public void create() {
		userService.create(user);
		Mockito.verify(userDao).create(user);
	}
	
	@Test
	public void delete() {
		userService.delete(user);
		Mockito.verify(userDao).delete(user);
	}
	
	@Test
	public void getById() {
		User user = userService.getById(1);
		assertEquals(user.getUserId(), this.user.getUserId());
		Mockito.verify(userDao).getById(1);
	}
	
	@Test
	public void getByIdFail() {
		User user = userService.getById(2);
		assertNull(user);
	}
	
	
	@Test
	public void updateDTO() {
		userService.update(userDTO);
		Mockito.verify(userDao).update(user);
	}
	
	@Test
	public void getByUsername() {
		userService.getByUsernameDTO(TEST_USERNAME);
		Mockito.verify(userDao).getByUsername(TEST_USERNAME);
	}
	
	@Test
	public void getByUserNameFail() {
		UserDTO user = userService.getByUsernameDTO("user1");
		assertNull(user);
	}
	
	@Test
	public void getAll() {
		userService.getAll();
		Mockito.verify(userDao).getAll();
	}
	
	@Test
	public void register() {
		userService.register(userDTO);
		Mockito.verify(userDao).create(Mockito.any());
	}
	
	@Test
	public void changePassword() {
		userService.changePassword(TEST_PASSWORD, userDTO);
		Mockito.verify(userDao).update(user);
	}
	
	@Test
	public void verifyPassword() {
		boolean pswdCheck = userService.verifyPassword(TEST_PASSWORD, 1);
		assertTrue(pswdCheck);
	}

}
