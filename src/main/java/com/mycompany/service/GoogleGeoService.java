package com.mycompany.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressType;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.mycompany.bean.DirectionMetrics;

public class GoogleGeoService
{
	protected static final Logger logger = LoggerFactory.getLogger( GoogleGeoService.class );
	private static String API_KEY = "AIzaSyCVPVHlKbkHE9fl3d913ZAyBJIWLSEJUX4";
	private static String lANGUAGE = "zh-TW";

	public LatLng geocode(String address) {
		LatLng result = null;

		try {
			GeoApiContext context = new GeoApiContext.Builder().apiKey( API_KEY ).build();
			GeocodingResult[] geocodingResults = GeocodingApi.geocode( context, address ).await();

			for (GeocodingResult geocodingResult : geocodingResults) {
				result = geocodingResult.geometry.location;
			}
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}

		return result;
	}

	public String reverseGeocode(String latitude, String longitude) {
		String result = null;

		try {
			GeoApiContext context = new GeoApiContext.Builder().apiKey( API_KEY ).build();
			LatLng latLng = new LatLng( Double.parseDouble( latitude ), Double.parseDouble( longitude ) );
			GeocodingResult[] geocodingResults = GeocodingApi.reverseGeocode( context, latLng ).resultType( AddressType.STREET_ADDRESS ).language( lANGUAGE ).await();

			for (GeocodingResult geocodingResult : geocodingResults) {
				result = geocodingResult.formattedAddress;
			}
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}

		return result;
	}

	public DirectionMetrics getLocatoinDirectionMetrics(String startAddress, String endAddress) {
		DirectionMetrics result = new DirectionMetrics();

		try {
			GeoApiContext context = new GeoApiContext.Builder().apiKey( API_KEY ).build();
			DirectionsResult directionsResult = DirectionsApi.getDirections( context, startAddress, endAddress ).language( lANGUAGE ).avoid( DirectionsApi.RouteRestriction.HIGHWAYS ).await();

			for (DirectionsRoute route : directionsResult.routes) {
				for (DirectionsLeg leg : route.legs) {
					result.setDistance( leg.distance.humanReadable );
					result.setDuration( leg.duration.humanReadable );
				}
			}
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}

		return result;
	}
}
