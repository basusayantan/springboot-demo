package com.blog.demo.service.utils.builders;

import java.util.Map;

import com.blog.demo.payload.ApiError;

public class ApiErrorBuilder {
	
	private int status;
	private String error;
	private Map<String, String> message;
	private String path;
	private String errortrace;
	
	public ApiErrorBuilder status(int status) {
		this.status = status;
		return this;
	}
	
	public ApiErrorBuilder error(String error) {
		this.error = error;
		return this;
	}
	
	public ApiErrorBuilder message(Map<String, String> message) {
		this.message = message;
		return this;
	}
	
	public ApiErrorBuilder path(String path) {
		this.path = path;
		return this;
	}
	
	public ApiErrorBuilder errortrace(String errortrace) {
		this.errortrace = errortrace;
		return this;
	}
	
	public ApiError build() {
		return new ApiError(status, error, message, path, errortrace);
	}

}