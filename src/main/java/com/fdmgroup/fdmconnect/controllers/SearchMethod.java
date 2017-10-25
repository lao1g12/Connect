package com.fdmgroup.fdmconnect.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

public class SearchMethod {
	@Autowired
	private EntityManagerFactory factory;

	
	public List<String> searchForListings(List<String> badWordList, String checkString){
		
		ArrayList<String> badReturnList = new ArrayList<String>();
			for (String badWord : badWordList) {
				if(checkString.contains(badWord)){
					badReturnList.add(badWord);
					}
				}
		return badReturnList;
		
	}
	

	

}
