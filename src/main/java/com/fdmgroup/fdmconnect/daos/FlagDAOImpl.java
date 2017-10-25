package com.fdmgroup.fdmconnect.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.fdmconnect.entities.Flag;

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
		
	}
	
	public Flag getFlag(int id) {
		EntityManager manager = factory.createEntityManager();
		Flag flag = manager.find(Flag.class, id);
		return flag;
	}
	

}
