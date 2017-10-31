package com.fdmgroup.fdmconnect.controllers;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailRunner {
	
	public static void main(String[] args) {
	    final String username = "joseph.parker@fdmgroup.com";  // like yourname@outlook.com
	    String to = "liam.oliver@fdmgroup.com";
	    
	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "outlook.fdmgroup.local");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "25");

	    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, "");
            }
          });
	    
	    session.setDebug(true);

	    try {
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(username));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));   // like inzi769@gmail.com
	        message.setSubject("Test");
	        message.setText("HI you have done sending mail with outlook");

	        Transport.send(message);

	        System.out.println("Done");

	    } catch (MessagingException e) {
	        throw new RuntimeException(e);
	    }
	}
	
}
