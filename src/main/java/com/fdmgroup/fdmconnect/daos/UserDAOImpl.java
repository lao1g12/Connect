package com.fdmgroup.fdmconnect.daos;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;

public class UserDAOImpl implements UserDAO {

	@Autowired
	private EntityManagerFactory factory;

	public UserDAOImpl() {}

	public UserDAOImpl(EntityManagerFactory factory) {
		super();
		this.factory = factory;
	}
	
	
}
