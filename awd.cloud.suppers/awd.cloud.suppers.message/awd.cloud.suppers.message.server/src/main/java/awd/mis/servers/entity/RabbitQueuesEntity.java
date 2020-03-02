/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.entity;

import javax.validation.GroupSequence;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.service.web.group.CreateGroup;
import awd.framework.common.service.web.group.UpdateGroup;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class RabbitQueuesEntity extends SimpleGenericEntity<String>  {

	//alias
	public static final String TABLE_ALIAS = "RabbitQueues";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_APPID = "APPID";
	public static final String ALIAS_APPNAME = "APP名称";
	public static final String ALIAS_VHOST = "虚拟主机";
	public static final String ALIAS_QUEUE_NAME = "对列名";
	public static final String ALIAS_TYPE = "类型";
	public static final String ALIAS_ISTEMP = "是否临时对列";
	public static final String ALIAS_AUTODELETE = "是否自动删除";

	//所有组
	@GroupSequence({CreateGroup.class, UpdateGroup.class})
	public interface All {
	}

		private java.lang.String appid;
	
		@Length(max=50,message="APP名称最大长度50位" ,groups=CreateGroup.class)
		private java.lang.String appname;
	
		@Length(max=30,message="虚拟主机最大长度30位" ,groups=CreateGroup.class)
		private java.lang.String vhost;
	
		@Length(max=255,message="对列名最大长度255位" ,groups=CreateGroup.class)
		private java.lang.String queuename;
	
		@Length(max=10,message="类型最大长度10位" ,groups=CreateGroup.class)
		private java.lang.String type;
	
		@Length(max=1,message="是否临时对列最大长度1位" ,groups=CreateGroup.class)
		private java.lang.String istemp;
	
		@Length(max=1,message="是否自动删除最大长度1位" ,groups=CreateGroup.class)
		private java.lang.String autodelete;
		
	//columns END


	public RabbitQueuesEntity(){
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

	
}

