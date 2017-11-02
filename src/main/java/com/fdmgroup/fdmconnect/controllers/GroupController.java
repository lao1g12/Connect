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

import com.fdmgroup.fdmconnect.daos.FlagDAOImpl;
import com.fdmgroup.fdmconnect.daos.GroupDAOImpl;
import com.fdmgroup.fdmconnect.daos.NotificationDAOImpl;
import com.fdmgroup.fdmconnect.daos.PostDAOImpl;
import com.fdmgroup.fdmconnect.daos.UserDAOImpl;
import com.fdmgroup.fdmconnect.entities.Flag;
import com.fdmgroup.fdmconnect.entities.Group;
import com.fdmgroup.fdmconnect.entities.Post;
import com.fdmgroup.fdmconnect.entities.Notification;
import com.fdmgroup.fdmconnect.entities.User;

@Controller
public class GroupController {

	@Autowired
	private PostDAOImpl postDao;
	@Autowired
	private GroupDAOImpl groupDao;
	@Autowired
	private UserDAOImpl userDao;
	@Autowired
	private FlagDAOImpl flagDao;
	@Autowired
	private NotificationDAOImpl notificationDao;

	public GroupController() {
	}

	public GroupController(PostDAOImpl postDao, UserDAOImpl userDao, FlagDAOImpl flagDao, GroupDAOImpl groupDao,
			NotificationDAOImpl notificationDao) {
		super();
		this.userDao = userDao;
		this.postDao = postDao;
		this.groupDao = groupDao;
		this.flagDao = flagDao;
		this.notificationDao = notificationDao;
	}

	@RequestMapping(value = { "user/goToGroupHome", "admin/goToGroupHome" })

	public String admin(Model model, @RequestParam String name) {

		Group group = groupDao.getGroup(name);
		model.addAttribute("allPosts", postDao.getAllPostsByGroup(name));
		model.addAttribute("group", group);
		return "user/GroupHome";

	}

	@RequestMapping("user/goToMyGroups")
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
	public String doCreateGroup(Model model, Group group, HttpSession session, RedirectAttributes ra) {

		User owner = (User) session.getAttribute("user");

		group.setOwner(owner);
		owner.getGroups().add(group);
		group.getUsers().add(owner);

		if (groupDao.getGroup(group.getName()) == null) {

			groupDao.createGroup(group);
			ra.addFlashAttribute("groupWasCreated", "Group was created successfully");
			Logging.Log("info", "Group Controller: " + session.getAttribute("username") + "created a group" + group);
			return "redirect:/user/goToMyGroups";

		} else {

			ra.addFlashAttribute("groupAlreadyExists", "Group already exists with that name.");
			return "redirect:/user/goToMyGroups";

		}

	}

	@RequestMapping("user/goToLeaveGroup")
	
	public String goToLeaveGroup(Model model, HttpSession session, @RequestParam("name") String groupname, RedirectAttributes ra){
		
		User user = (User) session.getAttribute("user");
		Group group = groupDao.getGroup(groupname);
		group.removeUser(user);
		session.setAttribute("user", user);
		groupDao.updateGroup(group);
		model.addAttribute("userLeftGroup", "User left group successfully");
		
		Logging.Log("info", "User left the group");
		return "redirect:/user/goToMyGroups";
		
	}

	@RequestMapping("/user/goToRemoveGroup")
	public String goToRemoveGroup(@RequestParam String name, HttpSession session, Model model, RedirectAttributes ra) {
		User user = (User) session.getAttribute("user");
		groupDao.removeGroup(name);
		session.setAttribute("user", user);
		Logging.Log("post", "post removed succesfully by admin" + name);
		ra.addFlashAttribute("groupRemovedByOwner", "Group removed succesfully.");
		return "redirect:/user/goToMyGroups";
		
	}
	
	@RequestMapping("user/goToSendInvite")
	public String goToSendInvite(HttpSession session, Model model, @RequestParam(name = "groupName") String name) {

		Group group = groupDao.getGroup(name);
		model.addAttribute("group", group);
		model.addAttribute("allPosts", postDao.getAllPostsByGroup(name));
		model.addAttribute("sendInvite", "send");
		return "user/GroupHome";
	}
	

	@RequestMapping("user/doSendInvite")
	public String doSendInvite(HttpSession session, Model model, @RequestParam(name = "groupName") String name,
			@RequestParam(name = "username") String username) {

		Group group = groupDao.getGroup(name);
		User sender = (User) session.getAttribute("user");
		User recipient = userDao.getUser(username);

		model.addAttribute("group", group);
		model.addAttribute("allPosts", postDao.getAllPostsByGroup(name));

		Notification notification = new Notification("Group Invite from " + sender.getUsername(), "invite", name);
		notification.setUser(recipient);
		notification.setSender(sender);
		notificationDao.addNotification(notification);

		model.addAttribute("inviteSentMessage", "A group invite has been sent to " + username);
		Logging.Log("info", "Group Controller: " + sender + " sent an invite to " + username);

		return "user/GroupHome";

	}

	@RequestMapping("user/doAcceptInvite")
	public String doAcceptInvite(HttpSession session, Model model, @RequestParam(name = "notificationId") String nId,
			@RequestParam(name = "groupName") String name, RedirectAttributes ra) {

		User user = (User) session.getAttribute("user");
		Group group = groupDao.getGroup(name);
		int notificationId = Integer.parseInt(nId);

		try {
			group.addUser(user);
			groupDao.updateGroup(group);
			notificationDao.removeNotification(notificationId);
		} catch (PersistenceException pe) {
			ra.addFlashAttribute("inviteAcceptError", "You already exist as a member in this group.");
			return "redirect:/user/goHome";
		}

		ra.addFlashAttribute("userAddedToGroupMessage", "You have been added to the group " + name);
		return "redirect:/user/goHome";

	}

	@RequestMapping("user/doDeclineInvite")
	public String doDeclineInvite(HttpSession session, Model model, @RequestParam(name = "notificationId") String nId,
			RedirectAttributes ra) {

		int notificationId = Integer.parseInt(nId);

		try {
			notificationDao.removeNotification(notificationId);
		} catch (PersistenceException pe) {
			ra.addFlashAttribute("inviteDeclineError", "Invite no longer exists.");
			return "redirect:/user/goHome";
		}

		return "redirect:/user/goHome";

	}

	@RequestMapping("user/addGroupPost")
	public String addGroupPost(Model model, @RequestParam String name, HttpServletRequest request) {

		Post post = new Post();
		Group group = groupDao.getGroup(name);
		model.addAttribute("allPosts", postDao.getAllPostsByGroup(name));
		model.addAttribute("group", group);
		model.addAttribute(post);

		request.setAttribute("addGroupPost", "hello");
		return "user/GroupHome";

	}

	@RequestMapping(value = { "user/groupPost" })
	public String addNewPost(Post post, HttpSession session, HttpServletRequest request, @RequestParam String name) {

		Group group = groupDao.getGroup(name);
		User user = (User) session.getAttribute("user");
		post.setPostOwner(user);
		BusinessLogic bl = new BusinessLogic();
		String checkString = post.getFullListOfKeyWords();
		Flag flag = flagDao.getFlag(1);
		String badWords = flag.getFlagInfo();
		List<String> checkedBadWords = bl.searchForListings(badWords, checkString);

		if (checkedBadWords.size() > 0) {
			StringBuffer sbReturn = new StringBuffer();
			for (String badWord : checkedBadWords) {
				sbReturn.append(badWord + " ");
			}
			String badWordString = sbReturn.toString();
			request.setAttribute("badPost",
					"You just tried to post an article with the following inappropriate words :" + badWordString);
			return "user/AddPost";
		}

		post.setGroup(group);
		postDao.addPost(post);
		Logging.Log("info", "User Controller: " + session.getAttribute("username") + "added post" + post);

		return "redirect:/user/login";

	}

}
