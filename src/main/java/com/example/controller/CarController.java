package com.example.controller;

import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.async.RedisAsyncCommands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Lettuce.RedisCli;
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
	Logger logger = LoggerFactory.getLogger(CarController.class);
	//@ResponseBody
	@GetMapping(value="/addcar")
	public void addCar(@RequestParam String bookid,@RequestParam String userid,HttpSession session,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
		int bookId = Integer.parseInt(bookid);
		int userId = Integer.parseInt(userid);
		Car car = null;
		car = readIdCar();
		if(car==null){
			car = carMapper.findIdcar(bookId,userId);
			//System.out.println(car.getCar_Id());
			if(car!=null){
				writeIdCar(car);
			}		
		}
		if(car==null){
			Book book = bookMapper.findOneBook(bookId);	
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
			writeCar(car);
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
			car = carMapper.findIdcar(bookId, userId);
			writeCar(car);
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
	
	private void writeCar(Car car) {
		// TODO Auto-generated method stub
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		Map<String, String> mapCar = new HashMap<String,String>();
		mapCar.put("car_Id", String.valueOf(car.getCar_Id()));
		mapCar.put("car_Userid", String.valueOf(car.getCar_Userid()));
		mapCar.put("car_Bookid", String.valueOf(car.getCar_Bookid()));
		mapCar.put("car_Images", car.getCar_Images());
		mapCar.put("car_Bookname", car.getCar_Bookname());
		mapCar.put("car_Price", String.valueOf(car.getCar_Price()));
		mapCar.put("car_Number", String.valueOf(car.getCar_Number()));
		mapCar.put("car_Total", String.valueOf(car.getCar_Total()));
		asyncCommands.hmset("car:"+car.getCar_Id(), mapCar);
	}

	private void writeIdCar(Car car) {
		// TODO Auto-generated method stub
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
			Map<String, String> mapCar = new HashMap<String,String>();
			mapCar.put("car_Id", String.valueOf(car.getCar_Id()));
			mapCar.put("car_Userid", String.valueOf(car.getCar_Userid()));
			mapCar.put("car_Bookid", String.valueOf(car.getCar_Bookid()));
			mapCar.put("car_Images", car.getCar_Images());
			mapCar.put("car_Bookname", car.getCar_Bookname());
			mapCar.put("car_Price", String.valueOf(car.getCar_Price()));
			mapCar.put("car_Number", String.valueOf(car.getCar_Number()));
			mapCar.put("car_Total", String.valueOf(car.getCar_Total()));
			asyncCommands.hmset("idcar:"+car.getCar_Id(), mapCar);
	}

	private Car readIdCar() throws Exception{
		// TODO Auto-generated method stub
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		final Car car = null;
		RedisFuture<List<String>> carKeys = asyncCommands.keys("idcar*");
		List<String> carkeys = carKeys.get();	
		if(carkeys.size()==0)
			return car;
		for(String key: carkeys){
			RedisFuture<Map<String, String>> futureMap = asyncCommands.hgetall(key);
			Map<String, String> map = futureMap.get(); //它阻塞和等待直到承诺的结果是可用状态
			
			//Car car = new Car(); 		
			car.setCar_Id(Integer.valueOf(map.get("car_Id")));
			car.setCar_Bookid(Integer.valueOf(map.get("car_Bookid")));
			car.setCar_Userid(Integer.valueOf(map.get("car_Userid")));
			car.setCar_Images(map.get("car_Images"));
			car.setCar_Bookname(map.get("car_Bookname"));
			car.setCar_Price(Float.valueOf(map.get("car_Price")));
			car.setCar_Number(Integer.valueOf(map.get("car_Number")));
			car.setCar_Total(Float.valueOf(map.get("car_Total")));
			//cars.add(car);
		}
		logger.debug("----------read from redis-------------------");
		return car;
	}
	
	@GetMapping("/change")
	public void change(@RequestParam String bookid,@RequestParam String cz, HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception{
		int userId = -1;
		if(session.getAttribute("userid")!=null){
			userId = (int) session.getAttribute("userid");
		}
		int bookId = Integer.parseInt(bookid);
		Car car1 = new Car();
		if("1".equals(cz)){
			carMapper.updateCar(bookId, userId);
			car1 = carMapper.findIdcar(bookId, userId);
			writeCar(car1);
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
					car1 = carMapper.findIdcar(bookId, userId);
					writeCar(car1);
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
					RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
					RedisFuture<List<String>> carKeys = asyncCommands.keys("car*");
					List<String> carkeys = carKeys.get();
					for(String key : carkeys){
						asyncCommands.del(key);
					}
					
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
	public void deleteCar(@RequestParam String bookid,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception{
		int userId = -1;
		if(session.getAttribute("userid")!=null){
			userId = (int) session.getAttribute("userid");
		}
		int bookId = Integer.parseInt(bookid);
		carMapper.deleteCar(bookId, userId);
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		RedisFuture<List<String>> carKeys = asyncCommands.keys("car*");
		List<String> carkeys = carKeys.get();
		for(String key : carkeys){
			asyncCommands.del(key);
		}
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
	public String shop(HttpSession session,HttpServletRequest request,HttpServletResponse response,RedirectAttributes attr) throws Exception{
		int userId = -1;
		if(session.getAttribute("userid")!=null){
			userId = (int) session.getAttribute("userid");
		}
		List<Car> car = null;
		car = readAllCar();
		if(car.size()==0){
			car = carMapper.findAllCar(userId);
			writeAllCar(car);
		}
		for(Car cars:car){
			Book book = bookMapper.findOneBook(cars.getCar_Bookid());
			if(cars.getCar_Number()>book.getBookAmount()){
				session.setAttribute("tip", "购买失败,库存不足");
				
				return "redirect:/cart";
				
			}
			else{
				bookMapper.updateBook(cars.getCar_Bookid(), cars.getCar_Number());
				RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
				asyncCommands.flushall();
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
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		RedisFuture<List<String>> carKeys = asyncCommands.keys("car*");
		List<String> carkeys = carKeys.get();
		for(String key : carkeys){
			asyncCommands.del(key);
		}
		session.setAttribute("tip", "购买成功！");
		return "redirect:/index";
	}
	
	private void writeAllCar(List<Car> cars) {
		// TODO Auto-generated method stub
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		for(Car car: cars){
			Map<String, String> mapCar = new HashMap<String,String>();
			mapCar.put("car_Id", String.valueOf(car.getCar_Id()));
			mapCar.put("car_Userid", String.valueOf(car.getCar_Userid()));
			mapCar.put("car_Bookid", String.valueOf(car.getCar_Bookid()));
			mapCar.put("car_Images", car.getCar_Images());
			mapCar.put("car_Bookname", car.getCar_Bookname());
			mapCar.put("car_Price", String.valueOf(car.getCar_Price()));
			mapCar.put("car_Number", String.valueOf(car.getCar_Number()));
			mapCar.put("car_Total", String.valueOf(car.getCar_Total()));
			asyncCommands.hmset("car:"+car.getCar_Id(), mapCar);
		}
	}

	private List<Car> readAllCar() throws Exception{
		// TODO Auto-generated method stub
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		final List<Car> cars = new ArrayList<Car>();
		RedisFuture<List<String>> carKeys = asyncCommands.keys("car*");
		List<String> carkeys = carKeys.get();	
		if(carkeys.size()==0)
			return cars;
		for(String key: carkeys){
			RedisFuture<Map<String, String>> futureMap = asyncCommands.hgetall(key);
			Map<String, String> map = futureMap.get(); //它阻塞和等待直到承诺的结果是可用状态
			
			Car car = new Car(); 		
			car.setCar_Id(Integer.valueOf(map.get("car_Id")));
			car.setCar_Bookid(Integer.valueOf(map.get("car_Bookid")));
			car.setCar_Userid(Integer.valueOf(map.get("car_Userid")));
			car.setCar_Images(map.get("car_Images"));
			car.setCar_Bookname(map.get("car_Bookname"));
			car.setCar_Price(Float.valueOf(map.get("car_Price")));
			car.setCar_Number(Integer.valueOf(map.get("car_Number")));
			car.setCar_Total(Float.valueOf(map.get("car_Total")));
			cars.add(car);
		}
		logger.debug("----------read from redis-------------------");
		return cars;
	}
}
