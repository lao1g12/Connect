package com.fdmgroup.fdmconnect.controllers;

import java.security.Principal;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.fdmconnect.daos.NotificationDAOImpl;
import com.fdmgroup.fdmconnect.daos.UserDAOImpl;
import com.fdmgroup.fdmconnect.entities.Notification;
import com.fdmgroup.fdmconnect.entities.User;

@Controller
public class MessageController {
	@Autowired
	private NotificationDAOImpl notifDao;
	@Autowired
	private UserDAOImpl userDao;
	

	public MessageController() {	}
	
	

	public MessageController(NotificationDAOImpl notifDao, UserDAOImpl userDao) {
		super();
		this.notifDao = notifDao;
		this.userDao = userDao;
	}



	@RequestMapping("user/goToSendMessage")
	public String GoToSendMessage(@RequestParam String username, HttpServletRequest request, Model model){
		
		Notification notification = new Notification();
		model.addAttribute(notification);
		request.setAttribute("username", username);
		
		return "user/SendMessage";
		
	}
	
	@RequestMapping("user/sendMessage")
	public String SendMessage(Notification notification, @RequestParam String recipient, HttpSession session, Principal principal, HttpServletRequest request){
		
		MessageLogic ml = new MessageLogic();
		User reciever = userDao.getUser(recipient);
		User sender = (User) session.getAttribute("user");
		notification.setUser(reciever);
		notification.setSender(sender);
		notifDao.addNotification(notification);
		User user = userDao.getUser(principal.getName());
		Set<User> senders = ml.getRecievedList(user.getNotifications());
		request.setAttribute("senders", senders);
		return "user/MyMessages";
	}
}
