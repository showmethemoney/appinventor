package com.mycompany.google;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.mycompany.SpringBootWebApplication;
import com.mycompany.entity.Customer;
import com.mycompany.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(SpringBootWebApplication.class)
public class TestAsyncMethod
{
	protected static final Logger logger = LoggerFactory.getLogger( TestAsyncMethod.class );
	@Autowired
	private CustomerService service = null;
	
	@Test
	public void testHelloWorld() {
		try {
			Customer instance = service.findCustomerByUsernameAndPassword("user1", "user1");
			logger.info("{}", instance.getId());
			
			
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}
	}
}
