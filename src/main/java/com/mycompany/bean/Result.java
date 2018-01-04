package com.mycompany.bean;

import java.io.Serializable;

public class Result implements Serializable
{
	private String id = null;
	private String status = null;
	private String nickname = null;
	private String customerLocation = null;
	private String longitude = null;
	private String latitude = null;
	private String duration = null;
	private String distance = null;
	private String deliveryLocation = null;

	public Result() {
	}

	public String getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public String getNickname() {
		return nickname;
	}

	public String getCustomerLocation() {
		return customerLocation;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getDuration() {
		return duration;
	}

	public String getDistance() {
		return distance;
	}

	public String getDeliveryLocation() {
		return deliveryLocation;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setCustomerLocation(String customerLocation) {
		this.customerLocation = customerLocation;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public void setDeliveryLocation(String deliveryLocation) {
		this.deliveryLocation = deliveryLocation;
	}

}
