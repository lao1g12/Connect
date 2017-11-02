package com.fdmgroup.fdmconnect.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fdmgroup.fdmconnect.controllers.SearchLogic;
import com.fdmgroup.fdmconnect.controllers.GroupController;
import com.fdmgroup.fdmconnect.daos.FlagDAOImpl;
import com.fdmgroup.fdmconnect.daos.GroupDAOImpl;
import com.fdmgroup.fdmconnect.daos.NotificationDAOImpl;
import com.fdmgroup.fdmconnect.daos.PostDAOImpl;
import com.fdmgroup.fdmconnect.daos.UserDAOImpl;
import com.fdmgroup.fdmconnect.entities.Flag;
import com.fdmgroup.fdmconnect.entities.Group;
import com.fdmgroup.fdmconnect.entities.Post;
import com.fdmgroup.fdmconnect.entities.User;

public class GroupControllerTest {

	private UserDAOImpl userDao;
	private PostDAOImpl postDao;
	private FlagDAOImpl flagDao;
	private GroupDAOImpl groupDao;
	private NotificationDAOImpl notificationDao;
	private HttpSession session;
	private Model model;
	private User user;
	private HttpServletRequest request;
	private Post post;
	private Flag flag;
	private RedirectAttributes ra;
	private Set<Group> groups;
	private Group group;
	private GroupController groupController;
	private SearchLogic bl;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		userDao = mock(UserDAOImpl.class);
		postDao = mock(PostDAOImpl.class);
		flagDao = mock(FlagDAOImpl.class);
		groupDao = mock(GroupDAOImpl.class);
		notificationDao = mock(NotificationDAOImpl.class);
		groupController = new GroupController(postDao, userDao, flagDao,
				groupDao, notificationDao);
		session = mock(HttpSession.class);
		model = mock(Model.class);
		user = mock(User.class);
		request = mock(HttpServletRequest.class);
		post = mock(Post.class);
		flag = mock(Flag.class);
		ra = mock(RedirectAttributes.class);
		groups = mock(HashSet.class);
		group = mock(Group.class);
		bl = mock(SearchLogic.class);
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
		String result = groupController
				.doCreateGroup(model, group, session, ra);

		assertEquals(result, "redirect:/user/goToMyGroups");

	}

	@Test
	public void test_goToSendInvite_returnsUserGroupHome() {

		String name = "";

		when(groupDao.getGroup(name)).thenReturn(group);
		String result = groupController.goToSendInvite(session, model, name);

		assertEquals(result, "user/GroupHome");

	}

	@Test
	public void test_doSendInvite_returnsUserGroupHome() {

		String name = "";
		String username = "";
		User sender = mock(User.class);
		User recipient = mock(User.class);

		when(groupDao.getGroup(name)).thenReturn(group);
		when(session.getAttribute("user")).thenReturn(sender);
		when(userDao.getUser(username)).thenReturn(recipient);
		when(sender.getUsername()).thenReturn(username);
		String result = groupController.doSendInvite(session, model, name,
				username);

		assertEquals(result, "user/GroupHome");

	}

	@Test
	public void test_doAcceptInvite_returnsRedirectToUserGoHome() {

		String name = "";
		String nId = "1";

		when(groupDao.getGroup(name)).thenReturn(group);
		when(session.getAttribute("user")).thenReturn(user);
		String result = groupController.doAcceptInvite(session, model, nId,
				name, ra);

		assertEquals(result, "redirect:/user/goHome");

	}

	@Test
	public void test_doDeclineInvite_returnsRedirectToUserGoHome() {

		String nId = "1";

		String result = groupController.doDeclineInvite(session, model, nId, ra);

		assertEquals(result, "redirect:/user/goHome");

	}
	
	@Test
	public void test_addGroupPost_returnsUserGroupHome() {
		
		String name = "";
		
		when(groupDao.getGroup(name)).thenReturn(group);
		String result = groupController.addGroupPost(model, name, request);
		
		assertEquals(result, "user/GroupHome");
		
	}
	
	@Test
	public void test_addNewPost_returnsUserAddPostIfCheckedBadWordsSizeMoreThanZero() {
		
		String name = ""; String checkString = "a"; String badWords = "a";
		List<String> checkedBadWords = new ArrayList<String>();
		checkedBadWords.add(checkString);
		
		when(groupDao.getGroup(name)).thenReturn(group);
		when(session.getAttribute("user")).thenReturn(user);
		when(post.getFullListOfKeyWords()).thenReturn(checkString);
		when(flagDao.getFlag(1)).thenReturn(flag);
		when(flag.getFlagInfo()).thenReturn(badWords);
		when(bl.searchForListings(badWords, checkString)).thenReturn(checkedBadWords);
		String result = groupController.addNewPost(post, session, request, name);
		
		assertEquals(result, "user/AddPost");
		
	}
	
	@Test
	public void test_addNewPost_returnsRedirectToUserLoginIfCheckedBadWordsSizeEqualZero() {
		
		String name = ""; String checkString = "b"; String badWords = "a";
		List<String> checkedBadWords = new ArrayList<String>();
		
		when(groupDao.getGroup(name)).thenReturn(group);
		when(session.getAttribute("user")).thenReturn(user);
		when(post.getFullListOfKeyWords()).thenReturn(checkString);
		when(flagDao.getFlag(1)).thenReturn(flag);
		when(flag.getFlagInfo()).thenReturn(badWords);
		when(bl.searchForListings(badWords, checkString)).thenReturn(checkedBadWords);
		String result = groupController.addNewPost(post, session, request, name);
		
		assertEquals(result, "redirect:/user/login");
		
	}

}
