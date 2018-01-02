package com.mycompany.bean;

import java.io.Serializable;

public class Result implements Serializable
{
	private String id = null;
	private String status = null;
	private String nickname = null;
	private String address = null;
	private String longitude = null;
	private String latitude = null;

	public Result() {
	}
	
	public Result(String status, String id, String nickname, String address, String longitude, String latitude) {
		this.status = status;
		this.id = id;
		this.nickname = nickname;
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

}
