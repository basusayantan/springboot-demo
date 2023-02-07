package com.blog.demo.service.utils.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.blog.demo.constants.Constants;
import com.blog.demo.service.utils.ContentValidator;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value=ElementType.FIELD)
@Constraint(validatedBy=ContentValidator.class)
public @interface Content {
	
	String message() default Constants.CONTENT_ERR_MSG;
	int max() default Constants.MAX_CONTENT_SIZE;
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}