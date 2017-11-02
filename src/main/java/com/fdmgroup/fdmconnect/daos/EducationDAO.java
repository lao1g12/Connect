package com.fdmgroup.fdmconnect.daos;

import com.fdmgroup.fdmconnect.entities.Education;

public interface EducationDAO {
	
	public Education getEducation(int educationId);
	public void removeEducation(int educationId);
	public void addEducation(Education education);


}
