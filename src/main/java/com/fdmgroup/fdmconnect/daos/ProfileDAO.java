package com.fdmgroup.fdmconnect.daos;

import com.fdmgroup.fdmconnect.entities.Profile;

public interface ProfileDAO {
	
	public void updateProfile(Profile profile);
	public Profile getProfile(int profileId);

}
