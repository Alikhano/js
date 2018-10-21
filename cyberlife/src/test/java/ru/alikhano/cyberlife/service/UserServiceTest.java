package ru.alikhano.cyberlife.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import ru.alikhano.cyberlife.DTO.RoleDTO;
import ru.alikhano.cyberlife.DTO.UserDTO;
import ru.alikhano.cyberlife.dao.UserDao;
import ru.alikhano.cyberlife.mapper.UserMapper;
import ru.alikhano.cyberlife.model.Role;
import ru.alikhano.cyberlife.model.User;
import ru.alikhano.cyberlife.service.impl.UserServiceImpl;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	@Mock
	UserDao userDaoMock;
	
	@Mock
	UserMapper userMapperMock;
	
	@InjectMocks
    UserServiceImpl userServiceMock;
	
	User userMock;
	UserDTO userDTOMock;
	List<User> users;
	List<UserDTO> usersDTO;
	
	@Before
	public void init() {
		Role role = new Role(1, "ADMIN");
		RoleDTO roleDTO = new RoleDTO(role);
		Set<Role> roles = new HashSet<>();
		Set<RoleDTO> rolesDTO=new HashSet<>();
		roles.add(role);
		rolesDTO.add(roleDTO);
		userMock = new User(1, "user", "1234", true, roles);
		userDTOMock = new UserDTO(userMock);
		userDTOMock.setRoles(rolesDTO);
		
		Mockito.doNothing().when(userDaoMock).create(userMock);
		Mockito.doNothing().when(userDaoMock).delete(userMock);
		Mockito.doNothing().when(userDaoMock).update(userMock);
		
		Mockito.when(userDaoMock.getByUsername("user")).thenReturn(userMock);
		Mockito.when(userDaoMock.getById(1)).thenReturn(userMock);
		Mockito.when(userDaoMock.getAll()).thenReturn(users);
		
		Mockito.when(userMapperMock.userDTOtoUser(userDTOMock)).thenReturn(userMock);
		Mockito.when(userMapperMock.userToUserDTO(userMock)).thenReturn(userDTOMock);
		
	}
	
	@Test
	public void create() {
		userServiceMock.create(userMock);
		Mockito.verify(userDaoMock).create(userMock);
	}
	
	@Test
	public void delete() {
		userServiceMock.delete(userMock);
		Mockito.verify(userDaoMock).delete(userMock);
	}
	
	@Test
	public void getById() {
		User user = userServiceMock.getById(1);
		assertEquals(user.getUserId(), userMock.getUserId());
		Mockito.verify(userDaoMock).getById(1);
	}
	
	@Test
	public void updateDTO() {
		userServiceMock.update(userDTOMock);
		Mockito.verify(userDaoMock).update(userMock);
	}
	
	@Test
	public void getByUsername() {
		UserDTO user = userServiceMock.getByUsernameDTO("user");
		assertEquals(user.getUserId(), userMock.getUserId());
		Mockito.verify(userDaoMock).getByUsername("user");
	}
	
	@Test
	public void getAll() {
		List<User> list = userServiceMock.getAll();
		Mockito.verify(userDaoMock).getAll();
	}
	
	@Test
	public void changePassword() {
		userServiceMock.changePassword("1234",userDTOMock);
		Mockito.verify(userServiceMock).update(userDTOMock);
		Mockito.verify(userDaoMock).update(userMock);
	}

}
