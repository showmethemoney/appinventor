package com.mycompany.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.entity.Customer;
import com.mycompany.service.CustomerService;

@Controller
public class CustomerController {
	protected static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService service = null;

	@RequestMapping(value = "/customer/login/{username}/{password}", method = { RequestMethod.GET })
	@ResponseBody
	public String login(@PathVariable("username") String username, @PathVariable("password") String password) {
		String result = "ERROR";

		try {
			Customer entity = service.findCustomerByUsernameAndPassword(username, password);
			
			result = entity.getId() + "," + entity.getNickname() + "," + entity.getAddress();
		} catch (Throwable cause) {
			logger.error(cause.getMessage(), cause);
		}

		return result;
	}

	@RequestMapping(value = "/customer/location/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public String getLocation(@PathVariable("id") String id) {
		String result = "ERROR";

		try {
			Customer entity = service.findCustomerById(id);

			if (null != entity) {
				result = entity.getDeliveryMan().getLongitude() + "," +  entity.getDeliveryMan().getLatitude();
			}
		} catch (Throwable cause) {
			logger.error(cause.getMessage(), cause);
		}

		return result;
	}
}
