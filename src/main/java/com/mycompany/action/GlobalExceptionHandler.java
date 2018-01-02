package com.mycompany.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.google.gson.GsonBuilder;
import com.mycompany.bean.Result;

@RestControllerAdvice
public class GlobalExceptionHandler
{
	protected static final Logger logger = LoggerFactory.getLogger( GlobalExceptionHandler.class );

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String noHandlerFoundException(NoHandlerFoundException cause) {
		Result result = new Result();
		result.setStatus( ResultType.FAILED.getMessage() );
		
		return new GsonBuilder().create().toJson( result );
	}
}
