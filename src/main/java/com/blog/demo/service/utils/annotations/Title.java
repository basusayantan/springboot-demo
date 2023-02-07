package com.blog.demo.service.utils.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.blog.demo.constants.Constants;
import com.blog.demo.service.utils.TitleValidator;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value=ElementType.FIELD)
@Constraint(validatedBy=TitleValidator.class)
public @interface Title {
	
	String message() default Constants.TITLE_ERR_MSG;
	int min() default Constants.MIN_TITLE_SIZE;
	int max() default Constants.MAX_TITLE_SIZE;
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}