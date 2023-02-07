package com.blog.demo.payload;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.blog.demo.service.utils.annotations.Content;
import com.blog.demo.service.utils.annotations.Title;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostDto {
	
	private long id;
	
	@Title
	private String title;
	
	@Content
	private String content;
	
	@NotEmpty
	private String username;
	
	private int likes;
	private List<CommentDto> comments;

}