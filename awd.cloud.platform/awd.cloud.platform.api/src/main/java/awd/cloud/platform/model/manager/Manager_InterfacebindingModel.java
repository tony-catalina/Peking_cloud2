/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.model.manager;

import awd.cloud.platform.model.Model;
import javax.validation.constraints.NotNull;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class Manager_InterfacebindingModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String appcode;
	
	private java.lang.String interfaceId;
	
	private java.lang.String bdzt;
	//columns END

	 

	public Manager_InterfacebindingModel(){
	}
	public Manager_InterfacebindingModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getAppcode() {
		return this.appcode;
	}
	
	public void setAppcode(java.lang.String value) {
		this.appcode = value;
	}
	public java.lang.String getInterfaceId() {
		return this.interfaceId;
	}
	
	public void setInterfaceId(java.lang.String value) {
		this.interfaceId = value;
	}
	public java.lang.String getBdzt() {
		return this.bdzt;
	}
	
	public void setBdzt(java.lang.String value) {
		this.bdzt = value;
	}
	 
}

