package com.fdmgroup.fdmconnect.daos;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.fdmconnect.controllers.Logging;
import com.fdmgroup.fdmconnect.entities.Education;
import com.fdmgroup.fdmconnect.entities.Experience;

public class ExperienceDAOImpl implements ExperienceDAO {

	@Autowired
	private EntityManagerFactory factory;

	public ExperienceDAOImpl(EntityManagerFactory factory) {
		super();
		this.factory = factory;
	}

	public ExperienceDAOImpl() {}
	
	public void addExperience(Experience experience) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(experience);
		manager.getTransaction().commit();
		Logging.Log("info", "Education has been added" + experience.getExperienceId());

	}
	
}
