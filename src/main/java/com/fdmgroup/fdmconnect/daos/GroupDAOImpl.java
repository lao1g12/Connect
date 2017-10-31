package com.fdmgroup.fdmconnect.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.fdmconnect.controllers.Logging;
import com.fdmgroup.fdmconnect.entities.Group;

public class GroupDAOImpl implements GroupDAO {

	@Autowired
	private EntityManagerFactory factory;

	public GroupDAOImpl() {
	}

	public GroupDAOImpl(EntityManagerFactory factory) {
		super();
		this.factory = factory;
	}

	public List<Group> getAllGroups() {
		
		EntityManager manager = factory.createEntityManager();
		TypedQuery<Group> query = manager.createQuery("select g from Group g",
				Group.class);
		List<Group> groups = query.getResultList();
		Logging.Log("info",
				"GroupDAOImpl: All groups have been retrieved from the database.");
		return groups;
		
	}

	public Group createGroup(Group group) {
		
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(group);
		manager.getTransaction().commit();
		Logging.Log("info", "GroupDAOImpl: " + group
				+ " has been added to the database");
		return group;

	}

	public Group getGroup(String name) {
		
		EntityManager manager = factory.createEntityManager();
		Group group = manager.find(Group.class, name);
		return group;
		
	}

	public void removeGroup(String name) {
		
		EntityManager manager = factory.createEntityManager();
		Group group = manager.find(Group.class, name);
		manager.getTransaction().begin();
		manager.remove(group);
		manager.getTransaction().commit();
		Logging.Log("info", "GroupDAOImpl: " + group
				+ " has been removed from the database");

	}

}
