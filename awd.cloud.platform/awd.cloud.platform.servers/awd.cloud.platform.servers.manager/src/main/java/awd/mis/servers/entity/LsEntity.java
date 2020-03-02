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
public class LsEntity extends SimpleGenericEntity<String> {
	//alias
	public static final String TABLE_ALIAS = "律师";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_XM = "姓名";
	public static final String ALIAS_DW = "单位";
	public static final String ALIAS_LSZH = "律师证号";
	public static final String ALIAS_LSZW = "律师指纹";
	public static final String ALIAS_LSHJH = "律师会见号";
	public static final String ALIAS_STATE = "状态";
	public static final String ALIAS_YXBZ = "有效标识";
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
	
	@Length(max=9,message="监所编号长度9位" ,groups=SaveGroup.class)
	private java.lang.String jsbh;
    @Length(max=50,message="姓名最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String xm;
    @Length(max=50,message="单位最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String dw;
    @Length(max=30,message="律师证号最大长度30位" ,groups=SaveGroup.class)
	private java.lang.String lszh;
    @Length(max=4000,message="律师指纹最大长度4000位" ,groups=SaveGroup.class)
	private java.sql.Blob lszw;
    @Length(max=30,message="律师会见号最大长度30位" ,groups=SaveGroup.class)
	private java.lang.String lshjh;
    @Length(max=2,message="状态最大长度2位" ,groups=SaveGroup.class)
	private java.lang.String state;
    @Length(max=1,message="有效标识最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String yxbz;
    @Length(max=50,message="创建人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String creator;
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


	public LsEntity(){
	}

	public LsEntity(
		java.lang.String id
	){
		super.setId(id);
	}

	
	public java.lang.String getJsbh() {
		return jsbh;
	}

	public void setJsbh(java.lang.String jsbh) {
		this.jsbh = jsbh;
	}

	public java.lang.String getXm() {
		return this.xm;
	}
	
	public void setXm(java.lang.String value) {
		this.xm = value;
	}
	
	public java.lang.String getDw() {
		return this.dw;
	}
	
	public void setDw(java.lang.String value) {
		this.dw = value;
	}
	
	public java.lang.String getLszh() {
		return this.lszh;
	}
	
	public void setLszh(java.lang.String value) {
		this.lszh = value;
	}
	
	public java.sql.Blob getLszw() {
		return this.lszw;
	}
	
	public void setLszw(java.sql.Blob value) {
		this.lszw = value;
	}
	
	public java.lang.String getLshjh() {
		return this.lshjh;
	}
	
	public void setLshjh(java.lang.String value) {
		this.lshjh = value;
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
	
	public java.lang.String getYxbz() {
		return this.yxbz;
	}
	
	public void setYxbz(java.lang.String value) {
		this.yxbz = value;
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

