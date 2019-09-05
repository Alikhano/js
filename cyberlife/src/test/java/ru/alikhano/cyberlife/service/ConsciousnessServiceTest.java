package ru.alikhano.cyberlife.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import ru.alikhano.cyberlife.dto.ConsDTO;
import ru.alikhano.cyberlife.dao.ConsciousnessDao;
import ru.alikhano.cyberlife.mapper.ConsciousnessMapper;
import ru.alikhano.cyberlife.model.Consciousness;
import ru.alikhano.cyberlife.service.impl.ConsciousnessServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ConsciousnessServiceTest {
	
	@Mock
	private ConsciousnessDao consDaoMock;
	
	@Mock
	private ConsciousnessMapper consMapperMock;
	
	@InjectMocks
	private ConsciousnessServiceImpl consServiceMock;

	private Consciousness consMock;
	private ConsDTO consDTOMock;
	private List<Consciousness> consListMock;
	private List<ConsDTO> consDTOListMock;

	private static final String CONS_LEVEL = "testAI";
	
	@Before
	public void init() {
		consMock = new Consciousness(1, CONS_LEVEL, CONS_LEVEL);
		consDTOMock = new ConsDTO(consMock);
		consListMock = new ArrayList<>();
		consDTOListMock = new ArrayList<>();
		consListMock.add(consMock);
		consDTOListMock.add(consDTOMock);
		Mockito.when(consDaoMock.getById(1)).thenReturn(consMock);
		Mockito.when(consDaoMock.getConsByLevel(CONS_LEVEL)).thenReturn(consMock);
		Mockito.when(consDaoMock.getAll()).thenReturn(consListMock);
		Mockito.doNothing().when(consDaoMock).create(consMock);
		Mockito.when(consMapperMock.consDTOtoCons(consDTOMock)).thenReturn(consMock);
		Mockito.when(consMapperMock.consToConsDTO(consMock)).thenReturn(consDTOMock);
	}
	
	@Test
	public void create() {
		consServiceMock.create(consDTOMock);
		Mockito.verify(consDaoMock).create(consMock);
		
	}
	
	@Test
	public void getById() {
		ConsDTO consDTO = consServiceMock.getById(1);
	    assertEquals(consDTO.getConsId(), consDTOMock.getConsId());
		Mockito.verify(consDaoMock).getById(1);
	}
	
	@Test
	public void getByLevel() {
		ConsDTO consDTO = consServiceMock.getByLevel(CONS_LEVEL);
		assertEquals(consDTO.getConsId(), consDTOMock.getConsId());
		Mockito.verify(consDaoMock).getConsByLevel(CONS_LEVEL);
	}
	
	@Test public void getAll() {
		List<ConsDTO> list = consServiceMock.getAll();
		assertEquals(list.size(), consDTOListMock.size());
		Mockito.verify(consDaoMock).getAll();
	}
}
