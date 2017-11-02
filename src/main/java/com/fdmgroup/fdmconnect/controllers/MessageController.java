package com.fdmgroup.fdmconnect.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.fdmconnect.daos.NotificationDAO;
import com.fdmgroup.fdmconnect.daos.UserDAO;
import com.fdmgroup.fdmconnect.entities.Notification;
import com.fdmgroup.fdmconnect.entities.User;

@Controller
public class MessageController {
	
	@Autowired
	private NotificationDAO notifDao;
	@Autowired
	private UserDAO userDao;
	
	public MessageController() {	}
	
	public MessageController(NotificationDAO notifDao, UserDAO userDao) {
		super();
		this.notifDao = notifDao;
		this.userDao = userDao;
	}
	
	@RequestMapping("user/sendMessage")
	public String SendMessage(Notification notification, @RequestParam String recipient, HttpSession session, Principal principal, HttpServletRequest request, Model model){
		
		User reciever = userDao.getUser(recipient);
		User sender = (User) session.getAttribute("user");
		notification.setUser(reciever);
		notification.setSender(sender);
		notifDao.addNotification(notification);
		List<Notification> conversation = notifDao.getAllNotificationsByGroup(sender, reciever);
		model.addAttribute("conversation", conversation);
		notification = new Notification();
		model.addAttribute(notification);
		request.setAttribute("username", recipient);
		Logging.Log("info", "Message Controller: "+session.getAttribute("username")+" has sent a Message.");
		
		return "user/Messages";
	}
	
	@RequestMapping(value = { "/admin/goToMyMessages", "/user/goToMyMessages"})
	public String goToMyMessages(Principal principal, HttpServletRequest request){
		MessageLogic ml = new MessageLogic();
		User user = userDao.getUser(principal.getName());
		Set<User> contacts = ml.getContactList(user.getNotifications(), user.getNotificationsSent());
		request.setAttribute("contacts", contacts);
		Logging.Log("info", "Message Controller: "+user.getUsername()+" is going to Messages." );
		
		return "user/MyMessages";
		
	}
	
	@RequestMapping("user/messages")
	public String messageBox(@RequestParam String username, Principal principal, Model model, HttpServletRequest request){

		User user = userDao.getUser(username);
		User curUsername = userDao.getUser(principal.getName());
		List<Notification> conversation = notifDao.getAllNotificationsByGroup(user, curUsername);
		model.addAttribute("conversation", conversation);
		Notification notification = new Notification();
		model.addAttribute(notification);
		request.setAttribute("username", username);
		Logging.Log("info", "Message Controller: "+ "showing " +user.getUsername()+ " their messages");
		return "user/Messages";
		
	}
}
