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
import awd.framework.common.utils.ApplicationContextHolder;
import awd.framework.expands.redisclient.RedisUtils;
import awd.mis.servers.tools.CacheUtils;



/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class BarEntity extends SimpleGenericEntity<String> {
	
	//alias
	public static final String TABLE_ALIAS = "办案人信息";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_SFZH = "身份证号";
	public static final String ALIAS_XM = "姓名";
	public static final String ALIAS_DWLX = "单位类型(DWLX)";
	public static final String ALIAS_BADW = "办案单位";
	public static final String ALIAS_ZWTX = "指纹图像";
	public static final String ALIAS_ZWTZ = "指纹特征";
	public static final String ALIAS_ACTIVE = "启用状态(QYZT)";
	public static final String ALIAS_STATE = "状态(STATE)";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	
	//date formats
	public static final String FORMAT_TIMESTAMP = TIMESTAMP_FORMAT;
	
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
    @Length(max=9,message="监所编号长度9位" ,groups=SaveGroup.class)
	private java.lang.String jsbh;
	@NotNull(message="身份证号不能为空",groups=SaveGroup.class)
    @Length(max=20,message="身份证号最大长度20位" ,groups=SaveGroup.class)
	private java.lang.String sfzh;
	@NotNull(message="姓名不能为空",groups=SaveGroup.class)
    @Length(max=50,message="姓名最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String xm;
    @Length(max=2,message="单位类型(DWLX)最大长度2位" ,groups=SaveGroup.class)
	private java.lang.String dwlx;
    @Length(max=60,message="办案单位最大长度60位" ,groups=SaveGroup.class)
	private java.lang.String badw;
    @Length(max=4000,message="指纹图像最大长度4000位" ,groups=SaveGroup.class)
	private java.sql.Blob zwtx;
    @Length(max=4000,message="指纹特征最大长度4000位" ,groups=SaveGroup.class)
	private java.sql.Blob zwtz;
    @Length(max=1,message="启用状态(QYZT)最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String active;
    @Length(max=2,message="状态(STATE)最大长度2位" ,groups=SaveGroup.class)
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
	//columns END


	public BarEntity(){
	}

	public BarEntity(
		java.lang.String id
	){
		super.setId(id);
	}

	
	
	public java.lang.String getJsbh() {
		return jsbh;
	}
	
	public java.lang.String getJsbhString() {
		return CacheUtils.get().getJsbhString(this.jsbh);
	}

	public void setJsbh(java.lang.String jsbh) {
		this.jsbh = jsbh;
	}

	public java.lang.String getSfzh() {
		return this.sfzh;
	}
	
	public void setSfzh(java.lang.String value) {
		this.sfzh = value;
	}
	
	public java.lang.String getXm() {
		return this.xm;
	}
	
	public void setXm(java.lang.String value) {
		this.xm = value;
	}
	
	public java.lang.String getDwlx() {
		return this.dwlx;
	}
	
	public java.lang.String getDwlxString() {
		return CacheUtils.get().getDictionary("DWLX", this.dwlx);
	}
	
	public void setDwlx(java.lang.String value) {
		this.dwlx = value;
	}
	
	public java.lang.String getBadw() {
		return this.badw;
	}
	
	public void setBadw(java.lang.String value) {
		this.badw = value;
	}
	
	public java.sql.Blob getZwtx() {
		return this.zwtx;
	}
	
	public void setZwtx(java.sql.Blob value) {
		this.zwtx = value;
	}
	
	public java.sql.Blob getZwtz() {
		return this.zwtz;
	}
	
	public void setZwtz(java.sql.Blob value) {
		this.zwtz = value;
	}
	
	public java.lang.String getActive() {
		return this.active;
	}
	public java.lang.String getActiveString() {
		return CacheUtils.get().getDictionary("QYZT", this.active);
	}
	
	public void setActive(java.lang.String value) {
		this.active = value;
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

