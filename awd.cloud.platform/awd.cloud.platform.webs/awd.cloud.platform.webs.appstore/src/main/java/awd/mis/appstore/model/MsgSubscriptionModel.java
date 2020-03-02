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
public class MsgSubscriptionModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "MsgSubscription";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_VHOST = "虚拟主机名";
	public static final String ALIAS_QUEUENAME = "队列名称";
	public static final String ALIAS_EXCHANGE = "交换机名称";
	public static final String ALIAS_ROUTINGKEY = "队列绑定key（routingkey）";
	public static final String ALIAS_SHBZ = "审核标志（0 是等待审批，1 是审核成功）";
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String vhost;
	
	private java.lang.String queuename;
	
	private java.lang.String exchange;
	
	private java.lang.String routingkey;
	
	private java.lang.String shbz;
	//columns END
	//开始生成字典的String属性
	//字典的get方法生成结束


	public MsgSubscriptionModel(){
	}
	public MsgSubscriptionModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
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
	public java.lang.String getExchange() {
		return this.exchange;
	}
	
	public void setExchange(java.lang.String value) {
		this.exchange = value;
	}
	public java.lang.String getRoutingkey() {
		return this.routingkey;
	}
	
	public void setRoutingkey(java.lang.String value) {
		this.routingkey = value;
	}
	public java.lang.String getShbz() {
		return this.shbz;
	}
	
	public void setShbz(java.lang.String value) {
		this.shbz = value;
	}
}

