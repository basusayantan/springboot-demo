package com.blog.demo.payload;

import java.time.LocalDateTime;
import java.util.Map;

import com.blog.demo.service.utils.builders.ApiErrorBuilder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiError {
	
	private final LocalDateTime timestamp = LocalDateTime.now();
	private int status;
	private String error;
	private Map<String, String> message;
	private String path;
	private String errortrace;
	
	public static ApiErrorBuilder builder() {
		return new ApiErrorBuilder();
	}
	
}