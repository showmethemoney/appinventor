package com.mycompany.google;

import java.util.LinkedHashSet;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApi.RouteRestriction;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.DistanceMatrixRow;
import com.mycompany.bean.DistanceMatrixResult;
import com.mycompany.service.GoogleGeoService;

public class TestGoogleMapDirectionAPI
{
	protected static final Logger logger = LoggerFactory.getLogger( TestGoogleMapDirectionAPI.class );

	private static String API_KEY = "AIzaSyCVPVHlKbkHE9fl3d913ZAyBJIWLSEJUX4";

	@Test
	public void testDistanceMatrix() {
		try {
			GeoApiContext context = new GeoApiContext.Builder().apiKey( API_KEY ).build();
			String[] origins = { "台北市信義路五段7號" };
			String[] destinations = { "花蓮縣秀林鄉富世村富世291號" };
			DistanceMatrix distanceMatrix = DistanceMatrixApi.getDistanceMatrix( context, origins, destinations ).avoid( RouteRestriction.HIGHWAYS )
			        .language( "zh-TW" ).await();

			for (DistanceMatrixRow distanceMatrixRow : distanceMatrix.rows) {
				for (DistanceMatrixElement distanceMatrixElement : distanceMatrixRow.elements) {
					logger.info( distanceMatrixElement.distance.humanReadable );
					logger.info( distanceMatrixElement.duration.humanReadable );
				}
			}

		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}
	}

	@Ignore
	@Test
	public void testGeocoding() {
		try {
			GoogleGeoService service = new GoogleGeoService();
			// LatLng latLng = service.geocode( "花蓮縣秀林鄉富世村富世291號" );
			// logger.info( "{} {}", latLng.lat, latLng.lng ); //24.1579087 121.6224745

			String address = service.reverseGeocode( "24.1579087", "121.6224745" );
			logger.info( address );

			DistanceMatrixResult metrics = service.getDistanceMatrix( "台北市信義路五段7號", "花蓮縣秀林鄉富世村富世291號" );
			logger.info( "{} - {}", metrics.getDistance(), metrics.getDuration() );

			// GeoApiContext context = new GeoApiContext.Builder().apiKey( API_KEY ).build();

			// GeocodingResult[] results = GeocodingApi.geocode( context, "花蓮縣秀林鄉富世村富世291號" ).await();
			//
			// for (GeocodingResult result : results) {
			// LatLng latlng = result.geometry.location;
			// logger.info( "{} {}", latlng.lng, latlng.lat );
			// }
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}
	}

	@Ignore
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

			DirectionsResult directionsResult = DirectionsApi.getDirections( context, "新北市新莊區中正路510號", "花蓮縣秀林鄉富世村富世291號" )
			        .avoid( DirectionsApi.RouteRestriction.HIGHWAYS ).await();

			for (DirectionsRoute route : directionsResult.routes) {
				for (DirectionsLeg leg : route.legs) {
					logger.info( leg.distance.humanReadable );
					logger.info( leg.duration.humanReadable );
				}
			}

			// for (DirectionsRoute route : directionsResult.routes) {
			// for (DirectionsLeg leg : route.legs) {
			// for (DirectionsStep step : leg.steps) {
			// // logger.info( "instructions : {}, start : {}, end : {}, duration : {}", step.htmlInstructions, step.startLocation.toUrlValue(),
			// // step.endLocation, step.duration.inSeconds );
			// logger.info( "{}, {}, {}", step.startLocation.toUrlValue(), step.endLocation, step.duration.inSeconds );
			//
			// // locations.add( step.startLocation.toUrlValue() );
			// // locations.add( step.endLocation.toUrlValue() );
			// }
			// }
			// }

			// for(String location : locations) {
			// logger.info(location);
			// }

		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}
	}

}
