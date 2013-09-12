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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.runners.model.Statement;
import org.log4ju.LogTester;
import org.log4ju.annotation.Message;
import org.log4ju.model.LogMessage;

/**
 * <code>MessagesStatement</code> is a custom JUnit 4.5+
 * {@link Statement} which adds support for <em>Log4Junit</em> {@link Messages}, {@link Message}
 * annotations to test logs which produces annotated method.
 * 
 * @author Maxim Krestovsky
 */
public class MessagesStatement extends Statement {

	private final Statement next;
	
	private final Method testMethod;
	
	private final boolean ordered;
	
	private final Message[] messages;
	
	public MessagesStatement(Statement next, Method testMethod, boolean ordered, Message... messages) {
		this.next = next;
		this.testMethod = testMethod;
		this.ordered = ordered;
		this.messages = messages;
	}
	
	@Override
	public void evaluate() throws Throwable {
		LogTester tester = new LogTester();
		tester.initializeFor(testMethod.getDeclaringClass());
		
		next.evaluate();
		
		List<LogMessage> logMessages = new ArrayList<LogMessage>(messages.length);
		for (Message m: messages) {
			logMessages.add(LogMessage.message(m.level(), m.message(), m.params()));
		}
		
		tester.performTest(ordered, logMessages.toArray(new LogMessage[logMessages.size()]));
	}

}
