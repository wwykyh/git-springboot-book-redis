package com.example.controller;

import io.lettuce.core.api.async.RedisAsyncCommands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Lettuce.RedisCli;
import com.example.mapper.OrderMapper;
import com.example.model.Messages;
import com.example.model.Orders;

@Controller
public class OrderController {
	
	@Autowired
	OrderMapper orderMapper;
	
/*	@GetMapping("admin/orders")
	public String orders(Model model){
		List<Orders> order = orderMapper.findAllOrder();
		model.addAttribute("order", order);
		return "admin/orders";
	}*/
	

}
