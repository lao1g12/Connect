package com.fdmgroup.fdmconnect.daos;

import java.util.List;

import com.fdmgroup.fdmconnect.entities.Comment;

public interface CommentDAO {
	
	public void addComment(Comment comment);
	public void removeComment(int commentId);
	public void updateComment(Comment comment);
	public Comment getComment(int commentId);
	public List<Comment> getAllCommentsByPost(int postId);

}
