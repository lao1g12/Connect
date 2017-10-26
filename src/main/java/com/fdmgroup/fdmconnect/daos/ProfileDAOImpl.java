package com.fdmgroup.fdmconnect.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.fdmconnect.entities.Profile;
import com.fdmgroup.fdmconnect.controllers.Logging;


public class ProfileDAOImpl implements ProfileDAO {

	@Autowired
	private EntityManagerFactory factory;
	
	public ProfileDAOImpl() {}

	public ProfileDAOImpl(EntityManagerFactory factory) {
		super();
		this.factory = factory;
	}

	public void updateProfile(Profile profile) {
		
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.merge(profile);
		manager.getTransaction().commit();
		Logging.Log("info", profile + " has been updated in the database.");
		
	}

	public Profile getProfile(int profileId) {
		
		EntityManager manager = factory.createEntityManager();
		Profile profile = manager.find(Profile.class, profileId);
		return profile;
		
	}
	
}
