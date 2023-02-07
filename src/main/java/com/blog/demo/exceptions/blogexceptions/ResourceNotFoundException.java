package com.blog.demo.exceptions.blogexceptions;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private static final String className = ResourceNotFoundException.class.getName();
	
	private String message;
	
	public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
		this.message = String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue);
	}
	
	@Override
	public String toString() {
		return String.format("%s : %s", className, message);
	}
	
}