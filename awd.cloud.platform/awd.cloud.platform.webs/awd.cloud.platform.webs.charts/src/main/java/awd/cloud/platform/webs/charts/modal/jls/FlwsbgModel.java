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
public class FlwsbgModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Flwsbg";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_TBR = "填表人";
	public static final String ALIAS_TBRQ = "填表日期";
	public static final String ALIAS_BGJG = "变更结果";
	public static final String ALIAS_BGDW = "变更单位";
	public static final String ALIAS_BGDWLX = "变更单位类型(DWLX)";
	public static final String ALIAS_YJLQX = "原拘留期限";
	public static final String ALIAS_XJLQX = "现拘留期限";
	public static final String ALIAS_FLWS = "法律文书";
	public static final String ALIAS_FLWSH = "法律文书号";
	public static final String ALIAS_BGSJ = "变更时间";
	public static final String ALIAS_PZR = "批准人";
	public static final String ALIAS_PZSJ = "批准时间";
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
	
	private java.lang.String tbr;
	@JSONField(format = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date tbrq;
		
	
	private java.lang.String bgjg;
	
	private java.lang.String bgdw;
	
	private java.lang.String bgdwlx;
	@JSONField(format = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date yjlqx;
		
	@JSONField(format = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date xjlqx;
		
	
	private java.lang.String flws;
	
	private java.lang.String flwsh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date bgsj;
		
	
	private java.lang.String pzr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date pzsj;
		
	
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
	
		
	private java.lang.String bgdwlxString;
		
	private java.lang.String stateString;
		
	private java.lang.String scbzString;
	//字典的get方法生成结束


	public FlwsbgModel(){
	}
	public FlwsbgModel(String id) {
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
	public java.lang.String getTbr() {
		return this.tbr;
	}
	
	public void setTbr(java.lang.String value) {
		this.tbr = value;
	}
	
	public java.util.Date getTbrq() {
		return this.tbrq;
	}
	
	public void setTbrq(java.util.Date value) {
		this.tbrq = value;
	}
	
	public java.lang.String getBgjg() {
		return this.bgjg;
	}
	
	public void setBgjg(java.lang.String value) {
		this.bgjg = value;
	}
	public java.lang.String getBgdw() {
		return this.bgdw;
	}
	
	public void setBgdw(java.lang.String value) {
		this.bgdw = value;
	}
	public java.lang.String getBgdwlx() {
		return this.bgdwlx;
	}
	
	public void setBgdwlx(java.lang.String value) {
		this.bgdwlx = value;
	}
	
	public java.util.Date getYjlqx() {
		return this.yjlqx;
	}
	
	public void setYjlqx(java.util.Date value) {
		this.yjlqx = value;
	}
	
	
	public java.util.Date getXjlqx() {
		return this.xjlqx;
	}
	
	public void setXjlqx(java.util.Date value) {
		this.xjlqx = value;
	}
	
	public java.lang.String getFlws() {
		return this.flws;
	}
	
	public void setFlws(java.lang.String value) {
		this.flws = value;
	}
	public java.lang.String getFlwsh() {
		return this.flwsh;
	}
	
	public void setFlwsh(java.lang.String value) {
		this.flwsh = value;
	}
	
	public java.util.Date getBgsj() {
		return this.bgsj;
	}
	
	public void setBgsj(java.util.Date value) {
		this.bgsj = value;
	}
	
	public java.lang.String getPzr() {
		return this.pzr;
	}
	
	public void setPzr(java.lang.String value) {
		this.pzr = value;
	}
	
	public java.util.Date getPzsj() {
		return this.pzsj;
	}
	
	public void setPzsj(java.util.Date value) {
		this.pzsj = value;
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
		
	public java.lang.String getBgdwlxString() {
		return this.bgdwlxString;
	}

	public void setBgdwlxString(java.lang.String value) {
		this.bgdwlxString = value;
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

