package com.fdmgroup.fdmconnect.daos;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.fdmconnect.entities.Group;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


public class GroupDAOImplTest {

	private GroupDAOImpl groupDao;
	private EntityManagerFactory factory;
	private EntityManager manager;
	private EntityTransaction transaction;
	private List<Group> groups;
	private TypedQuery<Group> query;
	private Group group;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		
		factory = mock(EntityManagerFactory.class);
		manager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);
		query = mock(TypedQuery.class);
		
		groupDao = new GroupDAOImpl(factory);
		group = mock(Group.class);
		
		when(factory.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
		
	}

	@Test
	public void test_getAllGroups_returnsListOfGroups() {

		when(manager.createQuery("select g from Group g", Group.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(groups);
		List<Group> retrievedGroups = groupDao.getAllGroups();
		
		assertEquals(retrievedGroups, groups);
		
	}
	
	@Test
	public void test_createGroup_invokesGetTransactionAndPersist() {
		
		groupDao.createGroup(group);
		
		verify(transaction).begin();
		verify(manager).persist(group);
		verify(transaction).commit();
		
	}
	
	@Test
	public void test_getGroup_returnsGroup() {
		
		String name = "";
		
		when(manager.find(Group.class, name)).thenReturn(group);
		Group retrievedGroup = groupDao.getGroup(name);
		
		assertEquals(retrievedGroup, group);
		
	}
	
	@Test
	public void test_removeGroup_invokesGetTransactionAndRemove() {
		
		String name = "";
		
		when(manager.find(Group.class, name)).thenReturn(group);
		groupDao.removeGroup(name);
		
		verify(transaction).begin();
		verify(manager).remove(group);
		verify(transaction).commit();
		
	}
	
	@Test
	public void test_updateGroup_invokesGetTransactionAndMerge() {
		
		groupDao.updateGroup(group);
		
		verify(transaction).begin();
		verify(manager).merge(group);
		verify(transaction).commit();
	
	}

}
