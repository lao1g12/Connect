package com.fdmgroup.fdmconnect.daos;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import javassist.bytecode.Mnemonic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.fdmconnect.entities.Comment;
import com.fdmgroup.fdmconnect.entities.Education;

public class CommentDAOImplTest {

	private CommentDAOImpl commentDao;
	private EntityManagerFactory factory;
	private EntityManager manager;
	private EntityTransaction transaction;
	private TypedQuery<Education> query;
	private List<Comment> comments;
	private Comment comment;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		
		factory = mock(EntityManagerFactory.class);
		manager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);
		
		commentDao = new CommentDAOImpl(factory);
		query = mock(TypedQuery.class);
		comments = mock(ArrayList.class);
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

}
