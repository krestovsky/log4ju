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
	
	Class<?> klazz() default Default.class;
	
	int times() default 1;
}
