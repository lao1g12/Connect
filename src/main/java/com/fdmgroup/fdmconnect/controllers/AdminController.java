package com.fdmgroup.fdmconnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.fdmconnect.daos.PostDAOImpl;

public class AdminController {
	
	
	@Autowired
	private PostDAOImpl postDao;

	public AdminController() {}
	
	public AdminController(PostDAOImpl postDao) { 
		super();
		this.postDao = postDao;
	}
	

	
	

}
