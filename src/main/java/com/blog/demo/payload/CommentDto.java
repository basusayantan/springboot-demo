package com.blog.demo.payload;

import javax.validation.constraints.NotEmpty;

import com.blog.demo.service.utils.annotations.Content;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentDto {
	
	private long id;
	
	@Content
	private String content;
	
	@NotEmpty
	private String username;

}