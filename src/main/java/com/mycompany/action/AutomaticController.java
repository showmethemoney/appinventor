package com.mycompany.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.service.AutomaticService;

@Controller
public class AutomaticController
{
	protected static final Logger logger = LoggerFactory.getLogger( RestController.class );

	@Autowired
	private AutomaticService service = null;

	@RequestMapping(value = "/auto/{deliveryman}", method = RequestMethod.GET)
	@ResponseBody
	public String auto(@PathVariable("deliveryman") String deliveryman) {
		String result = null;

		try {
			service.asyncRunMapStep( deliveryman );
			
			result = "OK";
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}

		return result;
	}

}
