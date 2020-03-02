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




/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginlogsEntity extends SimpleGenericEntity<String> {
	
	//alias
	public static final String TABLE_ALIAS = "登录日志";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_LOGINNAME = "登录名";
	public static final String ALIAS_LOGINTIME = "登录时间";
	public static final String ALIAS_LOGINIP = "登录IP";
	public static final String ALIAS_LOGINTYPE = "登录类型";
	public static final String ALIAS_LOGOUTTIME = "注销时间";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	
	//date formats
	public static final String FORMAT_LOGINTIME = DATE_TIME_FORMAT;
	public static final String FORMAT_LOGOUTTIME = DATE_TIME_FORMAT;
	
	//保存组（不需要id验证）
	public static interface SaveGroup {};  
	//新增组（需要id验证）
	public static interface UpdateGroup {};
	//所有组
	@GroupSequence({SaveGroup.class, UpdateGroup.class})  
	public interface All {       
	} 

	//columns START

	@NotNull(message="监所编号不能为空",groups=SaveGroup.class)
    @Length(max=9,message="监所编号最大长度9位" ,groups=SaveGroup.class)
	private java.lang.String jsbh;
	@NotNull(message="登录名不能为空",groups=SaveGroup.class)
    @Length(max=50,message="登录名最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String loginname;
	
	@NotNull(message="登录时间不能为空")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date logintime;

	@NotNull(message="登录IP不能为空",groups=SaveGroup.class)
    @Length(max=30,message="登录IP最大长度30位" ,groups=SaveGroup.class)
	private java.lang.String loginip;
	@NotNull(message="登录类型(LGOINTYPE)不能为空",groups=SaveGroup.class)
    @Length(max=1,message="登录类型(LGOINTYPE)最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String logintype;
	
	@NotNull(message="注销时间不能为空")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date logouttime;

	@NotNull(message="创建人不能为空",groups=SaveGroup.class)
    @Length(max=50,message="创建人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String creator;
	@NotNull(message="创建时间不能为空",groups=SaveGroup.class)
    @Length(max=11,message="创建时间最大长度11位" ,groups=SaveGroup.class)
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
    @Length(max=50,message="更新人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String updator;
    @Length(max=11,message="更新时间最大长度11位" ,groups=SaveGroup.class)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
	//columns END


	public LoginlogsEntity(){
	}

	public LoginlogsEntity(
		java.lang.String id
	){
		super.setId(id);
	}
	
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	
	public java.lang.String getLoginname() {
		return this.loginname;
	}
	
	public void setLoginname(java.lang.String value) {
		this.loginname = value;
	}
	
	public String getLogintimeString() {
		return date2String(getLogintime(), FORMAT_LOGINTIME);
	}
	
	public java.util.Date getLogintime() {
		return this.logintime;
	}
	
	public void setLogintime(java.util.Date value) {
		this.logintime = value;
	}
	
	public java.lang.String getLoginip() {
		return this.loginip;
	}
	
	public void setLoginip(java.lang.String value) {
		this.loginip = value;
	}
	
	public java.lang.String getLogintype() {
		return this.logintype;
	}
	
	public void setLogintype(java.lang.String value) {
		this.logintype = value;
	}
	
	public String getLogouttimeString() {
		return date2String(getLogouttime(), FORMAT_LOGOUTTIME);
	}
	
	public java.util.Date getLogouttime() {
		return this.logouttime;
	}
	
	public void setLogouttime(java.util.Date value) {
		this.logouttime = value;
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

