package com.mycompany.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "DELIVERY_MAN")
public class DeliveryMan {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "ID")
	private String id = null;
	@Column(name = "NAME")
	private String name = null;
	@Column(name = "PASSWORD")
	private String password = null;
	@Column(name = "NICK_NAME")
	private String nickname = null;
	@Column(name = "LATITUDE")
	private String latitude = null; // 緯度
	@Column(name = "LONGITUDE")
	private String longitude = null; // 經度

	public DeliveryMan() {
	}

	public DeliveryMan(String name, String password, String nickname, String latitude, String longitude) {
		this.name = name;
		this.password = password;
		this.nickname = nickname;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}
