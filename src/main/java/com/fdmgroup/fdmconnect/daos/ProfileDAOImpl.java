package com.fdmgroup.fdmconnect.daos;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;

public class ProfileDAOImpl implements ProfileDAO {

	@Autowired
	private EntityManagerFactory factory;

	public ProfileDAOImpl() {}

	public ProfileDAOImpl(EntityManagerFactory factory) {
		super();
		this.factory = factory;
	}
	
}
