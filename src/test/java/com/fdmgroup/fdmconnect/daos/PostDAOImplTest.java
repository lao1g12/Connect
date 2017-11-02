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

import com.fdmgroup.fdmconnect.entities.Experience;
import com.fdmgroup.fdmconnect.entities.Group;
import com.fdmgroup.fdmconnect.entities.Post;
import com.fdmgroup.fdmconnect.entities.User;

public class PostDAOImplTest {
	
	private PostDAOImpl postDao;
	private EntityManagerFactory factory;
	private EntityManager manager;
	private EntityTransaction transaction;
	private TypedQuery<Post> query;
	private List<Post> posts;
	private Post post;
	private User user;
	private Group group;




	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		factory = mock(EntityManagerFactory.class);
		manager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);
		postDao = new PostDAOImpl(factory);
		query = mock(TypedQuery.class);
		posts = mock(List.class);
		post = mock(Post.class);
		group = mock(Group.class);
		user = mock(User.class);
		
		when(factory.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
	}

	@Test
	public void test_addPost_invokesTransactionMethodsAndPersist() {
		PostDAOImpl postDao = new PostDAOImpl(factory);
		Post post = new Post();
		postDao.addPost(post);
		
		verify(transaction).begin();
		verify(manager).persist(post);
		verify(transaction).commit();
	}
	
	@Test
	public void test_getAllPosts_() { 
		//Arrange 
		when(manager.createQuery("select p from Post p", Post.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(posts);
	
		//Act
		postDao.getAllPosts();
		//verify
		
		assertEquals(query.getResultList(), posts);
		
		
	}
	
	@Test
	public void test_removePost_invokesGetTransactionAndRemove(){
		
		int postId = 0;
		
		when(manager.find(Post.class, postId)).thenReturn(post);
		postDao.removePost(postId);
		
		verify(manager.getTransaction()).begin();
		verify(manager).remove(post);
		verify(manager.getTransaction()).commit();
		
	}
	
	@Test
	public void test_getPost_returnsPost(){
		
		int postId = 0;
		
		when(manager.find(Post.class, postId)).thenReturn(post);
		Post retrievedPost = postDao.getPost(postId);
		
		assertEquals(retrievedPost, post);
		
	}
	
	@Test
	public void test_updatePost_invokesTransactionMethodsAndMerge() {

		postDao.updatePost(post);
		
		verify(transaction).begin();
		verify(manager).merge(post);
		verify(transaction).commit();
		
	}
	
	@Test
	public void test_getAllPostsByUser_returnsListOfPosts(){
		
		String username = "";
		
		when(manager.find(User.class, username)).thenReturn(user);
		when(manager.createQuery("select p from Post as p where :user = p.user", Post.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(posts);
		List<Post> retrievedPosts = postDao.getAllPostsByUser(username);
		
		assertEquals(posts, retrievedPosts);
		
	}

	@Test
	public void test_getAllPostsByGroup_returnsListOfPosts(){
		
		String name = "";
		
		when(manager.find(Group.class, name)).thenReturn(group);
		when(manager.createQuery("select p from Post as p where p.group = ?", Post.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(posts);
		List<Post> retrievedPosts = postDao.getAllPostsByGroup(name);
		
		assertEquals(posts, retrievedPosts);
		
	}
	
	@Test
	public void test_getAllPostsWhereGroupIsNull_returnsPosts() {
		
		when(manager.createQuery("select p from Post p where p.group =?", Post.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(posts);
		List<Post> retrievedPosts = postDao.getAllPostsWhereGroupsIsNull();
		
		assertEquals(retrievedPosts, posts);
		
	}

}
