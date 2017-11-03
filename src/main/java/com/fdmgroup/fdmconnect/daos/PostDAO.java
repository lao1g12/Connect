package com.fdmgroup.fdmconnect.daos;

import java.util.List;

import com.fdmgroup.fdmconnect.entities.Post;
import com.fdmgroup.fdmconnect.entities.User;

public interface PostDAO {
	
	public void addPost(Post post);
	public List<Post> getAllPosts();
	public Post getPost(int postId);
	public void removePost(int postId);
	public List<Post> getAllPostsWhereGroupsAll();
	public List<Post> getAllPostsByGroup(String name);
	public void updatePost(Post post);
	public List<Post> getAllPostsByUserAndAll(User user);

}
