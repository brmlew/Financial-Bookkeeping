package com.sandbag;
/**
 * 
 */

/**
 * @author ZeAus
 *
 */
public class PersonalInfo {
	int Id;
	String firstName;
	String lastName;
	String emailAddress;
	String phoneNumber;
	Address address;
	LoginInfo loginInfo;
	
	PersonalInfo() {
		
	}
	
	PersonalInfo(int Id, String firstName, String lastName, String emailAddress, String phoneNumber, Address address, LoginInfo loginInfo) {
		this.Id = Id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.loginInfo = loginInfo;
	}
	
	public int getId() {
		return this.Id;
	}
	
	public void setId(int id) {
		this.Id = id;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastname() {
		return this.lastName; 
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return this.emailAddress;
	}
	
	public void setEmail(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getPhone() {
		return this.phoneNumber;
	}
	
	public void setPhone(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public Address getAddress() {
		return this.address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public LoginInfo getLoginInfo() {
		return this.loginInfo;
	}
	
	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}
	
}
