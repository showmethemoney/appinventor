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
import com.mycompany.service.AutomaticService;

@RestController
public class AutomaticController
{
	protected static final Logger logger = LoggerFactory.getLogger( RestController.class );

	@Autowired
	private AutomaticService service = null;

	@RequestMapping(value = "/auto/{deliveryman}", method = RequestMethod.GET)
	public String auto(@PathVariable("deliveryman") String deliveryman) {
		Result result = new Result(); 

		try {
			service.asyncRunMapStep( deliveryman );
			
			result.setStatus( ResultType.SUCCESS.getMessage() );
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
			
			result.setStatus( ResultType.FAILED.getMessage() );
		}

		return new GsonBuilder().create().toJson( result );
	}

}
