package com.fdmgroup.fdmconnect.controllers;

import static org.junit.Assert.*;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.fdmgroup.fdmconnect.daos.FlagDAOImpl;
import com.fdmgroup.fdmconnect.daos.PostDAOImpl;
import com.fdmgroup.fdmconnect.daos.UserDAOImpl;
import com.fdmgroup.fdmconnect.entities.User;

public class IndexControllerTest {
	
	private UserDAOImpl userDao;
	private PostDAOImpl postDao;
	private IndexController index;
	private HttpSession session;
	private HttpServletRequest request;
	private Principal principal;
	private Calendar calendar;
	private User user;
	private FlagDAOImpl flagDao;

	@Before
	public void setUp() {
		userDao = mock(UserDAOImpl.class);
		postDao = mock(PostDAOImpl.class);
		flagDao = mock(FlagDAOImpl.class);
		index = new IndexController(userDao, postDao, flagDao);
		session = mock(HttpSession.class);
		request = mock(HttpServletRequest.class);
		principal = mock(Principal.class);
		user = mock(User.class);
		calendar = mock(Calendar.class);
	}

	@Test
	public void test_goToIndex_returnsIndexPage() {
		String result = index.goToIndex();
		
		assertEquals(result, "index");
	}
	
//	@Test
//	public void test_goToHome_callsGetUserAndReturnsHomePage(){
//		when(principal.getName()).thenReturn("username");
//		when(userDao.getUser("username")).thenReturn(user);
//		when(user.getLastLogin()).thenReturn(calendar);
//		String result = index.goToHome(session, principal);
//		
//		verify(userDao).getUser("username");
//		assertEquals(result, "user/Home");
//	}
	
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
