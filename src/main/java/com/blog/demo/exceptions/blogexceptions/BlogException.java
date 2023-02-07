package com.blog.demo.exceptions.blogexceptions;

import lombok.Getter;

@Getter
public class BlogException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private static final String className = BlogException.class.getName();
	
	private String message;
	
	public BlogException(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return String.format("%s: %s", className, message);
	}
	
}