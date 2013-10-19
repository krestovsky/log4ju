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
package org.log4ju.model;

import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class LogKeeper {
	
	public static final String SEPARATOR = ":";

	private ByteArrayOutputStream baos;
	private String separator;
	
	protected LogKeeper() {
		this(new ByteArrayOutputStream());
	}
	
	public LogKeeper(ByteArrayOutputStream baos) {
		this.baos = baos;
		this.separator = SEPARATOR;
	}
	
	public void setSeparator(String separator) {
		this.separator = separator;
	}
	
	public void reset() {
		baos.reset();
	}
	
	public List<String[]> getMessages() {
		String msgs = baos.toString();
		List<String[]> result = new ArrayList<String[]>();
		
		String[] msgTuple = msgs.split(System.getProperty("line.separator"));
		for (int i=0; i<msgTuple.length; i++) {
			String[] split = msgTuple[i].split(separator, 2);
			if (split.length != 2) fail();
			result.add(split);
		}
		return result;
	}
}
