/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class ApidocModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Apidoc";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_APPCODE = "应用编号";
	public static final String ALIAS_STATE = "删除状态";
	public static final String ALIAS_RESTFUL = "请求方式";
	public static final String ALIAS_PARAM = "请求参数";
	public static final String ALIAS_RESPONSE = "相应参数";
	
	//columns START
	
	private String id;

	
	private String jsbh;
	
	private String creator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
		
	
	private String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
		
	
	private String appcode;
	
	private String state;
	
	private String restful;
	
	private String param;
	
	private String response;
	//columns END
	//开始生成字典的String属性
	private String jsbhString;
	
	//字典的get方法生成结束


	public ApidocModel(){
	}
	public ApidocModel(String id) {
		this.id = id;
	}
	

	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return this.id;
	}

	public String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(String value) {
		this.jsbh = value;
	}
	public String getCreator() {
		return this.creator;
	}
	
	public void setCreator(String value) {
		this.creator = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public String getUpdator() {
		return this.updator;
	}
	
	public void setUpdator(String value) {
		this.updator = value;
	}
	
	public java.util.Date getUpdatetime() {
		return this.updatetime;
	}
	
	public void setUpdatetime(java.util.Date value) {
		this.updatetime = value;
	}
	
	public String getAppcode() {
		return this.appcode;
	}
	
	public void setAppcode(String value) {
		this.appcode = value;
	}
	public String getState() {
		return this.state;
	}
	
	public void setState(String value) {
		this.state = value;
	}
	public String getRestful() {
		return this.restful;
	}
	
	public void setRestful(String value) {
		this.restful = value;
	}
	public String getParam() {
		return this.param;
	}
	
	public void setParam(String value) {
		this.param = value;
	}
	public String getResponse() {
		return this.response;
	}
	
	public void setResponse(String value) {
		this.response = value;
	}
	//开始生成字典的getString方法
	public String getJsbhString() {
		return this.jsbhString;
	}
	//字典的get方法生成结束
	 
}

