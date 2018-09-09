package ru.alikhano.cyberlife.dao;

import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.model.Customer;

@Repository
public interface CustomerDao extends GenericDao<Customer> {
	
	Customer getByUserId(int id);

}
