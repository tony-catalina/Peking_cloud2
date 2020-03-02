package awd.mis.servers.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.mis.servers.tools.CacheUtils;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Groupapp implements Serializable {

	String jsbh;
	String groupid;
	String creator;
	List<String> appcode;

	public String getJsbh() {
		return jsbh;
	}
	
	public String getJsbhString() {
    	return CacheUtils.get().getJsbhString(this.jsbh);
    }

	public void setJsbh(String jsbh) {
		this.jsbh = jsbh;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public List<String> getAppcode() {
		return appcode;
	}

	public void setAppcode(List<String> appcode) {
		this.appcode = appcode;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

}
