package com.fdmgroup.fdmconnect.daos;

import com.fdmgroup.fdmconnect.entities.User;

public interface UserDAO {
	
	public User getUser(String username);

}
