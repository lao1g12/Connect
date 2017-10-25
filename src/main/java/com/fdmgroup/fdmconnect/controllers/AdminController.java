
package com.fdmgroup.fdmconnect.controllers;


import javax.persistence.PersistenceException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	public AdminController(PostDAOImpl postDao, UserDAOImpl userDao, FlagDAOImpl flagDao) {
		super();
		this.postDao = postDao;
		this.userDao = userDao;
		this.flagDao = flagDao;
	}

	@RequestMapping("/admin")
	public String admin() {

		return "admin/Home";
	}

	@RequestMapping("admin/submitPost")
	public String submitPost(Model model, HttpSession session){
		User user = (User) session.getAttribute("user");
		Post post = new Post();

		model.addAttribute(post);
		return "admin/AddPost";
		
	}
	
	@RequestMapping("admin/addPost")
	public String addNewPost(Post post, HttpSession session,HttpServletRequest request) {
		
		User user = (User) session.getAttribute("user");
		post.setPostOwner(user);
		StringBuffer sb = new StringBuffer();
		SearchMethod sm= new SearchMethod();
		sb.append(post.getBodyText()+" "+post.getTitle()+" "+" "+post.getImgUrl()+" "+post.getLink());
		String checkString = sb.toString();
		Flag flag = flagDao.getFlag(1);
		String badWords = flag.getFlagInfo();
		List<String> badWordList = new ArrayList<String>(Arrays.asList(badWords.split(" ")));
		List<String> checkedBadWords = sm.searchForListings(badWordList, checkString);
		if(checkedBadWords.size() > 0){
			StringBuffer sbReturn = new StringBuffer();
			for(String badWord : checkedBadWords){
				sbReturn.append(badWord+" ");
			}
			String badWordString = sbReturn.toString();
			request.setAttribute("badPost", "You just tried to post an article with the following inappropriate words :"+badWordString);
			return "admin/AddPost";
		}
		
		
		
		postDao.addPost(post);
		return "redirect:/user/login";

	}

	
	@RequestMapping("/admin/viewAllPosts")
	public String goToViewAllPosts(Model model) {
		
		Logging.Log("trace", "Client request to url : Display All Users");
		List<Post> posts =postDao.getAllPosts();
		model.addAttribute("post", posts);
		return "admin/DisplayAllPosts";
		
	}
	
	
	@RequestMapping("/admin/processRemovePost")
	public String processRemovePost(@RequestParam int postId, Model model) {
		
		Logging.Log("post", "post removed succesfully" + postId);
		postDao.removePost(postId);
		model.addAttribute("message", "post removed succesfully");
		return "admin/DisplayAllPosts";
		
	}
	
	
	//@RequestMapping("/admin/processFlagPostAdmin")
	//public String processFlagPostAdmin(@RequestParam int postId, Model model) {
		
		
		//postDao.removePost(postId);
		//model.addAttribute("message", "flag printed succesfully");
		//return "admin/DisplayAllPosts";
		
	//}
	
	
	//@RequestMapping("/admin/processFlagPostUser")
	//public String processFlagPostUser(@RequestParam int postId, Model model) {
		
		
		//postDao.removePost(postId);
	//	model.addAttribute("message", "flag printed succesfully");
	//	return "user/DisplayAllPosts";
		
	//}
	
	@RequestMapping("/admin/goToAddUser")
	public String goToAddUser(HttpSession session, Model model) {

		User user = new User();
		Profile profile = new Profile();
		user.setProfile(profile);

		model.addAttribute("user", user);
		return "admin/AddUser";

	}

	@RequestMapping("/admin/doAddUser")
	public String doAddUser(HttpSession session, Model model, User user) {
		
		if (!user.getPassword().equals(user.getConfirmPassword())){
			model.addAttribute("passwordErrorMessage","Passwords do not match");
			return "admin/AddUser";
		}
		
		user.setEmail(user.getUsername()+"@fdmgroup.com");
		
//		Profile profile = user.getProfile();
//		Calendar temp = Calendar.getInstance();
//		
//		// Set start and end date
//		String[] date = startDate.split("/");
//		temp.set(Integer.parseInt(date[0]),Integer.parseInt(date[1]),
//				Integer.parseInt(date[2]));
//		
//		profile.setStartDate(temp);
		
		try {
			userDao.addUser(user);
		} catch (PersistenceException pe) {
			model.addAttribute("userErrorMessage", "Username already exists.");
			return "admin/AddUser";
		}
		
		Logging.Log("info", "Admin Controller: "+session.getAttribute("username")+" added user "
				+user.getUsername());
		model.addAttribute("userAddedMessage", "User successfully added, they can now update their profile.");
		return "admin/Home";
		
	}
		
	@RequestMapping("/admin/processRemoveUser")
	public String processRemoveUser(@RequestParam String username, Model model) {
		
		Logging.Log("info", "user removed succesfully" + username);
		userDao.removeUser(username);
		model.addAttribute("message", "User removed succesfully");
		return "admin/DisplayAllUsers";
		
	}

	@RequestMapping("/admin/viewAllUsers")
	public String goToViewAllUsers(Model model) {
		
		Logging.Log("trace", "Client request to url : Display All Users");
		List<User> users = userDao.getAllUsers();
		model.addAttribute("users", users);
		return "admin/DisplayAllUsers";
		
	}
	
	

}
