package com.mycompany.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CUSTOMER")
public class Customer
{

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "ID")
	private String id = null;
	@Column(name = "NAME")
	private String name = null;
	@Column(name = "NICK_NAME")
	private String nickname = null;
	@Column(name = "PASSWORD")
	private String password = null;
	@Column(name = "ADDRESS")
	private String address = null;
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "DELIVERY_MAN_ID")
	private DeliveryMan deliveryMan = null;

	public Customer() {
	}

	public Customer(String name, String password, String nickname, String address, DeliveryMan deliveryMan) {
		this.name = name;
		this.password = password;
		this.nickname = nickname;
		this.address = address;
		this.deliveryMan = deliveryMan;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public DeliveryMan getDeliveryMan() {
		return deliveryMan;
	}

	public void setDeliveryMan(DeliveryMan deliveryMan) {
		this.deliveryMan = deliveryMan;
	}

}
