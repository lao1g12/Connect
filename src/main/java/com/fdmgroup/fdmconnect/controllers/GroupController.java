package com.fdmgroup.fdmconnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.fdmconnect.daos.FlagDAOImpl;
import com.fdmgroup.fdmconnect.daos.PostDAOImpl;
import com.fdmgroup.fdmconnect.daos.UserDAOImpl;
import com.fdmgroup.fdmconnect.entities.Flag;

@Controller
public class GroupController {

	@Autowired
	private PostDAOImpl postDao;
	@Autowired
	private UserDAOImpl userDao;
	@Autowired
	private FlagDAOImpl flagDao;
	@Autowired 
	private GroupDAOImpl groupDao;
	
	public GroupController() {	}

	public GroupController(PostDAOImpl postDao, UserDAOImpl userDao, FlagDAOImpl flagDao, GroupDAOImpl groupDao) {
		super();
		this.postDao = postDao;
		this.userDao = userDao;
		this.flagDao = flagDao;
		this.groupDao = groupDao;
	}
	
	@RequestMapping("/goToGroupHome")
	public String admin(Model model, @RequestParam int groupId) {

		Group group = groupDao.getGroup(1);
		model.addAttribute("allPosts", postDao.getAllPostsByGroup(groupId));
		model.addAttribute("group", group);
		return "user/GroupHome";
	}
}
