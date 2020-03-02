/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.entity;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;

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
public class DictionaryEntity extends SimpleGenericEntity<String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//alias
	public static final String TABLE_ALIAS = "字典信息";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_FIELDNAME = "分类名";
	public static final String ALIAS_CODE = "代码";
	public static final String ALIAS_CONTENT = "内容";
	public static final String ALIAS_PY = "拼音";
	public static final String ALIAS_ISGB = "是否是国标";
	public static final String ALIAS_SYPL = "使用频率";
	public static final String ALIAS_EDITABLE = "是否可编辑";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_JSLX = " 监所类型";
	
	//date formats
	public static final String FORMAT_TIMESTAMP = TIMESTAMP_FORMAT;

	//columns START
	@Expose
	private java.lang.String fieldname;
	@Expose
	private java.lang.String code;
	@Expose
	private java.lang.String content;
	@Expose
	private java.lang.String py;
	@Expose
	private java.lang.String isgb;
	@Expose
	private java.math.BigDecimal sypl;
	@Expose
	private java.lang.String editable;
	@Expose
	private java.lang.String creator;
	@Expose
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
	@Expose
	private java.lang.String updator;
	@Expose
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
	@Expose
	private java.lang.String jslx;
	//columns END


	public DictionaryEntity(){
	}
	
	
	
	public java.lang.String getFieldname() {
		return this.fieldname;
	}
	
	public void setFieldname(java.lang.String value) {
		this.fieldname = value;
	}
	
	public java.lang.String getCode() {
		return this.code;
	}
	
	public void setCode(java.lang.String value) {
		this.code = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getPy() {
		return this.py;
	}
	
	public void setPy(java.lang.String value) {
		this.py = value;
	}
	
	public java.lang.String getIsgb() {
		return this.isgb;
	}
	
	public void setIsgb(java.lang.String value) {
		this.isgb = value;
	}
	
	public java.math.BigDecimal getSypl() {
		return this.sypl;
	}
	
	public void setSypl(java.math.BigDecimal value) {
		this.sypl = value;
	}
	
	public java.lang.String getEditable() {
		return this.editable;
	}
	
	public void setEditable(java.lang.String value) {
		this.editable = value;
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
	
	public java.lang.String getJslx() {
		return this.jslx;
	}
	
	public void setJslx(java.lang.String value) {
		this.jslx = value;
	}
	
	
	public java.lang.String getJslxString() {
		return CacheUtils.get().getDictionary("DM", this.jslx);
	}
	
	public java.lang.String getFieldnameString() {
		return CacheUtils.get().getDictionary("ZDMC", this.fieldname);
	}
	public java.lang.String getEditableString() {
		return CacheUtils.get().getDictionary("SHFO", this.editable);
	}
	public java.lang.String getIsgbString() {
		return CacheUtils.get().getDictionary("SHFO", this.isgb);
	}
	
	
	
}

