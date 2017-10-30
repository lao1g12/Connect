package com.fdmgroup.fdmconnect.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.fdmconnect.daos.FlagDAOImpl;
import com.fdmgroup.fdmconnect.daos.PostDAOImpl;
import com.fdmgroup.fdmconnect.daos.UserDAOImpl;
import com.fdmgroup.fdmconnect.entities.Flag;
import com.fdmgroup.fdmconnect.entities.Post;
import com.fdmgroup.fdmconnect.entities.User;


@Controller
public class LiamSearchController {
	
	@Autowired
	private PostDAOImpl postDao;
	@Autowired
	private UserDAOImpl userDao;
	@Autowired
	private FlagDAOImpl flagDao;

	public LiamSearchController() {
	}

	public LiamSearchController(PostDAOImpl postDao, UserDAOImpl userDao, FlagDAOImpl flagDao) {
		super();
		this.postDao = postDao;
		this.userDao = userDao;
		this.flagDao = flagDao;
	}

	
	@RequestMapping("user/searchPosts")
	public String addNewPost(Post post, HttpSession session,
			HttpServletRequest request, @RequestParam String input) {

		BuisnessLogic sm = new BuisnessLogic();
		System.out.println(input);
		input = input.replaceAll("[^a-zA-Z\\s]", " ");
		input = input.toLowerCase();
		List<String> keyWordList = new ArrayList<String>(Arrays.asList(input
				.split(" ")));
		System.out.println(keyWordList +"KJFOHEAFOHAEJUHAWJUWAOJDNBAWOJNDWJ");
		List<Post> allPosts = postDao.getAllPosts();
		int listSize = allPosts.size();

		for (int i = 0; i < keyWordList.size(); i++) {
			listSize = keyWordList.size() - i;
			List<Post> searchPosts = sm.searchForPostKeyWords(keyWordList, listSize, allPosts);
			if (searchPosts.size() > 0) {
				request.setAttribute("allPosts", searchPosts);
				Logging.Log("info", "A search was made and " + searchPosts.size() + " listings were found");

				return "user/Home";
			}
		}
		Logging.Log("info", "A search was made and no listings were found.");
			String username = (String) session.getAttribute("username");
			return ("redirect:/user/login");


	}
}
