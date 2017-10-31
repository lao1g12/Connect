package com.fdmgroup.fdmconnect.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "FC_GROUPS")
public class Group {
	
	
	@Id
	private String name; 
	private String description;
	private String imageUrl; 
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.MERGE, mappedBy="group")
	private Set<Post> posts = new HashSet<Post>();
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinTable(name = "FC_USER_GROUPS")
	private Set<User> users = new HashSet<User>();
	
		
	
	public Group() {}


	public Group( String name, String description, String imageUrl) {
		super();
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public Set<Post> getPosts() {
		return posts;
	}


	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	
	
	public void addPost(Post post){ 
		this.posts.add(post);
	}


	public Set<User> getUsers() {
		return users;
	}


	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	public void addUser(User user){ 
		this.users.add(user);
	}
	
	
	
	

}
