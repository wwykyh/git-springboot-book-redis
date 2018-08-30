package com.example.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mapper.BookMapper;
import com.example.mapper.CarMapper;
import com.example.mapper.OrderMapper;
import com.example.model.Book;
import com.example.model.Car;
import com.example.model.Orders;


@Controller
public class CarController {
	@Autowired
	CarMapper carMapper;
	@Autowired
	BookMapper bookMapper;
	@Autowired
	OrderMapper orderMapper;
	//@ResponseBody
	@GetMapping(value="/addcar")
	public void addCar(@RequestParam String bookid,@RequestParam String userid,HttpSession session,HttpServletRequest request,HttpServletResponse response,Model model){
		//System.out.println(userid);
		int bookId = Integer.parseInt(bookid);
		int userId = Integer.parseInt(userid);
		Car car = carMapper.findIdcar(bookId,userId);
		if(car==null){
			
			//System.out.println("----------car mapper 0-----------------");
			
			Book book = bookMapper.findOneBook(bookId);	
			//if(session.getAttribute("userid")!=null){
			//	userId = (int) session.getAttribute("userid");
			//}
			
			//System.out.println("----------car mapper 1-----------------");
			
			car = new Car();
			car.setCar_Userid(userId);
			car.setCar_Bookid(bookId);
			car.setCar_Images(book.getBookImages());
			car.setCar_Bookname(book.getBookName());
			float price = book.getBookPrice() * book.getBookDiscount();
			car.setCar_Price(price);
			car.setCar_Number(1);
			car.setCar_Total(price);
			int i = carMapper.addCar(car);
			if(i!=0){
				session.setAttribute("tip", "添加成功");
			}else{
				session.setAttribute("tip", "添加失败");
			}
			try {
				request.getRequestDispatcher("/index").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			int i = carMapper.updateCar(bookId, userId);
			System.out.println(i);
			if(i>0){
				session.setAttribute("tip", "添加成功");
			}
			else{
				session.setAttribute("tip", "添加失败");
			}
			try {
				request.getRequestDispatcher("/category").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
		
	}
	
	@GetMapping("/change")
	public void change(@RequestParam String bookid,@RequestParam String cz, HttpSession session,HttpServletRequest request,HttpServletResponse response){
		int userId = -1;
		if(session.getAttribute("userid")!=null){
			userId = (int) session.getAttribute("userid");
		}
		int bookId = Integer.parseInt(bookid);
		if("1".equals(cz)){
			carMapper.updateCar(bookId, userId);
			try {
				request.getRequestDispatcher("/cart").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			Car car = carMapper.findIdcar(bookId, userId);
			if(car!=null){
				if(car.getCar_Number()>1){
					carMapper.changeDelCar(bookId, userId);
					try {
						request.getRequestDispatcher("/cart").forward(request, response);
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					carMapper.deleteCar(bookId, userId);
					try {
						request.getRequestDispatcher("/cart").forward(request, response);
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
		
	}
	
	@GetMapping("deleteCar")
	public void deleteCar(@RequestParam String bookid,HttpSession session,HttpServletRequest request,HttpServletResponse response){
		int userId = -1;
		if(session.getAttribute("userid")!=null){
			userId = (int) session.getAttribute("userid");
		}
		int bookId = Integer.parseInt(bookid);
		carMapper.deleteCar(bookId, userId);
		try {
			request.getRequestDispatcher("/cart").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping("shop")
	public String shop(HttpSession session,HttpServletRequest request,HttpServletResponse response,RedirectAttributes attr){
		int userId = -1;
		if(session.getAttribute("userid")!=null){
			userId = (int) session.getAttribute("userid");
		}
		List<Car> car = carMapper.findAllCar(userId);
		for(Car cars:car){
			Book book = bookMapper.findOneBook(cars.getCar_Bookid());
			if(cars.getCar_Number()>book.getBookAmount()){
				session.setAttribute("tip", "购买失败,库存不足");
				
				return "redirect:/cart";
				
			}
			else{
				bookMapper.updateBook(cars.getCar_Bookid(), cars.getCar_Number());
				//向订单表中插入数据
				Orders order = new Orders();
				order.setBookId(cars.getCar_Bookid());
				order.setUserId(userId);
				order.setBookCount(cars.getCar_Number());
				order.setBookPrice(cars.getCar_Total());
				orderMapper.addOrder(order);

			}
		}
		carMapper.delete(userId);
		session.setAttribute("tip", "购买成功！");
		return "redirect:/index";
	}
}
