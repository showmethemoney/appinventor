package com.mycompany.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.maps.DirectionsApi.RouteRestriction;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressType;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.mycompany.bean.DistanceMatrixResult;

@Component
public class GoogleGeoService {
	protected static final Logger logger = LoggerFactory.getLogger(GoogleGeoService.class);
	private static String API_KEY = "AIzaSyCVPVHlKbkHE9fl3d913ZAyBJIWLSEJUX4";
	private static String lANGUAGE = "zh-TW";

	public LatLng geocode(String address) {
		LatLng result = null;

		try {
			GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY).build();
			GeocodingResult[] geocodingResults = GeocodingApi.geocode(context, address).await();

			for (GeocodingResult geocodingResult : geocodingResults) {
				result = geocodingResult.geometry.location;
			}
		} catch (Throwable cause) {
			logger.error(cause.getMessage(), cause);
		}

		return result;
	}

	public String reverseGeocode(String latitude, String longitude) {
		String result = null;

		try {
			GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY).build();
			LatLng latLng = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
			GeocodingResult[] geocodingResults = GeocodingApi.reverseGeocode(context, latLng)
					.resultType(AddressType.STREET_ADDRESS).language(lANGUAGE).await();

			for (GeocodingResult geocodingResult : geocodingResults) {
				result = geocodingResult.formattedAddress;
			}
		} catch (Throwable cause) {
			logger.error(cause.getMessage(), cause);
		}

		return result;
	}

	public DistanceMatrixResult getDistanceMatrix(String origin, String destination) {
		DistanceMatrixResult result = new DistanceMatrixResult();

		try {
			GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY).build();

			DistanceMatrix distanceMatrix = DistanceMatrixApi
					.getDistanceMatrix(context, new String[] { origin }, new String[] { destination })
					.avoid(RouteRestriction.HIGHWAYS).language(lANGUAGE).await();

			for (DistanceMatrixRow distanceMatrixRow : distanceMatrix.rows) {
				for (DistanceMatrixElement distanceMatrixElement : distanceMatrixRow.elements) {
					result.setDistance(distanceMatrixElement.distance.humanReadable);
					result.setDuration(distanceMatrixElement.duration.humanReadable);
				}
			}

		} catch (Throwable cause) {
			logger.error(cause.getMessage(), cause);
		}

		return result;
	}
}
