package com.fdmgroup.fdmconnect.controllers;

import static org.junit.Assert.*;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.fdmgroup.fdmconnect.daos.UserDAOImpl;
import com.fdmgroup.fdmconnect.entities.User;

public class IndexTest {
	
	private UserDAOImpl userDao;
	private Index index;
	private HttpSession session;
	private Principal principal;
	private User user;

	@Before
	public void setUp() {
		userDao = mock(UserDAOImpl.class);
		index = new Index(userDao);
		session = mock(HttpSession.class);
		principal = mock(Principal.class);
		user = mock(User.class);
	}

	@Test
	public void test_goToIndex_returnsIndexPage() {
		String result = index.goToIndex();
		
		assertEquals(result, "Index");
	}
	
	@Test
	public void test_goToHome_callsGetUserAndReturnsHomePage(){
		when(principal.getName()).thenReturn("username");
		when(userDao.getUser("username")).thenReturn(user);
		String result = index.goToHome(session, principal);
		
		verify(userDao).getUser("username");
		assertEquals(result, "Home");
	}
	
	@Test
	public void test_goToLogout_callsSessionInvalidateAndReturnsIndexMapping(){
		String result = index.goToLogout(session);
		
		verify(session).invalidate();
		assertEquals(result, "redirect:/");
	}
	

}
