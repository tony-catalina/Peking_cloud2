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
import awd.framework.common.utils.StringUtils;
import awd.mis.servers.tools.CacheUtils;


/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserinfoEntity extends SimpleGenericEntity<String> {
	//alias
	public static final String TABLE_ALIAS = "用户信息";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_USERID = "用户编号";
	public static final String ALIAS_USERTYPE = "用户类型";
	public static final String ALIAS_JSBH = "单位号";
	public static final String ALIAS_LOGINNAME = "登录名";
	public static final String ALIAS_LOGINPASS = "登录密码";
	public static final String ALIAS_SFZH = "身份证号";
	public static final String ALIAS_JH = "警号";
	public static final String ALIAS_EMAIL = "EMAIL";
	public static final String ALIAS_REALNAME = "真实姓名";
	public static final String ALIAS_GLYBZ = "管理员标识";
	public static final String ALIAS_SPR = "审批人";
	public static final String ALIAS_SPSJ = "审批时间";
	public static final String ALIAS_SPBZ = "审批标识";
	public static final String ALIAS_STATE = "启用状态";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";	
	
	
	//date formats
	public static final String FORMAT_SPSJ = DATE_FORMAT;
	
	//保存组（不需要id验证）
	public static interface SaveGroup {};  
	//新增组（需要id验证）
	public static interface UpdateGroup {};
	//所有组
	@GroupSequence({SaveGroup.class, UpdateGroup.class})  
	public interface All {       
	} 

	//columns START

    @Length(max=60,message="用户编号最大长度60位" ,groups=SaveGroup.class)
	private java.lang.String userid;
	@NotNull(message="用户类型不能为空",groups=SaveGroup.class)
    @Length(max=2,message="用户类型最大长度2位" ,groups=SaveGroup.class)
	private java.lang.String usertype;
	@NotNull(message="单位号不能为空",groups=SaveGroup.class)
    @Length(max=9,message="单位号最大长度9位" ,groups=SaveGroup.class)
	private java.lang.String jsbh;
	@NotNull(message="登录名不能为空",groups=SaveGroup.class)
    @Length(max=50,message="登录名最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String loginname;
	@NotNull(message="登录密码不能为空",groups=SaveGroup.class)
    @Length(max=50,message="登录密码最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String loginpass;
    @Length(max=18,message="身份证号最大长度18位" ,groups=SaveGroup.class)
	private java.lang.String sfzh;
    @Length(max=6,message="警号最大长度6位" ,groups=SaveGroup.class)
	private java.lang.String jh;
    @Length(max=50,message="EMAIL最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String email;
	@NotNull(message="真实姓名不能为空",groups=SaveGroup.class)
    @Length(max=50,message="真实姓名最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String realname;
	@NotNull(message="管理员标识不能为空",groups=SaveGroup.class)
    @Length(max=1,message="管理员标识最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String glybz;
    @Length(max=50,message="审批人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String spr;
	
	@NotNull(message="审批时间不能为空")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date spsj;

    @Length(max=1,message="审批标识最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String spbz;
	@NotNull(message="启用状态不能为空",groups=SaveGroup.class)
    @Length(max=2,message="启用状态最大长度2位" ,groups=SaveGroup.class)
	private java.lang.String state;
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
    private java.lang.String xkid;
	//columns END


	public UserinfoEntity(){
	}

	public UserinfoEntity(
		java.lang.String id
	){
		super.setId(id);
	}

	
	public java.lang.String getUserid() {
		return this.userid;
	}
	
	public void setUserid(java.lang.String value) {
		this.userid = value;
	}
	
	public java.lang.String getUsertype() {
		return this.usertype;
	}
	
	public java.lang.String getUsertypeString() {
		if(StringUtils.isNullOrEmpty(this.usertype)) {
			return "";
		}else {
			return CacheUtils.get().getDictionary("JSTYPE", this.usertype);
		}
	}
	
	public void setUsertype(java.lang.String value) {
		this.usertype = value;
	}
	
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public java.lang.String getJsbhString() {
		if(StringUtils.isNullOrEmpty(this.jsbh)) {
			return "";
		}else {
			return CacheUtils.get().getJsbhString(this.jsbh);
		}
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
	
	public java.lang.String getLoginpass() {
		return this.loginpass;
	}
	
	public void setLoginpass(java.lang.String value) {
		this.loginpass = value;
	}
	
	public java.lang.String getSfzh() {
		return this.sfzh;
	}
	
	public void setSfzh(java.lang.String value) {
		this.sfzh = value;
	}
	
	public java.lang.String getJh() {
		return this.jh;
	}
	
	public void setJh(java.lang.String value) {
		this.jh = value;
	}
	
	public java.lang.String getEmail() {
		return this.email;
	}
	
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	
	public java.lang.String getRealname() {
		return this.realname;
	}
	
	public void setRealname(java.lang.String value) {
		this.realname = value;
	}
	
	public java.lang.String getGlybz() {
		return this.glybz;
	}
	
	public java.lang.String getGlybzString() {
		if(StringUtils.isNullOrEmpty(this.glybz)) {
			return "";
		}else {
			return CacheUtils.get().getDictionary("SHFO", this.glybz);
		}
	}
	
	public void setGlybz(java.lang.String value) {
		this.glybz = value;
	}
	
	public java.lang.String getSpr() {
		return this.spr;
	}
	
	public void setSpr(java.lang.String value) {
		this.spr = value;
	}
	
	public java.util.Date getSpsj() {
		return this.spsj;
	}
	
	public void setSpsj(java.util.Date value) {
		this.spsj = value;
	}
	
	public java.lang.String getSpbz() {
		return this.spbz;
	}
	
	public void setSpbz(java.lang.String value) {
		this.spbz = value;
	}
	
	public java.lang.String getState() {
		return this.state;
	}
	
	public java.lang.String getStateString() {
		return CacheUtils.get().getDictionary("USERSTATE", this.state);
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

	public java.lang.String getXkid() {
		return xkid;
	}

	public void setXkid(java.lang.String xkid) {
		this.xkid = xkid;
	}
	
	
}

