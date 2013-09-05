package org.log4ju;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.log4ju.model.LogHolder;
import org.log4ju.model.LogMessage;
import org.log4ju.model.LogScope;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.OutputStreamAppender;

/**
 * Helper for testing log messages:
 * <p>
 * usage:
 * <p>
 * 1. in test method <br/>
 * <pre>
 *
 * public void test_some_object_in_common_cases() {
 *     SomeObject so = new SomeObject();
 *     LogHolder holder = initializeLogger(so.getClass());
 *     so.doSomeOperation();
 *     testLogMessagesInOrder(holder, debugMessage("some debug message"));
 * }
 * </pre>
 * 
 * <p>
 * 2. in setUp method <br/>
 * 
 * <pre>
 * 
 * private LogHolder holer;
 * 
 * public void setUp() {
 *     holder = initializeLogger(SomeObject.class);
 * }
 * 
 * public void test_first_case() {
 *     SomeObject so = new SomeObject();
 *     so.doSome();
 *     testLogMessagesInOrder(holder, infoMessage("some info message"));
 * }
 * 
 * public void test_second_case() {
 *     SomeObject so = new SomeObject();
 *     so.doAnother();
 *     testLogMessagesInOrder(holder, errorMessage("some error message"));
 * }
 * </pre>
 * 
 * @author krestovskiy.mg
 *
 */
public class LogsTestingHelper {

	public static LogHolder initializeLogger(Class<?> klazzFor) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		LogHolder holder = new LogHolder(out);
		
		Logger logger = (Logger)LoggerFactory.getLogger(klazzFor);
		LoggerContext ctx = logger.getLoggerContext();
		ctx.reset();
		
		PatternLayoutEncoder encoder = new PatternLayoutEncoder();
		encoder.setContext(ctx);
		encoder.setPattern("%p:%msg%n");
		encoder.start();
		
		OutputStreamAppender<ILoggingEvent> appender = new OutputStreamAppender<ILoggingEvent>();
		appender.setContext(ctx);
		appender.setEncoder(encoder);
		appender.setOutputStream(out);
		appender.start();
		
		logger.setLevel(Level.TRACE);
		logger.addAppender(appender);
        
		return holder;
	}
	
	public static void testLogMessagesInOrder(LogHolder holder, LogMessage ... logMsgs) {
		assertNotNull("Log messages is null", holder);
		assertNotNull("Do you really want to test null with log messages?", logMsgs);
		
		List<String[]> messages = holder.getMessages();
		assertEquals("Not equal expected and actual messages", logMsgs.length, messages.size());
		
		for (int i=0; i<logMsgs.length; i++) {
			LogMessage tested = logMsgs[i];
			String[] mess = messages.get(i);
			
			assertSame(tested.getScope(), LogScope.valueOf(mess[0]));
			assertEquals(tested.getMessage(), mess[1]);
		}
	}
}
