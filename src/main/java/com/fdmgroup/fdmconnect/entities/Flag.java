package com.fdmgroup.fdmconnect.entities;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="FC_FLAGS")
public class Flag {
	
	@Id
	@SequenceGenerator(name="flagId_sequence", sequenceName="flagId_sequence", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="flagId_sequence")
	@Column(name="ID")
	private int flagId;
	@Column(name="FLAGINFO")
	private String flagInfo;
	@Column(name="DATEADDED")
	private Calendar dateAdded = Calendar.getInstance();
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinTable(name="FC_FLAG_POST")
	private Post flaggedPost;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinTable(name="FC_FLAG_USER")
	private User reporter;
	
	public Flag() {}

	public Flag(String flagInfo) {
		super();
		this.flagInfo = flagInfo;
	}

	public int getFlagId() {
		return flagId;
	}

	public void setFlagId(int flagId) {
		this.flagId = flagId;
	}

	public String getFlagInfo() {
		return flagInfo;
	}

	public void setFlagInfo(String flagInfo) {
		this.flagInfo = flagInfo;
	}

	public Date getFlagDateFormatted(){
		Date date = this.dateAdded.getTime();
		return date;
	}

	public void setDateAdded(Calendar dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Post getFlaggedPost() {
		return flaggedPost;
	}

	public void setFlaggedPost(Post flaggedPost) {
		this.flaggedPost = flaggedPost;
	}

	public User getReporter() {
		return reporter;
	}

	public void setReporter(User reporter) {
		this.reporter = reporter;
	}

}
