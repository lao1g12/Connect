package com.fdmgroup.fdmconnect.daos;

import java.util.List;

import com.fdmgroup.fdmconnect.entities.Profile;

public interface ProfileDAO {
	
	public void updateProfile(Profile profile);
	public Profile getProfile(int profileId);
	public List<Profile> getAllProfiles();
	
}
