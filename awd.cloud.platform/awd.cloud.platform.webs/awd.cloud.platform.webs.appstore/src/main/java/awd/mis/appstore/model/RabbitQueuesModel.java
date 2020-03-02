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
public class RabbitQueuesModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "RabbitQueues";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_APPID = "APPID";
	public static final String ALIAS_APPNAME = "APP名称";
	public static final String ALIAS_VHOST = "虚拟主机";
	public static final String ALIAS_QUEUENAME = "队列名";
	public static final String ALIAS_TYPE = "类型";
	public static final String ALIAS_ISTEMP = "是否临时对列";
	public static final String ALIAS_AUTODELETE = "是否自动删除";
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String appid;
	
	private java.lang.String appname;
	
	private java.lang.String vhost;
	
	private java.lang.String queuename;
	
	private java.lang.String type;
	
	private java.lang.String istemp;
	
	private java.lang.String autodelete;
	//columns END
	//开始生成字典的String属性
	//字典的get方法生成结束


	public RabbitQueuesModel(){
	}
	public RabbitQueuesModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	public java.lang.String getAppid() {
		return this.appid;
	}
	
	public void setAppid(java.lang.String value) {
		this.appid = value;
	}
	public java.lang.String getAppname() {
		return this.appname;
	}
	
	public void setAppname(java.lang.String value) {
		this.appname = value;
	}
	public java.lang.String getVhost() {
		return this.vhost;
	}
	
	public void setVhost(java.lang.String value) {
		this.vhost = value;
	}
	public java.lang.String getQueuename() {
		return this.queuename;
	}
	
	public void setQueuename(java.lang.String value) {
		this.queuename = value;
	}
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String value) {
		this.type = value;
	}
	public java.lang.String getIstemp() {
		return this.istemp;
	}
	
	public void setIstemp(java.lang.String value) {
		this.istemp = value;
	}
	public java.lang.String getAutodelete() {
		return this.autodelete;
	}
	
	public void setAutodelete(java.lang.String value) {
		this.autodelete = value;
	}
	//开始生成字典的getString方法
	//字典的get方法生成结束
	 
}

