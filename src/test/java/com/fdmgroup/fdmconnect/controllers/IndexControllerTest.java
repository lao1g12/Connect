package com.fdmgroup.fdmconnect.controllers;

import static org.junit.Assert.*;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.fdmconnect.daos.NotificationDAO;
import com.fdmgroup.fdmconnect.daos.PostDAO;
import com.fdmgroup.fdmconnect.daos.PostDAOImpl;
import com.fdmgroup.fdmconnect.daos.UserDAO;
import com.fdmgroup.fdmconnect.daos.UserDAOImpl;
import com.fdmgroup.fdmconnect.entities.Profile;
import com.fdmgroup.fdmconnect.entities.User;

public class IndexControllerTest {
	
	private UserDAO userDao;
	private PostDAO postDao;
	private NotificationDAO notificationDao;
	private IndexController index;
	private HttpSession session;
	private HttpServletRequest request;
	private Principal principal;
	private Calendar calendar;
	private User user;
	private Profile profile;

	@Before
	public void setUp() {
		userDao = mock(UserDAOImpl.class);
		postDao = mock(PostDAOImpl.class);
		index = new IndexController(userDao, postDao, notificationDao);
		session = mock(HttpSession.class);
		request = mock(HttpServletRequest.class);
		principal = mock(Principal.class);
		user = mock(User.class);
		calendar = mock(Calendar.class);
		profile = mock(Profile.class);
	}

	@Test
	public void test_goToIndex_returnsIndexPage() {
		String result = index.goToIndex();
		
		assertEquals(result, "index");
	}
	
	@Test
	public void test_goToHome_callsGetUserAndReturnsHomePageIfUserPreviouslyLoggedIn(){
		
		when(principal.getName()).thenReturn("username");
		when(userDao.getUser("username")).thenReturn(user);
		when(user.getProfile()).thenReturn(profile);
		when(user.getLastLogin()).thenReturn(calendar);
		String result = index.goToHome(session, principal, request);
		
		verify(userDao).getUser("username");
		assertEquals(result, "user/Home");
		
	}
	
	@Test
	public void test_goToHome_returnsUpdatePasswordIfFirstLogIn(){
		
		when(principal.getName()).thenReturn("username");
		when(userDao.getUser("username")).thenReturn(user);
		when(user.getProfile()).thenReturn(profile);
		when(user.getLastLogin()).thenReturn(null);
		String result = index.goToHome(session, principal, request);
		
		assertEquals(result, "user/UpdatePassword");
		
	}
	
	@Test
	public void test_goToLogout_callsSessionInvalidateAndReturnsIndexMapping(){
		String result = index.goToLogout(session);
		
		verify(session).invalidate();
		assertEquals(result, "redirect:/");
	}
	
	@Test
	public void test_doPasswordUpdate_returnsMappingToLoginIfPasswordsMatch(){
		
		String newPassword = ""; String confNewPassword = "";
		
		when(request.getParameter("newPassword")).thenReturn(newPassword);
		when(request.getParameter("confNewPassword")).thenReturn(confNewPassword);
		when(session.getAttribute("user")).thenReturn(user);
		
		String result = index.doPasswordUpdate(request, session);
		
		assertEquals(result, "redirect:/user/login");
	}
	
	@Test
	public void test_doPasswordUpdate_returnsMappingToUpdatePasswordIfPasswordsDoNotMatch(){
		
		String newPassword = ""; String confNewPassword = "a";
		
		when(request.getParameter("newPassword")).thenReturn(newPassword);
		when(request.getParameter("confNewPassword")).thenReturn(confNewPassword);
		when(session.getAttribute("user")).thenReturn(user);
		
		String result = index.doPasswordUpdate(request, session);
		
		assertEquals(result, "user/UpdatePassword");
	}
	

}
