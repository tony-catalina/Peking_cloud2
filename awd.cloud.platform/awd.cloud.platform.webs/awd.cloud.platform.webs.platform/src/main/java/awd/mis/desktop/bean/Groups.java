package awd.mis.desktop.bean;

import java.util.Map;

public class Groups {
	
	private java.lang.String id;
	private java.lang.String group_id;
	private java.lang.String name;
	private java.lang.String parent_id;
	private Map<String, Object> config;
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(java.lang.String group_id) {
		this.group_id = group_id;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.String getParent_id() {
		return parent_id;
	}
	public void setParent_id(java.lang.String parent_id) {
		this.parent_id = parent_id;
	}
	public Map<String, Object> getConfig() {
		return config;
	}
	public void setConfig(Map<String, Object> config) {
		this.config = config;
	}



}
