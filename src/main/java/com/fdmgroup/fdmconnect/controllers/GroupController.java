package com.fdmgroup.fdmconnect.controllers;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fdmgroup.fdmconnect.daos.FlagDAOImpl;
import com.fdmgroup.fdmconnect.daos.GroupDAOImpl;
import com.fdmgroup.fdmconnect.daos.PostDAOImpl;
import com.fdmgroup.fdmconnect.daos.UserDAOImpl;
import com.fdmgroup.fdmconnect.entities.Group;
import com.fdmgroup.fdmconnect.entities.User;

@Controller
public class GroupController {

	@Autowired
	private PostDAOImpl postDao;
	@Autowired 
	private GroupDAOImpl groupDao;
	
	public GroupController() {	}

	public GroupController(PostDAOImpl postDao, UserDAOImpl userDao, FlagDAOImpl flagDao, GroupDAOImpl groupDao) {
		super();
		this.postDao = postDao;
		this.groupDao = groupDao;
	}
	
	@RequestMapping("user/goToGroupHome")
	public String admin(Model model, @RequestParam String name) {

		Group group = groupDao.getGroup(name);
		model.addAttribute("allPosts", postDao.getAllPostsByGroup(name));
		model.addAttribute("group", group);
		return "user/GroupHome";
		
	}
	
	@RequestMapping("user/goToMyGroups")
	public String goToViewAllGroups(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		Set<Group> groups= user.getGroups();
		Group group = new Group();
		model.addAttribute(group);
		model.addAttribute("groups", groups);
		Logging.Log("trace", "Admin Controller:  My groups called.");
		return "user/MyGroups";

	}
	
	@RequestMapping("user/doCreateGroup")
	public String doCreateGroup(Model model, Group group, HttpSession session, RedirectAttributes ra){
		User owner = (User) session.getAttribute("user");
		group.setOwner(owner);
		owner.getGroups().add(group);
		group.getUsers().add(owner);
		groupDao.createGroup(group);
		ra.addFlashAttribute("groupWasCreated", "Group was created successfully");
		return"redirect:/user/goToMyGroups";
	}
	

	@RequestMapping("user/postToGroup")
	public String postToGroup() { 
		
		return "AddPost";
	}

	
}
