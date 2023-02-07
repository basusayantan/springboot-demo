package com.blog.demo.endpoints;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.demo.payload.PostDto;
import com.blog.demo.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value="/api/blog/posts")
@RequiredArgsConstructor
public class PostController {
	
	private final PostService service;
	
	private ResponseEntity<Object> buildResponse(Object body, HttpStatus status) {
		return new ResponseEntity<>(body, status);
	}
	
	@RequestMapping(value="/post", method=RequestMethod.POST)
	public ResponseEntity<Object> create(@Valid @RequestBody PostDto dto) {
		return buildResponse(service.createPost(dto), HttpStatus.CREATED);
	}

	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ResponseEntity<Object> get(@RequestParam(name="title") String title) {
		return buildResponse(service.getPostByTitle(title), HttpStatus.OK);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public ResponseEntity<Object> update(@RequestParam(name="title") String title, @RequestBody String content) {
		return buildResponse(service.updatePost(content, title), HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@RequestParam(name="title") String title) {
		service.deletePost(title);
		return buildResponse(String.format("Post %s deleted", title), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/like", method=RequestMethod.PUT)
	public ResponseEntity<Object> like(@PathVariable(name="id") long id) {
		return buildResponse(service.likePost(id), HttpStatus.OK);
	}
	
}