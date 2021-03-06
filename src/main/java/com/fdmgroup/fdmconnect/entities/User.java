package com.fdmgroup.fdmconnect.entities;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="FC_USERS")
public class User {
	
	@Id
	private String username;
	private String password;
	@Transient
	private String confirmPassword;
	private String email;
	private String job;
	private String role;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastLogin;

	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Profile profile;
	@OneToMany(fetch=FetchType.EAGER, mappedBy="postOwner", orphanRemoval=true)
	private Set<Post> posts;
	@OneToMany(fetch = FetchType.EAGER, mappedBy="reporter", cascade = CascadeType.REMOVE)
	private List<Flag> flags;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy="users")
	private Set<Group> groups = new HashSet<Group>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="owner", cascade = CascadeType.REMOVE)
	private Set<Group> ownedGroups;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="recipient", cascade = CascadeType.REMOVE)
	private Set<Notification> notifications;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="sender", cascade = CascadeType.REMOVE)
	private Set<Notification> notificationsSent;
	
	
	public User() {	}

	public User(String username, String password, String email, String job, String role) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.job = job;
		this.role = role;
	}
	
	
	
	public Set<Notification> getNotificationsSent() {
		return notificationsSent;
	}

	public void setNotificationsSent(Set<Notification> notificationsSent) {
		this.notificationsSent = notificationsSent;
	}

	public Set<Group> getOwnedGroups() {
		return ownedGroups;
	}
	
	public void setOwnedGroups(Set<Group> ownedGroups) {
		this.ownedGroups = ownedGroups;
	}
	
	public Set<Notification> getNotifications() {
		return notifications;
	}
	
	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}
	
	public List<Flag> getFlags() {
		return flags;
	}
	
	public void setFlags(List<Flag> flags) {
		this.flags = flags;
	}
	
	public void addFlag(Flag flag) {
		this.flags.add(flag);
	}
	
	public Calendar getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin() {
		this.lastLogin = Calendar.getInstance();
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public Set<Post> getPosts() {
		return posts;
	}
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	
	public void addPost(Post post){
		posts.add(post);
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public void removeGroup(Group group) {
		groups.remove(group);
	}

	
}
