package com.fdmgroup.fdmconnect.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.fdmconnect.controllers.Logging;
import com.fdmgroup.fdmconnect.entities.Post;

public class PostDAOImpl implements PostDAO {

	@Autowired
	private EntityManagerFactory factory;

	public PostDAOImpl() {
	}

	public PostDAOImpl(EntityManagerFactory factory) {
		super();
		this.factory = factory;

	}

	public void addPost(Post post) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(post);
		manager.getTransaction().commit();
		Logging.Log("info", "Post has been added" + post.getPostId());

	}

}