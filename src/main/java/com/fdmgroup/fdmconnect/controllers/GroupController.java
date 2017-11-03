package com.fdmgroup.fdmconnect.controllers;

import java.util.List;
import java.util.Set;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fdmgroup.fdmconnect.daos.CommentDAO;
import com.fdmgroup.fdmconnect.daos.FlagDAO;
import com.fdmgroup.fdmconnect.daos.GroupDAO;
import com.fdmgroup.fdmconnect.daos.NotificationDAO;
import com.fdmgroup.fdmconnect.daos.PostDAO;
import com.fdmgroup.fdmconnect.daos.UserDAO;
import com.fdmgroup.fdmconnect.entities.Comment;
import com.fdmgroup.fdmconnect.entities.Flag;
import com.fdmgroup.fdmconnect.entities.Group;
import com.fdmgroup.fdmconnect.entities.Post;
import com.fdmgroup.fdmconnect.entities.Notification;
import com.fdmgroup.fdmconnect.entities.User;

@Controller
public class GroupController {

	@Autowired
	private PostDAO postDao;
	@Autowired
	private GroupDAO groupDao;
	@Autowired
	private UserDAO userDao;
	@Autowired
	private FlagDAO flagDao;
	@Autowired
	private NotificationDAO notificationDao;
	@Autowired
	private CommentDAO commentDao;

	public GroupController() {
	}

	public GroupController(PostDAO postDao, UserDAO userDao, FlagDAO flagDao,
			GroupDAO groupDao, NotificationDAO notificationDao, CommentDAO commentDao) {
		super();
		this.userDao = userDao;
		this.postDao = postDao;
		this.groupDao = groupDao;
		this.flagDao = flagDao;
		this.notificationDao = notificationDao;
		this.commentDao = commentDao;
	}

	@RequestMapping(value = { "user/goToGroupHome", "admin/goToGroupHome" })
	public String admin(Model model, HttpSession session,
			@RequestParam String name) {

		Group group = groupDao.getGroup(name);
		session.setAttribute("allPosts", postDao.getAllPostsByGroup(name));
		session.setAttribute("group", group);
		return "user/GroupHome";

	}

	@RequestMapping(value = { "/admin/goToMyGroups", "user/goToMyGroups" })
	public String goToViewAllGroups(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		Set<Group> groups = user.getGroups();
		Group group = new Group();
		model.addAttribute(group);
		model.addAttribute("groups", groups);
		Logging.Log("trace", "Group Controller:  My groups called.");
		return "user/MyGroups";

	}

	@RequestMapping("user/doCreateGroup")
	public String doCreateGroup(Model model, Group group, HttpSession session,
			RedirectAttributes ra) {

		User owner = (User) session.getAttribute("user");

		group.setOwner(owner);
		owner.getGroups().add(group);
		group.getUsers().add(owner);

		if (groupDao.getGroup(group.getName()) == null) {

			groupDao.createGroup(group);
			ra.addFlashAttribute("groupWasCreated",
					"Group was created successfully");
			Logging.Log("info",
					"Group Controller: " + session.getAttribute("username")
							+ "created a group" + group);
			return "redirect:/user/goToMyGroups";

		} else {

			ra.addFlashAttribute("groupAlreadyExists",
					"Group already exists with that name.");
			return "redirect:/user/goToMyGroups";

		}

	}

	@RequestMapping("user/goToLeaveGroup")
	public String goToLeaveGroup(Model model, HttpSession session,
			@RequestParam("name") String groupname, RedirectAttributes ra) {

		User user = (User) session.getAttribute("user");

		Group group = groupDao.getGroup(groupname);
		group.removeUser(user);
		session.setAttribute("user", user);

		groupDao.updateGroup(group);
		ra.addFlashAttribute("userLeftGroup", "User left group  successfully");

		Logging.Log("info", "User left the group "+user);
		return "redirect:/user/goToMyGroups";

	}

	@RequestMapping("/user/doSetNewOwner")
	public String doSetNewOwner(@RequestParam String name, HttpSession session,
			Model model, RedirectAttributes ra, @RequestParam String username) {
		
		User user = (User) session.getAttribute("user");
		Group group = groupDao.getGroup(name);
		User newOwner = userDao.getUser(username);
		user.getOwnedGroups().remove(group);
		newOwner.getOwnedGroups().add(group);
		group.setOwner(newOwner);
		group.getUsers().remove(newOwner);
		group.getUsers().add(newOwner);
		userDao.updateUser(newOwner);
		groupDao.updateGroup(group);
		userDao.updateUser(user);

		ra.addFlashAttribute("groupRemovedByOwner",
				"Group removed succesfully.");
		Logging.Log("info", "New owner set for group " + name);
		return "redirect:/user/goToMyGroups";

	}

	@RequestMapping("/user/goToRemoveGroup")
	public String goToRemoveGroup(@RequestParam String name,
			HttpSession session, Model model, RedirectAttributes ra) {

		User user = (User) session.getAttribute("user");

		groupDao.removeGroup(name);
		session.setAttribute("user", user);

		ra.addFlashAttribute("groupRemovedByOwner",
				"Group removed succesfully.");
		Logging.Log("info", "Group removed succesfully " + name);
		return "redirect:/user/goToMyGroups";

	}

	@RequestMapping("user/goToSendInvite")
	public String goToSendInvite(HttpSession session, Model model,
			@RequestParam(name = "groupName") String name) {

		Group group = groupDao.getGroup(name);
		model.addAttribute("group", group);
		model.addAttribute("allPosts", postDao.getAllPostsByGroup(name));
		model.addAttribute("sendInvite", "send");
		return "user/GroupHome";

	}

	@RequestMapping("user/doSendInvite")
	public String doSendInvite(HttpSession session, Model model,
			@RequestParam(name = "groupName") String name,
			@RequestParam(name = "username") String username) {

		Group group = groupDao.getGroup(name);
		User sender = (User) session.getAttribute("user");
		User recipient = userDao.getUser(username);

		model.addAttribute("group", group);
		model.addAttribute("allPosts", postDao.getAllPostsByGroup(name));

		if (recipient == null) {

			model.addAttribute("userErrorMessage", "User with username "
					+ username + " does not exist.");
			return "user/GroupHome";

		}

		Notification notification = new Notification("Group Invite from "
				+ sender.getUsername(), "invite", name);
		notification.setUser(recipient);
		notification.setSender(sender);

		notificationDao.addNotification(notification);

		model.addAttribute("inviteSentMessage",
				"A group invite has been sent to " + username);
		Logging.Log("info", "Group Controller: " + sender
				+ " sent an invite to " + username);

		return "user/GroupHome";

	}

	@RequestMapping("user/doAcceptInvite")
	public String doAcceptInvite(HttpSession session, Model model,
			@RequestParam(name = "notificationId") String nId,
			@RequestParam(name = "groupName") String name, RedirectAttributes ra) {

		User user = (User) session.getAttribute("user");
		Group group = groupDao.getGroup(name);
		int notificationId = Integer.parseInt(nId);

		try {
			group.addUser(user);
			groupDao.updateGroup(group);
			Logging.Log("info", "Group Controller: User "+user+" accepted invite to group "+name);
			notificationDao.removeNotification(notificationId);
		} catch (PersistenceException pe) {
			ra.addFlashAttribute("inviteAcceptError",
					"You already exist as a member in this group.");
			notificationDao.removeNotification(notificationId);
			return "redirect:/user/goHome";
		}

		ra.addFlashAttribute("userAddedToGroupMessage",
				"You have been added to the group " + name);
		return "redirect:/user/login";

	}

	@RequestMapping("user/doDeclineInvite")
	public String doDeclineInvite(HttpSession session, Model model,
			@RequestParam(name = "notificationId") String nId,
			RedirectAttributes ra) {

		int notificationId = Integer.parseInt(nId);

		try {
			notificationDao.removeNotification(notificationId);
			Logging.Log("info", "Group Controller: Notification removed "+nId);
		} catch (PersistenceException pe) {
			ra.addFlashAttribute("inviteDeclineError",
					"Invite no longer exists.");
			return "redirect:/user/goHome";
		}

		return "redirect:/user/login";

	}

	@RequestMapping("user/addGroupPost")
	public String addGroupPost(Model model, @RequestParam String name,
			HttpServletRequest request) {

		Post post = new Post();
		Group group = groupDao.getGroup(name);
		model.addAttribute("group", group);
		model.addAttribute("allPosts", postDao.getAllPostsByGroup(name));
		model.addAttribute(post);

		request.setAttribute("addGroupPost", "hello");
		return "user/GroupHome";

	}

	@RequestMapping(value = { "user/groupPost" })
	public String addNewPost(Post post, HttpSession session,
			HttpServletRequest request, @RequestParam String name) {

		Group group = groupDao.getGroup(name);
		User user = (User) session.getAttribute("user");
		post.setPostOwner(user);
		SearchLogic bl = new SearchLogic();
		String checkString = post.getFullListOfKeyWords();
		Flag flag = flagDao.getFlag(1);
		String badWords = flag.getFlagInfo();
		List<String> checkedBadWords = bl.searchForListings(badWords,
				checkString);

		if (checkedBadWords.size() > 0) {
			StringBuffer sbReturn = new StringBuffer();
			for (String badWord : checkedBadWords) {
				sbReturn.append(badWord + " ");
			}
			String badWordString = sbReturn.toString();
			request.setAttribute("badPost",
					"You just tried to post an article with the following inappropriate words :"
							+ badWordString);
			return "user/AddPost";
		}

		post.setGroup(group);
		postDao.addPost(post);
		
		Logging.Log("info",
				"User Controller: " + session.getAttribute("username")
						+ "added post" + post);

		return "redirect:/user/login";

	}

	@RequestMapping(value = { "/user/goToViewGroupComments",
			"/admin/goToViewGroupComments" })
	public String goToViewGroupComments(HttpSession session, Model model,
			@RequestParam(name = "postId") int postId) {

		model.addAttribute("postId", postId);
		model.addAttribute("viewComments", "show");
		List<Comment> comments = commentDao.getAllCommentsByPost(postId);
		model.addAttribute("comments", comments);
		return "user/GroupHome";

	}

	@RequestMapping(value = { "/user/goToAddGroupComment",
			"/admin/goToAddGroupComment" })
	public String goToAddGroupComment(HttpSession session, Model model,
			HttpServletRequest request, @ModelAttribute("postId") int postId) {

		if (postId == 0) {
			postId = Integer.parseInt(request.getParameter("postId"));
		}

		model.addAttribute("postId", postId);
		model.addAttribute("viewComments", "show");
		model.addAttribute("addComment", "add");
		return "user/GroupHome";

	}

	@RequestMapping(value = { "/user/doAddGroupComment",
			"/admin/doAddGroupComment" })
	public String doAddGroupComment(HttpSession session, Model model,
			@RequestParam(name = "postId") int postId,
			@RequestParam(name = "commentBody") String commentBody,
			RedirectAttributes ra, HttpServletRequest request) {

		Comment comment = new Comment(commentBody);
		SearchLogic bl = new SearchLogic();
		Flag flag = flagDao.getFlag(1);
		String badWords = flag.getFlagInfo();
		List<String> checkedBadWords = bl.searchForListings(badWords,
				commentBody);

		if (checkedBadWords.size() > 0) {
			StringBuffer sbReturn = new StringBuffer();
			for (String badWord : checkedBadWords) {
				sbReturn.append(badWord + " ");
			}
			String badWordString = sbReturn.toString();
			ra.addFlashAttribute("badComment",
					"You just tried to post an article with the following inappropriate words :"
							+ badWordString);
			ra.addFlashAttribute("postId", postId);
			return "redirect:/user/goToAddGroupComment";
		}

		comment.setUser((User) session.getAttribute("user"));
		comment.setPost(postDao.getPost(postId));

		try {
			commentDao.addComment(comment);
		} catch (PersistenceException pe) {
			model.addAttribute("addCommentErrorMessage",
					"Could not post comment at this time.");
			return "user/GroupHome";
		}

		Logging.Log("info", "User Controller: " + session.getAttribute("username")
						+ " added a comment " + comment);

		return "redirect:/user/goToGroupHome";

	}

	@RequestMapping(value = { "/user/doRemoveGroupComment",
			"/admin/doRemoveGroupComment" })
	String doRemoveGroupComment(HttpSession session, Model model,
			@RequestParam(name = "commentId") int commentId) {

		commentDao.removeComment(commentId);
		Logging.Log("info", "User Controller: " + session.getAttribute("username")
						+ " removed a comment " + commentId);

		return "redirect:/user/goToGroupHome";

	}

	@RequestMapping(value = { "/user/goToEditGroupComment",
			"/admin/goToEditGroupComment" })
	String goToEditGroupComment(HttpSession session, Model model,
			@RequestParam(name = "postId") int postId,
			@RequestParam(name = "commentId") int commentId) {

		model.addAttribute("postId", postId);
		model.addAttribute("commentId", commentId);
		model.addAttribute("viewComments", "show");
		model.addAttribute("editComment", "edit");
		List<Comment> comments = commentDao.getAllCommentsByPost(postId);
		model.addAttribute("comments", comments);

		return "user/GroupHome";

	}

	@RequestMapping(value = { "/user/doEditGroupComment",
			"/admin/doEditGroupComment" })
	String doEditGroupComment(HttpSession session, Model model,
			@RequestParam(name = "commentId") int commentId,
			@RequestParam(name = "commentBody") String commentBody) {

		Comment comment = commentDao.getComment(commentId);
		comment.setCommentBody(commentBody);

		commentDao.updateComment(comment);
		Logging.Log("info", "User Controller: " + session.getAttribute("username")
						+ " edited a comment " + commentId);

		return "redirect:/user/goToGroupHome";

	}

}
