/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.model;

import awd.framework.common.model.Model;
import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class InterfacebindingModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Interfacebinding";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_APPCODE = "app标识符";
	public static final String ALIAS_INTERFACE_ID = "接口ID";
	public static final String ALIAS_BDZT = "绑定状态(BDZT)";
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String appcode;
	
	private java.lang.String interfaceId;
	
	private java.lang.String bdzt;
	//columns END


	public InterfacebindingModel(){
	}
	public InterfacebindingModel(String id) {
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

