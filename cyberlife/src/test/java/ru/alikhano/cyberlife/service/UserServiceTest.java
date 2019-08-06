package ru.alikhano.cyberlife.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ru.alikhano.cyberlife.dto.RoleDTO;
import ru.alikhano.cyberlife.dto.UserDTO;
import ru.alikhano.cyberlife.dao.UserDao;
import ru.alikhano.cyberlife.mapper.UserMapper;
import ru.alikhano.cyberlife.model.Role;
import ru.alikhano.cyberlife.model.User;
import ru.alikhano.cyberlife.service.impl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	@Mock
	UserDao userDaoMock;
	
	@Mock
	UserMapper userMapperMock;
	
	@Mock
	BCryptPasswordEncoder encoder;
	
	@Mock
	RoleService roleService;
	
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
		userDTOMock = Mockito.mock(UserDTO.class);
		userDTOMock.setRoles(rolesDTO);
		
		Mockito.doNothing().when(userDaoMock).create(userMock);
		Mockito.doNothing().when(userDaoMock).delete(userMock);
		Mockito.doNothing().when(userDaoMock).update(userMock);
		
		Mockito.when(userDaoMock.getByUsername("user")).thenReturn(userMock);
		Mockito.when(userDaoMock.getById(1)).thenReturn(userMock);
		Mockito.when(userDaoMock.getAll()).thenReturn(users);
		
		Mockito.when(userMapperMock.userDTOtoUser(userDTOMock)).thenReturn(userMock);
		Mockito.when(userMapperMock.userToUserDTO(userMock)).thenReturn(userDTOMock);
		Mockito.doReturn("$2a$11$CGbN7TL4PbIg3nnRzRCPjOJPGl2e2enng/xSbf1SQ.WuKWCfTLtF6").when(userDTOMock).getPassword();
		
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
	public void getByIdFail() {
		User user = userServiceMock.getById(2);
		assertNull(user);
	}
	
	
	@Test
	public void updateDTO() {
		userServiceMock.update(userDTOMock);
		Mockito.verify(userDaoMock).update(userMock);
	}
	
	@Test
	public void getByUsername() {
		userServiceMock.getByUsernameDTO("user");
		Mockito.verify(userDaoMock).getByUsername("user");
	}
	
	@Test
	public void getByUserNameFail() {
		UserDTO user = userServiceMock.getByUsernameDTO("user1");
		assertNull(user);
	}
	
	@Test
	public void getAll() {
		userServiceMock.getAll();
		Mockito.verify(userDaoMock).getAll();
	}
	
	@Test
	public void register() {
		userServiceMock.register(userDTOMock);
	}
	
	@Test
	public void changePassword() {
		userServiceMock.changePassword("1234", userDTOMock);
		Mockito.verify(userDaoMock).update(userMock);
		
	}
	
	@Test
	public void verifyPassword() {
		boolean pswdCheck = userServiceMock.verifyPassword("1234", 1);
		assertEquals(true, pswdCheck);
		
	}

}
