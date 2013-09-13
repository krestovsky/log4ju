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
package org.log4ju.junit4;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class Spring4JunitLog4JuClassRunner extends SpringJUnit4ClassRunner {
	
	private Log4JuJUnit4ClassRunner juRunner;

	public Spring4JunitLog4JuClassRunner(Class<?> klazz) throws InitializationError {
		super(klazz);
		juRunner = new Log4JuJUnit4ClassRunner(klazz);
	}
	
	@Override
	protected Statement methodBlock(FrameworkMethod frameworkMethod) {
		Statement statement =  super.methodBlock(frameworkMethod);
		statement = juRunner.withMessages(frameworkMethod, statement);
		return statement;
	}
	
}