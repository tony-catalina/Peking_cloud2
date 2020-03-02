/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.entity;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.mis.servers.tools.CacheUtils;



/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersettingEntity extends SimpleGenericEntity<String> {
	//alias
	public static final String TABLE_ALIAS = "用户应用设置";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "jsbh";
	public static final String ALIAS_USERID = "userid";
	public static final String ALIAS_APP = "app";
	public static final String ALIAS_KEY = "key";
	public static final String ALIAS_VALUE = "value";
	public static final String ALIAS_STATE = "state";
	public static final String ALIAS_CREATOR = "creator";
	public static final String ALIAS_CREATETIME = "createtime";
	public static final String ALIAS_UPDATOR = "updator";
	public static final String ALIAS_UPDATETIME = "updatetime";
	
	//date formats
	
	//保存组（不需要id验证）
	public static interface SaveGroup {};  
	//新增组（需要id验证）
	public static interface UpdateGroup {};
	//所有组
	@GroupSequence({SaveGroup.class, UpdateGroup.class})  
	public interface All {       
	} 

	//columns START

    @Length(max=9,message="jsbh最大长度9位" ,groups=SaveGroup.class)
	private java.lang.String jsbh;
    @Length(max=200,message="userid最大长度200位" ,groups=SaveGroup.class)
	private java.lang.String userid;
    @Length(max=300,message="app最大长度300位" ,groups=SaveGroup.class)
	private java.lang.String app;
    @Length(max=50,message="key最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String key;
    @Length(max=4000,message="value最大长度4000位" ,groups=SaveGroup.class)
	private java.lang.String value;
    @Length(max=2,message="state最大长度2位" ,groups=SaveGroup.class)
	private java.lang.String state;
    @Length(max=50,message="creator最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String creator;
    @Length(max=11,message="createtime最大长度11位" ,groups=SaveGroup.class)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
    @Length(max=50,message="updator最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String updator;
    @Length(max=11,message="updatetime最大长度11位" ,groups=SaveGroup.class)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
	//columns END


	public UsersettingEntity(){
	}

	public UsersettingEntity(
		java.lang.String id
	){
		super.setId(id);
	}

	
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public java.lang.String getJsbhString() {
		return CacheUtils.get().getJsbhString(this.jsbh);
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	
	public java.lang.String getUserid() {
		return this.userid;
	}
	
	public void setUserid(java.lang.String value) {
		this.userid = value;
	}
	
	public java.lang.String getApp() {
		return this.app;
	}
	public java.lang.String getAppString() {
		return CacheUtils.get().getApp(this.app);
	}
	
	public void setApp(java.lang.String value) {
		this.app = value;
	}
	
	public java.lang.String getKey() {
		return this.key;
	}
	
	public void setKey(java.lang.String value) {
		this.key = value;
	}
	
	public java.lang.String getValue() {
		return this.value;
	}
	
	public void setValue(java.lang.String value) {
		this.value = value;
	}
	
	public java.lang.String getState() {
		return this.state;
	}
	
	public java.lang.String getStateString() {
		return CacheUtils.get().getDictionary("YWSTATE", this.state);
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	
	public java.lang.String getCreator() {
		return this.creator;
	}
	
	
	public void setCreator(java.lang.String value) {
		this.creator = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.lang.String getUpdator() {
		return this.updator;
	}
	
	public void setUpdator(java.lang.String value) {
		this.updator = value;
	}
	
	public java.util.Date getUpdatetime() {
		return this.updatetime;
	}
	
	
	public void setUpdatetime(java.util.Date value) {
		this.updatetime = value;
	}
	
	
}

