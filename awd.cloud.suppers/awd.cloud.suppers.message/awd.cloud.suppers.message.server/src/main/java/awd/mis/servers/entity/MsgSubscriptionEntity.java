/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.entity;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

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
public class MsgSubscriptionEntity extends SimpleGenericEntity<String>  {

	//alias
	public static final String TABLE_ALIAS = "MsgSubscription";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_VHOST = "虚拟主机名";
	public static final String ALIAS_QUEUENAME = "队列名称";
	public static final String ALIAS_EXCHANGE = "交换机名称";
	public static final String ALIAS_ROUTINGKEY = "队列绑定key（routingkey）";
	public static final String ALIAS_SHBZ = "审核标志（0 是等待审批，1 是审核成功）";

	//所有组
	@GroupSequence({CreateGroup.class, UpdateGroup.class})
	public interface All {
	}

	//columns START
		/*@NotNull(message="id不能为空",groups=CreateGroup.class)
	    @Length(max=23,message="id最大长度23位" ,groups=CreateGroup.class)
		private java.lang.String id;*/
	
		@Length(max=30,message="虚拟主机名最大长度30位" ,groups=CreateGroup.class)
		private java.lang.String vhost;
	
		@NotNull(message="队列名称不能为空",groups=CreateGroup.class)
		@Length(max=150,message="队列名称最大长度150位" ,groups=CreateGroup.class)
		private java.lang.String queuename;
	
		@NotNull(message="交换机名称不能为空",groups=CreateGroup.class)
		@Length(max=150,message="交换机名称最大长度150位" ,groups=CreateGroup.class)
		private java.lang.String exchange;
	
		@NotNull(message="队列绑定key（routingkey）不能为空",groups=CreateGroup.class)
		@Length(max=300,message="队列绑定key（routingkey）最大长度300位" ,groups=CreateGroup.class)
		private java.lang.String routingkey;
	
		@NotNull(message="审核标志（0 是等待审批，1 是审核成功）不能为空",groups=CreateGroup.class)
		@Length(max=1,message="审核标志（0 是等待审批，1 是审核成功）最大长度1位" ,groups=CreateGroup.class)
		private java.lang.String shbz;
	
	//columns END


	public MsgSubscriptionEntity(){
	}

	/*public MsgSubscriptionEntity(
		java.lang.String id
	){
		this.id = id;
	}*/



	/*public void setId(java.lang.String value) {
		this.id = value;
	}

	public java.lang.String getId() {
		return this.id;
	}*/


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

