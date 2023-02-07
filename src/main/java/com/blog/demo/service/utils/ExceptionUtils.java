package com.blog.demo.service.utils;

import java.util.Objects;

public class ExceptionUtils {
	
	public static Throwable getRootCause(Throwable throwable) {
		Throwable root = Objects.requireNonNull(throwable);
		while(root.getCause() != null && root.getCause() !=  root) {
			root = root.getCause();
		}
		return new Throwable(root.toString());
	}

}