/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.suppers.finger.model;

import awd.framework.common.model.Model;
import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class MzwtzmModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Mzwtzm";
	public static final String ALIAS_ID = "ID号";
	public static final String ALIAS_ZWTZXH1 = "第一个手指指纹特征文件序号";
	public static final String ALIAS_ZWTZ1 = "第一个手指指纹特征文件";
	public static final String ALIAS_ZWTZXH2 = "第二个手指指纹特征文件序号";
	public static final String ALIAS_ZWTZ2 = "第二个手指指纹特征文件";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_OPERATOR = "创建人";
	public static final String ALIAS_UPDATEOPERATOR = "更新人";
	public static final String ALIAS_SCBZ = "上传标志";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_STATE = "状态  R3删除  R2正常";
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String zwtzxh1;
	
	private byte[] zwtz1;
	
	private java.lang.String zwtzxh2;
	
	private byte[] zwtz2;
	//创建时间
	private java.util.Date createtime;
	//更新时间
	private java.util.Date updatetime;
	
	private java.lang.String operator;
	
	private java.lang.String updateoperator;
	
	private java.lang.String scbz;
	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String state;
	//columns END


	public MzwtzmModel(){
	}
	public MzwtzmModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getZwtzxh1() {
		return this.zwtzxh1;
	}
	
	public void setZwtzxh1(java.lang.String value) {
		this.zwtzxh1 = value;
	}
	public byte[] getZwtz1() {
		return this.zwtz1;
	}
	
	public void setZwtz1(byte[] value) {
		this.zwtz1 = value;
	}
	public java.lang.String getZwtzxh2() {
		return this.zwtzxh2;
	}
	
	public void setZwtzxh2(java.lang.String value) {
		this.zwtzxh2 = value;
	}
	public byte[] getZwtz2() {
		return this.zwtz2;
	}
	
	public void setZwtz2(byte[] value) {
		this.zwtz2 = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	
	public java.util.Date getUpdatetime() {
		return this.updatetime;
	}
	
	public void setUpdatetime(java.util.Date value) {
		this.updatetime = value;
	}
	
	public java.lang.String getOperator() {
		return this.operator;
	}
	
	public void setOperator(java.lang.String value) {
		this.operator = value;
	}
	public java.lang.String getUpdateoperator() {
		return this.updateoperator;
	}
	
	public void setUpdateoperator(java.lang.String value) {
		this.updateoperator = value;
	}
	public java.lang.String getScbz() {
		return this.scbz;
	}
	
	public void setScbz(java.lang.String value) {
		this.scbz = value;
	}
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	public java.lang.String getRybh() {
		return this.rybh;
	}
	
	public void setRybh(java.lang.String value) {
		this.rybh = value;
	}
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	
}

