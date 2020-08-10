package com.sandbag;

import java.math.BigDecimal;
import java.sql.Date;

public class Account {
	int id;
	String name;
	String balance;
	String asOfDate;
	
	public Account(int id, String name, String balance, String asOfDate) {
		super();
		this.id = id;
		this.name = name;
		this.balance = balance;
		this.asOfDate = asOfDate;
	}
	
	
	public Account() {	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBalance() {
		return balance;
	}
	
	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	public String getAsOfDate() {
		return asOfDate;
	}
	
	public void setAsOfDate(String asOfDate) {
		this.asOfDate = asOfDate;
	}
}
