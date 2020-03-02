/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.model.logs;

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


public class Logs_ExceptionmessageModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String messageid;
	
	private java.lang.String content;
	
	private java.lang.Integer cscs;
	
	private java.lang.String flag;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
	//columns END

	 

	public Logs_ExceptionmessageModel(){
	}
	public Logs_ExceptionmessageModel(String id) {
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

