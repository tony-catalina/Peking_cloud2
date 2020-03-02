package awd.mis.servers.model;

import java.util.List;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.mis.servers.entity.AppEntity;

public class JsInfo extends SimpleGenericEntity<String>{
	
	private String jsbh;
	private List<AppEntity> apps;
	
	public String getJsbh() {
		return jsbh;
	}
	public void setJsbh(String jsbh) {
		this.jsbh = jsbh;
	}
	public List<AppEntity> getApps() {
		return apps;
	}
	public void setApps(List<AppEntity> apps) {
		this.apps = apps;
	}
	
	
	

}
