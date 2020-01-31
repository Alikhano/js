package ru.alikhano.cyberlife.dao;


import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.model.Consciousness;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Repository
public interface ConsciousnessDao extends GenericDao<Consciousness> {

	Consciousness getConsByLevel(String consLevel);

}
