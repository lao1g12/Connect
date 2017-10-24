package com.fdmgroup.fdmconnect.daos;

import java.util.List;

import com.fdmgroup.fdmconnect.entities.Post;

public interface PostDAO {
	
	public void addPost(Post post);
	public List<Post> getAllPosts();

}
