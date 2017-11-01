package com.fdmgroup.fdmconnect.daos;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.fdmconnect.entities.Comment;
import com.fdmgroup.fdmconnect.entities.Post;

public class CommentDAOImplTest {

	private CommentDAOImpl commentDao;
	private EntityManagerFactory factory;
	private EntityManager manager;
	private EntityTransaction transaction;
	private TypedQuery<Comment> query;
	private Comment comment;
	private Post post;
	private List<Comment> comments;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		
		factory = mock(EntityManagerFactory.class);
		manager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);
		query = mock(TypedQuery.class);
		
		commentDao = new CommentDAOImpl(factory);
		comment = mock(Comment.class);
		comments = mock(ArrayList.class);
		post = mock(Post.class);
		
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
	
	@Test
	public void test_getAllCommentsByPost_returnsComments() {
		
		int postId = 0;
		
		when(manager.find(Post.class, postId)).thenReturn(post);
		when(manager.createQuery("select c from Comment as c where :post = c.post "
				+ "order by c.commentTime", Comment.class )).thenReturn(query);
		when(query.getResultList()).thenReturn(comments);
		List<Comment> retrievedComments = commentDao.getAllCommentsByPost(postId);
		
		assertEquals(retrievedComments, comments);
		
	}

}
