package com.fdmgroup.fdmconnect.daos;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.fdmconnect.entities.Experience;

public class ExperienceDAOImplTest {

	private ExperienceDAOImpl experienceDao;
	private EntityManagerFactory factory;
	private EntityManager manager;
	private EntityTransaction transaction;
	private TypedQuery<Experience> query;
	private List<Experience> experiences;
	private Experience experience;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		
		factory = mock(EntityManagerFactory.class);
		manager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);
		
		experienceDao = new ExperienceDAOImpl(factory);
		query = mock(TypedQuery.class);
		experiences = mock(ArrayList.class);
		experience = mock(Experience.class);
		
		when(factory.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
	}

	@Test
	public void test_addExperience_invokesTransactionMethodsAndPersist() {
	
		experienceDao.addExperience(experience);
		
		verify(transaction).begin();
		verify(manager).persist(experience);
		verify(transaction).commit();
		
	}
	
	@Test
	public void test_removeExperience_invokesTransactionMethodsAndRemove() {
		
		int experienceId = 0;
		
		when(manager.find(Experience.class, experienceId)).thenReturn(experience);
		experienceDao.removeExperience(experienceId);
		
		verify(transaction).begin();
		verify(manager).remove(experience);
		verify(transaction).commit();
		
	}

}
