package com.fdmgroup.fdmconnect.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

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
		Logging.Log("info", "ProfileDAOImpl: "+profile+" has been updated in the database.");
		
	}

	public Profile getProfile(int profileId) {
		
		EntityManager manager = factory.createEntityManager();
		Profile profile = manager.find(Profile.class, profileId);
		return profile;
		
	}
	
	public List<Profile> getAllProfiles(){
		
		EntityManager manager = factory.createEntityManager();
		TypedQuery<Profile> query = manager.createQuery("select pr from Profile pr", Profile.class);
		List<Profile> profiles = query.getResultList();
		Logging.Log("info", "ProfileDAOImpl: All profiles have been retrieved from the database.");
		
		return profiles;
		
	}
	
}
