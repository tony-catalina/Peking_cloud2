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


public class Manager_DataqualityruleModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jslx;
	
	private java.lang.String tablename;
	
	private java.lang.String datarule;
	
	private java.lang.String timelinessrule;
	
	private java.lang.String dictionarycheckrules;
	//columns END

	 

	public Manager_DataqualityruleModel(){
	}
	public Manager_DataqualityruleModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getJslx() {
		return this.jslx;
	}
	
	public void setJslx(java.lang.String value) {
		this.jslx = value;
	}
	public java.lang.String getTablename() {
		return this.tablename;
	}
	
	public void setTablename(java.lang.String value) {
		this.tablename = value;
	}
	public java.lang.String getDatarule() {
		return this.datarule;
	}
	
	public void setDatarule(java.lang.String value) {
		this.datarule = value;
	}
	public java.lang.String getTimelinessrule() {
		return this.timelinessrule;
	}
	
	public void setTimelinessrule(java.lang.String value) {
		this.timelinessrule = value;
	}
	public java.lang.String getDictionarycheckrules() {
		return this.dictionarycheckrules;
	}
	
	public void setDictionarycheckrules(java.lang.String value) {
		this.dictionarycheckrules = value;
	}
	 
}

