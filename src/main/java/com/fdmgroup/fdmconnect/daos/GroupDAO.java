package com.fdmgroup.fdmconnect.daos;

import java.util.List;

import com.fdmgroup.fdmconnect.entities.Group;

public interface GroupDAO {
	
	public void removeGroup(String name);
	public List<Group> getAllGroups();
	public Group createGroup(Group group);
	public Group getGroup(String name);
	public void updateGroup(Group group);

}
