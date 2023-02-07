package com.blog.demo.service.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.blog.demo.service.utils.annotations.Title;

public class TitleValidator implements ConstraintValidator<Title, String> {
	
	private int min;
	private int max;
	private String message;
	
	@Override
	public void initialize(Title constraintAnnotation) {
		this.min = constraintAnnotation.min();
		this.max = constraintAnnotation.max();
		this.message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value != null && value.length() > min && value.length() < max) {
			return true;
		}
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message)
			.addConstraintViolation();
		return false;
	}

}