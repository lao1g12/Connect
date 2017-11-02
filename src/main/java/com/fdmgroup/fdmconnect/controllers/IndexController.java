package com.fdmgroup.fdmconnect.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.fdmconnect.daos.NotificationDAO;
import com.fdmgroup.fdmconnect.daos.PostDAO;
import com.fdmgroup.fdmconnect.daos.UserDAO;
import com.fdmgroup.fdmconnect.entities.Profile;
import com.fdmgroup.fdmconnect.entities.User;
import com.fdmgroup.fdmconnect.controllers.Logging;


@Controller
public class IndexController {

	@Autowired
	private UserDAO userDao;
	@Autowired
	private PostDAO postDao;
	@Autowired
	private NotificationDAO notificationDao;
	
	public IndexController() {}

	public IndexController(UserDAO userDao, PostDAO postDao, NotificationDAO notificationDao) {
		super();
		this.userDao = userDao;
		this.postDao = postDao;
		this.notificationDao = notificationDao;
	}
	
	@RequestMapping("/")
	public String goToIndex() {
		return "index";
	}
	
	
	@RequestMapping("/user/login")
	public String goToHome(HttpSession session, Principal principal, HttpServletRequest request) {

		User user = userDao.getUser(principal.getName());
		Profile profile = user.getProfile();
		session.setAttribute("username", principal.getName());
		session.setAttribute("user", user);
		session.setAttribute("profile", profile);
		
		if(user.getLastLogin() == null){
			return "user/UpdatePassword";
		}
		
		user.setLastLogin();
		Logging.Log("info", "Index Controller: "+session.getAttribute("username")+" has logged in.");
		session.setAttribute("allPosts", postDao.getAllPostsWhereGroupsAll());
		session.setAttribute("notifications", 
				notificationDao.getAllNotificationsByUser(principal.getName()));
		
		return "user/Home";
		
	}
	
	@RequestMapping(value = { "/admin/goToAdmin", "/user/goToAdmin"})
	public String goToAdmin(Model model){

		return "redirect:/admin";
		
	}
	
	@RequestMapping(value = { "/admin/logout", "/user/logout"})
	public String goToLogout(HttpSession session) {
		
		Logging.Log("info", "Index Controller: "+session.getAttribute("username")+" has logged out.");
		session.invalidate();
		return "redirect:/";
		
	}
	
	@RequestMapping("/user/passwordUpdate")
	public String doPasswordUpdate(HttpServletRequest request, HttpSession session) {
		
		String newPassword = request.getParameter("newPassword");
		String confNewPassword = request.getParameter("confNewPassword");
		User user = (User) session.getAttribute("user");
		
			if (newPassword.equals(confNewPassword)) {
				
				request.setAttribute("UpdatedPass", "Your password has been succesfully changed");
				user.setPassword(newPassword);
				user.setLastLogin();
				userDao.updateUser(user);
				Logging.Log("info", "Index Controller: "+user.getUsername()+" updated their password");
				return "redirect:/user/login";
				
			} else {
				
				request.setAttribute("passNotMatch", "The two passwords you entered do not match!");
				Logging.Log("info", "Index Controller: "+user.getUsername()+" attempted to change password but the two new passwords were different, redirected to the UpdateInfo page");
				return "user/UpdatePassword";
				
			}
		
	}
	@RequestMapping(value = { "/admin/goHome", "/user/goHome"})
	public String goHome(){
		
		return "redirect:/user/login";
		
	}
	
	
	
	
}
