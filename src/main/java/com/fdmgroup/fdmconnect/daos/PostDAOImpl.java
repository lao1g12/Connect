package com.fdmgroup.fdmconnect.daos;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;

public class PostDAOImpl implements PostDAO {

	@Autowired
	private EntityManagerFactory factory;
	
	public PostDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostDAOImpl(EntityManagerFactory factory) {
		super();
		this.factory = factory;
	}
	
}