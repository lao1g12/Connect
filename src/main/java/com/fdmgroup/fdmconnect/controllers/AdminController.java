package com.fdmgroup.fdmconnect.controllers;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.fdmconnect.daos.PostDAOImpl;
import com.fdmgroup.fdmconnect.daos.UserDAOImpl;
import com.fdmgroup.fdmconnect.entities.Profile;
import com.fdmgroup.fdmconnect.entities.User;
import com.fdmgroup.fdmconnect.controllers.Logging;

public class AdminController {
	
	
	@Autowired
	private PostDAOImpl postDao;
	@Autowired
	private UserDAOImpl userDao;

	public AdminController() {}
	
	public AdminController(PostDAOImpl postDao) { 
		super();
		this.postDao = postDao;
		this.userDao = userDao;
	}
	
	@RequestMapping("/admin")
	public String admin() {

		return "admin/Home";
	}
	
	@RequestMapping("/addPost")
	public String addNewPost() {

		
		return "Home"; 
		
	}
	
	@RequestMapping("/admin/goToAddUser")
	public String goToAddUser(HttpSession session, Model model){
		
		User user = new User();
		Profile profile = new Profile();
		user.setProfile(profile);
		
		model.addAttribute("user", user);
		return "admin/AddUser";
		
	}
	
	@RequestMapping("/admin/doAddUser")
	public String doAddUser(HttpSession session, Model model, User user,
			@RequestParam(name="startDate") String startDate, @RequestParam(name="endDate") String endDate){
	
		if (!user.getPassword().equals(user.getConfirmPassword())){
			model.addAttribute("passwordErrorMessage","Passwords do not match");
			return "admin/AddUser";
		}
		
		user.setEmail(user.getUsername()+"@fdmgroup.com");
		
//		Profile profile = user.getProfile();
//		Calendar temp = Calendar.getInstance();
//		
//		// Set start and end date
//		String[] date = startDate.split("/");
//		temp.set(Integer.parseInt(date[0]),Integer.parseInt(date[1]),
//				Integer.parseInt(date[2]));
//		
//		profile.setStartDate(temp);
		
		try {
			userDao.addUser(user);
		} catch (PersistenceException pe) {
			model.addAttribute("userErrorMessage", "Username already exists.");
			return "admin/AddUser";
		}
		
		Logging.Log("info", "Admin Controller: "+session.getAttribute("username")+" added user "
				+user.getUsername());
		model.addAttribute("userAddedMessage", "User successfully added, they can now update their profile.");
		return "admin/Home";
		
	}
	
}
