package org.log4ju.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a multiple messages annotation
 * 
 * <pre>
 * &#64;Test
 * &#64;Messages(
 * 	messages = {
 * 	&#64;Message("info test message")
 * 	&#64;Message(message = "debug test message", level = LogLevel.DEBUG)
 * 	}
 * )
 * public void test_messages() {
 *   //...
 * }
 * </pre>
 * 
 * @author Maxim Krestovsky
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Messages {

	boolean ordered() default true;
	
	Message[] messages();
	
}
