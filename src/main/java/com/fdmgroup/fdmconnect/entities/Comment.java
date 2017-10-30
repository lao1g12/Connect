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
@Table(name="FC_COMMENTS")
public class Comment {
	
	@Id
	@SequenceGenerator(name="commentId_sequence", sequenceName="commentId_sequence", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="commentId_sequence")
	private int commentId;
	private String commentBody;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar commentTime = Calendar.getInstance();
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinTable(name="FC_COMMENT_POST")
	private Post post;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinTable(name="FC_COMMENT_USER")
	private User user;
	

	
	public Comment() {	}
	public Comment(String commentBody) {
		super();
		this.commentBody = commentBody;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getCommentBody() {
		return commentBody;
	}
	public void setCommentBody(String commentBody) {
		this.commentBody = commentBody;
	}
	public Calendar getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Calendar commentTime) {
		this.commentTime = commentTime;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getCommentDateFormatted(){
		Date date = this.commentTime.getTime();
		return date;
	}
	
	
	
	
}
