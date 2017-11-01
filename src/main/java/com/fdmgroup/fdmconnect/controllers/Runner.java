package com.fdmgroup.fdmconnect.controllers;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fdmgroup.fdmconnect.daos.FlagDAOImpl;
import com.fdmgroup.fdmconnect.daos.UserDAOImpl;
import com.fdmgroup.fdmconnect.entities.Flag;
import com.fdmgroup.fdmconnect.entities.Profile;
import com.fdmgroup.fdmconnect.entities.User;

public class Runner {

	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		UserDAOImpl userDao = new UserDAOImpl(factory);
		FlagDAOImpl flagDao = new FlagDAOImpl(factory);
		
		Profile profile = new Profile("someUrl", "I am an admin!", "Stopping people having fun.");
		profile.setFirstName("Optimus"); profile.setLastName("Prime");
		User user = new User("admin","password","admin@fdmgroup.com","Admin","Admin");
		user.setProfile(profile);
		userDao.addUser(user);
		
	}

}
