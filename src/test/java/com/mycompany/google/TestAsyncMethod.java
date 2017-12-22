package com.mycompany.google;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.mycompany.SpringBootWebApplication;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(SpringBootWebApplication.class)
public class TestAsyncMethod
{
	protected static final Logger logger = LoggerFactory.getLogger( TestAsyncMethod.class );

	@Test
	public void testHelloWorld() {
		try {
			
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}
	}
}
