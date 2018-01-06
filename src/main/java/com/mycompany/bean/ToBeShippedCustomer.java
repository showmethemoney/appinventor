package com.mycompany.bean;

public class ToBeShippedCustomer
{
	private String id = null;
	private String nickname = null;

	public ToBeShippedCustomer() {
	}

	public ToBeShippedCustomer(String id, String nickname) {
		this.id = id;
		this.nickname = nickname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
