package com.fdmgroup.fdmconnect.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.fdmconnect.entities.Education;
import com.fdmgroup.fdmconnect.controllers.Logging;
import com.fdmgroup.fdmconnect.entities.Post;


public class EducationDAOImpl implements EducationDAO {

	@Autowired
	private EntityManagerFactory factory;

	
	public EducationDAOImpl() {}


	public EducationDAOImpl(EntityManagerFactory factory) {
		super();
		this.factory = factory;
	}
	
	public void addEducation(Education education) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(education);
		manager.getTransaction().commit();
		Logging.Log("info", "Education has been added" + education.getEducationId());

	}
	
}
