package com.fdmgroup.fdmconnect.daos;

import java.util.List;

import com.fdmgroup.fdmconnect.entities.Notification;

public interface NotificationDAO {
	
	public void addNotification(Notification notification);
	public void removeNotification(int notificationId);
	public Notification getNotification(int notificationId);
	public List<Notification> getAllNotificationsByUser(String username);

}
