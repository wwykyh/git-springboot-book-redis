package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.mapper.MessageMapper;
import com.example.model.Messages;

@Controller
public class MessageController {
	@Autowired
	MessageMapper messageMapper;
	
	@PostMapping(value="/message")
	public String doreg(Messages message){
		System.out.println(message);
		int i = messageMapper.insertMessage(message);
		System.out.println(i);
		return "redirect:/contact";
	}
	
	@GetMapping("/liuyan")
	public String  messages(Model model){
		List<Messages> messages = messageMapper.findAllMessage();
		model.addAttribute("message", messages);
		return "message";
	}
}
