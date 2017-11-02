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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fdmgroup.fdmconnect.daos.CommentDAOImpl;
import com.fdmgroup.fdmconnect.daos.EducationDAOImpl;
import com.fdmgroup.fdmconnect.daos.ExperienceDAOImpl;
import com.fdmgroup.fdmconnect.daos.FlagDAOImpl;
import com.fdmgroup.fdmconnect.daos.GroupDAOImpl;
import com.fdmgroup.fdmconnect.daos.PostDAOImpl;
import com.fdmgroup.fdmconnect.daos.ProfileDAOImpl;
import com.fdmgroup.fdmconnect.daos.UserDAOImpl;
import com.fdmgroup.fdmconnect.entities.Comment;
import com.fdmgroup.fdmconnect.entities.Education;
import com.fdmgroup.fdmconnect.entities.Experience;
import com.fdmgroup.fdmconnect.entities.Flag;
import com.fdmgroup.fdmconnect.entities.Group;
import com.fdmgroup.fdmconnect.entities.Post;
import com.fdmgroup.fdmconnect.entities.Profile;
import com.fdmgroup.fdmconnect.entities.User;

public class UserControllerTest {

	private UserDAOImpl userDao;
	private PostDAOImpl postDao;
	private FlagDAOImpl flagDao;
	private GroupDAOImpl groupDao;
	private UserController userController;
	private HttpSession session;
	private Principal principal;
	private RedirectAttributes ra;
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
	private Post post;
	private SearchLogic bl;
	private CommentDAOImpl commentDao;
	private Comment comment;
	private Group group;
	private Set<Post> posts;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		userDao = mock(UserDAOImpl.class);
		postDao = mock(PostDAOImpl.class);
		profileDao = mock(ProfileDAOImpl.class);
		educationDao = mock(EducationDAOImpl.class);
		experienceDao = mock(ExperienceDAOImpl.class);
		groupDao = mock(GroupDAOImpl.class);
		flagDao = mock(FlagDAOImpl.class);
		commentDao = mock(CommentDAOImpl.class);
		session = mock(HttpSession.class);
		model = mock(Model.class);
		user = mock(User.class);
		flag = mock(Flag.class);
		users = mock(ArrayList.class);
		posts = mock(HashSet.class);
		principal = mock(Principal.class);
		ra = mock(RedirectAttributes.class);
		profile = mock(Profile.class);
		userController = new UserController(userDao, profileDao, flagDao, postDao,
				educationDao, experienceDao, commentDao, groupDao);
		flaggedPost = mock(Post.class);
		education = mock(Education.class);
		request = mock(HttpServletRequest.class);
		experience = mock(Experience.class);
		post = mock(Post.class);
		comment = mock(Comment.class);
		group = mock(Group.class);
		bl = mock(SearchLogic.class);
	}

	@Test
	public void test_createProfile_returnsMappingToUserViewAccount() {

		when(principal.getName()).thenReturn("username");
		when(userDao.getUser("username")).thenReturn(user);
		when(user.getProfile()).thenReturn(profile);
		String result = userController.createProfile(model, session, principal,
				request);

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

		String startDate = "";

		when(principal.getName()).thenReturn("username");
		when(userDao.getUser("username")).thenReturn(user);
		when(user.getProfile()).thenReturn(profile);
		String result = userController.doAddEducation(education, session,
				principal, model, request, startDate);

		assertEquals(result, "user/EditAccount");
	}

	@Test
	public void test_updateProfile_returnsMappingToUserViewAccount() {

		String result = userController.updateProfile(principal, profile,
				session, request);

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
	public void test_doPasswordChange_returnsUserEditAccountIfNewPasswordEqualsConfPassword() {

		String password = "";
		String oldPassword = "";
		String newPassword = "";
		String confNewPassword = "";

		when(user.getPassword()).thenReturn(password);
		when(request.getParameter("password")).thenReturn(oldPassword);
		when(request.getParameter("newPassword")).thenReturn(newPassword);
		when(request.getParameter("confNewPassword")).thenReturn(
				confNewPassword);
		when(session.getAttribute("user")).thenReturn(user);
		when(user.getProfile()).thenReturn(profile);
		String result = userController
				.doPasswordChange(request, session, model);

		assertEquals(result, "user/EditAccount");

	}

	@Test
	public void test_doPasswordChange_returnsUserEditAccountIfOldPasswordDoesNotEqualUserGetPassword() {

		String password = "";
		String oldPassword = "a";
		String newPassword = "";
		String confNewPassword = "";

		when(user.getPassword()).thenReturn(password);
		when(request.getParameter("password")).thenReturn(oldPassword);
		when(request.getParameter("newPassword")).thenReturn(newPassword);
		when(request.getParameter("confNewPassword")).thenReturn(
				confNewPassword);
		when(session.getAttribute("user")).thenReturn(user);
		when(user.getProfile()).thenReturn(profile);
		String result = userController
				.doPasswordChange(request, session, model);

		assertEquals(result, "user/EditAccount");

	}

	@Test
	public void test_doPasswordChange_returnsUserEditAccountIfNewPasswordDoesNotMatchConfPassword() {

		String password = "";
		String oldPassword = "";
		String newPassword = "";
		String confNewPassword = "a";

		when(user.getPassword()).thenReturn(password);
		when(request.getParameter("password")).thenReturn(oldPassword);
		when(request.getParameter("newPassword")).thenReturn(newPassword);
		when(request.getParameter("confNewPassword")).thenReturn(
				confNewPassword);
		when(session.getAttribute("user")).thenReturn(user);
		when(user.getProfile()).thenReturn(profile);
		String result = userController
				.doPasswordChange(request, session, model);

		assertEquals(result, "user/EditAccount");

	}

	@Test
	public void test_addExperience_returnsUserAddExperience() {

		String result = userController.addExperience(session, model);

		assertEquals(result, "user/AddExperience");

	}

	@Test
	public void test_doAddExperience_returnsUserEditAccount() {

		when(principal.getName()).thenReturn("username");
		when(userDao.getUser("username")).thenReturn(user);
		when(user.getProfile()).thenReturn(profile);
		String result = userController.doAddExperience(experience, session,
				principal, model, request);

		assertEquals(result, "user/EditAccount");

	}

	@Test
	public void test_goToViewProfile_returnsUserViewProfile() {

		int profileId = 0;

		when(profileDao.getProfile(profileId)).thenReturn(profile);
		when(user.getPosts()).thenReturn(posts);
		String result = userController.goToViewProfile(session, model, profileId, request);

		assertEquals(result, "user/ViewAccount");

	}

	@Test
	public void test_goToUserSearch_returnsUserViewAllUsers() {

		String result = userController.goToUserSearch(session, model);

		assertEquals(result, "user/ViewAllUsers");
	}

	@Test
	public void test_doUserSearch_invokesAddProfileAndReturnsUserSearchResultsIfProfileInSet() {

		String name = "a";
		Set<Profile> profiles = new HashSet<Profile>();
		List<Profile> profileDaoProfiles = new ArrayList<Profile>();
		Profile profile = new Profile();
		profile.setFirstName("a");
		profile.setLastName("b");

		profiles.add(profile);
		profileDaoProfiles.add(profile);
		when(profileDao.getAllProfiles()).thenReturn(profileDaoProfiles);
		String result = userController.doUserSearch(session, model, name);

		verify(model).addAttribute("profiles", profiles);
		assertEquals(result, "user/SearchResults");

	}

	@Test
	public void test_doUserSearch_invokesModelAddAttributeAndReturnsUserSearchResultsIfProfileIsNotInSet() {

		String name = "";

		String result = userController.doUserSearch(session, model, name);

		verify(model).addAttribute("nullSearchMessage", "No results found!");
		assertEquals(result, "user/SearchResults");

	}

	@Test
	public void test_submitPost_returnsUserAddPost() {

		when(session.getAttribute("user")).thenReturn(user);
		String result = userController.submitPost(model, session);

		assertEquals(result, "user/AddPost");

	}

	@Test
	public void test_addNewPost_returnsUserAddPostIfCheckedBadWordsSizeMoreThanZero() {
		
		String checkString = "a"; String badWords = "a"; String name = "";
		List<String> checkedBadWords = new ArrayList<String>();
		checkedBadWords.add(checkString);
		
		when(session.getAttribute("user")).thenReturn(user);
		when(post.getFullListOfKeyWords()).thenReturn(checkString);
		when(flagDao.getFlag(1)).thenReturn(flag);
		when(flag.getFlagInfo()).thenReturn(badWords);
		when(bl.searchForListings(badWords, checkString)).thenReturn(checkedBadWords);
		String result = userController.addNewPost(post, session, request, name);
		
		assertEquals(result, "user/AddPost");
		
	}
	
	@Test
	public void test_addNewPost_returnsRedirectToUserLoginIfCheckedBadWordsSizeEqualZero() {
		
		String checkString = "b"; String badWords = "a"; String name = "";
		List<String> checkedBadWords = new ArrayList<String>();
		
		when(session.getAttribute("user")).thenReturn(user);
		when(post.getFullListOfKeyWords()).thenReturn(checkString);
		when(flagDao.getFlag(1)).thenReturn(flag);
		when(flag.getFlagInfo()).thenReturn(badWords);
		when(bl.searchForListings(badWords, checkString)).thenReturn(checkedBadWords);
		String result = userController.addNewPost(post, session, request, name);
		
		assertEquals(result, "redirect:/user/login");
		
	}

	@Test
	public void test_processRemovePostUser_returnsUserViewAccount() {

		int postId = 0;

		String result = userController.processRemovePostUser(postId, model, ra);

		assertEquals(result, "redirect:/user/account");

	}

	@Test
	public void test_removeEducation_returnsRedirectToUserAccount() {

		int educationId = 0;

		String result = userController.removeEducation(educationId, ra);

		assertEquals(result, "redirect:/user/account");

	}

	@Test
	public void test_removeExperience_returnsRedirectToUserAccount() {

		int experienceId = 0;

		String result = userController.removeExperience(experienceId, ra);

		assertEquals(result, "redirect:/user/account");

	}

	@Test
	public void test_goToEditPost_returnsRedirectToUserAccount() {

		int postId = 0;

		String result = userController.goToEditPost(ra, principal, postId);

		assertEquals(result, "redirect:/user/account");

	}

	@Test
	public void test_doEditPost_returnsRedirectToUserAccount() {

		String title = ""; String category = ""; String bodyText = "";
		String imgUrl = ""; String link = ""; int postId = 0;
		
		when(postDao.getPost(postId)).thenReturn(post);
		String result = userController.doEditPost(session, model, postId,
				ra, title, category, bodyText, imgUrl, link);
		
		assertEquals(result, "redirect:/user/account");
		
	}
	
	@Test
	public void test_goToViewComments_returnsUserHome() {
		
		int postId = 0;
		
		String result = userController.goToViewComments(session, model, postId, ra);
		
		assertEquals(result, "redirect:/user/login");
		
	}
	
	@Test
	public void test_goToAddComment_returnsUserHome() {
		
		int postId = 0;
		
		when(request.getParameter("postId")).thenReturn("0");
		String result = userController.goToAddComment(session, model, request, postId);
		
		assertEquals(result, "user/Home");
		
	}
	
	@Test
	public void test_doAddComment_returnsRedirectToUserGoToAddCommentIfCheckedBadWordsSizeMoreThanOne() {
		
		String badWords = "a";
		String commentBody = "a";
		int postId = 0;
		List<String> checkedBadWords = new ArrayList<String>();
		checkedBadWords.add("a");
		
		when(flagDao.getFlag(1)).thenReturn(flag);
		when(flag.getFlagInfo()).thenReturn(badWords);
		when(bl.searchForListings(badWords, commentBody)).thenReturn(checkedBadWords);
		String result = userController.doAddComment(session, model, postId, commentBody, ra, request);
		
		assertEquals(result, "redirect:/user/goToAddComment");
		
	}
	
	@Test
	public void test_doAddComment_returnsRedirectToUserGoHomeIfCheckedBadWordsSizeEqualsZero() {
		
		String badWords = "a";
		String commentBody = "b";
		int postId = 0;
		List<String> checkedBadWords = new ArrayList<String>();
		
		when(flagDao.getFlag(1)).thenReturn(flag);
		when(flag.getFlagInfo()).thenReturn(badWords);
		when(bl.searchForListings(badWords, commentBody)).thenReturn(checkedBadWords);
		String result = userController.doAddComment(session, model, postId, commentBody, ra, request);
		
		assertEquals(result, "redirect:/user/goHome");
		
	}

	@Test
	public void test_doRemoveComment_returnsRedirectToUserGoHome() {
		
		int commentId = 0;
		
		String result = userController.doRemoveComment(session, model, commentId);
		
		assertEquals(result, "redirect:/user/goHome");
		
	}
	
	@Test
	public void test_goToEditComment_returnsUserHome() {
		
		int postId = 0; int commentId = 0;
		
		String result = userController.goToEditComment(session, model, postId, commentId);
		
		assertEquals(result, "user/Home");
		
	}
	
	@Test
	public void test_doEditComment_returnsRedirectToUserGoHome() {
		
		int commentId = 0;
		String commentBody = "";
		
		when(commentDao.getComment(commentId)).thenReturn(comment);
		String result = userController.doEditComment(session, model, commentId, commentBody);
		
		assertEquals(result, "redirect:/user/goHome");
		
	}
	
}