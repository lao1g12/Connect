package com.fdmgroup.fdmconnect.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.fdmconnect.daos.PostDAOImpl;
import com.fdmgroup.fdmconnect.daos.UserDAOImpl;
import com.fdmgroup.fdmconnect.entities.User;
import com.fdmgroup.fdmconnect.controllers.Logging;


@Controller
public class IndexController {

	@Autowired
	private UserDAOImpl userDao;
	@Autowired
	private PostDAOImpl postDao;

	public IndexController() {}

	public IndexController(UserDAOImpl userDao, PostDAOImpl postDao) {
		super();
		this.userDao = userDao;
		this.postDao = postDao;
	}
	
	@RequestMapping("/")
	public String goToIndex() {
		return "index";
	}
	
	
	@RequestMapping("/user/login")
	public String goToHome(HttpSession session, Principal principal) {

		User user = userDao.getUser(principal.getName());
		session.setAttribute("username", principal.getName());
		session.setAttribute("user", user);
		Logging.Log("info", "Index Controller: "+session.getAttribute("username")+" has logged in.");
		session.setAttribute("allPosts", postDao.getAllPosts());
		
		return "user/Home";
		
	}
	
	@RequestMapping("/user/logout")
	public String goToLogout(HttpSession session) {
		
		Logging.Log("info", "Index Controller: "+session.getAttribute("username")+" has logged out.");
		session.invalidate();
		return "redirect:/";
		
	}
	
	
	
	
}
