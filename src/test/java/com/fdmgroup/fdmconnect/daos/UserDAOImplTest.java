package com.fdmgroup.fdmconnect.daos;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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

import com.fdmgroup.fdmconnect.entities.Profile;
import com.fdmgroup.fdmconnect.entities.User;

public class UserDAOImplTest {
	
	private UserDAOImpl userDao;
	private EntityManagerFactory factory;
	private EntityManager manager;
	private EntityTransaction transaction;
	private TypedQuery<User> query;
	private List<User> users;
	private User user;
	private Profile profile;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		factory = mock(EntityManagerFactory.class);
		manager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);
		query = mock(TypedQuery.class);
		
		user = mock(User.class);
		profile = mock(Profile.class);
		users = mock(ArrayList.class);
		userDao = new UserDAOImpl(factory);
		
		when(factory.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
	}

	@Test
	public void test_getUser_callsFindAndReturnsUser() {
		when(manager.find(User.class, "username")).thenReturn(user);
		User result = userDao.getUser("username");
		
		verify(manager).find(User.class, "username");
		assertEquals(result, user);
	}
	
	@Test
	public void test_getAllUsers_returnsListOfUsersAndCallsCreateQuery(){
		when(manager.createQuery("select u from User u", User.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(users);
		List<User> result = userDao.getAllUsers();
		
		verify(manager).createQuery("select u from User u", User.class);
		assertEquals(result, users);
	}

	@Test
	public void test_addUser_invokesTransactionAndPersistUser(){
		
		userDao.addUser(user);
		
		verify(manager).persist(user);
		
	}
	
	@Test
	public void test_removeUser_invokesTransactionAndRemove(){
		
		when(manager.find(User.class, "username")).thenReturn(user);
		userDao.removeUser("username");
		
		verify(manager.getTransaction()).begin();
		verify(manager).remove(user);
		verify(manager.getTransaction()).commit();
		
	}
	
	@Test
	public void test_updateUser_invokesTransactionAndMerge(){
		
		userDao.updateUser(user);
		
		verify(manager.getTransaction()).begin();
		verify(manager).merge(user);
		verify(manager.getTransaction()).commit();
		
	}
	
	@Test
	public void test_getUserByProfile_returnsUserObject(){
		
		when(manager.createQuery("select u from User u where u.profile = ?",
				User.class)).thenReturn(query);
		when(query.getSingleResult()).thenReturn(user);
		User retrievedUser = userDao.getUserByProfile(profile);
		
		assertEquals(user, retrievedUser);
		
	}
}
