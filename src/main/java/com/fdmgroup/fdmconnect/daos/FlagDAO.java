package com.fdmgroup.fdmconnect.daos;

import com.fdmgroup.fdmconnect.entities.Flag;

public interface FlagDAO {

	public void addFlag(Flag flag);
	public Flag getFlag(int i);
	public void updateFlag(Flag flag);
	
}
