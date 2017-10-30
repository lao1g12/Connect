package com.fdmgroup.fdmconnect.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.fdmconnect.entities.Post;

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


	public List<Post> searchForPostKeyWords(List<String> keyWordList, int listSize, List<Post> allPosts) {
		
		ArrayList<Post> searchList = new ArrayList<Post>();
		for (Post post : allPosts) {
			int count = 0;
			for (String keyword : keyWordList) {
				if(post.getListOfKeyWords().contains(keyword)){
					count += 1;
					System.out.println(listSize);
					System.out.println(count);
					if(count == listSize || count == 7){
						searchList.add(post);
						System.out.println(searchList);
						break;
					}
				}
//				} else
//					continue;

			}
			}
		
		System.out.println(searchList);
		return searchList;
		
	}
}
	


	


