package awd.mis.servers.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Usermenu implements Serializable {
	List<String> userid;
	Map<String, String> appmenu;
	public List<String> getUserid() {
		return userid;
	}
	public void setUserid(List<String> userid) {
		this.userid = userid;
	}
	public Map<String, String> getAppmenu() {
		return appmenu;
	}
	public void setAppmenu(Map<String, String> appmenu) {
		this.appmenu = appmenu;
	}
	
	

}
