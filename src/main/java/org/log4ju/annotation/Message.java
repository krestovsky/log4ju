package org.log4ju.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.log4ju.model.LogScope;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Message {

	String message();
	
	LogScope scope() default LogScope.INFO;
}
