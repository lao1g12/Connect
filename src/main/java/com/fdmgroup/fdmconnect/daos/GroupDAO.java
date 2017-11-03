package com.fdmgroup.fdmconnect.daos;

import java.util.List;

import com.fdmgroup.fdmconnect.entities.Group;
import com.fdmgroup.fdmconnect.entities.User;

public interface GroupDAO {
	
	public void removeGroup(String name);
	public List<Group> getAllGroups();
	public List<Group> getAllGroupsByUser(User user);
	public Group createGroup(Group group);
	public Group getGroup(String name);
	public void updateGroup(Group group);

}
