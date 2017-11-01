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
import com.fdmgroup.fdmconnect.daos.GroupDAOImpl;
import com.fdmgroup.fdmconnect.daos.PostDAOImpl;
import com.fdmgroup.fdmconnect.daos.UserDAOImpl;
import com.fdmgroup.fdmconnect.entities.Flag;
import com.fdmgroup.fdmconnect.entities.Group;
import com.fdmgroup.fdmconnect.entities.Post;
import com.fdmgroup.fdmconnect.entities.User;

public class GroupDAOImplTest {

	private UserDAOImpl userDao;
	private PostDAOImpl postDao;
	private FlagDAOImpl flagDao;
	private GroupDAOImpl groupDao;
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
	private Set<Group> groups;
	private Group group;
	private GroupController groupController;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		userDao = mock(UserDAOImpl.class);
		postDao = mock(PostDAOImpl.class);
		flagDao = mock(FlagDAOImpl.class);
		groupDao = mock(GroupDAOImpl.class);
		groupController = new GroupController(postDao, userDao, flagDao, groupDao);
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
		groups = mock(HashSet.class);
		group = mock(Group.class);
	}

	@Test
	public void test_admin_returnsUserGroupHome() {
		
		String name = "";
		
		when(groupDao.getGroup(name)).thenReturn(group);
		String result = groupController.admin(model, name);
		
		assertEquals(result, "user/GroupHome");
		
	}
	
	@Test
	public void test_goToViewAllGroups_returnsUserMyGroups() {
		
		when(session.getAttribute("user")).thenReturn(user);
		when(user.getGroups()).thenReturn(groups);
		String result = groupController.goToViewAllGroups(model, session);
		
		assertEquals(result, "user/MyGroups");
		
	}
	
	@Test
	public void test_doCreateGroup_returnsRedirectToUserGoToMyGroups() {
		
		when(session.getAttribute("user")).thenReturn(user);
		String result = groupController.doCreateGroup(model, group, session, ra);
		
		assertEquals(result, "redirect:/user/goToMyGroups");
		
	}

}
