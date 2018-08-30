package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mapper.OrderMapper;
import com.example.model.Orders;

@Controller
public class OrderController {
	
	@Autowired
	OrderMapper orderMapper;
	
	@GetMapping("admin/orders")
	public String orders(Model model){
		List<Orders> order = orderMapper.findAllOrder();
/*		for(Orders orders:order){
			System.out.println(orders.getUser().getUserName());
		}*/
		model.addAttribute("order", order);
		//System.out.println("---");
		return "admin/orders";
	}
	
	@GetMapping("admin/deleteOrder")
	public String deleteOrder(@RequestParam String orderid){
		int id = -1;
		//System.out.println(orderid);
		if(orderid!=null){
			id = Integer.parseInt(orderid);
		}
		orderMapper.deleteIdOrder(id);
		return "redirect:admin/orders";
	}
}
