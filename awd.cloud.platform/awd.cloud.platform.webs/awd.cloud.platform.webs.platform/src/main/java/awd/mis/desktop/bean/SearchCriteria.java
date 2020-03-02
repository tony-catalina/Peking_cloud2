package awd.mis.desktop.bean;

import awd.mis.desktop.model.Model;

public class SearchCriteria implements Model {

	String username;
	String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



}
