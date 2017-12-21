package com.mycompany.google;

import java.util.LinkedHashSet;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;

public class TestGoogleMapDirectionAPI
{
	protected static final Logger logger = LoggerFactory.getLogger( TestGoogleMapDirectionAPI.class );

	private static String API_KEY = "AIzaSyCVPVHlKbkHE9fl3d913ZAyBJIWLSEJUX4";

	@Test
	public void testTestGoogleMap() {
		try {
			// String url = "https://maps.googleapis.com/maps/api/directions/xml?origin=台北市信義路五段7號&destination=新北市新莊區中正路510號&avoid=highways&key=" + API_KEY;
			//
			// CloseableHttpClient httpclient = HttpClients.createDefault();
			//
			// HttpGet httpget = new HttpGet( url );
			//
			// HttpEntity entity = httpclient.execute( httpget ).getEntity();
			//
			// logger.info( IOUtils.toString( entity.getContent(), "UTF-8" ) );
			
			LinkedHashSet<String> locations = new LinkedHashSet<String>();
			
			GeoApiContext context = new GeoApiContext.Builder().apiKey( API_KEY ).build();

			DirectionsResult directionsResult = DirectionsApi.getDirections( context, "台北市信義路五段7號", "新北市新莊區中正路510號" )
					.avoid( DirectionsApi.RouteRestriction.HIGHWAYS )
			        .await();

			for (DirectionsRoute route : directionsResult.routes) {
				for (DirectionsLeg leg : route.legs) {
					for (DirectionsStep step : leg.steps) {
						logger.info( "instructions : {}, start : {}, end : {}, duration : {}", step.htmlInstructions, step.startLocation.toUrlValue(), step.endLocation, step.duration.inSeconds );
						
						locations.add( step.startLocation.toUrlValue() );
						locations.add( step.endLocation.toUrlValue() );
					}
				}
			}
			
			for(String location : locations) {
				logger.info(location);
			}
			
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}
	}
}
