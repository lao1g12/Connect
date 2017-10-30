package com.fdmgroup.fdmconnect.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.fdmconnect.entities.Post;

public class BuisnessLogic {
	@Autowired
	private EntityManagerFactory factory;

	
	public List<String> searchForListings(String badWords, String checkString){
		badWords = badWords.replaceAll("[^a-zA-Z\\s]", " ");
		badWords = badWords.toLowerCase();

		List<String> badWordList = new ArrayList<String>(Arrays.asList(badWords
				.split(" ")));
		ArrayList<String> badReturnList = new ArrayList<String>();
			for (String badWord : badWordList) {
				if(checkString.contains(badWord)){
					badReturnList.add(badWord);
					}
				}
		return badReturnList;
		
	}
	
	public void badWordCheck(){
		
	}


	public List<Post> searchForPostKeyWords(List<String> keyWordList, int listSize, List<Post> allPosts) {
		
		ArrayList<Post> searchList = new ArrayList<Post>();
		for (Post post : allPosts) {
			int count = 0;
			for (String keyword : keyWordList) {
				System.out.println(post.getListOfKeyWords());
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
	


	


