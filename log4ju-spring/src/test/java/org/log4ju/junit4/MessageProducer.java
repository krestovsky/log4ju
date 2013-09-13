package org.log4ju.junit4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageProducer {
	private final Logger log = LoggerFactory.getLogger(getClass());

	public void produceMessages() {
		log.info("produced message with {}", "param");
	}

}
