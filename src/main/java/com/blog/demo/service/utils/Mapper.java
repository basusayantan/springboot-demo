package com.blog.demo.service.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.blog.demo.model.Comment;
import com.blog.demo.model.Post;
import com.blog.demo.payload.CommentDto;
import com.blog.demo.payload.PostDto;

@Component
public class Mapper {

	@Value("${app.relevant_package}")
	private String pkgName;
	
	public PostDto mapToDto(Post post) {
		List<CommentDto> comments = post.getComments().stream()
				.map(this::mapToDto)
				.collect(Collectors.toList());
		return new PostDto(post.getId(), post.getTitle(), post.getContent(), post.getUsername(), post.getLikes(), comments);
	}
	
	public CommentDto mapToDto(Comment comment) {
		return new CommentDto(comment.getId(), comment.getContent(), comment.getUsername());
	}
	
	public Post mapToEntity(PostDto dto) {
		return new Post(dto.getTitle(), dto.getContent(), dto.getUsername());
	}
	
	public Comment mapToEntity(CommentDto dto) {
		return new Comment(dto.getContent(), dto.getUsername());
	}
	
	public String stackTraceToString(StackTraceElement[] errorTrace) {
		List<StackTraceElement> errors = Arrays.asList(errorTrace);
		return errors.stream()
				.map(Object::toString)
				.filter(error->error.contains(pkgName))
				.collect(Collectors.joining("; "));
	}

}