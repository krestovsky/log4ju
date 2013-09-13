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
package org.log4ju.junit4;

import org.log4ju.model.LogLevel;

public class Message {

	private String message;
	
	private LogLevel level;
	
	private String[] params;
	
	private Class<?> klazz;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LogLevel getLevel() {
		return level;
	}

	public void setLevel(LogLevel level) {
		this.level = level;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	public Class<?> getKlazz() {
		return klazz;
	}

	public void setKlazz(Class<?> klazz) {
		this.klazz = klazz;
	}
	
	public static Message[] fromAnnotations(org.log4ju.annotation.Message[] messages) {
		Message[] result = new Message[messages.length];
		for (int i=0; i<messages.length; i++) {
			result[i] = fromAnnotation(messages[i]);
		}
		return result;
	}
	
	public static Message fromAnnotation(org.log4ju.annotation.Message message) {
		Message result = new Message();
		result.setKlazz(message.klazz());
		result.setLevel(message.level());
		result.setMessage(message.message());
		result.setParams(message.params());
		return result;
	}
	
}
