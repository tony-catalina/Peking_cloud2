package awd.mis.servers.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Userapp implements Serializable {
	
	List<String> userid;
	List<String> appcode;
	public List<String> getUserid() {
		return userid;
	}
	public void setUserid(List<String> userid) {
		this.userid = userid;
	}
	public List<String> getAppcode() {
		return appcode;
	}
	public void setAppcode(List<String> appcode) {
		this.appcode = appcode;
	}
	
	
	

}
