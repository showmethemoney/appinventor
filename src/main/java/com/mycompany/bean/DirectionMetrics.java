package com.mycompany.bean;

public class DirectionMetrics
{
	private String distance = null;
	private String duration = null;
	
	public DirectionMetrics() {}
	
	public DirectionMetrics(String distance, String duration) {
		this.distance = distance;
		this.duration = duration;
	}
	
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	
}
