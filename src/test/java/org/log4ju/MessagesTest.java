package org.log4ju;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.log4ju.annotation.Message;
import org.log4ju.annotation.Messages;
import org.log4ju.junit4.Log4JuJUnit4ClassRunner;
import org.log4ju.model.LogScope;
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
	}
	
	@Test
	@Messages(
		//ordered = true,
		messages = {
			@Message( message = TestClass.MESSAGE, scope = LogScope.ERROR),
			@Message( message = TestClass.MESSAGE, scope = LogScope.WARN),
			@Message( message = TestClass.MESSAGE, scope = LogScope.INFO),
			@Message( message = TestClass.MESSAGE, scope = LogScope.DEBUG),
			@Message( message = TestClass.MESSAGE, scope = LogScope.TRACE)
		}
	)
	public void test_messages_in_order() {
		new TestClass().doMessages();
	}

}
