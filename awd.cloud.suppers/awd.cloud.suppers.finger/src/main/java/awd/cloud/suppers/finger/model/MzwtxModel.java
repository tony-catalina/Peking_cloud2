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


public class MzwtxModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Mzwtx";
	public static final String ALIAS_ID = "公民身份号码";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_ZWTXXH11 = "第一个手指第一枚指纹序号";
	public static final String ALIAS_ZWTX11 = "第一个手指第一枚指纹图像";
	public static final String ALIAS_ZWTXXH12 = "第一个手指第二枚指纹序号";
	public static final String ALIAS_ZWTX12 = "第一个手指第二枚指纹图像";
	public static final String ALIAS_ZWTXXH13 = "第一个手指第三枚指纹序号";
	public static final String ALIAS_ZWTX13 = "第一个手指第三枚指纹图像";
	public static final String ALIAS_ZWTXXH21 = "第二个手指第一枚指纹序号";
	public static final String ALIAS_ZWTX21 = "第二个手指第一枚指纹图像";
	public static final String ALIAS_ZWTXXH22 = "第二个手指第二枚指纹序号";
	public static final String ALIAS_ZWTX22 = "第二个手指第二枚指纹图像";
	public static final String ALIAS_ZWTXXH23 = "第二个手指第三枚指纹序号";
	public static final String ALIAS_ZWTX23 = "第二个手指第三枚指纹图像";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_OPERATOR = "创建人";
	public static final String ALIAS_UPDATEOPERATOR = "更新人";
	public static final String ALIAS_STATE = "状态  R3删除  R2正常";
	public static final String ALIAS_SCBZ = "上次标志";
	public static final String ALIAS_ZJH = "证件号";
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String zwtxxh11;
	
	private byte[] zwtx11;
	
	private java.lang.String zwtxxh12;
	
	private byte[] zwtx12;
	
	private java.lang.String zwtxxh13;
	
	private byte[] zwtx13;
	
	private java.lang.String zwtxxh21;
	
	private byte[] zwtx21;
	
	private java.lang.String zwtxxh22;
	
	private byte[] zwtx22;
	
	private java.lang.String zwtxxh23;
	
	private byte[] zwtx23;
	//创建时间
	private java.util.Date createtime;
	//更新时间
	private java.util.Date updatetime;
	
	private java.lang.String operator;
	
	private java.lang.String updateoperator;
	
	private java.lang.String state;
	
	private java.lang.String scbz;
	
	private java.lang.String zjh;
	//columns END


	public MzwtxModel(){
	}
	public MzwtxModel(String id) {
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
	public java.lang.String getZwtxxh11() {
		return this.zwtxxh11;
	}
	
	public void setZwtxxh11(java.lang.String value) {
		this.zwtxxh11 = value;
	}
	public byte[] getZwtx11() {
		return this.zwtx11;
	}
	
	public void setZwtx11(byte[] value) {
		this.zwtx11 = value;
	}
	public java.lang.String getZwtxxh12() {
		return this.zwtxxh12;
	}
	
	public void setZwtxxh12(java.lang.String value) {
		this.zwtxxh12 = value;
	}
	public byte[] getZwtx12() {
		return this.zwtx12;
	}
	
	public void setZwtx12(byte[] value) {
		this.zwtx12 = value;
	}
	public java.lang.String getZwtxxh13() {
		return this.zwtxxh13;
	}
	
	public void setZwtxxh13(java.lang.String value) {
		this.zwtxxh13 = value;
	}
	public byte[] getZwtx13() {
		return this.zwtx13;
	}
	
	public void setZwtx13(byte[] value) {
		this.zwtx13 = value;
	}
	public java.lang.String getZwtxxh21() {
		return this.zwtxxh21;
	}
	
	public void setZwtxxh21(java.lang.String value) {
		this.zwtxxh21 = value;
	}
	public byte[] getZwtx21() {
		return this.zwtx21;
	}
	
	public void setZwtx21(byte[] value) {
		this.zwtx21 = value;
	}
	public java.lang.String getZwtxxh22() {
		return this.zwtxxh22;
	}
	
	public void setZwtxxh22(java.lang.String value) {
		this.zwtxxh22 = value;
	}
	public byte[] getZwtx22() {
		return this.zwtx22;
	}
	
	public void setZwtx22(byte[] value) {
		this.zwtx22 = value;
	}
	public java.lang.String getZwtxxh23() {
		return this.zwtxxh23;
	}
	
	public void setZwtxxh23(java.lang.String value) {
		this.zwtxxh23 = value;
	}
	public byte[] getZwtx23() {
		return this.zwtx23;
	}
	
	public void setZwtx23(byte[] value) {
		this.zwtx23 = value;
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
	public java.lang.String getZjh() {
		return this.zjh;
	}
	
	public void setZjh(java.lang.String value) {
		this.zjh = value;
	}
	
}

