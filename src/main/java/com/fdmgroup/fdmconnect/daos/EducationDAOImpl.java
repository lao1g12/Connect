package com.fdmgroup.fdmconnect.daos;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;

public class EducationDAOImpl implements EducationDAO {

	@Autowired
	private EntityManagerFactory factory;

	
	public EducationDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}


	public EducationDAOImpl(EntityManagerFactory factory) {
		super();
		this.factory = factory;
	}
	
}
