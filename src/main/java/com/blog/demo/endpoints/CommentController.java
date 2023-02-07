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

import com.blog.demo.constants.Constants;
import com.blog.demo.payload.CommentDto;
import com.blog.demo.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value="api/blog/posts/{postid}/comments")
@RequiredArgsConstructor
public class CommentController {
	
	private final CommentService service;
	
	private ResponseEntity<Object> buildResponse(Object body, HttpStatus status) {
		return new ResponseEntity<>(body, status);
	}
	
	@RequestMapping(value="/comment", method=RequestMethod.POST)
	public ResponseEntity<Object> create(@PathVariable(name="postid") Long postid, @Valid @RequestBody CommentDto dto) {
		return buildResponse(service.createComment(postid, dto), HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getAll(
			@PathVariable(name="postid") long postid,
			@RequestParam(name="pageNo", defaultValue=Constants.PAGE_NO, required=false) int pageNo,
			@RequestParam(name="pageSize", defaultValue=Constants.PAGE_SIZE, required=false) int pageSize) {
		return buildResponse(service.getAllComments(postid, pageNo, pageSize), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/update", method=RequestMethod.PUT)
	public ResponseEntity<Object> update(@PathVariable(name="postid") long postid, @PathVariable(name="id") long id, @RequestBody String content) {
		return buildResponse(service.updateComment(postid, id, content), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/delete", method=RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable(name="postid") long postid, @PathVariable(name="id") long id) {
		service.deleteComment(postid, id);
		return buildResponse("Comment deleted", HttpStatus.OK);
	}
	
}