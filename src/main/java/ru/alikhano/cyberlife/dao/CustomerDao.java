package ru.alikhano.cyberlife.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.model.Customer;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Repository
public interface CustomerDao extends GenericDao<Customer> {
	
	/** 
	 * enables database search by customer's id
	 * @param id customer id to search in database
	 * @return instance of Customer with corresponding customer id
	 */
	Customer getByUserId(int id);
	
	
	/** 
	 * @return list of most active customers
	 */
	Customer getByEmail(String email);
	
	/**
	 * @return instance of User with corresponding email
	 */
	List<Customer> getTopCustomers();

}
