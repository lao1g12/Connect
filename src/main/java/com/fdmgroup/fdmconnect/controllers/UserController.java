package com.fdmgroup.fdmconnect.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
	
	Logger logger = Logger.getLogger(getClass());
	
	public UserController() {}

	public UserController(UserDAOImpl userDao, ProfileDAOImpl profileDao) {
		super();
		this.userDao = userDao;
		this.profileDao = profileDao;
	}
	
	
	@RequestMapping("user/account")
	public String createPofile(Model model, HttpSession session, Principal principal) {
		
		User user = userDao.getUser(principal.getName());
		Profile profile = user.getProfile();
		model.addAttribute("profile", profile);
		logger.info(session.getAttribute("username")+"going to profile");
		return "user/ViewPersonalAccount"; 
		
	}
	
	
	
	
}
