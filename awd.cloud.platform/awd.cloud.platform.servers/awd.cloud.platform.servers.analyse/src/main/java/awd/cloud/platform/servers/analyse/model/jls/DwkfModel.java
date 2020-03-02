/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.model.jls;

import awd.framework.common.model.Model;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class DwkfModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Dwkf";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_TBR = "填表人";
	public static final String ALIAS_TBRQ = "填表日期";
	public static final String ALIAS_KFSJ = "开放时间";
	public static final String ALIAS_KFLX = "开放类型(DWKFLX)";
	public static final String ALIAS_CGDX = "参观对象";
	public static final String ALIAS_CGRS = "参观人数";
	public static final String ALIAS_DWMC = "单位名称";
	public static final String ALIAS_SQLY = "申请理由";
	public static final String ALIAS_SCBZ = "上传标志";
	public static final String ALIAS_STATE = "删除状态(STATE)";
	public static final String ALIAS_OPERATOR = "操作人(DELETE)";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_BZ = "备注";
	
	//columns START

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date starttime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date endtime;

	
	private String id;


	private String jsbh;

	private String tbr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date tbrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date kfsj;

	private String kflx;

	private String cgdx;

	private String cgrs;

	private String dwmc;

	private String sqly;

	private String scbz;

	private String state;

	private String operator;

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

	private String bz;
	//columns END



	public DwkfModel(){
	}
	public DwkfModel(String id) {
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
	public String getTbr() {
		return this.tbr;
	}

	public void setTbr(String value) {
		this.tbr = value;
	}

	public java.util.Date getTbrq() {
		return this.tbrq;
	}

	public void setTbrq(java.util.Date value) {
		this.tbrq = value;
	}


	public java.util.Date getKfsj() {
		return this.kfsj;
	}

	public void setKfsj(java.util.Date value) {
		this.kfsj = value;
	}

	public String getKflx() {
		return this.kflx;
	}

	public void setKflx(String value) {
		this.kflx = value;
	}
	public String getCgdx() {
		return this.cgdx;
	}

	public void setCgdx(String value) {
		this.cgdx = value;
	}
	public String getCgrs() {
		return this.cgrs;
	}

	public void setCgrs(String value) {
		this.cgrs = value;
	}
	public String getDwmc() {
		return this.dwmc;
	}

	public void setDwmc(String value) {
		this.dwmc = value;
	}
	public String getSqly() {
		return this.sqly;
	}

	public void setSqly(String value) {
		this.sqly = value;
	}
	public String getScbz() {
		return this.scbz;
	}

	public void setScbz(String value) {
		this.scbz = value;
	}
	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
	}
	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String value) {
		this.operator = value;
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

	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}


	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
}

