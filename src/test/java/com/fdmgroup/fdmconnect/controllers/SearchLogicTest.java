package com.fdmgroup.fdmconnect.controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.fdmconnect.entities.Post;

public class SearchLogicTest {

	private List<String> keyWordList;
	private List<Post> allPosts;
	private SearchLogic searchLogic;
	private Post post;
	int listSize;
	
	@Before
	public void setUp() {
		
		keyWordList = new ArrayList<String>();
		allPosts = new ArrayList<Post>();
		searchLogic = new SearchLogic();
		post = new Post();
		
	}

	@Test
	public void test_searchForPostKeyWords_returnsSearchList() {
		
		keyWordList.add("a");
		post.setBodyText("a");
		allPosts.add(post);
		listSize = 1;
		List<Post> resultList = new ArrayList<Post>();
		resultList.add(post);
		
		List<Post> searchList = searchLogic.searchForPostKeyWords(keyWordList, listSize, allPosts);
		
		assertEquals(searchList, resultList);
		
	}

}
