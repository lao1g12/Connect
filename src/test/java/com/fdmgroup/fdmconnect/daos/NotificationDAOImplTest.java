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

import com.fdmgroup.fdmconnect.entities.Notification;
import com.fdmgroup.fdmconnect.entities.User;

public class NotificationDAOImplTest {

	private NotificationDAOImpl notificationDao;
	private EntityManagerFactory factory;
	private EntityManager manager;
	private EntityTransaction transaction;
	private TypedQuery<Notification> query;
	private Notification notification;
	private User user;
	private List<Notification> notifications;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {

		factory = mock(EntityManagerFactory.class);
		notificationDao = new NotificationDAOImpl(factory);
		manager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);
		query = mock(TypedQuery.class);

		when(factory.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);

		notification = mock(Notification.class);
		notifications = mock(ArrayList.class);
		user = mock(User.class);

	}

	@Test
	public void test_addNotification_invokesGetTransactionAndPersist() {

		notificationDao.addNotification(notification);

		verify(transaction).begin();
		verify(manager).persist(notification);
		verify(transaction).commit();

	}

	@Test
	public void test_removeNotification_invokesGetTransactionAndRemove() {

		int notificationId = 0;
		
		when(manager.find(Notification.class, notificationId)).thenReturn(notification);
		notificationDao.removeNotification(notificationId);

		verify(transaction).begin();
		verify(manager).remove(notification);
		verify(transaction).commit();

	}
	
	@Test
	public void test_getNotification_returnsANotification() {
		
		int notificationId = 0;
		
		when(manager.find(Notification.class, notificationId)).thenReturn(notification);
		Notification retrievedNotification = notificationDao.getNotification(notificationId);
		
		assertEquals(retrievedNotification, notification);
		
	}
	
	@Test
	public void test_getAllNotificationsByUser_returnsListOfNotifications() {
		
		String username = "";
		
		when(manager.find(User.class, username)).thenReturn(user);
		when(manager.createQuery("select n from Notification as n where :user = n.recipient "
				+ "order by n.dateAdded", Notification.class )).thenReturn(query);
		when(query.getResultList()).thenReturn(notifications);
		List<Notification> retrievedNotifications = notificationDao.getAllNotificationsByUser(username);
		
		assertEquals(retrievedNotifications, notifications);
		
	}
	
//	@Test
//	public void test_getAllNotificationsByGroup_returnsListOfNotifications() {
//		
//		User sender = mock(User.class); User recipient = mock(User.class);
//		
//		when(manager.createQuery("select n from Notification as n where n.sender = ? "
//				+"AND n.recipient = ?", Notification.class)).thenReturn(query);
//		when(query.getResultList()).thenReturn(notifications);
//		List<Notification> retrievedNotifications = notificationDao.getAllNotificationsByGroup(sender, recipient);
//		
//		assertEquals(retrievedNotifications, notifications);
//		
//	}

}
