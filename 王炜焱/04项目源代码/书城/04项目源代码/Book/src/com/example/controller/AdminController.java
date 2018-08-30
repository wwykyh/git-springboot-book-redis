package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
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
import org.springframework.web.multipart.MultipartFile;

import com.example.mapper.AdMapper;
import com.example.mapper.AdminMapper;
import com.example.mapper.BookMapper;
import com.example.mapper.MessageMapper;
import com.example.model.Admin;
import com.example.model.Book;
import com.example.model.Category;
import com.example.model.Messages;
import com.example.model.Users;

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
	
	@GetMapping("/admin/index")
	public String index(Model model){
		List<Book> books= adminMapper.findAllBook();
		/*for(Book books:book){
			System.out.println(books);
		}*/
		model.addAttribute("books", books);
		return "admin/index";
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
		//session.setAttribute("tip", "退出成功！");
		return "login";
	}
	
	
	@GetMapping("/admin/add")
	public String add(Model model) {
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
		 * 文件上传
		 */
//		System.out.println(booktypeId);
		String filenewname="";
		if(file.isEmpty()){ 
        	System.out.println("文件未上传!");
        	return "admin/add";
        }
        else{
        	//得到上传的文件名
			String fileName = file.getOriginalFilename();
			System.out.println("------file----------"+fileName);
			//得到服务器项目发布运行所在地址
			String path1 = request.getSession().getServletContext().getRealPath("/WEB-INF/bookImage")+File.separator;
			//  此处未使用UUID来生成唯一标识,用日期做为标识
			 filenewname = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ fileName;
			 System.out.println("++++++++"+filenewname);
			String path = path1+ filenewname;
			//查看文件上传路径,方便查找
			System.out.println(path);
			//把文件上传至path的路径
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
		System.out.println("----------"+i+"---------------");
		session.setAttribute("tip", "添加书籍成功！");
		
		return "redirect:/admin/index";
	}
	
	@GetMapping("/admin/addtype")
	public String addType() {
		return "admin/addtype";
	}
	
	@PostMapping("/admin/doaddtype")
	public String doaddtype(Category categoyr,HttpSession session){
	int i = adminMapper.inserteBookType(categoyr);
	System.out.println("----------"+i+"---------------");
	session.setAttribute("tip", "添加类型成功！");
	return "redirect:/admin/type";
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
        	System.out.println("文件未上传!");
        	return "admin/add";
        }
        else{
        	//得到上传的文件名
			String fileName = file.getOriginalFilename();
			System.out.println("------file----------"+fileName);
			//得到服务器项目发布运行所在地址
			String path1 = request.getSession().getServletContext().getRealPath("/WEB-INF/bookImage")+File.separator;
			//  此处未使用UUID来生成唯一标识,用日期做为标识
			 filenewname = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ fileName;
			 System.out.println("++++++++"+filenewname);
			String path = path1+ filenewname;
			//查看文件上传路径,方便查找
			System.out.println(path);
			//把文件上传至path的路径
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
		session.setAttribute("tip", "修改书籍成功！");
		System.out.println("i:"+i);
		return "redirect:/admin/index";
	}
	
	@GetMapping("/admin/delete")
	public String delete(@RequestParam int id,Model model,HttpSession session) {
		System.out.println(id);
		int i =adminMapper.deleteBook(id);
		//System.out.println(book);
		//model.addAttribute("book", book);
		session.setAttribute("tip", "删除书籍成功！");
		return "redirect:/admin/index";
	}
	
	
	@GetMapping("/admin/liuyan")
	public String  messages(Model model){
		List<Messages> messages = messageMapper.findAllMessage();
		model.addAttribute("message", messages);
		return "admin/message";
	}
	
	@GetMapping("/deleteMassage")
	public String deleteMassage(@RequestParam(value="messageid",required = false) String messageid,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		int id = -1;
		if(messageid!=null){
			id = Integer.parseInt(messageid);
		}
		messageMapper.deleteIdMessage(id);
		System.out.println(id);
		session.setAttribute("tip", "删除留言成功！");
			return "redirect:/admin/liuyan";
		
	}

	@GetMapping("/admin/type")
	public String type(Model model){
		List<Category> types= adminMapper.findAllType();
	/*	for(Category type:types){
			System.out.println(type);
		}*/
		model.addAttribute("types", types);
		return "admin/type";
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
		/*for(Book books:book){
			System.out.println(books);
		}*/
		session.setAttribute("tip", "删除类型成功！");
		return "redirect:/admin/type";
	}
	
	@GetMapping("/admin/deletetype")
	public String deletetype(@RequestParam int id,Model model,HttpSession session) {
		//System.out.println(id);
		int i =adminMapper.deleteType(id);
		//System.out.println(book);
		//model.addAttribute("book", book);
		/*session.setAttribute("tip", i);*/
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
