package com.mycompany.google;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.GsonBuilder;
import com.mycompany.SpringBootWebApplication;
import com.mycompany.action.ResultType;
import com.mycompany.bean.AssignStatusResult;
import com.mycompany.bean.ToBeShippedCustomer;
import com.mycompany.bean.ToBeShippedResult;
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

	@Ignore
	@Test
	public void testHelloWorld() {
		try {
			// Customer instance = service.findCustomerByUsernameAndPassword("user1", "user1");
			List<Customer> result = (List<Customer>) service.findToBeShippedCustomer();

			logger.info( "{}", result.size() );
			for (Customer customer : result) {
				logger.info( "{}", customer.getNickname() );
			}

			Customer customer = service.findCustomerByUsernameAndPassword( "user1", "user1" );
			logger.info( "{}", service.isShippedById( customer.getId() ) );

		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}
	}

	@Ignore
	@Test
	public void testCheckAssign() {
		AssignStatusResult result = new AssignStatusResult();
		try {
			Customer customer = service.findCustomerByUsernameAndPassword( "user1", "user1" );

			result.setStatus( String.valueOf( service.isShippedById( customer.getId() ) ) );
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );

			result.setStatus( ResultType.FAILED.getMessage() );
		}

		logger.info( new GsonBuilder().create().toJson( result ) );
	}

	@Ignore
	@Test
	public void testGetToBeShipped() {
		ToBeShippedResult result = new ToBeShippedResult();

		try {
			List<Customer> toBeShippedCustomers = (List<Customer>) service.findToBeShippedCustomer();

			if (0 != toBeShippedCustomers.size()) {
				List<ToBeShippedCustomer> toBeShipped = new ArrayList<ToBeShippedCustomer>();

				for (Customer toBeShippedCustomer : toBeShippedCustomers) {
					toBeShipped.add( new ToBeShippedCustomer( toBeShippedCustomer.getId(), toBeShippedCustomer.getNickname() ) );
				}

				result.setCount( toBeShippedCustomers.size() );
				result.setCustomers( toBeShipped );
			}
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}

		logger.info( new GsonBuilder().create().toJson( result ) );
	}
}
