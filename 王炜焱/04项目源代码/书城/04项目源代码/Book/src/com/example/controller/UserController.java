package com.example.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mapper.BookMapper;
import com.example.mapper.UserMapper;
import com.example.model.Users;

@Controller
public class UserController {

	@Autowired
	UserMapper userMapper;

	@Autowired
	BookMapper bookMapper;

	@GetMapping("/myaccount")
	public String Login(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		model.addAttribute("bookcount", bookMapper.selectByDiscount());
		if (request.getCookies() != null)
			for (Cookie ck : request.getCookies()) {
				if (ck.getName().equals("username")) {
					String username1 = ck.getValue();
					/*
					 * System.out.println("----------");
					 * System.out.println(username1);
					 */
					model.addAttribute("username1", username1);
				}
			}

		return "myaccount";
	}

	@PostMapping("/dologin")
	public String doLogin(@RequestParam String username,
			@RequestParam String password, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		// System.out.println(username + "  " + password);
		Users user = userMapper.selectUser(username, password);
		// System.out.println(user.getUserId());
		System.out.println(user);
		if (user != null) {
			session.setAttribute("userid", user.getUserId());
			session.setAttribute("username", user.getUserName());
			session.setMaxInactiveInterval(900);
			// System.out.println(session.getAttribute("username"));
			// System.out.println(request.getParameter("remember"));
			if (request.getParameter("remember") != null) {
				Cookie ckusername = new Cookie("username", username);
				ckusername.setMaxAge(3600);
				response.addCookie(ckusername);
			} else {
				for (Cookie ckusername : request.getCookies()) {
					if (ckusername.getName().equals("username")) {
						// System.out.println("=========ck=========");
						ckusername.setValue(null);
						ckusername.setMaxAge(0);
						response.addCookie(ckusername);
						break;

					}
				}
			}

			//session.setAttribute("username", username);
			// System.out.println("=================");
			request.setAttribute("tip", "");
			return "redirect:/index";
		} else {
			// System.out.println("----------------------");
			request.setAttribute("tip", "用户名或者密码错误");
			return "myaccount";
		}
	}

	@PostMapping(value = "/doreg")
	public String doreg(Users user) {
		System.out.println(user);
		int i = userMapper.insertUser(user);
		System.out.println("i=" + i);
		return "redirect:/index";
	}

	@GetMapping("/edit")
	public String Edit(Model model, HttpSession session) {
		model.addAttribute("bookcount", bookMapper.selectByDiscount());
		int userid = -1;

		if (session.getAttribute("userid") != null) {
			userid = (int) session.getAttribute("userid");
		}
		String username = (String) session.getAttribute("username");
		System.out.println(username);
		if (username != null) {
			model.addAttribute("bookcount", bookMapper.selectByDiscount());
			Users user = userMapper.selectOneUser(userid);
			// System.out.println(user);

			model.addAttribute("User", user);

			return "edit";
		}
		return "myaccount";
		// System.out.println(userid);

	}

	@PostMapping(value = "/doedit")
	public String doedit(Users user,HttpSession session) {
		System.out.println(user);
		int i = userMapper.updateUser(user);
		System.out.println("i=" + i);
		if(i>0){
			session.setAttribute("tip", "修改成功！");
		}
		else{
			session.setAttribute("tip", "修改失败！");
		}
		return "redirect:/index";
	}

	@GetMapping("/exit")
	public String exit(Model model, HttpSession session,
			HttpServletRequest request) {
		session.removeAttribute("username");
		session.setAttribute("tip", "退出成功！");
		return "redirect:/index";
	}

}
