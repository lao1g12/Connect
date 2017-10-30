package com.fdmgroup.fdmconnect.controllers;

import javax.persistence.PersistenceException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fdmgroup.fdmconnect.daos.FlagDAOImpl;
import com.fdmgroup.fdmconnect.daos.PostDAOImpl;
import com.fdmgroup.fdmconnect.daos.UserDAOImpl;
import com.fdmgroup.fdmconnect.entities.Flag;
import com.fdmgroup.fdmconnect.entities.Post;
import com.fdmgroup.fdmconnect.entities.Profile;
import com.fdmgroup.fdmconnect.entities.User;
import com.fdmgroup.fdmconnect.controllers.Logging;

@Controller
public class AdminController {

	@Autowired
	private PostDAOImpl postDao;
	@Autowired
	private UserDAOImpl userDao;
	@Autowired
	private FlagDAOImpl flagDao;

	public AdminController() {
	}

	public AdminController(PostDAOImpl postDao, UserDAOImpl userDao,
			FlagDAOImpl flagDao) {
		super();
		this.postDao = postDao;
		this.userDao = userDao;
		this.flagDao = flagDao;
	}

	@RequestMapping("/admin")
	public String admin(Model model) {
		
		Flag flag = flagDao.getFlag(1);
		model.addAttribute("flag", flag);
		return "admin/Home";
	}

	@RequestMapping("admin/submitPost")
	public String submitPost(Model model, HttpSession session) {
		
		Post post = new Post();

		model.addAttribute(post);
		return "admin/AddPost";

	}

	@RequestMapping("admin/addPost")
	public String addNewPost(Post post, HttpSession session,
			HttpServletRequest request) {

		User user = (User) session.getAttribute("user");
		post.setPostOwner(user);
		StringBuffer sb = new StringBuffer();
		SearchMethod sm = new SearchMethod();
		sb.append(post.getBodyText() + " " + post.getTitle() + " " + " "
				+ post.getImgUrl() + " " + post.getLink());
		String checkString = sb.toString();
		checkString = checkString.replaceAll("[^a-zA-Z\\s]", " ");
		checkString = checkString.toLowerCase();
		Flag flag = flagDao.getFlag(1);
		String badWords = flag.getFlagInfo();
		badWords = badWords.replaceAll("[^a-zA-Z\\s]", " ");
		badWords = badWords.toLowerCase();
		List<String> badWordList = new ArrayList<String>(Arrays.asList(badWords
				.split(" ")));
		List<String> checkedBadWords = sm.searchForListings(badWordList,
				checkString);

		if (checkedBadWords.size() > 0) {
			StringBuffer sbReturn = new StringBuffer();
			for (String badWord : checkedBadWords) {
				sbReturn.append(badWord + " ");
			}
			String badWordString = sbReturn.toString();
			request.setAttribute("badPost",
					"You just tried to post an article with the following inappropriate words :"
							+ badWordString);
			return "admin/AddPost";
		}

		postDao.addPost(post);
		return "redirect:/user/login";

	}

	@RequestMapping("/admin/viewAllPosts")
	public String goToViewAllPosts(Model model) {

		List<Post> posts = postDao.getAllPosts();
		model.addAttribute("post", posts);
		Logging.Log("trace", "Admin Controller: Display all posts called.");
		return "admin/DisplayAllPosts";

	}

	@RequestMapping("/admin/processRemovePost")
	public String processRemovePost(@RequestParam int postId, Model model,
			RedirectAttributes ra) {

		postDao.removePost(postId);
		ra.addFlashAttribute("message", "Post removed succesfully.");
		Logging.Log("info", "Admin Controller: Post removed succesfully "
				+ postId);
		return "redirect:/admin/viewAllFlaggedPosts";

	}

	@RequestMapping("/admin/viewAllFlags")
	public String goToViewAllFlags(Model model, @RequestParam int postId,
			HttpServletRequest request) {

		Post post = postDao.getPost(postId);
		Set<Flag> flagList = post.getFlags();
		request.setAttribute("flagList", flagList);
		request.setAttribute("post", post);
		Logging.Log("info", "Admin Controller: Display all flags called.");
		return "admin/DisplayAllFlags";

	}

	@RequestMapping("/admin/goToAddUser")
	public String goToAddUser(HttpSession session, Model model) {

		User user = new User();
		Profile profile = new Profile();
		user.setProfile(profile);

		model.addAttribute("user", user);		
		Logging.Log("info", "Admin Controller: Add user called.");
		return "admin/AddUser";

	}

	@RequestMapping("/admin/doAddUser")
	public String doAddUser(HttpSession session, Model model, User user) {

		if (! user.getPassword().equals(user.getConfirmPassword())) {
			model.addAttribute("passwordErrorMessage", "Passwords do not match");
			return "admin/AddUser";
		}

		user.setEmail(user.getUsername() + "@fdmgroup.com");

		try {
			userDao.addUser(user);
		} catch (PersistenceException pe) {
			model.addAttribute("userErrorMessage", "Username already exists.");
			return "admin/AddUser";
		}

		Logging.Log("info",
				"Admin Controller: " + session.getAttribute("username")
						+ " added user " + user.getUsername());
		model.addAttribute("userAddedMessage",
				"User successfully added, they can now update their profile.");
		return "redirect:/admin";

	}

	@RequestMapping("/admin/processRemoveUser")
	public String processRemoveUser(@RequestParam String username, Model model, RedirectAttributes ra) {

		userDao.removeUser(username);
		ra.addFlashAttribute("message", "User removed succesfully");
		Logging.Log("info", "Admin Controller: User removed succesfully" + username);
		return "redirect:/admin/viewAllUsers";

	}

	@RequestMapping("/admin/viewAllUsers")
	public String goToViewAllUsers(Model model) {

		List<User> users = userDao.getAllUsers();
		model.addAttribute("users", users);
		Logging.Log("info", "Admin Controller: Display all users called");
		return "admin/DisplayAllUsers";

	}

	@RequestMapping("/admin/viewAllFlaggedPosts")
	public String goToViewAllFlaggedPosts(Model model,
			HttpServletRequest request) {

		Logging.Log("info", "Admin Controller: Display all flagged posts called.");
		List<Post> posts = postDao.getAllPosts();
		ArrayList<Post> flaggedPosts = new ArrayList<Post>();
		for (Post post : posts) {
			if (post.getFlags().size() > 0) {
				flaggedPosts.add(post);
			}
		}
		request.setAttribute("flaggedPosts", flaggedPosts);
		return "admin/DisplayAllFlaggedPosts";

	}

	@RequestMapping("admin/addBadWords")
	public String addBadWords(@RequestParam String badWords) {
		
		Flag flag = flagDao.getFlag(1);
		flag.setFlagInfo(badWords);
		flagDao.updateFlag(flag);
		return "redirect:/admin";

	}

}
