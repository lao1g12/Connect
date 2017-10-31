package com.fdmgroup.fdmconnect.daos;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.fdmconnect.entities.Comment;

public class CommentDAOImplTest {

	private CommentDAOImpl commentDao;
	private EntityManagerFactory factory;
	private EntityManager manager;
	private EntityTransaction transaction;
	private Comment comment;

	@Before
	public void setUp() throws Exception {
		
		factory = mock(EntityManagerFactory.class);
		manager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);
		
		commentDao = new CommentDAOImpl(factory);
		comment = mock(Comment.class);
		
		when(factory.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
	}

	@Test
	public void test_addComment_invokesTransactionalMethodsAndPersists(){
		
		commentDao.addComment(comment);
		
		verify(transaction).begin();
		verify(manager).persist(comment);
		verify(transaction).commit();
		
	}
	
	@Test
	public void test_removeComment_invokesTransactionalMethodsAndRemove(){

		int commentId = 0;
		
		when(manager.find(Comment.class, commentId)).thenReturn(comment);
		commentDao.removeComment(commentId);
		
		verify(transaction).begin();
		verify(manager).remove(comment);
		verify(transaction).commit();
		
	}
	
	@Test
	public void test_updateComment_invokesTransactionalMethodsAndMerge(){
		
		commentDao.updateComment(comment);
		
		verify(transaction).begin();
		verify(manager).merge(comment);
		verify(transaction).commit();
		
	}
	
	@Test
	public void test_getComment_returnsComment() {
		
		int commentId = 0;
		
		when(manager.find(Comment.class, commentId)).thenReturn(comment);
		Comment retrievedComment = commentDao.getComment(commentId);
		
		assertEquals(retrievedComment, comment);
		
	}

}
