package com.fdmgroup.fdmconnect.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	private HttpServletRequest request;
	private Post post;
	private Set<Flag> flagList;
	private Flag flag;
	private RedirectAttributes ra;

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
		posts = mock(ArrayList.class);
		users = mock(ArrayList.class);
		request = mock(HttpServletRequest.class);
		post = mock(Post.class);
		flagList = mock(HashSet.class);
		flag = mock(Flag.class);
		ra = mock(RedirectAttributes.class);
	}

	@Test
	public void test_admin_returnsAdminHome() {

		String result = adminController.admin(model);

		assertEquals(result, "admin/Home");

	}
	
	@Test
	public void test_goToViewAllFlags_returnsAdminDisplayAllFlags(){
		int postId = 0;

		when(postDao.getPost(postId)).thenReturn(post);
		when(post.getFlags()).thenReturn(flagList);
		String result = adminController.goToViewAllFlags(model, postId, request);
		
		assertEquals(result, "admin/DisplayAllFlags");
	}
	
	@Test
	public void test_processRemovePostAdmin_returnsUserHome() {
		
		int postId = 0;
		
		String result = adminController.processRemovePostAdmin(postId, model, session);
		
		assertEquals(result, "user/Home");
		
	}
	
	@Test
	public void test_processRemovePost_returnsRedirectToAdminViewAllFlaggedPosts() {
		
		int postId = 0;
		
		String result = adminController.processRemovePost(postId, model, ra);
		
		assertEquals(result, "redirect:/admin/viewAllFlaggedPosts");
		
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
		assertEquals(result, "redirect:/admin");
		
	}
	
	@Test
	public void test_processRemoveUser_returnsRedirectToAdminViewAllUsers(){
		
		String result = adminController.processRemoveUser("username", model, ra);
		
		assertEquals(result, "redirect:/admin/viewAllUsers");
		
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
	
	@Test
	public void test_goToViewAllFlaggedPosts_returnsAdminDisplayAllFlaggedPosts(){
		
		List<Post> posts = new ArrayList<Post>();
		Set<Flag> flags = new HashSet<Flag>();
		
		flags.add(new Flag());
		posts.add(post);
		when(post.getFlags()).thenReturn(flags);
		when(postDao.getAllPosts()).thenReturn(posts);
		String result = adminController.goToViewAllFlaggedPosts(model, request);
		
		assertEquals(result, "admin/DisplayAllFlaggedPosts");
		
	}
	
	@Test
	public void test_addBadWords_returnsRedirectToAdmin() { 
		
		String badWords = "";
		
		when(flagDao.getFlag(1)).thenReturn(flag);
		String result = adminController.addBadWords(badWords);
		
		assertEquals(result, "redirect:/admin");
		
	}
}
