package com.blog.demo.service.utils.builders;

import java.util.List;

import com.blog.demo.payload.CommentDto;
import com.blog.demo.payload.CommentPagedDto;
import com.blog.demo.payload.PostDto;

public class CommentPagedDtoBuilder {
	
	private PostDto post;
	private List<CommentDto> comments;
	private int pageNo;
	private int totalPages;
	private long totalComments;
	
	public CommentPagedDtoBuilder post(PostDto post) {
		this.post = post;
		return this;
	}
	
	public CommentPagedDtoBuilder comments(List<CommentDto> comments) {
		this.comments = comments;
		return this;
	}
	
	public CommentPagedDtoBuilder pageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}
	
	public CommentPagedDtoBuilder totalPages(int totalPages) {
		this.totalPages = totalPages;
		return this;
	}
	
	public CommentPagedDtoBuilder totalComments(long totalComments) {
		this.totalComments = totalComments;
		return this;
	}
	
	public CommentPagedDto build() {
		return new CommentPagedDto(post, comments, pageNo, totalPages, totalComments);
	}
	
}