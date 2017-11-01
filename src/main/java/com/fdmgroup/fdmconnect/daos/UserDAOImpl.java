package com.fdmgroup.fdmconnect.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.fdmconnect.entities.Group;
import com.fdmgroup.fdmconnect.entities.Profile;
import com.fdmgroup.fdmconnect.entities.User;
import com.fdmgroup.fdmconnect.controllers.Logging;

public class UserDAOImpl implements UserDAO {

	@Autowired
	private EntityManagerFactory factory;

	public UserDAOImpl() {
	}

	public UserDAOImpl(EntityManagerFactory factory) {
		super();
		this.factory = factory;
	}

	public User getUser(String username) {
		EntityManager manager = factory.createEntityManager();
		User user = manager.find(User.class, username);
		return user;
	}

	public List<User> getAllUsers() {
		EntityManager manager = factory.createEntityManager();
		TypedQuery<User> query = manager.createQuery("select u from User u", User.class);
		List<User> users = query.getResultList();
		Logging.Log("info",
				"UserDAOImpl: All users have been retrieved from the database.");
		return users;
	}

	public void addUser(User user) {

		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(user);
		manager.getTransaction().commit();
		Logging.Log("info", "UserDAOImpl: "+user + " has been added to the database.");

	}

	public void removeUser(String username) {

		EntityManager manager = factory.createEntityManager();
		User user = manager.find(User.class, username);
		manager.getTransaction().begin();
		manager.remove(user);
		manager.getTransaction().commit();
		Logging.Log("info", "UserDAOImpl: "+user + "have been removed from the database");

	}

	public void updateUser(User user) {

		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.merge(user);
		manager.getTransaction().commit();
		Logging.Log("info", "UserDAOImpl: "+user + "has been updated on the database");


	}

	@Override
	public User getUserByProfile(Profile profile) {
		EntityManager manager = factory.createEntityManager();
		TypedQuery<User> query = manager.createQuery("select u from User u where u.profile = ?", User.class);
		query.setParameter(1, profile);
		User user = query.getSingleResult();
		return user;
	}
	
	//public User getUserByGroup(Group group, String name){
	//	EntityManager manager = factory.createEntityManager();
	 

		//Group group1 = manager.find(Group.class, name);

	//	TypedQuery<User> query = manager.createQuery("select u from User u where group=u.group", User.class);
	//	query.setParameter("name", group);
		//User user = query.getSingleResult();

		//TypedQuery<User> query = manager.createQuery("select u from User u where u.group= ?", User.class);
		//query.setParameter(1, group);
		//User user = query.getSingleResult();

		
		//return user;
		
//	}

}
