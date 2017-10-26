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

import com.fdmgroup.fdmconnect.entities.Flag;

public class FlagDAOImplTest {
	
	private FlagDAOImpl flagDao;
	private EntityManagerFactory factory;
	private EntityManager manager;
	private EntityTransaction transaction;
	private TypedQuery<Flag> query;
	private List<Flag> flags;
	private Flag flag;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		
		factory = mock(EntityManagerFactory.class);
		manager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);
		
		flagDao = new FlagDAOImpl(factory);
		query = mock(TypedQuery.class);
		flags = mock(ArrayList.class);
		flag = mock(Flag.class);
		
		when(factory.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
	}
	
	@Test
	public void test_addFlag_invokesTransactionMethodsAndPersist() {
	
		flagDao.addFlag(flag);
		
		verify(transaction).begin();
		verify(manager).persist(flag);
		verify(transaction).commit();
	}

	@Test
	public void test_getFlag_returnsFlag(){
		
		int flagId = 0;
		
		when(manager.find(Flag.class, flagId)).thenReturn(flag);
		Flag retrievedFlag = flagDao.getFlag(flagId);
		
		assertEquals(retrievedFlag, flag);
		
	}

	@Test
	public void test_getAllFlags_returnsFlags(){
		
		when(manager.createQuery("select f from Flag f",
				Flag.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(flags);
		List<Flag> retrievedFlags = flagDao.getAllFlags();
		
		assertEquals(retrievedFlags, flags);
	}
}
