package ru.alikhano.cyberlife.dao;


import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.model.Consciousness;

@Repository
public interface ConsciousnessDao extends GenericDao<Consciousness> {
	
	Consciousness getConsByLevel(String consLevel);


}
