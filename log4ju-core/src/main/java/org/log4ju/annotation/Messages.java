/*
 * Copyright 2012-2013 the original author.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
	
	Class<?> klazz() default Default.class;
	
}
