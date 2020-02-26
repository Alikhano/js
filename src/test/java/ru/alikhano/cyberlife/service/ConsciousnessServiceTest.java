package ru.alikhano.cyberlife.service;

import static org.junit.Assert.assertEquals;

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
import ru.alikhano.cyberlife.supplier.ConsciousnessSupplier;

@RunWith(MockitoJUnitRunner.class)
public class ConsciousnessServiceTest {
	@Mock
	private ConsciousnessDao    consciousnessDao;
	@Mock
	private ConsciousnessMapper consciousnessMapper;
	
	@InjectMocks
	private ConsciousnessServiceImpl consciousnessService;

	private Consciousness    consciousness;

	private ConsciousnessDTO consciousnessDTO;

	private List<Consciousness> consciousnessList;
	private List<ConsciousnessDTO> consciousnessDTOList;

	private static final String CONSCIOUSNESS_LEVEL = "middle AI";
	
	@Before
	public void init() {
		consciousness = ConsciousnessSupplier.getConsciousness();
		consciousnessDTO = ConsciousnessSupplier.getConsciousnessDTO();
		consciousnessList = ConsciousnessSupplier.getConsciousnessList();
		consciousnessDTOList = ConsciousnessSupplier.getConsciousnessDTOList();

		Mockito.when(consciousnessDao.getById(1)).thenReturn(consciousness);
		Mockito.when(consciousnessDao.getConsByLevel(CONSCIOUSNESS_LEVEL)).thenReturn(consciousness);
		Mockito.when(consciousnessDao.getAll()).thenReturn(consciousnessList);
		Mockito.doNothing().when(consciousnessDao).create(consciousness);
		Mockito.when(consciousnessMapper.backward(consciousnessDTO)).thenReturn(consciousness);
		Mockito.when(consciousnessMapper.forward(consciousness)).thenReturn(consciousnessDTO);
	}
	
	@Test
	public void create() {
		consciousnessService.create(consciousnessDTO);
		Mockito.verify(consciousnessDao).create(consciousness);
		
	}
	
	@Test
	public void getById() {
		ConsciousnessDTO consciousnessDTO = consciousnessService.getById(1);
	    assertEquals(consciousnessDTO.getConsId(), this.consciousnessDTO.getConsId());
		Mockito.verify(consciousnessDao).getById(1);
	}
	
	@Test
	public void getByLevel() {
		ConsciousnessDTO consciousnessDTO = consciousnessService.getByLevel(CONSCIOUSNESS_LEVEL);
		assertEquals(consciousnessDTO.getConsId(), this.consciousnessDTO.getConsId());
		Mockito.verify(consciousnessDao).getConsByLevel(CONSCIOUSNESS_LEVEL);
	}
	
	@Test public void getAll() {
		List<ConsciousnessDTO> list = consciousnessService.getAll();
		assertEquals(list.size(), consciousnessDTOList.size());
		Mockito.verify(consciousnessDao).getAll();
	}
}
