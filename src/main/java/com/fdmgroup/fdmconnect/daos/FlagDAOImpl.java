package com.fdmgroup.fdmconnect.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

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
		
	}
	
	public Flag getFlag(int id) {
		EntityManager manager = factory.createEntityManager();
		Flag flag = manager.find(Flag.class, id);
		return flag;
	}

	
	public List<Flag>getAllFlags(){
		EntityManager manager = factory.createEntityManager();
		TypedQuery<Flag> query = manager.createQuery("select f fromFlag g", Flag.class);
		List<Flag> flags=query.getResultList();
		return flags;
		
	}
	
}
