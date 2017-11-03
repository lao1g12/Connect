package com.fdmgroup.fdmconnect.controllers;

import javax.persistence.PersistenceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fdmgroup.fdmconnect.daos.FlagDAO;
import com.fdmgroup.fdmconnect.daos.GroupDAO;
import com.fdmgroup.fdmconnect.daos.PostDAO;
import com.fdmgroup.fdmconnect.daos.UserDAO;
import com.fdmgroup.fdmconnect.entities.Flag;
import com.fdmgroup.fdmconnect.entities.Group;
import com.fdmgroup.fdmconnect.entities.Post;
import com.fdmgroup.fdmconnect.entities.Profile;
import com.fdmgroup.fdmconnect.entities.User;
import com.fdmgroup.fdmconnect.controllers.Logging;

@Controller
public class AdminController {

	@Autowired
	private PostDAO postDao;
	@Autowired
	private UserDAO userDao;
	@Autowired
	private FlagDAO flagDao;
	@Autowired
	private GroupDAO groupDao;

	public AdminController() {
	}

	public AdminController(PostDAO postDao, UserDAO userDao, FlagDAO flagDao, GroupDAO groupDao) {
		super();
		this.postDao = postDao;
		this.userDao = userDao;
		this.flagDao = flagDao;
		this.groupDao = groupDao;
	}

	@RequestMapping("/admin")
	public String admin(Model model) {

		Flag flag = flagDao.getFlag(1);
		model.addAttribute("flag", flag);
		return "admin/Home";
		
	}

	@RequestMapping("/user/processRemovePostAdmin")
	public String processRemovePostAdmin(@RequestParam int postId, Model model, HttpSession session) {

		postDao.removePost(postId);
		Logging.Log("post", "post removed succesfully by admin" + postId);
		model.addAttribute("postRemovedByAdmin", "Post removed succesfully.");
		session.setAttribute("allPosts", postDao.getAllPostsWhereGroupsAll());
		
		return "user/Home";

	}

	@RequestMapping("/admin/viewAllPosts")
	public String goToViewAllPosts(Model model) {

		List<Post> posts = postDao.getAllPosts();
		model.addAttribute("post", posts);
		Logging.Log("trace", "Admin Controller: Display all posts called.");
		return "admin/DisplayAllPosts";

	}

	@RequestMapping("/admin/processRemovePost")
	public String processRemovePost(@RequestParam int postId, Model model, RedirectAttributes ra) {

		postDao.removePost(postId);
		ra.addFlashAttribute("message", "Post removed succesfully.");
		Logging.Log("info", "Admin Controller: Post removed succesfully " + postId);
		return "redirect:/admin/viewAllFlaggedPosts";

	}

	@RequestMapping("/admin/viewAllFlags")
	public String goToViewAllFlags(Model model, @RequestParam int postId, HttpServletRequest request) {

		Post post = postDao.getPost(postId);
		Set<Flag> flagList = post.getFlags();
		request.setAttribute("flagList", flagList);
		request.setAttribute("post", post);
		Logging.Log("info", "Admin Controller: Display all flags called.");
		return "admin/DisplayAllFlags";

	}

	@RequestMapping("/admin/goToAddUser")
	public String goToAddUser(HttpSession session, Model model) {

		User user = new User();
		Profile profile = new Profile();
		user.setProfile(profile);

		model.addAttribute("user", user);
		Logging.Log("info", "Admin Controller: Add user called.");
		return "admin/AddUser";

	}

	@RequestMapping("/admin/doAddUser")
	public String doAddUser(HttpSession session, Model model, User user) {

		if (!user.getPassword().equals(user.getConfirmPassword())) {
			model.addAttribute("passwordErrorMessage", "Passwords do not match");
			Logging.Log("warn", "Passwords did not match");
			return "admin/AddUser";
		}

		user.setEmail(user.getUsername() + "@fdmgroup.com");

		try {
			userDao.addUser(user);
		} catch (PersistenceException pe) {
			model.addAttribute("userErrorMessage", "Username already exists.");
			return "admin/AddUser";
		}

		Logging.Log("info",
				"Admin Controller: " + session.getAttribute("username") + " added user " + user.getUsername());
		model.addAttribute("userAddedMessage", "User successfully added, they can now update their profile.");
		return "redirect:/admin";

	}

	@RequestMapping("/admin/processRemoveUser")
	public String processRemoveUser(@RequestParam String username, Model model, RedirectAttributes ra) {

		userDao.removeUser(username);
		ra.addFlashAttribute("message", "User removed succesfully");
		Logging.Log("info", "Admin Controller: User removed succesfully" + username);
		return "redirect:/admin/viewAllUsers";

	}

	@RequestMapping("/admin/viewAllUsers")
	public String goToViewAllUsers(Model model) {

		List<User> users = userDao.getAllUsers();
		model.addAttribute("users", users);
		Logging.Log("info", "Admin Controller: Display all users called");
		return "admin/DisplayAllUsers";

	}

	@RequestMapping("/admin/viewAllFlaggedPosts")
	public String goToViewAllFlaggedPosts(Model model, HttpServletRequest request) {

		Logging.Log("info", "Admin Controller: Display all flagged posts called.");
		List<Post> posts = postDao.getAllPosts();
		ArrayList<Post> flaggedPosts = new ArrayList<Post>();
		for (Post post : posts) {
			if (post.getFlags().size() > 0) {
				flaggedPosts.add(post);
			}
		}
		request.setAttribute("flaggedPosts", flaggedPosts);
		return "admin/DisplayAllFlaggedPosts";

	}

	@RequestMapping("admin/addBadWords")
	public String addBadWords(@RequestParam String badWords) {

		Flag flag = flagDao.getFlag(1);
		flag.setFlagInfo(badWords);
		flagDao.updateFlag(flag);
		Logging.Log("info", "Admin Controller: Bad words updated.");

		return "redirect:/admin";

	}
	
	@RequestMapping("/admin/viewAllGroups")
	public String goToViewAllGroups(Model model) {

		List<Group> groups = groupDao.getAllGroups();
		model.addAttribute("groups", groups);
		Logging.Log("info", "Admin Controller: Display all groups called");
		return "admin/DisplayAllGroups";

	}
	
	@RequestMapping("/admin/processRemoveGroup")
	public String processRemoveGroup(@RequestParam String name, Model model, RedirectAttributes ra) {

		groupDao.removeGroup(name);
		Logging.Log("info", "Admin Controller: Group removed succesfully" + name);
		ra.addFlashAttribute("message", "Group removed succesfully");
		return "redirect:/admin/viewAllGroups";

	}

}
