package com.blog.demo.exceptions.blogexceptions;

import lombok.Getter;

@Getter
public class DuplicatePostException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private static final String className = DuplicatePostException.class.getName();
	
	private String message;
	
	public DuplicatePostException(String resourceName, String fieldName, String fieldValue) {	
		this.message = String.format("%s already exists with %s: '%s'", resourceName, fieldName, fieldValue);
	}
	
	@Override
	public String toString() {
		return String.format("%s: %s", className, message);
	}
	
}