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


public class HfModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Hf";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_HFLBID = "回复列表ID ";
	public static final String ALIAS_HFR = "回复人";
	public static final String ALIAS_BHFR = "被回复人";
	public static final String ALIAS_HFSJ = "恢复时间";
	public static final String ALIAS_HFNR = "回复内容";
	public static final String ALIAS_STATE = "数据状态";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String hflbid;
	
	private java.lang.String hfr;
	
	private java.lang.String bhfr;
	private java.util.Date hfsj;
	
	private java.lang.String hfnr;
	
	private java.lang.String state;
	
	private java.lang.String creator;
	private java.util.Date createtime;
	//columns END


	public HfModel(){
	}
	public HfModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getHflbid() {
		return this.hflbid;
	}
	
	public void setHflbid(java.lang.String value) {
		this.hflbid = value;
	}
	public java.lang.String getHfr() {
		return this.hfr;
	}
	
	public void setHfr(java.lang.String value) {
		this.hfr = value;
	}
	public java.lang.String getBhfr() {
		return this.bhfr;
	}
	
	public void setBhfr(java.lang.String value) {
		this.bhfr = value;
	}
	
	public java.util.Date getHfsj() {
		return this.hfsj;
	}
	
	public void setHfsj(java.util.Date value) {
		this.hfsj = value;
	}
	
	public java.lang.String getHfnr() {
		return this.hfnr;
	}
	
	public void setHfnr(java.lang.String value) {
		this.hfnr = value;
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

