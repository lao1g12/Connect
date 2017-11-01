package com.fdmgroup.fdmconnect.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.fdmconnect.controllers.Logging;
import com.fdmgroup.fdmconnect.entities.Comment;
import com.fdmgroup.fdmconnect.entities.Post;

public class CommentDAOImpl implements CommentDAO{
	
	@Autowired
	private EntityManagerFactory factory;

	public CommentDAOImpl() {	}
	

	public CommentDAOImpl(EntityManagerFactory factory) {
		super();
		this.factory = factory;
	}


	@Override
	public void addComment(Comment comment) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(comment);
		manager.getTransaction().commit();
		Logging.Log("info", "CommentDAOImpl: Comment has been added" + comment.getCommentId());
		
	}

	@Override
	public void removeComment(int commentId) {
		EntityManager manager = factory.createEntityManager();
		Comment comment = manager.find(Comment.class, commentId);
		manager.getTransaction().begin();
		manager.remove(comment);
		manager.getTransaction().commit();
		Logging.Log("info",  "CommentDAOImpl: "+comment+" has been removed from the database");
		
	}

	@Override
	public void updateComment(Comment comment) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.merge(comment);
		manager.getTransaction().commit();
		Logging.Log("info",  "CommentDAOImpl: "+comment+" has been updated on the database");
		
	}


	@Override
	public Comment getComment(int commentId) {
		EntityManager manager = factory.createEntityManager();
		Comment comment = manager.find(Comment.class, commentId);
		return comment;
	}

}
