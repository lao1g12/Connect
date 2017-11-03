package com.fdmgroup.fdmconnect.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fdmgroup.fdmconnect.daos.NotificationDAO;
import com.fdmgroup.fdmconnect.daos.NotificationDAOImpl;
import com.fdmgroup.fdmconnect.daos.UserDAO;
import com.fdmgroup.fdmconnect.daos.UserDAOImpl;
import com.fdmgroup.fdmconnect.entities.Flag;
import com.fdmgroup.fdmconnect.entities.Group;
import com.fdmgroup.fdmconnect.entities.Notification;
import com.fdmgroup.fdmconnect.entities.Post;
import com.fdmgroup.fdmconnect.entities.User;

public class MessageControllerTest {

	private UserDAO userDao;
	private NotificationDAO notifDao;
	private HttpSession session;
	private Model model;
	private User user;
	private HttpServletRequest request;
	private Principal principal;
	private MessageController messageController;
	private MessageLogic ml;
	private Notification notification;

	@Before
	public void setUp() {
		userDao = mock(UserDAOImpl.class);
		notifDao = mock(NotificationDAOImpl.class);
		session = mock(HttpSession.class);
		principal = mock(Principal.class);
		notification = mock(Notification.class);
		messageController = new MessageController(notifDao, userDao);
		model = mock(Model.class);
		user = mock(User.class);
		request = mock(HttpServletRequest.class);
		ml = mock(MessageLogic.class);
	}
	
	@Test
	public void test_sendMessage_returnsUserMyMessages() {
		
		String recipient = "";
		User reciever = mock(User.class); User sender = mock(User.class);
		Set<Notification> notifications = new HashSet<Notification>();
		Set<Notification> notificationsSent = new HashSet<Notification>();
		Set<User> contacts = new HashSet<User>();

		when(userDao.getUser(recipient)).thenReturn(reciever);
		when(session.getAttribute("user")).thenReturn(sender);
		when(userDao.getUser(principal.getName())).thenReturn(user);
		when(user.getNotifications()).thenReturn(notifications);
		when(user.getNotificationsSent()).thenReturn(notificationsSent);
		when(ml.getContactList(notifications, notificationsSent)).thenReturn(contacts);
		String result = messageController.SendMessage(notification, recipient, session, principal, request, model);
		
		assertEquals(result, "user/Messages");
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test_goToMyMessages_returnsUserMyMessages() {
		
		String username = ""; 
		Set<User> contacts = mock(HashSet.class);
		Set<Notification> notifications = new HashSet<Notification>();
		Set<Notification> notificationsSent = new HashSet<Notification>();
		
		when(principal.getName()).thenReturn(username);
		when(userDao.getUser(username)).thenReturn(user);
		when(user.getNotifications()).thenReturn(notifications);
		when(user.getNotificationsSent()).thenReturn(notificationsSent);
		when(ml.getContactList(notifications, notificationsSent)).thenReturn(contacts);
		String result = messageController.goToMyMessages(principal, request);
		
		assertEquals(result, "user/MyMessages");
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test_messageBox_returnsUserMessages() {
		
		String curUsername = ""; String username = "";
		User curUser = mock(User.class);
		List<Notification> conversation = mock(ArrayList.class);
		
		when(principal.getName()).thenReturn(curUsername);
		when(userDao.getUser(username)).thenReturn(user);
		when(userDao.getUser(curUsername)).thenReturn(curUser);
		when(notifDao.getAllNotificationsByGroup(user, curUser)).thenReturn(conversation);
		String result = messageController.messageBox(username, principal, model, request);
		
		assertEquals(result, "user/Messages");
		
		
	}

}
