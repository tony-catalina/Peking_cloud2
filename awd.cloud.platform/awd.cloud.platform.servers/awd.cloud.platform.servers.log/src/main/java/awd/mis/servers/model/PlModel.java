/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.model;

import awd.framework.common.model.Model;
import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class PlModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Pl";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_APPCODE = "应用编号";
	public static final String ALIAS_PLR = "评论人";
	public static final String ALIAS_PLSJ = "评论时间";
	public static final String ALIAS_CONTENT = "内容";
	public static final String ALIAS_DZ = "点赞数";
	public static final String ALIAS_CLZT = "处理状态";
	public static final String ALIAS_HFLBID = "回复列表ID";
	public static final String ALIAS_STATE = "数据状态";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String appcode;
	
	private java.lang.String plr;
	//评论时间
	private java.util.Date plsj;
	
	private java.lang.String content;
	
	private java.lang.Integer dz;
	
	private java.lang.String clzt;
	
	private java.lang.String hflbid;
	
	private java.lang.String state;
	
	private java.lang.String creator;
	//创建时间
	private java.util.Date createtime;
	//columns END


	public PlModel(){
	}
	public PlModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getAppcode() {
		return this.appcode;
	}
	
	public void setAppcode(java.lang.String value) {
		this.appcode = value;
	}
	public java.lang.String getPlr() {
		return this.plr;
	}
	
	public void setPlr(java.lang.String value) {
		this.plr = value;
	}
	
	public java.util.Date getPlsj() {
		return this.plsj;
	}
	
	public void setPlsj(java.util.Date value) {
		this.plsj = value;
	}
	
	public java.lang.String getContent() {
		return content;
	}
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	public java.lang.Integer getDz() {
		return this.dz;
	}
	
	public void setDz(java.lang.Integer value) {
		this.dz = value;
	}
	public java.lang.String getClzt() {
		return this.clzt;
	}
	
	public void setClzt(java.lang.String value) {
		this.clzt = value;
	}
	public java.lang.String getHflbid() {
		return this.hflbid;
	}
	
	public void setHflbid(java.lang.String value) {
		this.hflbid = value;
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
	
	
}

