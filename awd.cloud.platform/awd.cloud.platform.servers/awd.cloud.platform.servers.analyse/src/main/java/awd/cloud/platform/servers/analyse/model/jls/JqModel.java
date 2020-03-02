/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */


package awd.cloud.platform.servers.analyse.model.jls;

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
public class JqModel{
	
	//alias
	public static final String TABLE_ALIAS = "Jq";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "拘区编号";
	public static final String ALIAS_JQH = "拘区号";
	public static final String ALIAS_JQMC = "监区名称";
	public static final String ALIAS_JQICONS = "拘区图标";
	public static final String ALIAS_TYPE = "拘区类型（男，女）";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_OPERATOR = "操作人";
	public static final String ALIAS_STATE = "删除状态(YWSTATE)";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String jqh;
	
	private java.lang.String jqmc;
	
	private java.lang.String jqicons;
	
	private java.lang.String type;
	
	private java.lang.String bz;
	
	private java.lang.String operator;
	
	private java.lang.String state;
	
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
	
		
	private java.lang.String stateString;
	//字典的get方法生成结束


	public JqModel(){
	}
	public JqModel(String id) {
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
	public java.lang.String getJqmc() {
		return this.jqmc;
	}
	
	public void setJqmc(java.lang.String value) {
		this.jqmc = value;
	}
	public java.lang.String getJqicons() {
		return this.jqicons;
	}
	
	public void setJqicons(java.lang.String value) {
		this.jqicons = value;
	}
	
	public java.lang.String getType() {
		return type;
	}
	public void setType(java.lang.String type) {
		this.type = type;
	}
	public java.lang.String getBz() {
		return this.bz;
	}
	
	public void setBz(java.lang.String value) {
		this.bz = value;
	}
	public java.lang.String getOperator() {
		return this.operator;
	}
	
	public void setOperator(java.lang.String value) {
		this.operator = value;
	}
	public java.lang.String getState() {
		return this.state;
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
	
	//字典的get方法生成结束
	 
}

