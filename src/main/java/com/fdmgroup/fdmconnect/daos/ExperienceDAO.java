package com.fdmgroup.fdmconnect.daos;

import com.fdmgroup.fdmconnect.entities.Experience;

public interface ExperienceDAO {
	
	public void removeExperience(int experienceId);

	public void addExperience(Experience experience);

}
