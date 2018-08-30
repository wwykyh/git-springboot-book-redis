package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mapper.BookMapper;
import com.example.mapper.CarMapper;
import com.example.mapper.CategoryMapper;
import com.example.mapper.OrderMapper;
import com.example.model.Book;
import com.example.model.Car;
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
	@GetMapping("/index")
public String bookIndex(Model model, HttpSession session){
		/*List<Book> book=bookMapper.selectByDiscount();
		for(Book books:book){
			System.out.println(books);
		}*/
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
		model.addAttribute("number", number);
		model.addAttribute("Total", Total);
		model.addAttribute("book", bookMapper.findspecilBook());
		model.addAttribute("newbook", bookMapper.findNewAllBook());
		model.addAttribute("bookcount",bookMapper.selectByDiscount());
	return "index";
}
	
	
	
	@GetMapping("/cart")
public String bookCart(Model model,HttpSession session){
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
		model.addAttribute("number", number);
		model.addAttribute("Total", Total);
		model.addAttribute("bookcount",bookMapper.selectByDiscount());
		model.addAttribute("car", car);
	return "cart";
}
	@GetMapping("/about")
public String bookAbout(Model model){
		model.addAttribute("number", number);
		model.addAttribute("Total", Total);
		model.addAttribute("bookcount",bookMapper.selectByDiscount());
	return "about";
}
	@GetMapping("/category")
public String bookCategory(@RequestParam(value="page" ,defaultValue="1") int page,Model model){
		model.addAttribute("number", number);
		model.addAttribute("Total", Total);
		model.addAttribute("bookcount",bookMapper.selectByDiscount());
		
		//System.out.println(page);
		List<Book> books = categoryMapper.findAllBook(page);
//		System.out.println("------");
//		for(Book book: books){
//			System.out.println(book);
//		}
		//model.addAttribute("type", "全锟斤拷锟斤拷锟�");
		model.addAttribute("book", books);
		return"category";

	
}	
	
	
	
	
	@GetMapping("/contact")
	public String bookContact(Model model){
		model.addAttribute("number", number);
		model.addAttribute("Total", Total);
		model.addAttribute("bookcount",bookMapper.selectByDiscount());
		return "contact";
	}	
	
	@GetMapping("/details")
	public String bookDetails(@RequestParam int cz,Model model){
		
		//model.addAttribute("book", bk);
	//	System.out.println("++++++++++++");
		//System.out.println(cz);
		Book book=bookMapper.findOneBook(cz);
		model.addAttribute("number", number);
		model.addAttribute("Total", Total);
		//System.out.println(book);
		model.addAttribute("book", book);
		
		
//		List<Book> book1=bookMapper.selectByTypeId(book.getBooktypeId(),book.getBookId());
		/*for(Book books:book1){
			System.out.println(books);
		}*/
		model.addAttribute("number", number);
		model.addAttribute("Total", Total);
		model.addAttribute("booktype", bookMapper.selectByTypeId(book.getBooktypeId(),book.getBookId()));
		model.addAttribute("bookcount",bookMapper.selectByDiscount());
		return "details";
	}	
	@GetMapping("/license")
	public String bookLicense(Model model){
		model.addAttribute("bookcount",bookMapper.selectByDiscount());
		return "license";
	}	
	
	/*@GetMapping("/myaccount")
	public String bookMyaccount(Model model){
		model.addAttribute("bookcount",bookMapper.selectByDiscount());
		return "myaccount";
	}	*/
	
	@GetMapping("/register")
	public String bookRegister(Model model){
		model.addAttribute("number", number);
		model.addAttribute("Total", Total);
		model.addAttribute("bookcount",bookMapper.selectByDiscount());
		return "register";
	}	
	@GetMapping("/specials")
	public String bookSpecials(Model model){
		model.addAttribute("number", number);
		model.addAttribute("Total", Total);
		model.addAttribute("bookcount",bookMapper.selectByDiscount());
		model.addAttribute("bookAllcount",bookMapper.selectAllByDiscount());
		return "specials";
	}
	
	
	@GetMapping("/categoryType")
	public String findTypeBook(@RequestParam String type, Model model){
		model.addAttribute("bookcount",bookMapper.selectByDiscount());

		List<Book> books = categoryMapper.findTypeBook(type);
/*		for(Book book: books){
			System.out.println(book);
		}*/
		model.addAttribute("number", number);
		model.addAttribute("Total", Total);
		model.addAttribute("type", type);
		model.addAttribute("book",books);
		return "category";
	}

	
	@GetMapping("/search")
	public String findbookname(@RequestParam("search") String bookname, Model model){
		model.addAttribute("bookcount",bookMapper.selectByDiscount());

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
	public String order(Model model,HttpSession session){
		int userId = -1;
		if(session.getAttribute("userid")!=null){
			userId = (int) session.getAttribute("userid");
		}
		List<Orders> order = orderMapper.findIdOrder(userId);
		model.addAttribute("order", order);
		model.addAttribute("number", number);
		model.addAttribute("Total", Total);
		return "order";
	}
}
