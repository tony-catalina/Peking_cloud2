/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */


package awd.cloud.platform.servers.analyse.model.jls;

import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class JsModel{
	
	//alias
	public static final String TABLE_ALIAS = "Js";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_JQH = "拘区号";
	public static final String ALIAS_JSMC = "拘室名称";
	public static final String ALIAS_JSH = "拘室号";
	public static final String ALIAS_JSLB = "拘室类别(JSLX)";
	public static final String ALIAS_FIELDNAME = "拘区";
	public static final String ALIAS_CODE = "代码";
	public static final String ALIAS_CONTENT = "内容";
	public static final String ALIAS_TYPE = "男女类型(JSLB)";
	public static final String ALIAS_INNUM = "关押人数";
	public static final String ALIAS_BZNUM = "额定押量";
	public static final String ALIAS_ZGMJ = "主管民警";
	public static final String ALIAS_XGMJ = "协管民警";
	public static final String ALIAS_WMJS = "文明监室(SHFO)";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_STATE = "删除状态(YWSTATE)";
	public static final String ALIAS_SCBZ = "上传标志(SHFO)";
	public static final String ALIAS_OPERATOR = "操作人";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String jqh;
	
	private java.lang.String jsmc;
	
	private java.lang.String jsh;
	
	private java.lang.String jslb;
	
	private java.lang.String fieldname;
	
	private java.lang.String code;
	
	private java.lang.String content;
	
	private java.lang.String type;
	
	private java.lang.Integer innum;
	
	private java.lang.Integer bznum;
	
	private java.lang.String zgmj;
	
	private java.lang.String xgmj;
	
	private java.lang.String wmjs;
	
	private java.lang.String bz;
	
	private java.lang.String state;
	
	private java.lang.String scbz;
	
	private java.lang.String operator;
	
	private java.lang.String creator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
		
	
	private java.lang.String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
		
	//columns END
	//开始生成字典的String属性
	private java.lang.String jsbhString;
	
		
	private java.lang.String jslbString;
		
	private java.lang.String typeString;
		
	private java.lang.String wmjsString;
		
	private java.lang.String stateString;
		
	private java.lang.String scbzString;
	//字典的get方法生成结束


	public JsModel(){
	}
	public JsModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	public java.lang.String getJqh() {
		return this.jqh;
	}
	
	public void setJqh(java.lang.String value) {
		this.jqh = value;
	}
	public java.lang.String getJsmc() {
		return this.jsmc;
	}
	
	public void setJsmc(java.lang.String value) {
		this.jsmc = value;
	}
	public java.lang.String getJsh() {
		return this.jsh;
	}
	
	public void setJsh(java.lang.String value) {
		this.jsh = value;
	}
	public java.lang.String getJslb() {
		return this.jslb;
	}
	
	public void setJslb(java.lang.String value) {
		this.jslb = value;
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
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String value) {
		this.type = value;
	}
	public java.lang.Integer getInnum() {
		return this.innum;
	}
	
	public void setInnum(java.lang.Integer value) {
		this.innum = value;
	}
	public java.lang.Integer getBznum() {
		return this.bznum;
	}
	
	public void setBznum(java.lang.Integer value) {
		this.bznum = value;
	}
	public java.lang.String getZgmj() {
		return this.zgmj;
	}
	
	public void setZgmj(java.lang.String value) {
		this.zgmj = value;
	}
	public java.lang.String getXgmj() {
		return this.xgmj;
	}
	
	public void setXgmj(java.lang.String value) {
		this.xgmj = value;
	}
	public java.lang.String getWmjs() {
		return this.wmjs;
	}
	
	public void setWmjs(java.lang.String value) {
		this.wmjs = value;
	}
	public java.lang.String getBz() {
		return this.bz;
	}
	
	public void setBz(java.lang.String value) {
		this.bz = value;
	}
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	public java.lang.String getScbz() {
		return this.scbz;
	}
	
	public void setScbz(java.lang.String value) {
		this.scbz = value;
	}
	public java.lang.String getOperator() {
		return this.operator;
	}
	
	public void setOperator(java.lang.String value) {
		this.operator = value;
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
	
	//开始生成字典的getString方法
	//字典的get方法生成结束
	 
}

