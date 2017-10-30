package com.fdmgroup.fdmconnect.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.fdmconnect.controllers.Logging;
import com.fdmgroup.fdmconnect.entities.Post;
import com.fdmgroup.fdmconnect.entities.User;


public class PostDAOImpl implements PostDAO {

	@Autowired
	private EntityManagerFactory factory;

	public PostDAOImpl() {}

	public PostDAOImpl(EntityManagerFactory factory) {
		
		super();
		this.factory = factory;
		
	}

	public void addPost(Post post) {
		
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(post);
		manager.getTransaction().commit();
		Logging.Log("info", "PostDAOImpl: Post has been added" + post.getPostId());

	}
	
	public List<Post> getAllPosts(){
		
		EntityManager manager = factory.createEntityManager();
		TypedQuery<Post> query = manager.createQuery("select p from Post p", Post.class);
		List<Post> posts = query.getResultList();
		Logging.Log("info", "PostDAOImpl: All posts have been retrieved from the database.");
		return posts;
		
	}
	
	public List<Post> getAllPostsByUser(String username){
		
		EntityManager manager = factory.createEntityManager();
		User user = manager.find(User.class, username);
		TypedQuery<Post> query = manager.createQuery("select p from Post as p where :user = p.user", Post.class);
		query.setParameter("user", user);
		List<Post> posts = query.getResultList();
		
		return posts;
		
	}

	public void removePost(int postId){
		
		EntityManager manager = factory.createEntityManager();
		Post post = manager.find(Post.class, postId);
		manager.getTransaction().begin();
		manager.remove(post);
		manager.getTransaction().commit();
		Logging.Log("info",  "PostDAOImpl: "+post+" has been removed from the database");
		
	}
	
	public void updatePost(Post post){
		
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.merge(post);
		manager.getTransaction().commit();
		
	}

	public Post getPost(int postId) {
		
		EntityManager manager = factory.createEntityManager();
		Post post = manager.find(Post.class, postId);
		return post;
		
	}
}