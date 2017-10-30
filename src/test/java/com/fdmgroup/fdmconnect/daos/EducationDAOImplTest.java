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

import com.fdmgroup.fdmconnect.entities.Education;
import com.fdmgroup.fdmconnect.entities.Experience;

public class EducationDAOImplTest {

	private EducationDAOImpl educationDao;
	private EntityManagerFactory factory;
	private EntityManager manager;
	private EntityTransaction transaction;
	private TypedQuery<Education> query;
	private List<Education> educations;
	private Education education;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		
		factory = mock(EntityManagerFactory.class);
		manager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);
		
		educationDao = new EducationDAOImpl(factory);
		query = mock(TypedQuery.class);
		educations = mock(ArrayList.class);
		education = mock(Education.class);
		
		when(factory.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
	}

	@Test
	public void test_addEducation_invokesTransactionMethodsAndPersist() {
	
		educationDao.addEducation(education);
		
		verify(transaction).begin();
		verify(manager).persist(education);
		verify(transaction).commit();
		
	}
	
	@Test
	public void test_removeEducation_invokesTransactionMethodsAndRemove() {
		
		int educationId = 0;
		
		when(manager.find(Education.class, educationId)).thenReturn(education);
		educationDao.removeEducation(educationId);
		
		verify(transaction).begin();
		verify(manager).remove(education);
		verify(transaction).commit();
		
	}
	
	@Test
	public void test_getEducation_returnsEducationObject() {
		
		int educationId = 0;

		when(manager.find(Education.class, educationId)).thenReturn(education);
		Education retrievedEducation = educationDao.getEducation(educationId);
		
		assertEquals(retrievedEducation, education);
		
	}

}
