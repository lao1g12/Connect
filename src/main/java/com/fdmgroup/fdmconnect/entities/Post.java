package com.fdmgroup.fdmconnect.entities;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="FC_POSTS")
public class Post {
	
	@Id
	@SequenceGenerator(name="postId_sequence", sequenceName="postId_sequence", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="postId_sequence")
	private int postId;
	private String title;
	private String bodyText;
	private String link;
	private String category;
	private String imgUrl;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar postDate = Calendar.getInstance();
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "FC_POST_USER")
	private User postOwner;
	@OneToMany(fetch = FetchType.EAGER, mappedBy="flaggedPost", cascade = CascadeType.REMOVE)
	private Set<Flag> flags;
	@Transient
	private String ListOfWords;
		
	public Post() {	}

	public Post(String title, String bodyText, String link, String category, String imgUrl) {
		super();
		this.title = title;
		this.bodyText = bodyText;
		this.link = link;
		this.category = category;
		this.imgUrl = imgUrl;
//		this.postOwner = postOwner;
//		postOwner.addPost(this);
	}
	public String getListOfKeyWords(){
		StringBuffer sb = new StringBuffer();
		sb.append(title+" "+bodyText+" "+category);
		String searchString = sb.toString();
		searchString = searchString.replaceAll("[^a-zA-Z\\s]", " ");
		searchString = searchString.toLowerCase();
		return searchString;
	}
	
	public void addFlag(Flag flag) {
		this.flags.add(flag);
	}
	
	public Set<Flag> getFlags() {
		return flags;
	}
	
	public void setFlags(Set<Flag> flags) {
		this.flags = flags;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBodyText() {
		return bodyText;
	}
	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public Calendar getPostDate() {
		return postDate;
	}
	public void setPostDate(Calendar postDate) {
		this.postDate = postDate;
	}
	
	public Date getPostDateFormatted(){
		Date date = this.postDate.getTime();

		return date;
	}
	
	public User getPostOwner() {
		return postOwner;
	}
	public void setPostOwner(User postOwner) {
		this.postOwner = postOwner;
	}



}
