package com.blog.demo.service.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.blog.demo.service.utils.annotations.Content;

public class ContentValidator implements ConstraintValidator<Content, String> {
	
	private int max;
	private String message;
	
	@Override
	public void initialize(Content constraintAnnotation) {
		this.max = constraintAnnotation.max();
		this.message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value != null && !value.isEmpty() && value.length() < max) {
			return true;
		}
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message)
			.addConstraintViolation();
		return false;
	}

}