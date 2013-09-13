package org.log4ju.junit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.log4ju.annotation.Message;
import org.log4ju.annotation.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import config.TestConfig;

@RunWith(Spring4JunitLog4JuClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class RunnerTest {
	
	@Autowired
	private MessageProducer mp;
	
	@Test
	@Messages(
		klazz = MessageProducer.class,
		messages = {
			@Message(message = "produced message with {}", params = {"param"})
		}
	)
	public void test() {
		mp.produceMessages();
	}

}
