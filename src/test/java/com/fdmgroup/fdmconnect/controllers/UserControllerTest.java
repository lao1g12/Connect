package com.fdmgroup.fdmconnect.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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
	public void test_doPasswordUpdate_returnsUserEditAccountIfNewPasswordEqualsConfPassword(){
		
		String password = ""; String oldPassword = "";
		String newPassword = ""; String confNewPassword = "";
		
		when(user.getPassword()).thenReturn(password);
		when(request.getParameter("password")).thenReturn(oldPassword);
		when(request.getParameter("newPassword")).thenReturn(newPassword);
		when(request.getParameter("confNewPassword")).thenReturn(confNewPassword);
		when(session.getAttribute("user")).thenReturn(user);
		when(user.getProfile()).thenReturn(profile);
		String result = userController.doPasswordUpdate(request, session, model);
		
		assertEquals(result, "user/EditAccount");
		
	}
	
	@Test
	public void test_doPasswordUpdate_returnsUserEditAccountIfOldPasswordDoesNotEqualUserGetPassword(){
		
		String password = ""; String oldPassword = "a";
		String newPassword = ""; String confNewPassword = "";
		
		when(user.getPassword()).thenReturn(password);
		when(request.getParameter("password")).thenReturn(oldPassword);
		when(request.getParameter("newPassword")).thenReturn(newPassword);
		when(request.getParameter("confNewPassword")).thenReturn(confNewPassword);
		when(session.getAttribute("user")).thenReturn(user);
		when(user.getProfile()).thenReturn(profile);
		String result = userController.doPasswordUpdate(request, session, model);
		
		assertEquals(result, "user/EditAccount");
		
	}
	
	@Test
	public void test_doPasswordUpdate_returnsUserEditAccountIfNewPasswordDoesNotMatchConfPassword(){
		
		String password = ""; String oldPassword = "";
		String newPassword = ""; String confNewPassword = "a";
		
		when(user.getPassword()).thenReturn(password);
		when(request.getParameter("password")).thenReturn(oldPassword);
		when(request.getParameter("newPassword")).thenReturn(newPassword);
		when(request.getParameter("confNewPassword")).thenReturn(confNewPassword);
		when(session.getAttribute("user")).thenReturn(user);
		when(user.getProfile()).thenReturn(profile);
		String result = userController.doPasswordUpdate(request, session, model);
		
		assertEquals(result, "user/EditAccount");
		
	}
	
	@Test
	public void test_addExperience_returnsUserAddExperience(){
		
		String result = userController.addExperience(model);
		
		assertEquals(result, "user/AddExperience");
		
	}
	
	@Test
	public void testDoAddExperience_returnsUserEditAccount(){
		
		when(principal.getName()).thenReturn("username");
		when(userDao.getUser("username")).thenReturn(user);
		when(user.getProfile()).thenReturn(profile);
		String result = userController.doAddExperience(experience, session,
				principal, model, request);
		
		assertEquals(result, "user/EditAccount");
		
	}
	

}
