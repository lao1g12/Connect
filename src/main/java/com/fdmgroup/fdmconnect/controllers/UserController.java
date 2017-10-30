package com.fdmgroup.fdmconnect.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fdmgroup.fdmconnect.daos.EducationDAOImpl;
import com.fdmgroup.fdmconnect.daos.ExperienceDAOImpl;
import com.fdmgroup.fdmconnect.daos.FlagDAOImpl;
import com.fdmgroup.fdmconnect.daos.PostDAOImpl;
import com.fdmgroup.fdmconnect.daos.ProfileDAOImpl;
import com.fdmgroup.fdmconnect.daos.UserDAOImpl;
import com.fdmgroup.fdmconnect.entities.Education;
import com.fdmgroup.fdmconnect.entities.Experience;
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
	@Autowired
	private EducationDAOImpl educationDao;
	@Autowired
	private ExperienceDAOImpl experienceDao;
	
	
	
	public UserController() {}
	
	public UserController(UserDAOImpl userDao, ProfileDAOImpl profileDao, FlagDAOImpl flagDao, PostDAOImpl postDao,
			EducationDAOImpl educationDao, ExperienceDAOImpl experienceDao) {
		super();
		this.userDao = userDao;
		this.profileDao = profileDao;
		this.flagDao = flagDao;
		this.postDao = postDao;
		this.educationDao = educationDao;
		this.experienceDao= experienceDao;
	}

	@RequestMapping(value = { "/admin/account", "/user/account"})
	public String createProfile(Model model, HttpSession session, Principal principal, HttpServletRequest request) {

		User user = userDao.getUser(principal.getName());
		Profile profile = user.getProfile();
		Set<Education> education= profile.getEducation();
		Set<Experience> experience = profile.getExperiences();
		Set<Post> posts = user.getPosts();
		
		model.addAttribute("profile", profile);
		model.addAttribute("education", education);
		model.addAttribute("experience", experience);
		session.setAttribute("userPosts", posts);
		request.setAttribute("user", user);
		
		Logging.Log("info", "User Controller: "+session.getAttribute("username") + "viewed their profile.");
		return "user/ViewAccount";

	}
	
	@RequestMapping("user/submitPost")
	public String submitPost(Model model, HttpSession session) {
		
		Post post = new Post();

		model.addAttribute(post);
		return "user/AddPost";

	}
	
	@RequestMapping(value = {"user/addPost"} )
	public String addNewPost(Post post, HttpSession session,
			HttpServletRequest request) {

		User user = (User) session.getAttribute("user");
		post.setPostOwner(user);
		BuisnessLogic bl = new BuisnessLogic();
		String checkString = post.getFullListOfKeyWords();
		Flag flag = flagDao.getFlag(1);
		String badWords = flag.getFlagInfo();
		List<String> checkedBadWords = bl.searchForListings(badWords,
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
			return "user/AddPost";
		}

		postDao.addPost(post);
		return "redirect:/user/login";

	}
	

	@RequestMapping("/user/goToFlagPost")
	public String goToFlagPost(HttpSession session, Model model, @RequestParam(name = "postId") int postId) {

		Flag flag = new Flag();

		model.addAttribute("flagPost", "flagged");
		model.addAttribute("postId", postId);
		model.addAttribute("flag", flag);
		return "user/Home";

	}

	@RequestMapping("/user/doFlagPost")
	public String doFlagPost(HttpSession session, Model model, Flag flag, @RequestParam(name = "postId") int postId) {

		Post flaggedPost = postDao.getPost(postId);
		flag.setReporter((User) session.getAttribute("user"));
		flag.setFlaggedPost(flaggedPost);

		try {
			flagDao.addFlag(flag);
		} catch (PersistenceException pe) {
			model.addAttribute("flagErrorMessage", "Flag ID already exists.");
			return "user/Home";
		}

		Logging.Log("info",
				"User Controller: " + session.getAttribute("username") + " submitted flag " + flag.getFlagId());
		model.addAttribute("flagSubmittedMessage", "Post successfully flagged, an admin has been notified.");
		model.addAttribute("postId", postId);
		return "user/Home";

	}
	
	@RequestMapping("/user/viewAllUsers")
	public String goToViewAllUsers(HttpSession session, Model model){
		
		List<User> users = userDao.getAllUsers();
		session.setAttribute("users", users);
		Logging.Log("info",
				"User Controller: " + session.getAttribute("username") + " called view all users.");
		return "user/ViewAllUsers";
		
	}
	
	@RequestMapping("/user/addEducation")
	public String addEducation(Model model) {
		Education education = new Education();
		model.addAttribute(education);
		return "user/AddEducation";

	}

	@RequestMapping("/user/doAddEducation")
	public String doAddEducation(Education education, HttpSession session, Principal principal,Model model, HttpServletRequest request) {
		
		User user = userDao.getUser(principal.getName());
		Profile profile = user.getProfile();
		education.setProfile(profile);
		model.addAttribute(profile);
		educationDao.addEducation(education);
		request.setAttribute("message", "Your Education information has been added!");
		Logging.Log("info",
				"User Controller: " + session.getAttribute("username") + " added an education entry.");

		return "user/EditAccount";

	}
	
	
	@RequestMapping("/user/deleteEducation")
	public String removeEducation(@RequestParam int educationId,RedirectAttributes ra ) { 
		
		educationDao.removeEducation(educationId);
		ra.addFlashAttribute("message", "Education removed succesfully.");
		Logging.Log("info", "User Controller: education removed succesfully "
				+ educationId);
		return "redirect:/user/account";
	}

	
	
	@RequestMapping("/user/addExperience")
	public String addExperience(Model model) {
		Experience experience = new Experience();
		model.addAttribute(experience);
		return "user/AddExperience";

	}
	@RequestMapping("/user/doAddExperience")
	public String doAddExperience(Experience experience, HttpSession session, Principal principal,Model model,HttpServletRequest request) {
		
		User user = userDao.getUser(principal.getName());
		Profile profile = user.getProfile();
		experience.setProfile(profile);
		model.addAttribute(profile);
		experienceDao.addExperience(experience);
		request.setAttribute("message", "Your work experience has been added!");
		Logging.Log("info",
				"User Controller: " + session.getAttribute("username") + " added a work experience entry.");

		return "user/EditAccount";

	}
	
	

	@RequestMapping("/user/deleteExperience")
	public String removeExperience(@RequestParam int experienceId,RedirectAttributes ra ) { 
		
		experienceDao.removeExperience(experienceId);
		ra.addFlashAttribute("message", "Experience removed succesfully.");
		Logging.Log("info", "User Controller: Experience removed succesfully "
				+ experienceId);
		return "redirect:/user/account";
	}
	
	

	@RequestMapping("user/editProfile")
	public String editProfile(Model model, Principal principal) {
		
		User user = userDao.getUser(principal.getName());
		Profile profile = user.getProfile();
		model.addAttribute("profile", profile);
		return "user/EditAccount";
		
	}
	
	@RequestMapping("user/goToEditPost")
	public String goToEditPost(Model model, Principal pricipal, @RequestParam(name = "postId") int postId) {
		
		Post post = new Post();

		model.addAttribute("editPost", "doEdit");
		model.addAttribute("postId", postId);
		model.addAttribute("post", post);
		return "user/ViewAccount";
		
	}
	
	@RequestMapping("/user/doEditPost")
	public String doEditPost(HttpSession session, Model model, Post post, 
			@RequestParam(name = "postId") int postId, RedirectAttributes ra) {
		
		try {
			post.setPostId(postId);
			postDao.updatePost(post);
		} catch (PersistenceException pe) {
			model.addAttribute("postErrorMessage", "Error updating post.");
			return "user/ViewAccount";
		}

		Logging.Log("info",
				"User Controller: " + session.getAttribute("username") + " edited post " + postId);
		ra.addFlashAttribute("postEditedMessage", "Post successfully edited.");
		ra.addFlashAttribute("postId", postId);
		return "redirect:/user/account";

	}
	
	@RequestMapping("user/doUpdateProfile")
	public String updateProfile(Principal principal, Profile profile, HttpSession session, HttpServletRequest request) {

		profileDao.updateProfile(profile);
		Logging.Log("info",
				"User Controller: " + session.getAttribute("username") + " updated their profile.");

		return "redirect:/user/account";

	}

	@RequestMapping("/user/passwordChange")
	public String doPasswordChange(HttpServletRequest request, HttpSession session, Model model) {
		
		String oldPassword = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		String confNewPassword = request.getParameter("confNewPassword");
		User user = (User) session.getAttribute("user");
		Profile profile = user.getProfile();
		model.addAttribute("profile", profile);
		
		if (oldPassword.equals(user.getPassword())) {
			if (newPassword.equals(confNewPassword)) {
				request.setAttribute("UpdatedPass", "Your password has been succesfully changed");
				user.setPassword(newPassword);
				userDao.updateUser(user);
				Logging.Log("info", "User Controller: "+user.getUsername()+" updated password");
				return "user/EditAccount";
			} else {
				request.setAttribute("passNotMatch", "The two new passwords you entered do not match!");
				Logging.Log("info", "User Controller: "+user.getUsername()+" attempted to change password " +
						"but the two new passwords were different, redirected to the UpdateInfo page");
				return "user/EditAccount";
			}
		} else {
			request.setAttribute("incorrectPass", "The password you entered is not correct");
			Logging.Log("info", "User Controller: "+user.getUsername()+" attempted to change password but the " +
					"current password was wrong, redirected to the UpdateInfo page");

			return "user/EditAccount";
		}
		
	}
	
	@RequestMapping("/user/viewProfile")
	public String goToViewProfile(HttpSession session, Model model, @RequestParam(name="profileId") int profileId, HttpServletRequest request) {
		
		Profile profile = profileDao.getProfile(profileId);
		User user = userDao.getUserByProfile(profile);
		Set<Education> education= profile.getEducation();
		Set<Experience> experience = profile.getExperiences();
		model.addAttribute("profile", profile);
		model.addAttribute("education", education);
		model.addAttribute("experience", experience);
		request.setAttribute("userCur", user);

		return "user/ViewAccount";
		
	}
	
	@RequestMapping("/user/goToUserSearch")
	public String goToUserSearch(HttpSession session, Model model) {

		model.addAttribute("userSearch", "search");
		return "user/ViewAllUsers";

	}
	
	@RequestMapping("/user/doUserSearch")
	public String doUserSearch(HttpSession session, Model model, @RequestParam(name="profileName") String name){
		
		Set<Profile> profiles = new HashSet<Profile>();
		String lowerCaseName = name.toLowerCase();
		
		for(Profile profile : profileDao.getAllProfiles()){
			
			String firstNameLower = profile.getFirstName().toLowerCase();
			String lastNameLower = profile.getLastName().toLowerCase();
			
			if( firstNameLower.contains(lowerCaseName) || 
				lastNameLower.contains(lowerCaseName) ||
			    (firstNameLower+" "+lastNameLower).contains(lowerCaseName)){
				
				profiles.add(profile);
				
			} 
			
		}
		
		if (profiles.size() < 1){
			
			model.addAttribute("nullSearchMessage", "No results found!");
			return "user/SearchResults";
			
		}
		
		model.addAttribute("profiles", profiles);
		Logging.Log("info",
				"User Controller: " + session.getAttribute("username") + " searched for user by name "+name);

		return "user/SearchResults";
		
	}

}
