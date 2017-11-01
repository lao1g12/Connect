package com.fdmgroup.fdmconnect.controllers;

import java.util.HashSet;
import java.util.Set;

import com.fdmgroup.fdmconnect.entities.Notification;
import com.fdmgroup.fdmconnect.entities.User;

public class MessageLogic {

	public Set<User> getRecievedList(Set<Notification> notifications) {
		Set<User> senders = new HashSet<User>();
		for(Notification notification: notifications){
			User user = notification.getSender();
			senders.add(user);
		}
		return senders;
	}

}
