package com.fdmgroup.fdmconnect.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.ui.Model;

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

public class UserControllerTest {

	private UserDAOImpl userDao;
	private PostDAOImpl postDao;
	private FlagDAOImpl flagDao;
	private UserController userController;
	private HttpSession session;
	private Principal principal;
	private Model model;
	private User user;
	private List<User> users;
	private Flag flag;
	private Profile profile;
	private ProfileDAOImpl profileDao;
	private EducationDAOImpl educationDao;
	private ExperienceDAOImpl experienceDao;
	private Post flaggedPost;
	private Education education;
	private HttpServletRequest request;
	private Experience experience;
	private Set<Profile> profiles;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		userDao = mock(UserDAOImpl.class);
		postDao = mock(PostDAOImpl.class);
		profileDao = mock(ProfileDAOImpl.class);
		educationDao = mock(EducationDAOImpl.class);
		experienceDao = mock(ExperienceDAOImpl.class);
		flagDao = mock(FlagDAOImpl.class);
		session = mock(HttpSession.class);
		model = mock(Model.class);
		user = mock(User.class);
		flag = mock(Flag.class);
		users = mock(ArrayList.class);
		principal = mock(Principal.class);
		profile = mock(Profile.class);
		userController = new UserController(userDao, profileDao, flagDao,
				postDao, educationDao, experienceDao);
		flaggedPost = mock(Post.class);
		education = mock(Education.class);
		request = mock(HttpServletRequest.class);
		experience = mock(Experience.class);
		profiles = mock(HashSet.class);
	}

	@Test
	public void test_createProfile_returnsMappingToUserViewAccount() {

		when(principal.getName()).thenReturn("username");
		when(userDao.getUser("username")).thenReturn(user);
		when(user.getProfile()).thenReturn(profile);
		String result = userController.createProfile(model, session, principal, request);

		assertEquals(result, "user/ViewAccount");

	}

	@Test
	public void test_goToFlagPost_returnsMappingToUserHome() {

		int postId = 0;

		String result = userController.goToFlagPost(session, model, postId);

		assertEquals(result, "user/Home");

	}

	@Test
	public void test_doFlagPost_invokesFlagDAOAddFlagAndReturnsMappingToUserHome() {

		int postId = 0;

		when(postDao.getPost(postId)).thenReturn(flaggedPost);
		String result = userController.doFlagPost(session, model, flag, postId);

		verify(flagDao).addFlag(flag);
		assertEquals(result, "user/Home");

	}

	@Test
	public void test_goToViewAllUsers_returnsMappingToUserViewAllUsers() {

		when(userDao.getAllUsers()).thenReturn(users);
		String result = userController.goToViewAllUsers(session, model);

		assertEquals(result, "user/ViewAllUsers");

	}

	@Test
	public void test_addEducation_returnsMappingToUserAddEducation() {

		String result = userController.addEducation(model);

		assertEquals(result, "user/AddEducation");

	}

	@Test
	public void test_doAddEducation_returnsMappingToUserEditAccount() {

		when(principal.getName()).thenReturn("username");
		when(userDao.getUser("username")).thenReturn(user);
		when(user.getProfile()).thenReturn(profile);
		String result = userController.doAddEducation(education, session,
				principal, model, request);

		assertEquals(result, "user/EditAccount");
	}

	@Test
	public void test_updateProfile_returnsMappingToUserViewAccount() {

		String result = userController.updateProfile(principal, profile, session, request);

		assertEquals(result, "redirect:/user/account");
		
	}
	
	@Test
	public void test_editProfile_returnsMappingToUserEditAccount() {

		when(principal.getName()).thenReturn("username");
		when(userDao.getUser("username")).thenReturn(user);
		when(user.getProfile()).thenReturn(profile);
		String result = userController.editProfile(model, principal);

		assertEquals(result, "user/EditAccount");
		
	}
	
	@Test
	public void test_doPasswordChange_returnsUserEditAccountIfNewPasswordEqualsConfPassword(){
		
		String password = ""; String oldPassword = "";
		String newPassword = ""; String confNewPassword = "";
		
		when(user.getPassword()).thenReturn(password);
		when(request.getParameter("password")).thenReturn(oldPassword);
		when(request.getParameter("newPassword")).thenReturn(newPassword);
		when(request.getParameter("confNewPassword")).thenReturn(confNewPassword);
		when(session.getAttribute("user")).thenReturn(user);
		when(user.getProfile()).thenReturn(profile);
		String result = userController.doPasswordChange(request, session, model);
		
		assertEquals(result, "user/EditAccount");
		
	}
	
	@Test
	public void test_doPasswordChange_returnsUserEditAccountIfOldPasswordDoesNotEqualUserGetPassword(){
		
		String password = ""; String oldPassword = "a";
		String newPassword = ""; String confNewPassword = "";
		
		when(user.getPassword()).thenReturn(password);
		when(request.getParameter("password")).thenReturn(oldPassword);
		when(request.getParameter("newPassword")).thenReturn(newPassword);
		when(request.getParameter("confNewPassword")).thenReturn(confNewPassword);
		when(session.getAttribute("user")).thenReturn(user);
		when(user.getProfile()).thenReturn(profile);
		String result = userController.doPasswordChange(request, session, model);
		
		assertEquals(result, "user/EditAccount");
		
	}
	
	@Test
	public void test_doPasswordChange_returnsUserEditAccountIfNewPasswordDoesNotMatchConfPassword(){
		
		String password = ""; String oldPassword = "";
		String newPassword = ""; String confNewPassword = "a";
		
		when(user.getPassword()).thenReturn(password);
		when(request.getParameter("password")).thenReturn(oldPassword);
		when(request.getParameter("newPassword")).thenReturn(newPassword);
		when(request.getParameter("confNewPassword")).thenReturn(confNewPassword);
		when(session.getAttribute("user")).thenReturn(user);
		when(user.getProfile()).thenReturn(profile);
		String result = userController.doPasswordChange(request, session, model);
		
		assertEquals(result, "user/EditAccount");
		
	}
	
	@Test
	public void test_addExperience_returnsUserAddExperience(){
		
		String result = userController.addExperience(model);
		
		assertEquals(result, "user/AddExperience");
		
	}
	
	@Test
	public void test_doAddExperience_returnsUserEditAccount(){
		
		when(principal.getName()).thenReturn("username");
		when(userDao.getUser("username")).thenReturn(user);
		when(user.getProfile()).thenReturn(profile);
		String result = userController.doAddExperience(experience, session,
				principal, model, request);
		
		assertEquals(result, "user/EditAccount");
		
	}
	
	@Test
	public void test_goToViewProfile_returnsUserViewProfile(){
		
		int profileId = 0;
		
		when(profileDao.getProfile(profileId)).thenReturn(profile);
		String result = userController.goToViewProfile(session, model, profileId);
		
		assertEquals(result, "user/ViewProfile");
		
	}
	
	@Test
	public void test_goToUserSearch_returnsUserViewAllUsers(){
		
		String result = userController.goToUserSearch(session, model);
		
		assertEquals(result, "user/ViewAllUsers");
	}
	
	@Test
	public void test_doUserSearch_invokesAddProfileAndReturnsUserSearchResultsIfProfileInSet(){
		
		String name = "a";
		Set<Profile> profiles = new HashSet<Profile>();
		List<Profile> profileDaoProfiles = new ArrayList<Profile>();
		Profile profile = new Profile();
		profile.setFirstName("a"); profile.setLastName("b");
		
		profiles.add(profile); profileDaoProfiles.add(profile);
		when(profileDao.getAllProfiles()).thenReturn(profileDaoProfiles);
		String result = userController.doUserSearch(session, model, name);
		
		verify(model).addAttribute("profiles", profiles);
		assertEquals(result, "user/SearchResults");
		
	}
	
	@Test
	public void test_doUserSearch_invokesModelAddAttributeAndReturnsUserSearchResultsIfProfileIsNotInSet(){
		
		String name = "";
		
		String result = userController.doUserSearch(session, model, name);
		
		verify(model).addAttribute("nullSearchMessage", "No results found!");
		assertEquals(result, "user/SearchResults");
		
	}
	

}
