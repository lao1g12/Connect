package com.fdmgroup.fdmconnect.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fdmgroup.fdmconnect.entities.Notification;
import com.fdmgroup.fdmconnect.entities.User;

public class MessageLogic {

	public Set<User> getContactList(Set<Notification> notifications, Set<Notification> notificationsSent) {
		Set<User> contacts = new HashSet<User>();
		for(Notification notification: notifications){
			User user = notification.getSender();
			contacts.add(user);
		}
		for(Notification notification: notificationsSent){
			User user = notification.getUser();
			contacts.add(user);
		}
		return contacts;
	}

	public List<Notification> refineMessages(List<Notification> sent, User user, User curUsername) {
		List<Notification> conversation = new ArrayList<Notification>();
		for(Notification convo: sent){
			if(convo.getRecipientUsername().equals(user.getUsername()) || convo.getRecipientUsername().equals(curUsername.getUsername())){
				conversation.add(convo);
			}
		}
		return conversation;
	}

	

}
