package com.mycompany.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.entity.DeliveryMan;
import com.mycompany.service.DeliveryManService;

@Controller
public class DeliveryManController {
	protected static final Logger logger = LoggerFactory.getLogger(DeliveryManController.class);

	@Autowired
	private DeliveryManService service = null;
	
	@RequestMapping(value = "/deliveryman/login/{username}/{password}", method = { RequestMethod.GET })
	@ResponseBody
	public String login(@PathVariable("username") String username, @PathVariable("password") String password) {
		String result = "ERROR";
		
		try {
			DeliveryMan entity = service.findDeliveryManByUsernameAndPassword(username, password);
			
			result = entity.getId() + "," + entity.getNickname();
		} catch (Throwable cause) {
			logger.error(cause.getMessage(), cause);
		}
		
		return result;
 	}
	
	@RequestMapping(value = "/deliveryman/update/{longitude}/{latitude}/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public String update(@PathVariable("id") String id, @PathVariable("longitude") String longitude, @PathVariable("latitude") String latitude) {
		String result = "ERROR";
		
		try {
			DeliveryMan entity = service.findDeliveryManByID(id);
			entity.setLongitude(longitude);
			entity.setLatitude(latitude);
			
			service.update(entity);
			
			result = "OK";
		} catch (Throwable cause) {
			logger.error(cause.getMessage(), cause);
		}
		
		return result;
 	}
	

}
