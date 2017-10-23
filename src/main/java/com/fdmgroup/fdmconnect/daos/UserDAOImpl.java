package com.fdmgroup.fdmconnect.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.fdmconnect.entities.User;
import com.fdmgroup.fdmconnect.controllers.Logging;

public class UserDAOImpl implements UserDAO {

	@Autowired
	private EntityManagerFactory factory;

	public UserDAOImpl() {}

	public UserDAOImpl(EntityManagerFactory factory) {
		super();
		this.factory = factory;
	}

	public User getUser(String username) {
		EntityManager manager = factory.createEntityManager();
		User user = manager.find(User.class, username);
		return user;
	}
	
	public List<User> getAllUsers(){
		EntityManager manager = factory.createEntityManager();
		TypedQuery<User> query = manager.createQuery("select u from User u", User.class);
		List<User> users = query.getResultList();
		Logging.Log("info", "All users have been retrieved from the database.");
		return users;
	}
	
}
