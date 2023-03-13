package com.ecommerce.dto.user;

import com.ecommerce.admin.UserAdmin;
import com.ecommerce.entity.User;

public class SignInReposeDto {

	private String status;
	private String token;
	private User user;
	private UserAdmin admin;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public UserAdmin getAdmin() {
		return admin;
	}
	public void setAdmin(UserAdmin admin) {
		this.admin = admin;
	}
	public SignInReposeDto(String status, String token, User user) {

		this.status = status;
		this.token = token;
		this.user = user;
	}
	public SignInReposeDto() {
	
	}
		
}
