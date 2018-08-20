package com.example.controller;

import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.async.RedisAsyncCommands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Lettuce.RedisCli;
import com.example.mapper.BookMapper;
import com.example.mapper.CarMapper;
import com.example.mapper.CategoryMapper;
import com.example.mapper.OrderMapper;
import com.example.model.Book;
import com.example.model.Car;
import com.example.model.Category;
import com.example.model.Orders;

@Controller
public class BookController {
	@Autowired
	BookMapper bookMapper;
	
	@Autowired
	CategoryMapper categoryMapper;
	
	@Autowired
	OrderMapper orderMapper;
	
	@Autowired
	CarMapper carMapper;
	
	int number = 0;
	float Total = 0;
	Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@GetMapping("/index")
	public String bookIndex(Model model, HttpSession session) throws Exception{
		int userId = -1;
		if(session.getAttribute("userid")!=null){
			userId = (int) session.getAttribute("userid");
		}
		List<Car> car = carMapper.findAllCar(userId);
		number = 0;
		Total = 0;
		for(Car cars:car){
			number += cars.getCar_Number();
			Total += cars.getCar_Total();
		}
		if((number*3)<10 && (number*3)>0){
			model.addAttribute("yunfei", 10);
			Total = Total+10;
		}
		else if(number>17){
			model.addAttribute("yunfei", 50);
			Total = Total+50;
		}
		else{
			model.addAttribute("yunfei", number*3);
			Total = Total+(number*3);
		}
		List<Book> book = null;
		book = readspeciBook();
		if(book.size()==0){
			book = bookMapper.findspecilBook();
			writespeciBook(book);
			logger.debug("----read from database");
		}
		
		List<Book> newbook = null;
		newbook = readNewBook();
		if(newbook.size()==0){
			newbook = bookMapper.findNewAllBook();
			writeNewBook(newbook);
			logger.debug("----read from database");
		}
		
		
		List<Book> countbook = null;
		countbook = readCountBook();
		if(countbook.size()==0){
			countbook = bookMapper.selectByDiscount();
			writeCountBook(countbook);
			logger.debug("----read from database");
		}
		model.addAttribute("number", number);
		model.addAttribute("Total", Total);
		model.addAttribute("book", book);
		model.addAttribute("newbook", newbook);
		model.addAttribute("bookcount",countbook);
	return "index";
}
	
	private void writeCountBook(List<Book> countbook) {
		// TODO Auto-generated method stub
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		for(Book book: countbook){
			Map<String, String> mapBook = new HashMap<String,String>();
			mapBook.put("bookId", String.valueOf(book.getBookId()));
			mapBook.put("booktypeId", String.valueOf(book.getBooktypeId()));
			mapBook.put("bookName", book.getBookName());
			mapBook.put("bookAuthor", book.getBookAuthor());
			mapBook.put("bookEdition", book.getBookEdition());
			mapBook.put("bookPrice", String.valueOf(book.getBookPrice()));
			mapBook.put("bookAmount", String.valueOf(book.getBookAmount()));
			mapBook.put("bookIntroduce", book.getBookIntroduce());
			mapBook.put("issuanceDate", book.getIssuanceDate());
			mapBook.put("bookImages", book.getBookImages());
			mapBook.put("bookSpecil", String.valueOf(book.getBookSpecil()));
			mapBook.put("bookDiscount", String.valueOf(book.getBookDiscount()));
			asyncCommands.hmset("countbook:"+book.getBookId(), mapBook);
		}
	}

	private List<Book> readCountBook() throws Exception{
		// TODO Auto-generated method stub
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		final List<Book> books = new ArrayList<Book>();
		RedisFuture<List<String>> bookKeys = asyncCommands.keys("countbook*");
		List<String> bookkeys = bookKeys.get();
		
		if(bookkeys.size()==0)
			return books;
		for(String key: bookkeys){
			RedisFuture<Map<String, String>> futureMap = asyncCommands.hgetall(key);
			Map<String, String> map = futureMap.get(); //它阻塞和等待直到承诺的结果是可用状态			
			Book book = new Book(); 
			book.setBookId(Integer.valueOf(map.get("bookId")));
			book.setBooktypeId(Integer.valueOf(map.get("booktypeId")));
			book.setBookName(map.get("bookName"));
			book.setBookAuthor(map.get("bookAuthor"));
			book.setBookEdition(map.get("bookEdition"));
			book.setBookPrice(Float.valueOf(map.get("bookPrice")));
			book.setBookAmount(Integer.valueOf(map.get("bookAmount")));
			book.setBookIntroduce(map.get("bookIntroduce"));
			book.setIssuanceDate(map.get("issuanceDate"));
			book.setBookImages(map.get("bookImages"));
			book.setBookSpecil(Integer.valueOf(map.get("bookSpecil")));
			book.setBookDiscount(Float.valueOf(map.get("bookDiscount")));
			books.add(book);
		}
		logger.debug("----------read from redis-------------------");
		return books;
	}

	private List<Book> readNewBook() throws Exception{
		// TODO Auto-generated method stub
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		final List<Book> books = new ArrayList<Book>();
		RedisFuture<List<String>> bookKeys = asyncCommands.keys("newbook*");
		List<String> bookkeys = bookKeys.get();
		
		if(bookkeys.size()==0)
			return books;
		for(String key: bookkeys){
			RedisFuture<Map<String, String>> futureMap = asyncCommands.hgetall(key);
			Map<String, String> map = futureMap.get(); //它阻塞和等待直到承诺的结果是可用状态
			
			Book book = new Book(); 		
			book.setBookId(Integer.valueOf(map.get("bookId")));
			book.setBooktypeId(Integer.valueOf(map.get("booktypeId")));
			book.setBookName(map.get("bookName"));
			book.setBookAuthor(map.get("bookAuthor"));
			book.setBookEdition(map.get("bookEdition"));
			book.setBookPrice(Float.valueOf(map.get("bookPrice")));
			book.setBookAmount(Integer.valueOf(map.get("bookAmount")));
			book.setBookIntroduce(map.get("bookIntroduce"));
			book.setIssuanceDate(map.get("issuanceDate"));
			book.setBookImages(map.get("bookImages"));
			book.setBookSpecil(Integer.valueOf(map.get("bookSpecil")));
			book.setBookDiscount(Float.valueOf(map.get("bookDiscount")));
			books.add(book);
		}
		logger.debug("----------read from redis-------------------");
		return books;
	}

	private void writeNewBook(List<Book> newbook) {
		// TODO Auto-generated method stub
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		for(Book book: newbook){
			Map<String, String> mapBook = new HashMap<String,String>();
			mapBook.put("bookId", String.valueOf(book.getBookId()));
			mapBook.put("booktypeId", String.valueOf(book.getBooktypeId()));
			mapBook.put("bookName", book.getBookName());
			mapBook.put("bookAuthor", book.getBookAuthor());
			mapBook.put("bookEdition", book.getBookEdition());
			mapBook.put("bookPrice", String.valueOf(book.getBookPrice()));
			mapBook.put("bookAmount", String.valueOf(book.getBookAmount()));
			mapBook.put("bookIntroduce", book.getBookIntroduce());
			mapBook.put("issuanceDate", book.getIssuanceDate());
			mapBook.put("bookImages", book.getBookImages());
			mapBook.put("bookSpecil", String.valueOf(book.getBookSpecil()));
			mapBook.put("bookDiscount", String.valueOf(book.getBookDiscount()));
			asyncCommands.hmset("newbook:"+book.getBookId(), mapBook);
		}
	}

	private void writespeciBook(List<Book> books) {
		// TODO Auto-generated method stub
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		for(Book book: books){
			Map<String, String> mapBook = new HashMap<String,String>();
			mapBook.put("bookId", String.valueOf(book.getBookId()));
			mapBook.put("booktypeId", String.valueOf(book.getBooktypeId()));
			mapBook.put("bookName", book.getBookName());
			mapBook.put("bookAuthor", book.getBookAuthor());
			mapBook.put("bookEdition", book.getBookEdition());
			mapBook.put("bookPrice", String.valueOf(book.getBookPrice()));
			mapBook.put("bookAmount", String.valueOf(book.getBookAmount()));
			mapBook.put("bookIntroduce", book.getBookIntroduce());
			mapBook.put("issuanceDate", book.getIssuanceDate());
			mapBook.put("bookImages", book.getBookImages());
			mapBook.put("bookSpecil", String.valueOf(book.getBookSpecil()));
			mapBook.put("bookDiscount", String.valueOf(book.getBookDiscount()));
			asyncCommands.hmset("specibook:"+book.getBookId(), mapBook);
		}
	}

	private List<Book> readspeciBook() throws Exception{
		// TODO Auto-generated method stub
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		final List<Book> books = new ArrayList<Book>();
		RedisFuture<List<String>> bookKeys = asyncCommands.keys("specibook*");
		List<String> bookkeys = bookKeys.get();
		
		if(bookkeys.size()==0)
			return books;
		for(String key: bookkeys){
			RedisFuture<Map<String, String>> futureMap = asyncCommands.hgetall(key);
			Map<String, String> map = futureMap.get(); //它阻塞和等待直到承诺的结果是可用状态
			Book book = new Book(); 
			book.setBookId(Integer.valueOf(map.get("bookId")));
			book.setBooktypeId(Integer.valueOf(map.get("booktypeId")));
			book.setBookName(map.get("bookName"));
			book.setBookAuthor(map.get("bookAuthor"));
			book.setBookEdition(map.get("bookEdition"));
			book.setBookPrice(Float.valueOf(map.get("bookPrice")));
			book.setBookAmount(Integer.valueOf(map.get("bookAmount")));
			book.setBookIntroduce(map.get("bookIntroduce"));
			book.setIssuanceDate(map.get("issuanceDate"));
			book.setBookImages(map.get("bookImages"));
			book.setBookSpecil(Integer.valueOf(map.get("bookSpecil")));
			book.setBookDiscount(Float.valueOf(map.get("bookDiscount")));
			books.add(book);
		}
		logger.debug("----------read from redis-------------------");
		return books;
	}	
	
	@GetMapping("/cart")
	public String bookCart(Model model,HttpSession session) throws Exception{
		int userId = -1;
		if(session.getAttribute("userid")!=null){
			userId = (int) session.getAttribute("userid");
		}
		//List<Car> car = carMapper.findAllCar(userId);
		List<Car> car = null;
		car = readCar();
		if(car.size()==0){
			car = carMapper.findAllCar(userId);
			writeCar(car);
		}
		number = 0;
		Total = 0;
		for(Car cars:car){
			number += cars.getCar_Number();
			Total += cars.getCar_Total();
		}
		if((number*3)<10 && (number*3)>0){
			model.addAttribute("yunfei", 10);
			Total = Total+10;
		}
		else if(number>17){
			model.addAttribute("yunfei", 50);
			Total = Total+50;
		}
		else{
			model.addAttribute("yunfei", number*3);
			Total = Total+(number*3);
		}
		
		List<Book> countbook = null;
		countbook = readCountBook();
		if(countbook.size()==0){
			countbook = bookMapper.selectByDiscount();
			writeCountBook(countbook);
			logger.debug("----read from database");
		}
		model.addAttribute("number", number);
		model.addAttribute("Total", Total);
		model.addAttribute("bookcount",countbook);
		model.addAttribute("car", car);
	return "cart";
}
	
	
	private void writeCar(List<Car> cars) {
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

	private List<Car> readCar() throws Exception{
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

	@GetMapping("/about")
public String bookAbout(Model model) throws Exception{
		List<Book> countbook = null;
		countbook = readCountBook();
		if(countbook.size()==0){
			countbook = bookMapper.selectByDiscount();
			writeCountBook(countbook);
			logger.debug("----read from database");
		}
		model.addAttribute("number", number);
		model.addAttribute("Total", Total);
		model.addAttribute("bookcount",countbook);
	return "about";
}
	@GetMapping("/category")
public String bookCategory(@RequestParam(value="page" ,defaultValue="1") int page,Model model) throws Exception{
		List<Book> countbook = null;
		countbook = readCountBook();
		if(countbook.size()==0){
			countbook = bookMapper.selectByDiscount();
			writeCountBook(countbook);
			logger.debug("----read from database");
		}
		model.addAttribute("number", number);
		model.addAttribute("Total", Total);
		model.addAttribute("bookcount",countbook);
		List<Book> books = null;
		books = readAllBook();
		if(books.size()==0){
			books = categoryMapper.findAllBook(page);
			writeAllBook(books);
		}
		model.addAttribute("book", books);
		return"category";

	
}	
	
	private void writeAllBook(List<Book> books) {
		// TODO Auto-generated method stub
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		for(Book book: books){
			Map<String, String> mapBook = new HashMap<String,String>();
			mapBook.put("bookId", String.valueOf(book.getBookId()));
			mapBook.put("booktypeId", String.valueOf(book.getBooktypeId()));
			mapBook.put("bookName", book.getBookName());
			mapBook.put("bookAuthor", book.getBookAuthor());
			mapBook.put("bookEdition", book.getBookEdition());
			mapBook.put("bookPrice", String.valueOf(book.getBookPrice()));
			mapBook.put("bookAmount", String.valueOf(book.getBookAmount()));
			mapBook.put("bookIntroduce", book.getBookIntroduce());
			mapBook.put("issuanceDate", book.getIssuanceDate());
			mapBook.put("bookImages", book.getBookImages());
			mapBook.put("bookSpecil", String.valueOf(book.getBookSpecil()));
			mapBook.put("bookDiscount", String.valueOf(book.getBookDiscount()));
			asyncCommands.hmset("allbook:"+book.getBookId(), mapBook);
		}
		
	}

	private List<Book> readAllBook() throws Exception {
		// TODO Auto-generated method stub
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		final List<Book> books = new ArrayList<Book>();
		RedisFuture<List<String>> bookKeys = asyncCommands.keys("allbook*");
		List<String> bookkeys = bookKeys.get();
		
		if(bookkeys.size()==0)
			return books;
		for(String key: bookkeys){
			RedisFuture<Map<String, String>> futureMap = asyncCommands.hgetall(key);
			Map<String, String> map = futureMap.get(); //它阻塞和等待直到承诺的结果是可用状态			
			Book book = new Book(); 
			book.setBookId(Integer.valueOf(map.get("bookId")));
			book.setBooktypeId(Integer.valueOf(map.get("booktypeId")));
			book.setBookName(map.get("bookName"));
			book.setBookAuthor(map.get("bookAuthor"));
			book.setBookEdition(map.get("bookEdition"));
			book.setBookPrice(Float.valueOf(map.get("bookPrice")));
			book.setBookAmount(Integer.valueOf(map.get("bookAmount")));
			book.setBookIntroduce(map.get("bookIntroduce"));
			book.setIssuanceDate(map.get("issuanceDate"));
			book.setBookImages(map.get("bookImages"));
			book.setBookSpecil(Integer.valueOf(map.get("bookSpecil")));
			book.setBookDiscount(Float.valueOf(map.get("bookDiscount")));
			books.add(book);
		}
		logger.debug("----------read from redis-------------------");
		return books;
	}

	@GetMapping("/contact")
	public String bookContact(Model model) throws Exception{
		List<Book> countbook = null;
		countbook = readCountBook();
		if(countbook.size()==0){
			countbook = bookMapper.selectByDiscount();
			writeCountBook(countbook);
			logger.debug("----read from database");
		}
		model.addAttribute("number", number);
		model.addAttribute("Total", Total);
		model.addAttribute("bookcount",countbook);
		return "contact";
	}	
	
	@GetMapping("/details")
	public String bookDetails(@RequestParam int cz,Model model) throws Exception{

		List<Book> countbook = null;
		countbook = readCountBook();
		if(countbook.size()==0){
			countbook = bookMapper.selectByDiscount();
			writeCountBook(countbook);
			logger.debug("----read from database");
		}
		Book book = bookMapper.findOneBook(cz);;
		model.addAttribute("number", number);
		model.addAttribute("Total", Total);
		//System.out.println(book);
		
//		List<Book> book1=bookMapper.selectByTypeId(book.getBooktypeId(),book.getBookId());
		List<Book> typebook = null;
		typebook = readTypeBook();
		if(typebook.size()==0){
			typebook = bookMapper.selectByTypeId(book.getBooktypeId(),book.getBookId());
			writeTypeBook(typebook);
		}
		model.addAttribute("book", book);
		model.addAttribute("number", number);
		model.addAttribute("Total", Total);
		model.addAttribute("booktype", typebook);
		model.addAttribute("bookcount",countbook);
		return "details";
	}	

	private void writeTypeBook(List<Book> typebook) {
		// TODO Auto-generated method stub
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		for(Book book: typebook){
			Map<String, String> mapBook = new HashMap<String,String>();
			mapBook.put("bookId", String.valueOf(book.getBookId()));
			mapBook.put("booktypeId", String.valueOf(book.getBooktypeId()));
			mapBook.put("bookName", book.getBookName());
			mapBook.put("bookAuthor", book.getBookAuthor());
			mapBook.put("bookEdition", book.getBookEdition());
			mapBook.put("bookPrice", String.valueOf(book.getBookPrice()));
			mapBook.put("bookAmount", String.valueOf(book.getBookAmount()));
			mapBook.put("bookIntroduce", book.getBookIntroduce());
			mapBook.put("issuanceDate", book.getIssuanceDate());
			mapBook.put("bookImages", book.getBookImages());
			mapBook.put("bookSpecil", String.valueOf(book.getBookSpecil()));
			mapBook.put("bookDiscount", String.valueOf(book.getBookDiscount()));
			asyncCommands.hmset("typebook:"+book.getBookId(), mapBook);
		}
		
	}

	private List<Book> readTypeBook() throws Exception{
		// TODO Auto-generated method stub
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		final List<Book> books = new ArrayList<Book>();
		RedisFuture<List<String>> bookKeys = asyncCommands.keys("typebook*");
		List<String> bookkeys = bookKeys.get();
		
		if(bookkeys.size()==0)
			return books;
		for(String key: bookkeys){
			RedisFuture<Map<String, String>> futureMap = asyncCommands.hgetall(key);
			Map<String, String> map = futureMap.get(); //它阻塞和等待直到承诺的结果是可用状态			
			Book book = new Book(); 
			book.setBookId(Integer.valueOf(map.get("bookId")));
			book.setBooktypeId(Integer.valueOf(map.get("booktypeId")));
			book.setBookName(map.get("bookName"));
			book.setBookAuthor(map.get("bookAuthor"));
			book.setBookEdition(map.get("bookEdition"));
			book.setBookPrice(Float.valueOf(map.get("bookPrice")));
			book.setBookAmount(Integer.valueOf(map.get("bookAmount")));
			book.setBookIntroduce(map.get("bookIntroduce"));
			book.setIssuanceDate(map.get("issuanceDate"));
			book.setBookImages(map.get("bookImages"));
			book.setBookSpecil(Integer.valueOf(map.get("bookSpecil")));
			book.setBookDiscount(Float.valueOf(map.get("bookDiscount")));
			books.add(book);
		}
		logger.debug("----------read from redis-------------------");
		return books;
	}
	
	@GetMapping("/license")
	public String bookLicense(Model model) throws Exception{
		List<Book> countbook = null;
		countbook = readCountBook();
		if(countbook.size()==0){
			countbook = bookMapper.selectByDiscount();
			writeCountBook(countbook);
			logger.debug("----read from database");
		}
		model.addAttribute("bookcount",countbook);
		return "license";
	}	
	
	
	@GetMapping("/register")
	public String bookRegister(Model model) throws Exception{
		List<Book> countbook = null;
		countbook = readCountBook();
		if(countbook.size()==0){
			countbook = bookMapper.selectByDiscount();
			writeCountBook(countbook);
			logger.debug("----read from database");
		}
		model.addAttribute("number", number);
		model.addAttribute("Total", Total);
		model.addAttribute("bookcount",countbook);
		return "register";
	}	
	@GetMapping("/specials")
	public String bookSpecials(Model model) throws Exception{
		List<Book> countbook = null;
		countbook = readCountBook();
		if(countbook.size()==0){
			countbook = bookMapper.selectByDiscount();
			writeCountBook(countbook);
			logger.debug("----read from database");
		}
		model.addAttribute("number", number);
		model.addAttribute("Total", Total);
		model.addAttribute("bookcount",countbook);
		model.addAttribute("bookAllcount",bookMapper.selectAllByDiscount());
		return "specials";
	}
	
	
	@GetMapping("/categoryType")
	public String findTypeBook(@RequestParam String type, Model model) throws Exception{
		
		List<Book> countbook = null;
		countbook = readCountBook();
		if(countbook.size()==0){
			countbook = bookMapper.selectByDiscount();
			writeCountBook(countbook);
			logger.debug("----read from database");
		}
		
		model.addAttribute("bookcount",countbook);

		List<Book> books = null;
		books = readBookType();
		if(books.size()==0){
			books = categoryMapper.findTypeBook(type);
			writeBookType(books);
		}
/*		for(Book book: books){
			System.out.println(book);
		}*/
		model.addAttribute("number", number);
		model.addAttribute("Total", Total);
		model.addAttribute("type", type);
		model.addAttribute("book",books);
		return "category";
	}

	
	private void writeBookType(List<Book> books) {
		// TODO Auto-generated method stub
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		for(Book book: books){
			Map<String, String> mapBook = new HashMap<String,String>();
			mapBook.put("bookId", String.valueOf(book.getBookId()));
			mapBook.put("booktypeId", String.valueOf(book.getBooktypeId()));
			mapBook.put("bookName", book.getBookName());
			mapBook.put("bookAuthor", book.getBookAuthor());
			mapBook.put("bookEdition", book.getBookEdition());
			mapBook.put("bookPrice", String.valueOf(book.getBookPrice()));
			mapBook.put("bookAmount", String.valueOf(book.getBookAmount()));
			mapBook.put("bookIntroduce", book.getBookIntroduce());
			mapBook.put("issuanceDate", book.getIssuanceDate());
			mapBook.put("bookImages", book.getBookImages());
			mapBook.put("bookSpecil", String.valueOf(book.getBookSpecil()));
			mapBook.put("bookDiscount", String.valueOf(book.getBookDiscount()));
			asyncCommands.hmset("booktype:"+book.getBookId(), mapBook);
		}
	}

	private List<Book> readBookType() throws Exception{
		// TODO Auto-generated method stub
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		final List<Book> books = new ArrayList<Book>();
		RedisFuture<List<String>> bookKeys = asyncCommands.keys("booktype*");
		List<String> bookkeys = bookKeys.get();
		
		if(bookkeys.size()==0)
			return books;
		for(String key: bookkeys){
			RedisFuture<Map<String, String>> futureMap = asyncCommands.hgetall(key);
			Map<String, String> map = futureMap.get(); //它阻塞和等待直到承诺的结果是可用状态			
			Book book = new Book(); 
			book.setBookId(Integer.valueOf(map.get("bookId")));
			book.setBooktypeId(Integer.valueOf(map.get("booktypeId")));
			book.setBookName(map.get("bookName"));
			book.setBookAuthor(map.get("bookAuthor"));
			book.setBookEdition(map.get("bookEdition"));
			book.setBookPrice(Float.valueOf(map.get("bookPrice")));
			book.setBookAmount(Integer.valueOf(map.get("bookAmount")));
			book.setBookIntroduce(map.get("bookIntroduce"));
			book.setIssuanceDate(map.get("issuanceDate"));
			book.setBookImages(map.get("bookImages"));
			book.setBookSpecil(Integer.valueOf(map.get("bookSpecil")));
			book.setBookDiscount(Float.valueOf(map.get("bookDiscount")));
			books.add(book);
		}
		logger.debug("----------read from redis-------------------");
		return books;
	}

	@GetMapping("/search")
	public String findbookname(@RequestParam("search") String bookname, Model model) throws Exception{
		List<Book> countbook = null;
		countbook = readCountBook();
		if(countbook.size()==0){
			countbook = bookMapper.selectByDiscount();
			writeCountBook(countbook);
			logger.debug("----read from database");
		}
		model.addAttribute("bookcount",countbook);

	//	System.out.println("%"+bookname+"%");
		List<Book> books = bookMapper.selectByName("%"+bookname+"%");
		/*for(Book book: books){
			System.out.println(book);
		}*/
		//model.addAttribute("type", type);

		model.addAttribute("book",books);
		model.addAttribute("number", number);
		model.addAttribute("Total", Total);
		return "category";
	}
	
	@GetMapping("/order")
	public String order(Model model,HttpSession session) throws Exception{
		int userId = -1;
		if(session.getAttribute("userid")!=null){
			userId = (int) session.getAttribute("userid");
		}
		List<Orders> order = orderMapper.findIdOrder(userId);
		List<Book> countbook = null;
		countbook = readCountBook();
		if(countbook.size()==0){
			countbook = bookMapper.selectByDiscount();
			writeCountBook(countbook);
			logger.debug("----read from database");
		}
		model.addAttribute("bookcount",countbook);
		model.addAttribute("order", order);
		model.addAttribute("number", number);
		model.addAttribute("Total", Total);
		return "order";
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
