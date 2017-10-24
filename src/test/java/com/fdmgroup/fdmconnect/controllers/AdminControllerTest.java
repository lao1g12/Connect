package com.fdmgroup.fdmconnect.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.security.Principal;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import com.fdmgroup.fdmconnect.daos.PostDAOImpl;
import com.fdmgroup.fdmconnect.daos.UserDAOImpl;
import com.fdmgroup.fdmconnect.entities.User;

public class AdminControllerTest {

	private UserDAOImpl userDao;
	private PostDAOImpl postDao;
	private AdminController adminController;
	private HttpSession session;
	private Model model;
	private HttpServletRequest request;
	private Principal principal;
	private Calendar calendar;
	private User user;

	@Before
	public void setUp() {
		userDao = mock(UserDAOImpl.class);
		postDao = mock(PostDAOImpl.class);
		adminController = new AdminController(postDao, userDao);
		session = mock(HttpSession.class);
		model = mock(Model.class);
		request = mock(HttpServletRequest.class);
		principal = mock(Principal.class);
		user = mock(User.class);
		calendar = mock(Calendar.class);
	}

	@Test
	public void test_admin_returnsAdminHome() {

		String result = adminController.admin();

		assertEquals(result, "admin/Home");

	}

	@Test
	public void test_submitPost_returnsAdminAddPost() {

		when(session.getAttribute("user")).thenReturn(user);
		String result = adminController.submitPost(model, session);

		assertEquals(result, "admin/AddPost");

	}

	@Test
	public void test_goToAddUser_returnsAdminAddUser() {

		String result = adminController.goToAddUser(session, model);

		assertEquals(result, "admin/AddUser");
	}
	
	@Test
	public void test_doAddUser_returnsAdminAddUserIfPasswordsDoNotMatch(){
		
		when(user.getConfirmPassword()).thenReturn("");
		when(user.getPassword()).thenReturn("a");
		String result = adminController.doAddUser(session, model, user);
		
		assertEquals(result, "admin/AddUser");
		
	}
	
	@Test
	public void test_doAddUser_invokesUserDAOAddUserAndReturnsAdminHomeIfUserDoesNotExist(){
		
		when(user.getConfirmPassword()).thenReturn("");
		when(user.getPassword()).thenReturn("");
		String result = adminController.doAddUser(session, model, user);
		
		verify(userDao).addUser(user);
		assertEquals(result, "admin/Home");
		
	}
}
