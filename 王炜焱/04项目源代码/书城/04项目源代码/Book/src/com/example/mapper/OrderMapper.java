package com.example.mapper;

import java.util.List;

import com.example.model.Orders;

public interface OrderMapper {
	void addOrder(Orders order);
	List<Orders> findIdOrder(int userId);
	List<Orders> findAllOrder();
	void deleteIdOrder(int id);
}
