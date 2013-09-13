package org.log4ju;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.log4ju.annotation.Message;
import org.log4ju.annotation.Messages;
import org.log4ju.junit4.Log4JuJUnit4ClassRunner;
import org.log4ju.model.LogLevel;

@RunWith(Log4JuJUnit4ClassRunner.class)
public class MessagesTest {
	
	@Test
	@Messages(
		messages = {
			@Message( message = TestClass.MESSAGE, level = LogLevel.ERROR, klazz = TestClass.class),
			@Message( message = TestClass.MESSAGE, level = LogLevel.WARN, klazz = TestClass.class),
			@Message( message = TestClass.MESSAGE, klazz = TestClass.class),
			@Message( message = TestClass.MESSAGE, level = LogLevel.DEBUG, klazz = TestClass.class),
			@Message( message = TestClass.MESSAGE, level = LogLevel.TRACE, klazz = TestClass.class)
		}
	)
	public void test_messages_in_order() {
		new TestClass().doMessages();
	}
	
	@Test
	@Messages(
		ordered = false,
		klazz = TestClass.class,
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
	@Message(message = "Debug {} with {}", level = LogLevel.DEBUG, params = {"message", "params"}, klazz = TestClass.class)
	public void test_message_params() {
		new TestClass().debugMessage("Debug {} with {}", "message", "params");
	}
	
	@Test
	@Messages(
		klazz = TestClass.class,
		messages = {
			@Message(message = "first object message", klazz = TestWrapper.class),
			@Message(message = "second object message")
		}
	)
	public void test_nested_logged_objects() {
		new TestWrapper().doMessages();
	}

}
