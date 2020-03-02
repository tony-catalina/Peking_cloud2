/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */


package awd.cloud.platform.webs.charts.modal.jls;

import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.bj.base.model.Model;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class SwjyModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Swjy";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_SNBH = "所内编号";
	public static final String ALIAS_BLLX = "办理类型(CSJYLX)";
	public static final String ALIAS_LY = "入院/出院诊断";
	public static final String ALIAS_BLRQ = "入院/出院日期";
	public static final String ALIAS_JBMC = "疾病名称";
	public static final String ALIAS_JZYY = "就诊医院";
	public static final String ALIAS_YSYJ = "医生意见";
	public static final String ALIAS_YSQM = "医生签名";
	public static final String ALIAS_QMSJ = "签名时间";
	public static final String ALIAS_ZDZYJ = "中队长意见(PSBZ)";
	public static final String ALIAS_ZDZYJNR = "中队长意见内容";
	public static final String ALIAS_ZDZQM = "中队长签名";
	public static final String ALIAS_ZDZQMSJ = "中队长签名时间";
	public static final String ALIAS_SZYJ = "所长意见(PSBZ)";
	public static final String ALIAS_SZYJNR = "所长意见内容";
	public static final String ALIAS_SZQM = "所长签名";
	public static final String ALIAS_SZQMSJ = "所长签名时间";
	public static final String ALIAS_CSSJ = "出所时间";
	public static final String ALIAS_CSQX = "出所去向";
	public static final String ALIAS_HSSJ = "回所时间";
	public static final String ALIAS_YWLCID = "业务流程ID";
	public static final String ALIAS_TASKID = "业务进程ID";
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
	
	private java.lang.String rybh;
	
	private java.lang.String snbh;
	
	private java.lang.String bllx;
	
	private java.lang.String ly;
	@JSONField(format = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date blrq;
		
	
	private java.lang.String jbmc;
	
	private java.lang.String jzyy;
	
	private java.lang.String ysyj;
	
	private java.lang.String ysqm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date qmsj;
		
	
	private java.lang.String zdzyj;
	
	private java.lang.String zdzyjnr;
	
	private java.lang.String zdzqm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date zdzqmsj;
		
	
	private java.lang.String szyj;
	
	private java.lang.String szyjnr;
	
	private java.lang.String szqm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date szqmsj;
		
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date cssj;
		
	
	private java.lang.String csqx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hssj;
		
	
	private java.lang.String ywlcid;
	
	private java.lang.String taskid;
	
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
	
		
	private java.lang.String bllxString;
		
	private java.lang.String zdzyjString;
		
	private java.lang.String szyjString;
		
	private java.lang.String stateString;
		
	private java.lang.String scbzString;
	//字典的get方法生成结束


	public SwjyModel(){
	}
	public SwjyModel(String id) {
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
	public java.lang.String getRybh() {
		return this.rybh;
	}
	
	public void setRybh(java.lang.String value) {
		this.rybh = value;
	}
	public java.lang.String getSnbh() {
		return this.snbh;
	}
	
	public void setSnbh(java.lang.String value) {
		this.snbh = value;
	}
	public java.lang.String getBllx() {
		return this.bllx;
	}
	
	public void setBllx(java.lang.String value) {
		this.bllx = value;
	}
	public java.lang.String getLy() {
		return this.ly;
	}
	
	public void setLy(java.lang.String value) {
		this.ly = value;
	}
	
	public java.util.Date getBlrq() {
		return this.blrq;
	}
	
	public void setBlrq(java.util.Date value) {
		this.blrq = value;
	}
	
	public java.lang.String getJbmc() {
		return this.jbmc;
	}
	
	public void setJbmc(java.lang.String value) {
		this.jbmc = value;
	}
	public java.lang.String getJzyy() {
		return this.jzyy;
	}
	
	public void setJzyy(java.lang.String value) {
		this.jzyy = value;
	}
	public java.lang.String getYsyj() {
		return this.ysyj;
	}
	
	public void setYsyj(java.lang.String value) {
		this.ysyj = value;
	}
	public java.lang.String getYsqm() {
		return this.ysqm;
	}
	
	public void setYsqm(java.lang.String value) {
		this.ysqm = value;
	}
	
	public java.util.Date getQmsj() {
		return this.qmsj;
	}
	
	public void setQmsj(java.util.Date value) {
		this.qmsj = value;
	}
	
	public java.lang.String getZdzyj() {
		return this.zdzyj;
	}
	
	public void setZdzyj(java.lang.String value) {
		this.zdzyj = value;
	}
	public java.lang.String getZdzyjnr() {
		return this.zdzyjnr;
	}
	
	public void setZdzyjnr(java.lang.String value) {
		this.zdzyjnr = value;
	}
	public java.lang.String getZdzqm() {
		return this.zdzqm;
	}
	
	public void setZdzqm(java.lang.String value) {
		this.zdzqm = value;
	}
	
	public java.util.Date getZdzqmsj() {
		return this.zdzqmsj;
	}
	
	public void setZdzqmsj(java.util.Date value) {
		this.zdzqmsj = value;
	}
	
	public java.lang.String getSzyj() {
		return this.szyj;
	}
	
	public void setSzyj(java.lang.String value) {
		this.szyj = value;
	}
	public java.lang.String getSzyjnr() {
		return this.szyjnr;
	}
	
	public void setSzyjnr(java.lang.String value) {
		this.szyjnr = value;
	}
	public java.lang.String getSzqm() {
		return this.szqm;
	}
	
	public void setSzqm(java.lang.String value) {
		this.szqm = value;
	}
	
	public java.util.Date getSzqmsj() {
		return this.szqmsj;
	}
	
	public void setSzqmsj(java.util.Date value) {
		this.szqmsj = value;
	}
	
	
	public java.util.Date getCssj() {
		return this.cssj;
	}
	
	public void setCssj(java.util.Date value) {
		this.cssj = value;
	}
	
	public java.lang.String getCsqx() {
		return this.csqx;
	}
	
	public void setCsqx(java.lang.String value) {
		this.csqx = value;
	}
	
	public java.util.Date getHssj() {
		return this.hssj;
	}
	
	public void setHssj(java.util.Date value) {
		this.hssj = value;
	}
	
	public java.lang.String getYwlcid() {
		return this.ywlcid;
	}
	
	public void setYwlcid(java.lang.String value) {
		this.ywlcid = value;
	}
	public java.lang.String getTaskid() {
		return this.taskid;
	}
	
	public void setTaskid(java.lang.String value) {
		this.taskid = value;
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
	public String getJsbhString() {
		return this.jsbhString;
	}
		
	public java.lang.String getBllxString() {
		return this.bllxString;
	}

	public void setBllxString(java.lang.String value) {
		this.bllxString = value;
	}
		
	public java.lang.String getZdzyjString() {
		return this.zdzyjString;
	}

	public void setZdzyjString(java.lang.String value) {
		this.zdzyjString = value;
	}
		
	public java.lang.String getSzyjString() {
		return this.szyjString;
	}

	public void setSzyjString(java.lang.String value) {
		this.szyjString = value;
	}
		
	public java.lang.String getStateString() {
		return this.stateString;
	}

	public void setStateString(java.lang.String value) {
		this.stateString = value;
	}
		
	public java.lang.String getScbzString() {
		return this.scbzString;
	}

	public void setScbzString(java.lang.String value) {
		this.scbzString = value;
	}
	//字典的get方法生成结束
	 
}

