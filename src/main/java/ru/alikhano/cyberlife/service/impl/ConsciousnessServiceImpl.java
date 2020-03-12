package ru.alikhano.cyberlife.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.dto.ConsciousnessDTO;
import ru.alikhano.cyberlife.dao.ConsciousnessDao;
import ru.alikhano.cyberlife.mapper.ConsciousnessMapper;
import ru.alikhano.cyberlife.service.ConsciousnessService;

@Service
public class ConsciousnessServiceImpl implements ConsciousnessService{

	private static final Logger LOGGER = LogManager.getLogger(ConsciousnessServiceImpl.class);
	
	@Autowired
	private ConsciousnessDao consciousnessDao;
	
	@Autowired
	private ConsciousnessMapper consciousnessMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<ConsciousnessDTO> getAll() {
		return consciousnessDao.getAll().stream()
				.filter(Objects::nonNull)
				.map(consciousness -> consciousnessMapper.forward(consciousness))
				.collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public ConsciousnessDTO getById(int id) {
		return consciousnessMapper.forward(consciousnessDao.getById(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public ConsciousnessDTO getByLevel(String consciousnessLevel) {
		if (StringUtils.isNullOrEmpty(consciousnessLevel)) {
			LOGGER.info("Consciousness level is null or empty");
			return null;
		}
		return consciousnessMapper.forward(consciousnessDao.getConsByLevel(consciousnessLevel));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void create(ConsciousnessDTO consciousnessDTO) {
		if (consciousnessDTO == null) {
			LOGGER.info("Consciousness is null");
			return;
		}
		consciousnessDao.create(consciousnessMapper.backward(consciousnessDTO));
	}
}
