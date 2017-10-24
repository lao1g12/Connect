package com.fdmgroup.fdmconnect.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.fdmconnect.daos.PostDAOImpl;
import com.fdmgroup.fdmconnect.daos.UserDAOImpl;
import com.fdmgroup.fdmconnect.entities.User;

@Controller
public class AdminController {

	@Autowired
	private PostDAOImpl postDao;
	@Autowired
	private UserDAOImpl userDao;

	public AdminController() {
	}

	public AdminController(PostDAOImpl postDao, UserDAOImpl userDao) {
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
	public String goToAddUser(HttpSession session, Model model) {

		User user = new User();
		model.addAttribute("user", user);
		return "admin/AddUser";

	}

	@RequestMapping("/admin/doAddUser")
	public String doAddUser(HttpSession session, Model model, User user) {

		return "";

	}
	@RequestMapping("/admin/processRemoveUser")
	public String processRemoveUser(@RequestParam String username, Model model){
	Logging.Log("info", "user removed succesfully" + username);
	userDao.removeUser(username);
	model.addAttribute("message", "User removed succesfully");
	return "admin/RemoveUser";
}

	@RequestMapping("/admin/...........")
	public String goToViewAllUsers(Model model){
		Logging.Log("trace", "Client request to url : Display All Users");
	List<User> users = userDao.getAllUsers();
	model.addAttribute("users", users);
	return "admin/DisplayAllUsers";
	}


}
