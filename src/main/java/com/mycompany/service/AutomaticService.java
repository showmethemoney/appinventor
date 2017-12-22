package com.mycompany.service;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.mycompany.entity.DeliveryMan;

@Service
@EnableAsync
public class AutomaticService
{
	protected static final Logger logger = LoggerFactory.getLogger( AutomaticService.class );
	private static final Long MULTIPLYING = 10l;

	@Autowired
	private DeliveryManService service = null;

	class MapStep implements Serializable
	{
		private String latitude = null; // 緯度
		private String longitude = null; // 經度
		private String duration = null;

		public MapStep() {
		}

		public MapStep(String latitude, String longitude, String duration) {
			this.latitude = latitude;
			this.longitude = longitude;
			this.duration = duration;
		}

		public String getLatitude() {
			return latitude;
		}

		public String getLongitude() {
			return longitude;
		}

		public String getDuration() {
			return duration;
		}

		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}

		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}

		public void setDuration(String duration) {
			this.duration = duration;
		}

	}

	@Async("automaticUpdateMapLocationExecutor")
	public void asyncRunMapStep(String deliveryman) {
		try {
			List<MapStep> steps = getMapStepData( getClass().getClassLoader().getResourceAsStream( deliveryman + ".txt" ) );
			Iterator<MapStep> iterator = steps.iterator();

			while (iterator.hasNext()) {
				MapStep step = iterator.next();

				DeliveryMan entity = service.findDeliveryManByID( service.findDeliveryManByUsernameAndPassword( deliveryman, deliveryman ).getId() );
				entity.setLongitude( step.getLongitude() );
				entity.setLatitude( step.getLatitude() );

				service.update( entity );

				logger.info( "deliveryman : {} will go to {} {} in next {} ms", deliveryman, step.getLongitude(), step.getLatitude(), Long.valueOf( step.getDuration() ) * MULTIPLYING );

				try {
					Thread.sleep( Long.valueOf( step.getDuration() ) * MULTIPLYING );
				} catch (Throwable cause) {
				}
			}
			
			logger.info( "deliveryman : {} end of travel", deliveryman );
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}
	}

	protected List<MapStep> getMapStepData(InputStream inputStream) throws Throwable {
		List<MapStep> result = new LinkedList<MapStep>();

		for (String step : IOUtils.readLines( inputStream, "UTF-8" )) {
			String[] data = step.split( "," );
			result.add( new MapStep( data[0], data[1], data[2] ) );
		}

		return result;
	}
}
