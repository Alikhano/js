package ru.alikhano.cyberlife.dao.impl;

import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.dao.OrderHistoryDao;
import ru.alikhano.cyberlife.model.OrderHistory;

@Repository
public class OrderHistoryDaoImpl extends GenericDaoImpl<OrderHistory> implements OrderHistoryDao {

}
