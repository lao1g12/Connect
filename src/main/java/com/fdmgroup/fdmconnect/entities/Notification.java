package com.fdmgroup.fdmconnect.entities;


import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="FC_NOTIFICATIONS")
public class Notification {
	
	@Id
	@SequenceGenerator(name="notificationId_sequence", sequenceName="notificationId_sequence", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="notificationId_sequence")
	private int notificationId;
	private String title;
	private String body;
	private String type;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dateAdded = Calendar.getInstance();
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "FC_NOTIFICATION_USER")
	private User recipient;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "FC_NOTIFICATION_SENDER")
	private User sender;
	
	
	public Notification() {}

	public Notification(String title, String type, String body) {
		super();
		this.title = title;
		this.type = type;
		this.body = body;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public int getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getUser() {
		return recipient;
	}

	public void setUser(User user) {
		recipient = user;
	}
	
	public Date getNotificationDateFormatted(){
		Date date = this.dateAdded.getTime();
		return date;
	}

}
