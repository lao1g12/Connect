package com.fdmgroup.fdmconnect.controllers;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.fdmconnect.daos.PostDAOImpl;
import com.fdmgroup.fdmconnect.entities.User;

@Controller
public class AdminController {
	
	
	@Autowired
	private PostDAOImpl postDao;

	public AdminController() {}
	
	public AdminController(PostDAOImpl postDao) { 
		super();
		this.postDao = postDao;
	}
	@RequestMapping("/admin")
	public String admin(){
		
		return "admin/Home";
	}
	
	@RequestMapping("/addPost")
	public String addNewPost() {

		
		return "Home"; 
		
	}
	
	@RequestMapping("/admin/goToAddUser")
	public String goToAddUser(HttpSession session, Model model){
		
		User user = new User();
		model.addAttribute("user", user);
		return "admin/AddUser";
		
	}
	
	@RequestMapping("/admin/doAddUser")
	public String doAddUser(HttpSession session, Model model, User user){
	
		return "";
		
		
	}
	

}
