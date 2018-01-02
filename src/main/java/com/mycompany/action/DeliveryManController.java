package com.mycompany.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.GsonBuilder;
import com.mycompany.bean.Result;
import com.mycompany.entity.DeliveryMan;
import com.mycompany.service.DeliveryManService;

@RestController
@RequestMapping("/deliveryman")
public class DeliveryManController
{
	protected static final Logger logger = LoggerFactory.getLogger( DeliveryManController.class );

	@Autowired
	private DeliveryManService service = null;

	@RequestMapping(value = "/login/{username}/{password}", method = { RequestMethod.GET })
	public String login(@PathVariable("username") String username, @PathVariable("password") String password) {
		Result result = new Result();

		try {
			DeliveryMan entity = service.findDeliveryManByUsernameAndPassword( username, password );

			result.setStatus( ResultType.SUCCESS.getMessage() );
			result.setId( entity.getId() );
			result.setNickname( entity.getNickname() );
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
			
			result.setStatus( ResultType.FAILED.getMessage() );
		}

		return new GsonBuilder().create().toJson( result );
	}

	@RequestMapping(value = "/update/{longitude}/{latitude}/{id}", method = { RequestMethod.GET })
	public String update(@PathVariable("id") String id, @PathVariable("longitude") String longitude, @PathVariable("latitude") String latitude) {
		Result result = new Result();

		try {
			DeliveryMan entity = service.findDeliveryManByID( id );
			entity.setLongitude( longitude );
			entity.setLatitude( latitude );

			service.update( entity );

			result.setStatus( ResultType.SUCCESS.getMessage() );
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
			
			result.setStatus( ResultType.FAILED.getMessage() );
		}

		return new GsonBuilder().create().toJson( result );
	}

}
