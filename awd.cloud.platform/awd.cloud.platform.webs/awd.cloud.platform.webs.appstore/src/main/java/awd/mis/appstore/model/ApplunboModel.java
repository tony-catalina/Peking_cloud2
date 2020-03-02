/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.appstore.model;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class ApplunboModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Applunbo";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_SORT = "排序 数字越小 图片越靠前";
	public static final String ALIAS_APPCODE = "应用编号";
	public static final String ALIAS_APPURL = "应用地址";
	public static final String ALIAS_PHOTOURL = "图片地址";
	public static final String ALIAS_STATE = "删除状态";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	
	//columns START
	
	private String id;

	
	private String sort;
	
	private String appcode;
	
	private String appurl;
	
	private String state;
	
	private String creator;
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
	
	private String photoURL;
	
	//app内字段，inco和name
	
	private String icon;
	private String name;
	//columns END


	public String getIcon() {
		return icon;
	}
	public String getName() {
		return name;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ApplunboModel(){
	}
	public ApplunboModel(String id) {
		this.id = id;
	}
	

	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getSort() {
		return this.sort;
	}
	
	public void setSort(String value) {
		this.sort = value;
	}
	public String getAppcode() {
		return this.appcode;
	}
	
	public void setAppcode(String value) {
		this.appcode = value;
	}
	public String getAppurl() {
		return this.appurl;
	}
	
	public void setAppurl(String value) {
		this.appurl = value;
	}
	public String getPhotoURL() {
		return photoURL;
	}
	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}
	public String getState() {
		return this.state;
	}
	
	public void setState(String value) {
		this.state = value;
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
	
	
}

