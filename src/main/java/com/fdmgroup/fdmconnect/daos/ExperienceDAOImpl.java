package com.fdmgroup.fdmconnect.daos;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;

public class ExperienceDAOImpl implements ExperienceDAO {

	@Autowired
	private EntityManagerFactory factory;

	public ExperienceDAOImpl(EntityManagerFactory factory) {
		super();
		this.factory = factory;
	}

	public ExperienceDAOImpl() {}
	
}
