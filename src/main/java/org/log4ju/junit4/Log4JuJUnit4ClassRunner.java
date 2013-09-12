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

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.log4ju.annotation.Message;
import org.log4ju.annotation.Messages;

/**
 * <p>
 * <code>Log4JuJUnit4ClassRunner</code> is a extension of
 * {@link BlockJUnit4ClassRunner}
 * </p>
 * <p>
 * The following list constitutes all annotations currently supported directly
 * by <code>Log4JuJUnit4ClassRunner</code>.
 * </p>
 * <ul>
 * <li>{@link Messages &#064;Messages(messages=...)}</li>
 * <li>{@link Message &#064;Message}</li>
 * </ul>
 * <p>
 * <b>NOTE:</b> <code>Log4JuJUnit4ClassRunner</code> requires JUnit 4.5+.
 * </p>
 * 
 * @author Maxim Krestovsky
 */
public class Log4JuJUnit4ClassRunner extends BlockJUnit4ClassRunner {
	
	public Log4JuJUnit4ClassRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	protected Statement methodBlock(FrameworkMethod frameworkMethod) {
		Statement statement =  super.methodBlock(frameworkMethod);
		statement = withMessages(frameworkMethod, statement);
		return statement;
	}
	
	protected Statement withMessages(FrameworkMethod frameworkMethod, Statement next) {
		Messages messagesAnnotation = frameworkMethod.getAnnotation(Messages.class);
		if (messagesAnnotation != null) {
			boolean ordered = messagesAnnotation.ordered();
			Message[] messages = messagesAnnotation.messages();
			return new MessagesStatement(next, frameworkMethod.getMethod(), ordered, messages);
		}
		
		Message messageAnnotation = frameworkMethod.getAnnotation(Message.class);
		if (messageAnnotation != null)  {
			return new MessagesStatement(next, frameworkMethod.getMethod(), true, messageAnnotation);
		}
		
		return next;
	}
	
}
