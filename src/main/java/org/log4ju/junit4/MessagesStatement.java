package org.log4ju.junit4;

import static org.log4ju.LogsTestingHelper.initializeLogger;
import static org.log4ju.LogsTestingHelper.testLogMessagesInOrder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.runners.model.Statement;
import org.log4ju.annotation.Message;
import org.log4ju.model.LogHolder;
import org.log4ju.model.LogMessage;

public class MessagesStatement extends Statement {

	private final Statement next;
	
	private final Method testMethod;
	
	private final boolean ordered;
	
	private final Message[] messages;
	
	public MessagesStatement(Statement next, Method testMethod, boolean ordered, Message[] messages) {
		this.next = next;
		this.testMethod = testMethod;
		this.ordered = ordered;
		this.messages = messages;
	}
	
	@Override
	public void evaluate() throws Throwable {
		LogHolder holder = initializeLogger(testMethod.getDeclaringClass());
		next.evaluate();
		if (ordered) {
			List<LogMessage> logMessages = new ArrayList<LogMessage>();
			for (Message m: messages) {
				logMessages.add(LogMessage.message(m.scope(), m.message()));
			}
			testLogMessagesInOrder(holder, logMessages.toArray(new LogMessage[logMessages.size()]));
		}
	}

}
