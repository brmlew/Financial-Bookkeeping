package com.sandbag;

/**
 * @author ZeAus
 *
 */
public class Address {

	int streetNumber;
	String streetName;
	String city;
	String country;
	String postalCode;
	
	Address() {}
	
	Address(int streetNumber, String streetName, String city, String country, String postalCode) {
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.city = city;
		this.country = country;
		this.postalCode = postalCode;
	}
	
	public int getStreetNumber() {
		return this.streetNumber;
	}
	
	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}
	
	public String getStreetName() {
		return this.streetName;
	}
	
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return this.country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getPostalCode() {
		return this.postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}
