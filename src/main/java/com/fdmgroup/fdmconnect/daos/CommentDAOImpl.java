package com.fdmgroup.fdmconnect.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.fdmconnect.controllers.Logging;
import com.fdmgroup.fdmconnect.entities.Comment;
import com.fdmgroup.fdmconnect.entities.Notification;
import com.fdmgroup.fdmconnect.entities.Post;
import com.fdmgroup.fdmconnect.entities.User;

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
	
	@Override
	public List<Comment> getAllCommentsByPost(int postId) {
		EntityManager manager = factory.createEntityManager();
		Post post = manager.find(Post.class, postId);
		TypedQuery<Comment> query = manager.createQuery("select c from Comment as c where :post = c.post "
				+ "order by c.commentTime", Comment.class );
		query.setParameter("post", post);
		List<Comment> comments = query.getResultList();
		return comments;
	}

}
