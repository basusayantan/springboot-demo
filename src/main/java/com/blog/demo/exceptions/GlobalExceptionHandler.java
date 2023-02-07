package com.blog.demo.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.blog.demo.exceptions.blogexceptions.BlogException;
import com.blog.demo.exceptions.blogexceptions.DuplicatePostException;
import com.blog.demo.exceptions.blogexceptions.ResourceNotFoundException;
import com.blog.demo.payload.ApiError;
import com.blog.demo.service.utils.Mapper;

import lombok.RequiredArgsConstructor;

import static com.blog.demo.service.utils.ExceptionUtils.getRootCause;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	private final Mapper mapper;
	
	private ResponseEntity<Object> buildResponse(ApiError error) {
		return new ResponseEntity<>(error, HttpStatus.valueOf(error.getStatus()));
	}
	
	@ExceptionHandler(value=ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFound(Exception e, HttpServletRequest request) {
		Map<String, String> message = new HashMap<>();
		message.put(e.getClass().getSimpleName(), e.getMessage());
		ApiError error = ApiError.builder()
				.status(HttpStatus.NOT_FOUND.value())
				.error(HttpStatus.NOT_FOUND.getReasonPhrase())
				.message(message)
				.path(request.getRequestURI())
				.errortrace(mapper.stackTraceToString(e.getStackTrace()))
				.build();
		return buildResponse(error);
	}
	
	@ExceptionHandler(value=DuplicatePostException.class)
	public ResponseEntity<Object> handleDuplicatePosts(Exception e, HttpServletRequest request) {
		Map<String, String> message = new HashMap<>();
		message.put(e.getClass().getSimpleName(), e.getMessage());
		ApiError error = ApiError.builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.error(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.message(message)
				.path(request.getRequestURI())
				.errortrace(mapper.stackTraceToString(e.getStackTrace()))
				.build();
		return buildResponse(error);
	}
	
	@ExceptionHandler(value=BlogException.class)
	public ResponseEntity<Object> handleBlogExceptions(Exception e, HttpServletRequest request) {
		Map<String, String> message = new HashMap<>();
		message.put(e.getClass().getSimpleName(), e.getMessage());
		ApiError error = ApiError.builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.error(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.message(message)
				.path(request.getRequestURI())
				.errortrace(mapper.stackTraceToString(e.getStackTrace()))
				.build();
		return buildResponse(error);
	}
	
	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<Object> handleGenericExceptions(Exception e, HttpServletRequest request) {	
		Map<String, String> message = new HashMap<>();
		message.put("GenericException", getRootCause(e).getMessage());
		ApiError error = ApiError.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
				.message(message)
				.path(request.getRequestURI())
				.errortrace(mapper.stackTraceToString(e.getStackTrace()))
				.build();
		return buildResponse(error);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> messages = new HashMap<>();
		e.getBindingResult().getAllErrors().stream()
			.forEach((message)->{
				String fieldName = ((FieldError)message).getField();
				String fieldValue = message.getDefaultMessage();
				messages.put(fieldName, fieldValue);	
			});
		ApiError error = ApiError.builder()
				.status(status.value())
				.error(status.getReasonPhrase())
				.message(messages)
				.path(((ServletWebRequest)request).getRequest().getRequestURI())
				.build();
		return buildResponse(error);
	}

}