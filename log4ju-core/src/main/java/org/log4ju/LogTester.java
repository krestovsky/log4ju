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
package org.log4ju;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

import org.log4ju.model.LogKeeper;
import org.log4ju.model.LogMessage;
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
 * &#64;Test
 * public void test_some_object_in_common_cases() {
 *     LogTester = new LogTester().initializeFor(so.getClass());
 *     SomeObject so = new SomeObject();
 *     so.doSomeOperation();
 *     tester.perfromTest(true, LogMessage.debugMessage("some debug message"));
 * }
 * </pre>
 * 
 * <p>
 * 2. in setUp method <br/>
 * 
 * <pre>
 * 
 * private LogTester tester;
 * 
 * &#64;Before
 * public void setUp() {
 *     tester = new LogTester().initializeFor(SomeObject.class);
 * }
 * 
 * &#64;Test
 * public void test_first_case() {
 *     SomeObject so = new SomeObject();
 *     so.doSome();
 *     tester.performTest(true, LogMessage.infoMessage("some info message"));
 * }
 * 
 * &#64;Test
 * public void test_second_case() {
 *     SomeObject so = new SomeObject();
 *     so.doAnother();
 *     tester.performTest(true, LogMessage.errorMessage("some error message"));
 * }
 * </pre>
 * 
 * @author Maxim Krestovsky
 *
 */
public class LogTester {

	private LogKeeper keeper;
	private boolean initialized;
	
	public LogTester initializeFor(Class<?> klazzFor) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		Logger logger = (Logger)LoggerFactory.getLogger(klazzFor);
		LoggerContext ctx = logger.getLoggerContext();
		
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
		
		keeper = new LogKeeper(out);
		initialized = true;
		
		return this;
	}
	
	public void performTest(boolean ordered, LogMessage ... logMsgs) {
		if (!initialized) fail("Tester is not initialized");
		assertNotNull("Do you really want to test null with log messages?", logMsgs);
		
		List<String[]> messages = keeper.getMessages();
		assertEquals("Not equal expected and actual messages", logMsgs.length, messages.size());
		
		performTest(ordered, messages, logMsgs);
	}
	
	private void performTest(boolean ordered, List<String[]> messages, LogMessage ... logMsgs) {
		LogMessage[] logged = LogMessage.toMessages(messages);
		
		if (!ordered) {
			Arrays.sort(logged);
			Arrays.sort(logMsgs);
		}
		
		assertArrayEquals(logMsgs, logged);
	}
}
