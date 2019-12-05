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

import ru.alikhano.cyberlife.dto.ConsciousnessDTO;
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

	private Consciousness  consciousness;
	private ConsciousnessDTO consciousnessDTOMock;
	private List<ConsciousnessDTO> consciousnessDTOList;

	private static final String CONS_LEVEL = "testAI";
	
	@Before
	public void init() {
		consciousness = new Consciousness(1, CONS_LEVEL, CONS_LEVEL);
		consciousnessDTOMock = new ConsciousnessDTO(consciousness);
		List<Consciousness> consList = new ArrayList<>();
		consciousnessDTOList = new ArrayList<>();
		consList.add(consciousness);
		consciousnessDTOList.add(consciousnessDTOMock);

		Mockito.when(consDaoMock.getById(1)).thenReturn(consciousness);
		Mockito.when(consDaoMock.getConsByLevel(CONS_LEVEL)).thenReturn(consciousness);
		Mockito.when(consDaoMock.getAll()).thenReturn(consList);
		Mockito.doNothing().when(consDaoMock).create(consciousness);
		Mockito.when(consMapperMock.consDTOtoCons(consciousnessDTOMock)).thenReturn(consciousness);
		Mockito.when(consMapperMock.consToConsDTO(consciousness)).thenReturn(consciousnessDTOMock);
	}
	
	@Test
	public void create() {
		consServiceMock.create(consciousnessDTOMock);
		Mockito.verify(consDaoMock).create(consciousness);
		
	}
	
	@Test
	public void getById() {
		ConsciousnessDTO consciousnessDTO = consServiceMock.getById(1);
	    assertEquals(consciousnessDTO.getConsId(), consciousnessDTOMock.getConsId());
		Mockito.verify(consDaoMock).getById(1);
	}
	
	@Test
	public void getByLevel() {
		ConsciousnessDTO consciousnessDTO = consServiceMock.getByLevel(CONS_LEVEL);
		assertEquals(consciousnessDTO.getConsId(), consciousnessDTOMock.getConsId());
		Mockito.verify(consDaoMock).getConsByLevel(CONS_LEVEL);
	}
	
	@Test public void getAll() {
		List<ConsciousnessDTO> list = consServiceMock.getAll();
		assertEquals(list.size(), consciousnessDTOList.size());
		Mockito.verify(consDaoMock).getAll();
	}
}
