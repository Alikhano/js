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

	Customer getByUserId(Integer id);

	Customer getByEmail(String email);

	List<Customer> getTopCustomers();

}
