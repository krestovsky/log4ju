package org.log4ju;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestClass {
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
	
	public void infoMessage(String message) {
		logger.info(message);
	}
}
