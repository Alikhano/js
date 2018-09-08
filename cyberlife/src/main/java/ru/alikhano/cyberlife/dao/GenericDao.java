package ru.alikhano.cyberlife.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface GenericDao<T> {
	
	void create(T entity);
	
	T getById(Integer id);
	
	void update(T entity);
	
	void delete(T entity);
	
	List<T> getAll();

}
