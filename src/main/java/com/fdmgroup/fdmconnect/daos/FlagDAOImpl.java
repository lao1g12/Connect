package com.fdmgroup.fdmconnect.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.fdmconnect.controllers.Logging;
import com.fdmgroup.fdmconnect.entities.Flag;
import com.fdmgroup.fdmconnect.entities.User;

public class FlagDAOImpl implements FlagDAO {

	@Autowired
	private EntityManagerFactory factory;
		
	public FlagDAOImpl() {}

	public FlagDAOImpl(EntityManagerFactory factory) {
		super();
		this.factory = factory;
	}

	public void addFlag(Flag flag) {
		
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(flag);
		manager.getTransaction().commit();
		Logging.Log("info",  "FlagDAOImpl: "+flag+" has been added to the database");
		
	}
	
	public Flag getFlag(int id) {
		
		EntityManager manager = factory.createEntityManager();
		Flag flag = manager.find(Flag.class, id);
		return flag;
	}

	
	public List<Flag> getAllFlags(){
		
		EntityManager manager = factory.createEntityManager();
		TypedQuery<Flag> query = manager.createQuery("select f from Flag f", Flag.class);
		List<Flag> flags=query.getResultList();
		return flags;
		
	}

	public void updateFlag(Flag flag) {
		
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.merge(flag);
		manager.getTransaction().commit();
		Logging.Log("info",  "FlagDAOImpl: "+flag+" has been updated on the database");
		
	}
	

	
}
