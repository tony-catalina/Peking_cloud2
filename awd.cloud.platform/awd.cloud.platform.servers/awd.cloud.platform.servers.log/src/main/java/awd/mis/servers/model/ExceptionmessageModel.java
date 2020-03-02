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


public class ExceptionmessageModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Exceptionmessage";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_MESSAGEID = "消息ID";
	public static final String ALIAS_CONTENT = "消息内容";
	public static final String ALIAS_CSCS = "重试次数";
	public static final String ALIAS_FLAG = "处理状况(异常消息SHFO处理)";
	public static final String ALIAS_CREATETIME = "创建时间";
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String messageid;
	
	private java.lang.String content;
	
	private java.lang.Integer cscs;
	
	private java.lang.String flag;
	//创建时间
	private java.util.Date createtime;
	//columns END


	public ExceptionmessageModel(){
	}
	public ExceptionmessageModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getMessageid() {
		return this.messageid;
	}
	
	public void setMessageid(java.lang.String value) {
		this.messageid = value;
	}
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	public java.lang.Integer getCscs() {
		return this.cscs;
	}
	
	public void setCscs(java.lang.Integer value) {
		this.cscs = value;
	}
	public java.lang.String getFlag() {
		return this.flag;
	}
	
	public void setFlag(java.lang.String value) {
		this.flag = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	
}

