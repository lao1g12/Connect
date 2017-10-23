package com.fdmgroup.fdmconnect.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.fdmconnect.daos.UserDAOImpl;
import com.fdmgroup.fdmconnect.entities.User;


@Controller
public class Index {

	@Autowired
	private UserDAOImpl userDao;

	public Index() {}

	public Index(UserDAOImpl userDao) {
		super();
		this.userDao = userDao;
	}
	
	@RequestMapping("/")
	public String goToIndex() {
		return "index";
	}
	
	@RequestMapping("/home")
	public String goToAdminPortal(HttpSession session, Principal principal) {

		User user = userDao.getUser(principal.getName());
		session.setAttribute("username", principal.getName());
		session.setAttribute("user", user);
		Logging.Log("info", "Index Controller:"+session.getAttribute("username")+" has logged in.");
		return "Home";
		
	}
	
	
	
	
}
