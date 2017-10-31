package com.fdmgroup.fdmconnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fdmgroup.fdmconnect.daos.FlagDAOImpl;
import com.fdmgroup.fdmconnect.daos.PostDAOImpl;
import com.fdmgroup.fdmconnect.daos.UserDAOImpl;

@Controller
public class GroupController {

	@Autowired
	private PostDAOImpl postDao;
	@Autowired
	private UserDAOImpl userDao;
	@Autowired
	private FlagDAOImpl flagDao;
	
	public GroupController() {	}

	public GroupController(PostDAOImpl postDao, UserDAOImpl userDao, FlagDAOImpl flagDao) {
		super();
		this.postDao = postDao;
		this.userDao = userDao;
		this.flagDao = flagDao;
	}
	
	
}
