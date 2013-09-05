package org.log4ju.junit4;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.log4ju.annotation.Message;
import org.log4ju.annotation.Messages;

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
		//boolean ordered = (messagesAnnotation != null ? messagesAnnotation.ordered(): true);
		Message[] messages = (messagesAnnotation != null? messagesAnnotation.messages(): new Message[] {});
		return new MessagesStatement(next, frameworkMethod.getMethod(), true, messages);
	}
	
}
