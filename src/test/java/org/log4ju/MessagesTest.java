package org.log4ju;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.log4ju.annotation.Message;
import org.log4ju.annotation.Messages;
import org.log4ju.junit4.Log4JuJUnit4ClassRunner;
import org.log4ju.model.LogLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Log4JuJUnit4ClassRunner.class)
public class MessagesTest {
	
	private class TestClass {
		public static final String MESSAGE = "message";
		
		private final Logger logger = LoggerFactory.getLogger(getClass());
		
		public void doMessages() {
			logger.error(MESSAGE);
			logger.warn(MESSAGE);
			logger.info(MESSAGE);
			logger.debug(MESSAGE);
			logger.trace(MESSAGE);
		}
		
		public void debugMessage(String message, Object... params) {
			logger.debug(message, params);
		}
	}
	
	@Test
	@Messages(
		messages = {
			@Message( message = TestClass.MESSAGE, level = LogLevel.ERROR),
			@Message( message = TestClass.MESSAGE, level = LogLevel.WARN),
			@Message( message = TestClass.MESSAGE),
			@Message( message = TestClass.MESSAGE, level = LogLevel.DEBUG),
			@Message( message = TestClass.MESSAGE, level = LogLevel.TRACE)
		}
	)
	public void test_messages_in_order() {
		new TestClass().doMessages();
	}
	
	@Test
	@Messages(
		ordered = false,
		messages = {
			@Message( message = TestClass.MESSAGE, level = LogLevel.WARN),
			@Message( message = TestClass.MESSAGE),
			@Message( message = TestClass.MESSAGE, level = LogLevel.DEBUG),
			@Message( message = TestClass.MESSAGE, level = LogLevel.ERROR),
			@Message( message = TestClass.MESSAGE, level = LogLevel.TRACE)
		}
	)
	public void test_messages_without_order() {
		new TestClass().doMessages();
	}
	
	@Test
	@Message( message = "Debug {} with {}", level = LogLevel.DEBUG, params = {"message", "params"} )
	public void test_message_params() {
		new TestClass().debugMessage("Debug {} with {}", "message", "params");
	}

}
