package org.log4ju.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.log4ju.model.LogLevel;

/**
 * Represents a message annotation
 * 
 * <pre>
 * &#64;Test
 * &#64;Message("info test message")
 * public void test_message() {
 *   //...
 * }
 * </pre>
 * 
 * @author Maxim Krestovsky
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Message {

	String message();
	
	LogLevel level() default LogLevel.INFO;
	
	String[] params() default {};
}
