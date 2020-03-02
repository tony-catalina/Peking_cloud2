/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.model.kss;

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


public class Kss_ThysModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String thlx;
	
	private java.lang.String nr;
	
	private java.lang.String lxmc;
	//columns END

	 

	public Kss_ThysModel(){
	}
	public Kss_ThysModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getThlx() {
		return this.thlx;
	}
	
	public void setThlx(java.lang.String value) {
		this.thlx = value;
	}
	public java.lang.String getNr() {
		return this.nr;
	}
	
	public void setNr(java.lang.String value) {
		this.nr = value;
	}
	public java.lang.String getLxmc() {
		return this.lxmc;
	}
	
	public void setLxmc(java.lang.String value) {
		this.lxmc = value;
	}
	 
}

