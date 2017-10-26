package com.fdmgroup.fdmconnect.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	

	Logger logger = Logger.getLogger(getClass());
		
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

	@RequestMapping("user/account")
	public String createProfile(Model model, HttpSession session, Principal principal, HttpServletRequest request) {

		User user = userDao.getUser(principal.getName());
		Profile profile = user.getProfile();
		Set<Education> education= profile.getEducation();
		Set<Experience> experience = profile.getExperiences();
		model.addAttribute("profile", profile);
		model.addAttribute("education", education);
		model.addAttribute("experience", experience);
		request.setAttribute("user", user);
		logger.info(session.getAttribute("username") + "going to profile");
		return "user/ViewAccount";

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
		model.addAttribute("users", users);
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


		return "user/EditAccount";

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


		return "user/EditAccount";

	}

	@RequestMapping("user/doUpdateProfile")
	public String updateProfile(Principal principal, Profile profile, HttpSession session, HttpServletRequest request) {

		profileDao.updateProfile(profile);

		return "redirect:/user/account";

	}

	@RequestMapping("user/editProfile")
	public String editProfile(Model model, Principal principal) {
		User user = userDao.getUser(principal.getName());
		Profile profile = user.getProfile();
		model.addAttribute("profile", profile);
		return "user/EditAccount";
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
				logger.info(user.getUsername()+" updated password");
				return "user/EditAccount";
			} else {
				request.setAttribute("passNotMatch", "The two new passwords you entered do not match!");
				logger.info(user.getUsername()+" attempted to change password but the two new passwords were different, redirected to the UpdateInfo page");
				return "user/EditAccount";
			}
		} else {
			request.setAttribute("incorrectPass", "The password you entered is not correct");
			logger.info(user.getUsername()+" attempted to change password but the current password was wrong, redirected to the UpdateInfo page");

			return "user/EditAccount";
		}
	}
	
	@RequestMapping("/user/viewProfile")
	public String goToViewProfile(HttpSession session, Model model, @RequestParam(name="profileId") int profileId) {
		
		Profile profile = profileDao.getProfile(profileId);
		model.addAttribute("profile", profile);
		model.addAttribute("experience", profile.getExperiences());
		model.addAttribute("education", profile.getEducation());
		
		return "user/ViewProfile";
		
	}
	
	@RequestMapping("/user/goToUserSearch")
	public String goToUserSearch(HttpSession session, Model model) {

		model.addAttribute("userSearch", "search");
		return "user/ViewAllUsers";

	}
	
	@RequestMapping("/user/doUserSearch")
	public String doUserSearch(HttpSession session, Model model, @RequestParam(name="profileName") String name){
		
		List<Profile> profiles = new ArrayList<Profile>();
		String lowerCaseName = name.toLowerCase();
		
		for(Profile profile : profileDao.getAllProfiles()){
			if( profile.getFirstName().toLowerCase().equals(lowerCaseName) || 
				profile.getLastName().toLowerCase().equals(lowerCaseName) ||
			    (profile.getFirstName().toLowerCase()+" "+profile.getLastName().toLowerCase()).equals(lowerCaseName)){
				
				profiles.add(profile);
				
			} else {
				
				model.addAttribute("nullSearchMessage", "No results found!");
				return "redirect:/user/viewAllUsers";
				
			}
		}
		
		model.addAttribute("profiles", profiles);
		return "user/SearchResults";
		
	}

}
