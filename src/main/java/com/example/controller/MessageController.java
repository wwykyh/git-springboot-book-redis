package com.example.controller;

import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.async.RedisAsyncCommands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Lettuce.RedisCli;
import com.example.mapper.MessageMapper;
import com.example.model.Messages;

@Controller
public class MessageController {
	@Autowired
	MessageMapper messageMapper;
	
	
	Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@PostMapping(value="/message")
	public String doreg(Messages message){
		System.out.println(message);
		int i = messageMapper.insertMessage(message);
		writeMessages(message);
		System.out.println(i);
		return "redirect:/contact";
	}
	
	private void writeMessages(Messages message) {
		// TODO Auto-generated method stub
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
			Map<String, String> map = new HashMap<String,String>();
			map.put("messageId", String.valueOf(message.getMessageId()));
			map.put("messageName", message.getMessageName());
			map.put("messageEmail",message.getMessageEmail());
			map.put("messagePhone", message.getMessagePhone());
			map.put("messageAddress", message.getMessageAddress());
			map.put("message", message.getMessage());
			asyncCommands.hmset("message:"+message.getMessageId(), map);		
	}
	
	@GetMapping("/admin/liuyan")
	public String  messages(Model model) throws Exception{
		logger.debug("----------runing.........-------------------");
		List<Messages> messages = null;
		messages = readMessages();
		if(messages.size()==0){
			logger.debug("----read from database");
			messages = messageMapper.findAllMessage();
			writeMessages(messages);
		}
		model.addAttribute("message", messages);
		return "admin/message";
	}
	
	private void writeMessages(List<Messages> messages) {
		// TODO Auto-generated method stub
		logger.debug("----write from database");
		
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		for(Messages message: messages){
			Map<String, String> map = new HashMap<String,String>();
			map.put("messageId", String.valueOf(message.getMessageId()));
			map.put("messageName", message.getMessageName());
			map.put("messageEmail",message.getMessageEmail());
			map.put("messagePhone", message.getMessagePhone());
			map.put("messageAddress", message.getMessageAddress());
			map.put("message", message.getMessage());
			asyncCommands.hmset("message:"+message.getMessageId(), map);
		}
		
	}

	private List<Messages> readMessages() throws Exception{
		// TODO Auto-generated method stub
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		final List<Messages> messages = new ArrayList<Messages>();
		RedisFuture<List<String>> messageKeys = asyncCommands.keys("message*");
		List<String> messagekeys = messageKeys.get();
		
		if(messagekeys.size()==0)
			return messages;
		for(String key: messagekeys){
			RedisFuture<Map<String, String>> futureMap = asyncCommands.hgetall(key);
			Map<String, String> map = futureMap.get(); //它阻塞和等待直到承诺的结果是可用状态			
			Messages message = new Messages(); 
			message.setMessageId(Integer.valueOf(map.get("messageId")));
			message.setMessageName(map.get("messageName"));
			message.setMessageEmail(map.get("messageEmail"));
			message.setMessagePhone(map.get("messagePhone"));
			message.setMessageAddress(map.get("messageAddress"));
			message.setMessage(map.get("message"));
			messages.add(message);
		}
		logger.debug("----------read from redis-------------------");
		return messages;
	}
	
	@GetMapping("/deleteMassage")
	public String deleteMassage(@RequestParam(value="messageid",required = false) String messageid,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception {
		int id = -1;
		if(messageid!=null){
			id = Integer.parseInt(messageid);
		}
		messageMapper.deleteIdMessage(id);
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		RedisFuture<List<String>> carKeys = asyncCommands.keys("message*");
		List<String> carkeys = carKeys.get();
		for(String key : carkeys){
			asyncCommands.del(key);
		}
		//System.out.println(id);
		session.setAttribute("tip", "删除成功！");
			return "redirect:/admin/liuyan";
		
	}
}
