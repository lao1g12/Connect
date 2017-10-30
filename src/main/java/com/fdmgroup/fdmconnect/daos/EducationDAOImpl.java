package com.fdmgroup.fdmconnect.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.fdmconnect.entities.Education;
import com.fdmgroup.fdmconnect.controllers.Logging;


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
		Logging.Log("info", "EducationDAOImpl: Education has been added " + education.getEducationId());

	}
	
	
	
public void removeEducation(int educationId) { 
		
		EntityManager manager = factory.createEntityManager();
		Education education = manager.find(Education.class, educationId);
		manager.getTransaction().begin();
		manager.remove(education);
		manager.getTransaction().commit();
		Logging.Log("info", "EducationDAOImpl: "+education+"has been removed from the database ");
		
	}
	
	public Education getEducation(int educationId) {
		EntityManager manager = factory.createEntityManager();
		Education education = manager.find(Education.class, educationId);
		return education; 
		
	}

	
	
	
	
	
}
