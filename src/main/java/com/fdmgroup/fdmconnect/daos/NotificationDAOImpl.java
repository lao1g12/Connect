package com.fdmgroup.fdmconnect.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.fdmconnect.entities.Notification;
import com.fdmgroup.fdmconnect.entities.User;

public class NotificationDAOImpl implements NotificationDAO {
	
	@Autowired
	private EntityManagerFactory factory;
	
	public NotificationDAOImpl() {}
	
	public NotificationDAOImpl(EntityManagerFactory factory) {
		super();
		this.factory = factory;
	}
	
	public void addNotification(Notification notification) {
		
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		manager.persist(notification);
		manager.getTransaction().commit();
		
	}

	public void removeNotification(int notificationId) {
		
		EntityManager manager = factory.createEntityManager();
		Notification notification = manager.find(Notification.class, notificationId);
		
		manager.getTransaction().begin();
		manager.remove(notification);
		manager.getTransaction().commit();
		
	}

	public Notification getNotification(int notificationId) {
		
		EntityManager manager = factory.createEntityManager();
		Notification notification = manager.find(Notification.class, notificationId);
		
		return notification;
		
	}

	public List<Notification> getAllNotificationsByUser(String username) {
		
		EntityManager manager = factory.createEntityManager();
		User user = manager.find(User.class, username);
		TypedQuery<Notification> query = manager.createQuery("select n from Notification as n where :user = n.recipient", Notification.class );
		
		query.setParameter("user", user);
		List<Notification> notifications = query.getResultList();
		return notifications;
		
	}

}
