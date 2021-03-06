package com.fdmgroup.fdmconnect.daos;

import java.util.List;

import com.fdmgroup.fdmconnect.entities.Profile;
import com.fdmgroup.fdmconnect.entities.User;

public interface UserDAO {
	
	public User getUser(String username);
	public User getUserByProfile(Profile profile);
	public List<User> getAllUsers();
	public void addUser(User user);
	public void removeUser(String username);
	public void updateUser(User user);
}
