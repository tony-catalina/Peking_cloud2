/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.appstore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class MsgRulesModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Msgffgz";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_MSGTYPE = "消息类型";
	public static final String ALIAS_METHODNAME = "方法名";
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String msgtype;
	
	private java.lang.String methodname;

	public MsgRulesModel(){
	}
	public MsgRulesModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	public java.lang.String getMsgtype() {
		return this.msgtype;
	}
	
	public void setMsgtype(java.lang.String value) {
		this.msgtype = value;
	}
	public java.lang.String getMethodname() {
		return this.methodname;
	}
	
	public void setMethodname(java.lang.String value) {
		this.methodname = value;
	}

}

