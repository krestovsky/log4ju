package org.log4ju;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestWrapper {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private TestClass tk = new TestClass();
	
	public void doMessages() {
		logger.info("first object message");
		tk.infoMessage("second object message");
	}
}
