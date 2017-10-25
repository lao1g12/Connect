package com.fdmgroup.fdmconnect.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import com.fdmgroup.fdmconnect.daos.FlagDAOImpl;
import com.fdmgroup.fdmconnect.daos.PostDAOImpl;
import com.fdmgroup.fdmconnect.daos.UserDAOImpl;
import com.fdmgroup.fdmconnect.entities.Flag;
import com.fdmgroup.fdmconnect.entities.Post;
import com.fdmgroup.fdmconnect.entities.User;

public class AdminControllerTest {

	private UserDAOImpl userDao;
	private PostDAOImpl postDao;
	private FlagDAOImpl flagDao;
	private AdminController adminController;
	private HttpSession session;
	private Model model;
	private User user;
	private List<User> users;
	private List<Post> posts;
	private List<Flag> flags;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		userDao = mock(UserDAOImpl.class);
		postDao = mock(PostDAOImpl.class);
		flagDao = mock(FlagDAOImpl.class);
		adminController = new AdminController(postDao, userDao, flagDao);
		session = mock(HttpSession.class);
		model = mock(Model.class);
		user = mock(User.class);
		flags = mock(ArrayList.class);
		posts = mock(ArrayList.class);
		users = mock(ArrayList.class);
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
	public void test_goToViewAllFlags_returnsAdminDisplayAllFlags(){
		
		when(flagDao.getAllFlags()).thenReturn(flags);
		String result = adminController.goToViewAllFlags(model);
		
		assertEquals(result, "admin/DisplayAllFlags");
	}
	
	@Test
	public void test_addNewPost_returnsMappingToUserLogin(){
		
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
	
	@Test
	public void test_processRemoveUser_returnsAdminRemoveUser(){
		
		String result = adminController.processRemoveUser("username", model);
		
		assertEquals(result, "admin/DisplayAllUsers");
		
	}
	
	@Test
	public void test_gotToViewAllUsers_returnsAdminDisplayAllUsers(){
		
		when(userDao.getAllUsers()).thenReturn(users);
		String result = adminController.goToViewAllUsers(model);
		
		assertEquals(result, "admin/DisplayAllUsers");
	}
	
	@Test
	public void test_goToViewAllPosts_returnsAdminDisplayAllPosts(){
		
		when(postDao.getAllPosts()).thenReturn(posts);
		String result = adminController.goToViewAllPosts(model);
		
		assertEquals(result, "admin/DisplayAllPosts");
	}
}
