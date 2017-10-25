package com.fdmgroup.fdmconnect.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.fdmconnect.daos.ProfileDAOImpl;
import com.fdmgroup.fdmconnect.daos.UserDAOImpl;
import com.fdmgroup.fdmconnect.entities.Profile;
import com.fdmgroup.fdmconnect.entities.User;

@Controller
public class UserController {
	
	@Autowired
	private UserDAOImpl userDao;
	@Autowired
	private ProfileDAOImpl profileDao;
	
	public UserController() {}

	public UserController(UserDAOImpl userDao, ProfileDAOImpl profileDao) {
		super();
		this.userDao = userDao;
		this.profileDao = profileDao;
	}
	
	
	@RequestMapping(value={"user/createProfile","admin/createProfile"})
	public String createPofile(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		Profile profile = new Profile(); 
		
		
		return null; 
		
	}
	
	
	
	
}
