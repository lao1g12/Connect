package com.fdmgroup.fdmconnect.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.fdmconnect.entities.Post;

public class PostDAOImplTest {
	
	private UserDAOImpl userDao;
	private PostDAOImpl postDao;
	private EntityManagerFactory factory;
	private EntityManager manager;
	private EntityTransaction transaction;
	private TypedQuery<Post> query;
	private List<Post> posts;




	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		factory = mock(EntityManagerFactory.class);
		manager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);
		userDao = new UserDAOImpl(factory);
		postDao = new PostDAOImpl(factory);
		query = mock(TypedQuery.class);
		posts = mock(List.class);
		
		when(factory.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
	}

	@Test
	public void test_AddPost_invokestransactionMethodsAndPersist() {
		PostDAOImpl postDao = new PostDAOImpl(factory);
		Post post = new Post();
		postDao.addPost(post);
		
		verify(transaction).begin();
		verify(manager).persist(post);
		verify(transaction).commit();
	}
	
	@Test
	public void test_GetAllPosts_() { 
		//Arrange 
		when(manager.createQuery("select p from Post p", Post.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(posts);
	
		//Act
		postDao.getAllPosts();
		//verify
		
		assertEquals(query.getResultList(), posts);
		
		
	}


}
