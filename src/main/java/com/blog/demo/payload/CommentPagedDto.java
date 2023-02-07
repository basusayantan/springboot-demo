package com.blog.demo.payload;

import java.util.List;

import com.blog.demo.service.utils.builders.CommentPagedDtoBuilder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentPagedDto {
	
	private PostDto post;
	private List<CommentDto> comments;
	private int pageNo;
	private int totalPages;
	private long totalComments;
	
	public static CommentPagedDtoBuilder builder() {
		return new CommentPagedDtoBuilder();
	}

}