package com.mycompany.bean;

import java.util.List;

public class ToBeShippedResult
{
	private int count = 0;
	private List<ToBeShippedCustomer> customers = null;

	public ToBeShippedResult() {
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<ToBeShippedCustomer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<ToBeShippedCustomer> customers) {
		this.customers = customers;
	}

}
