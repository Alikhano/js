package ru.alikhano.cyberlife.dao;

import java.util.List;

import ru.alikhano.cyberlife.model.Consciousness;

public interface ConsciousnessDao {

	List<Consciousness> getConList();

	Consciousness getConsById(int id);

	Consciousness getConsByLevel(String consLevel);

	void addLevel(Consciousness consciousness);

}
