package config;

import org.log4ju.junit4.MessageProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
	
	@Bean
	public Object messageProducer() {
		return new MessageProducer();
	}

}
