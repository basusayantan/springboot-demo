package com.blog.demo.service;

import com.blog.demo.payload.CommentDto;
import com.blog.demo.payload.CommentPagedDto;

public interface CommentService {
	
	CommentDto createComment(long postid, CommentDto dto);
	CommentPagedDto getAllComments(long postid, int pageNo, int pageSize);
	CommentDto updateComment(long postid, long id, String content);
	void deleteComment(long postid, long id);

}