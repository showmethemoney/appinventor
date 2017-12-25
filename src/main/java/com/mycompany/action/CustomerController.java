package com.mycompany.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.SpringBootWebApplication;
import com.mycompany.entity.Customer;
import com.mycompany.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController
{
	protected static final Logger logger = LoggerFactory.getLogger( CustomerController.class );

	@Autowired
	private CustomerService service = null;

	@RequestMapping(value = "/login/{username}/{password}", method = { RequestMethod.GET })
	public String login(@PathVariable("username") String username, @PathVariable("password") String password) {
		String result = ResultType.FAILED.getMessage();

		try {
			Customer entity = service.findCustomerByUsernameAndPassword( username, password );

			result = entity.getId() + SpringBootWebApplication.SEPERATOR + entity.getNickname() + SpringBootWebApplication.SEPERATOR + entity.getAddress();
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}

		return result;
	}

	@RequestMapping(value = "/location/{id}", method = { RequestMethod.GET })
	public String getLocation(@PathVariable("id") String id) {
		String result = ResultType.FAILED.getMessage();

		try {
			Customer entity = service.findCustomerById( id );

			if (null != entity) {
				result = entity.getDeliveryMan().getLongitude() + SpringBootWebApplication.SEPERATOR + entity.getDeliveryMan().getLatitude();
			}
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}

		return result;
	}
}
