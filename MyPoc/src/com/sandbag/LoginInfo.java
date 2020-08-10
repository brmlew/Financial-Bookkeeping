package com.sandbag;
import java.util.Date;


/**
 * 
 */

/**
 * @author ZeAus
 *
 */
public class LoginInfo {
	String userName;
	String password;
	Date lastLogin;
	
	LoginInfo(){}
	LoginInfo(String userName, String password, Date lastLogin) {
		this.userName = userName;
		this.password = password;
		this.lastLogin = lastLogin;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getLastLogin() {
		return this.lastLogin;
	}
	
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
}
