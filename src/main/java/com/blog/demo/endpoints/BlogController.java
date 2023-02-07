package com.blog.demo.endpoints;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.demo.constants.Constants;
import com.blog.demo.service.implementations.BlogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value="api/blog")
@RequiredArgsConstructor
public class BlogController {
	
	private final BlogService service;
	
	private ResponseEntity<Object> buildResponse(Object body, HttpStatus status) {
		return new ResponseEntity<>(body, status);
	}

	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ResponseEntity<Object> getPosts(
			@RequestParam(name="user", required=true) String username,
			@RequestParam(value="pageNo", defaultValue=Constants.PAGE_NO, required=false) int pageNo,
			@RequestParam(value="pageSize", defaultValue=Constants.PAGE_SIZE, required=false) int pageSize) {
		return buildResponse(service.getPostsByUser(username, pageNo, pageSize), HttpStatus.OK);
	}
	
}