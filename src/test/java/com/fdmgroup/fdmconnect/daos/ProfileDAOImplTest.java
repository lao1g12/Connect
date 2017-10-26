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
import com.fdmgroup.fdmconnect.entities.Profile;

public class ProfileDAOImplTest {

	private ProfileDAOImpl profileDao;
	private EntityManagerFactory factory;
	private EntityManager manager;
	private EntityTransaction transaction;
	private TypedQuery<Profile> query;
	private List<Profile> profiles;
	private Profile profile;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		
		factory = mock(EntityManagerFactory.class);
		manager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);

		profileDao = new ProfileDAOImpl(factory);
		query = mock(TypedQuery.class);
		profiles = mock(ArrayList.class);
		profile = mock(Profile.class);
		
		when(factory.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
	}

	@Test
	public void test_updateProfile_invokesTransactionMethodsAndMerge() {
	
		profileDao.updateProfile(profile);
		
		verify(transaction).begin();
		verify(manager).merge(profile);
		verify(transaction).commit();
		
	}

}
