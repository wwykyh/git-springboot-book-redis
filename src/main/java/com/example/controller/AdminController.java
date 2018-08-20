package com.example.controller;

//import io.lettuce.core.api.async.RedisAsyncCommands;

import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.async.RedisAsyncCommands;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import java.util.Map;






//import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;







import com.example.Lettuce.RedisCli;
//import com.example.Lettuce.RedisCli;
import com.example.mapper.AdMapper;
import com.example.mapper.AdminMapper;
import com.example.mapper.BookMapper;
import com.example.mapper.MessageMapper;
import com.example.model.Admin;
import com.example.model.Book;
import com.example.model.Category;
import com.example.model.Messages;
//import com.example.model.Users;

@Controller
public class AdminController {

	@Autowired
	AdminMapper adminMapper;
	@Autowired
	BookMapper bookMapper;
	@Autowired
	AdMapper adMapper;
	
	@Autowired
	MessageMapper messageMapper;
	
	Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@GetMapping("/admin/index")
	public String index(Model model) throws Exception{
		logger.debug("----------runing.........-------------------");
		List<Book> books = null;
		books = readBook();
		if(books.size()==0){
			logger.debug("----read from database");
			books= adminMapper.findAllBook();
			writeBook(books);
			
		}
		
		//List<Book> books = adminMapper.findAllBook();
		model.addAttribute("books", books);
		return "admin/index";
	}

	private void writeBook(List<Book> books) {
		// TODO Auto-generated method stub
		logger.debug("----write from database");
		
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
			mapBook.put("booktypeName",book.getCategory().getTypeName());
			asyncCommands.hmset("BookA:"+book.getBookId(), mapBook);
		}
	}

	private List<Book> readBook() throws Exception{
		// TODO Auto-generated method stub
		
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		final List<Book> books = new ArrayList<Book>();
		RedisFuture<List<String>> bookKeys = asyncCommands.keys("BookA*");
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
			Category category = new Category();
			category.setTypeName(map.get("booktypeName"));
			book.setCategory(category);
			books.add(book);
		}
		logger.debug("----------read from redis-------------------");
		return books;
	}

	@GetMapping("/login")
	public String Login() {	
		return "login";
	}
	
	
	
	@PostMapping("/doLogin")
	public String doLogin(@RequestParam String adminname,
			@RequestParam String adminpassword, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		// System.out.println(username + "  " + password);
		Admin admin = adMapper.selectAdmin(adminname, adminpassword);
		// System.out.println(user.getUserId());
		System.out.println(admin);
		if (admin != null) {
			//session.setAttribute("userid", admin.getAdminId());
			session.setAttribute("adminname", admin.getAdminName());
			session.setMaxInactiveInterval(900);
			// System.out.println(session.getAttribute("username"));
			// System.out.println(request.getParameter("remember"));
			if (request.getParameter("remember") != null) {
				Cookie ckadminname = new Cookie("adminname", adminname);
				ckadminname.setMaxAge(3600);
				response.addCookie(ckadminname);
			} else {
				for (Cookie ckadminname : request.getCookies()) {
					if (ckadminname.getName().equals("adminname")) {
						// System.out.println("=========ck=========");
						ckadminname.setValue(null);
						ckadminname.setMaxAge(0);
						response.addCookie(ckadminname);
						break;

					}
				}
			}

			//session.setAttribute("username", username);
			// System.out.println("=================");
			RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
			asyncCommands.flushall();
			request.setAttribute("tip", "");
			return "redirect:/admin/index";
		} else {
			// System.out.println("----------------------");
			request.setAttribute("tip", "用户名或者密码错误");
			return "redirect:/login";
		}
	
	}
	

	@GetMapping("/admin/adminExit")
	public String exit(Model model, HttpSession session,
			HttpServletRequest request) {
		session.removeAttribute("adminname");
		//session.setAttribute("tip", "�˳��ɹ���");
		return "login";
	}
	
	
	@GetMapping("/admin/add")
	public String add(Model model) throws Exception{
		/*List<Category> types = null;
		types = readType();
		if(types.size()==0){
			types = adminMapper.findAllType();
			writeType(types);
		}
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		RedisFuture<List<String>> categoryKeys = asyncCommands.keys("category*");
		List<String> categorykeys = categoryKeys.get();
		for(String key : categorykeys){
			asyncCommands.del(key);
		}*/
		
		
		List<Category> types = adminMapper.findAllType();
		model.addAttribute("types", types);
		return "admin/add";
	}
	
	
	
	

	@PostMapping("/admin/doadd")
	public String doadd(@RequestParam String bookName,@RequestParam int booktypeId,@RequestParam String bookAuthor,
			@RequestParam String bookEdition,@RequestParam float bookPrice,@RequestParam int bookAmount,
			@RequestParam String bookIntroduce,@RequestParam String issuanceDate,@RequestParam MultipartFile file,
			@RequestParam int bookSpecil,@RequestParam float bookDiscount,Model model,HttpServletRequest request,HttpSession session) throws IllegalStateException, IOException {
		
		/**
		 * �ļ��ϴ�
		 */
//		System.out.println(booktypeId);
		String filenewname="";
		if(file.isEmpty()){ 
        	//System.out.println("�ļ�δ�ϴ�!");
        	return "admin/add";
        }
        else{
        	//�õ��ϴ����ļ���
			String fileName = file.getOriginalFilename();
			System.out.println("------file----------"+fileName);
			//�õ���������Ŀ�����������ڵ�ַ
			String path1 = request.getSession().getServletContext().getRealPath("/WEB-INF/bookImage")+File.separator;
			//  �˴�δʹ��UUID������Ψһ��ʶ,��������Ϊ��ʶ
			 filenewname = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ fileName;
			 System.out.println("++++++++"+filenewname);
			String path = path1+ filenewname;
			//�鿴�ļ��ϴ�·��,�������
			System.out.println(path);
			//���ļ��ϴ���path��·��
			File localFile = new File(path);
			file.transferTo(localFile);
			/*imagenames.add(filenewname);*/
			}
		
		Book book = new Book();
		book.setBookAmount(bookAmount);
		book.setBookAuthor(bookAuthor);
		book.setBookDiscount(bookDiscount);
		book.setBookEdition(bookEdition);
		book.setBookIntroduce(bookIntroduce);
		book.setBookName(bookName);
		book.setBookPrice(bookPrice);
		book.setBooktypeId(booktypeId);
		book.setIssuanceDate(issuanceDate);
		//book.setBookIntroduce(bookIntroduce);
		book.setBookSpecil(bookSpecil);
		book.setBookImages(filenewname);
		System.out.println(book);
		
		int i =adminMapper.inserteBook(book);
		writeOneBook(book);
		System.out.println("----------"+i+"---------------");
		session.setAttribute("tip", "添加书籍成功！");
		
		return "redirect:/admin/index";
	}
	
	private void writeOneBook(Book book) {
		// TODO Auto-generated method stub
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
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
			mapBook.put("booktypeName",book.getCategory().getTypeName());
			asyncCommands.hmset("BookA:"+book.getBookId(), mapBook);
	}

	@GetMapping("/admin/addtype")
	public String addType() {
		return "admin/addtype";
	}
	
	@PostMapping("/admin/doaddtype")
	public String doaddtype(Category category,HttpSession session){
	int i = adminMapper.inserteBookType(category);
	WriteOneType(category);
	System.out.println("----------"+i+"---------------");
	session.setAttribute("tip", "添加类型成功！");
	return "redirect:/admin/type";
	}
	
	
	private void WriteOneType(Category category) {
		// TODO Auto-generated method stub
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
			Map<String, String> map = new HashMap<String,String>();
			map.put("typeId", String.valueOf(category.getTypeId()));
			map.put("typeName", category.getTypeName());
			map.put("typeIntroduce", category.getTypeIntroduce());
			asyncCommands.hmset("category:"+category.getTypeId(), map);
	}

	@GetMapping("/admin/xiugai")
	public String Edit(@RequestParam int id,Model model) {
		System.out.println(id);
		Book book =adminMapper.findOneBook(id);
		//System.out.println(book);
		model.addAttribute("book", book);
		return "admin/edit";
	}
	
	@PostMapping("/admin/doEdit")
	public String doEdit(@RequestParam int bookId,@RequestParam float bookPrice,@RequestParam int bookAmount,
	@RequestParam MultipartFile file,@RequestParam String bookIntroduce,
			@RequestParam int bookSpecil,@RequestParam float bookDiscount,Model model,HttpServletRequest request,HttpSession session) throws IllegalStateException, IOException {
		//System.out.println(book);
		
		String filenewname="";
		if(file.isEmpty()){ 
        	//System.out.println("�ļ�δ�ϴ�!");
        	return "admin/add";
        }
        else{
        	//�õ��ϴ����ļ���
			String fileName = file.getOriginalFilename();
			System.out.println("------file----------"+fileName);
			//�õ���������Ŀ�����������ڵ�ַ
			String path1 = request.getSession().getServletContext().getRealPath("/WEB-INF/bookImage")+File.separator;
			//  �˴�δʹ��UUID������Ψһ��ʶ,��������Ϊ��ʶ
			 filenewname = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ fileName;
			 System.out.println("++++++++"+filenewname);
			String path = path1+ filenewname;
			//�鿴�ļ��ϴ�·��,�������
			System.out.println(path);
			//���ļ��ϴ���path��·��
			File localFile = new File(path);
			file.transferTo(localFile);
			/*imagenames.add(filenewname);*/
			}
		
		Book book = new Book();
		book.setBookId(bookId);
		book.setBookPrice(bookPrice);	
		book.setBookAmount(bookAmount);
		book.setBookIntroduce(bookIntroduce);
		book.setBookSpecil(bookSpecil);
		book.setBookImages(filenewname);
		book.setBookDiscount(bookDiscount);
		System.out.println(book);
		
		int i =adminMapper.updateBook(book);
		writeOneBook(book);
		session.setAttribute("tip", "修改书籍成功！");
		System.out.println("i:"+i);
		return "redirect:/admin/index";
	}
	
	@GetMapping("/admin/delete")
	public String delete(@RequestParam int id,Model model,HttpSession session) {
		System.out.println(id);
		int i =adminMapper.deleteBook(id);
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		asyncCommands.flushall();
		//System.out.println(book);
		//model.addAttribute("book", book);
		session.setAttribute("tip", "删除书籍成功！");
		return "redirect:/admin/index";
	}
	
	@GetMapping("/admin/type")
	public String type(Model model) throws Exception{
		
		
		logger.debug("----------runing.........-------------------");
		List<Category> types = null;
		types = readType();
		//System.out.println(types.size());
		if(types.size()==0){
			logger.debug("----------read from database-------------------");
			types = adminMapper.findAllType();
			writeType(types);
		}
		/*RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		RedisFuture<List<String>> categoryKeys = asyncCommands.keys("category*");
		List<String> categorykeys = categoryKeys.get();*/
		/*for(String key : categorykeys){
			asyncCommands.del(key);
		}*/
		model.addAttribute("types", types);
		return "admin/type";
	}
	private void writeType(List<Category> categorys) {
		// TODO Auto-generated method stub
		
		logger.debug("----write from redis");
	   /* for(Category cat:categorys){
	    	System.out.println(cat);
	    }*/
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		for(Category category: categorys){
			Map<String, String> map = new HashMap<String,String>();
			map.put("typeId", String.valueOf(category.getTypeId()));
			map.put("typeName", category.getTypeName());
			map.put("typeIntroduce", category.getTypeIntroduce());
			asyncCommands.hmset("category:"+category.getTypeId(), map);
			//System.out.println("-----------------------------");
		}
	}

	private List<Category> readType() throws Exception{
		// TODO Auto-generated method stub
	//	System.out.println("1111111111111111111111111111111111");
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		final List<Category> categorys = new ArrayList<Category>();
		RedisFuture<List<String>> categoryKeys = asyncCommands.keys("category*");
		List<String> categorykeys = categoryKeys.get();
		
		if(categorykeys.size()==0)
			return categorys;
		for(String key: categorykeys){
			RedisFuture<Map<String, String>> futureMap = asyncCommands.hgetall(key);
			Map<String, String> map = futureMap.get(); //它阻塞和等待直到承诺的结果是可用状态			
			Category category = new Category(); 
			category.setTypeId(Integer.valueOf(map.get("typeId")));
			category.setTypeName(map.get("typeName"));
			category.setTypeIntroduce(map.get("typeIntroduce"));
			categorys.add(category);
			
		}
		logger.debug("----------read from redis-------------------");
		/* for(Category cat:categorys){
		    	System.out.println(cat);
		    }*/
		return categorys;
	}
	
	@GetMapping("/admin/updatetype")
	public String updatetype(@RequestParam int id,Model model){
		System.out.println(id);
		Category type= adminMapper.findOneType(id);
		System.out.println(type);
		/*for(Book books:book){
			System.out.println(books);
		}*/
		model.addAttribute("type", type);
		return "admin/updatetype";
	}

	@PostMapping("/admin/doupdatetype")
	public String doupdatetype(Category categoyr,HttpSession session){
		int i= adminMapper.updateType(categoyr);
		writeOneType(categoyr);
		session.setAttribute("tip", "修改类型成功");
		return "redirect:/admin/type";
	}
	
	private void writeOneType(Category category) {
		// TODO Auto-generated method stub
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
			Map<String, String> map = new HashMap<String,String>();
			map.put("typeId", String.valueOf(category.getTypeId()));
			map.put("typeName", category.getTypeName());
			map.put("typeIntroduce", category.getTypeIntroduce());
			asyncCommands.hmset("category:"+category.getTypeId(), map);
	}

	@GetMapping("/admin/deletetype")
	public String deletetype(@RequestParam int id,Model model,HttpSession session) throws Exception{
		//System.out.println(id);
		int i =adminMapper.deleteType(id);
		RedisAsyncCommands<String, String> asyncCommands = RedisCli.connection.async();
		RedisFuture<List<String>> categoryKeys = asyncCommands.keys("category*");
		List<String> categorykeys = categoryKeys.get();
		for(String key : categorykeys){
			asyncCommands.del(key);
		}
		return "redirect:/admin/type";
	}
	
	@PostMapping("/admin/adminsearch")
	public String adminsearch(@RequestParam("search") String bookname, Model model){
		List<Book> books = bookMapper.selectByName("%"+bookname+"%");
		/*for(Book books:book){
			System.out.println(books);
		}*/
		model.addAttribute("books", books);
		return "admin/index";
	}
	
	@PostMapping("/admin/adminsearchMessage")
	public String adminsearchMessage(@RequestParam("search") String messagename, Model model){
		List<Messages> messages = messageMapper.findByMessagename("%"+messagename+"%");
		/*for(Messages books:messages){
			System.out.println(books);
		}*/
		model.addAttribute("message", messages);
		return "admin/message";
	}
	
	@PostMapping("/admin/adminsearchType")
	public String adminsearchType(@RequestParam("search") String messagename, Model model){
		List<Category> types= adminMapper.findByType("%"+messagename+"%");
		/*for(Category books:types){
			System.out.println(books);
		}*/
		
		model.addAttribute("types", types);
		return "admin/type";
	}
	
}
