package com.fdmgroup.fdmconnect.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.persistence.PersistenceException;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.fdmconnect.daos.FlagDAOImpl;
import com.fdmgroup.fdmconnect.daos.PostDAOImpl;
import com.fdmgroup.fdmconnect.daos.ProfileDAOImpl;
import com.fdmgroup.fdmconnect.daos.UserDAOImpl;
import com.fdmgroup.fdmconnect.entities.Flag;
import com.fdmgroup.fdmconnect.entities.Post;
import com.fdmgroup.fdmconnect.entities.Profile;
import com.fdmgroup.fdmconnect.entities.User;

@Controller
public class UserController {
	
	@Autowired
	private UserDAOImpl userDao;
	@Autowired
	private ProfileDAOImpl profileDao;
	@Autowired
	private FlagDAOImpl flagDao;
	@Autowired
	private PostDAOImpl postDao;
	
	Logger logger = Logger.getLogger(getClass());
		
	public UserController() {}
	
	public UserController(UserDAOImpl userDao, ProfileDAOImpl profileDao, FlagDAOImpl flagDao, PostDAOImpl postDao) {
		super();
		this.userDao = userDao;
		this.profileDao = profileDao;
		this.flagDao = flagDao;
		this.postDao = postDao;
	}

	@RequestMapping("user/account")
	public String createProfile(Model model, HttpSession session, Principal principal) {
		
		User user = userDao.getUser(principal.getName());
		Profile profile = user.getProfile();
		model.addAttribute("profile", profile);
		logger.info(session.getAttribute("username")+"going to profile");
		return "user/ViewAccount";  

	}
	
	@RequestMapping("/user/goToFlagPost")
	public String goToFlagPost(HttpSession session, Model model, @RequestParam(name="postId") int postId){
				
		Flag flag = new Flag();
		
		model.addAttribute("flagPost", "flagged");
		model.addAttribute("postId", postId);
		model.addAttribute("flag", flag);
		return "user/Home";
		
	}
	
	@RequestMapping("/user/doFlagPost")
	public String doFlagPost(HttpSession session, Model model, Flag flag, @RequestParam(name="postId") int postId){
		
		Post flaggedPost = postDao.getPost(postId);
		flag.setReporter((User) session.getAttribute("user"));
		flag.setFlaggedPost(flaggedPost);
		
		try {
			flagDao.addFlag(flag);;
		} catch (PersistenceException pe) {
			model.addAttribute("flagErrorMessage", "Flag ID already exists.");
			return "user/Home";
		}
		
		Logging.Log("info", "User Controller: "+session.getAttribute("username")+" submitted flag "
				+flag.getFlagId());
		model.addAttribute("flagSubmittedMessage", "Post successfully flagged, an admin has been notified.");
		model.addAttribute("postId", postId);
		return "user/Home";
		
	}
}
