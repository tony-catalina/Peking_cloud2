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


public class ZdryModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Zdry";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_TBR = "填表人";
	public static final String ALIAS_TBRQ = "填表日期";
	public static final String ALIAS_CPR = "呈批人";
	public static final String ALIAS_CPSJ = "呈批时间";
	public static final String ALIAS_XM = "姓名";
	public static final String ALIAS_JSH = "监室号";
	public static final String ALIAS_JGCS = "监管措施";
	public static final String ALIAS_SZRQ = "设置日期";
	public static final String ALIAS_RYGLLB = "人员管理类别(RYGLLB)";
	public static final String ALIAS_ZDZYJ = "总队长意见";
	public static final String ALIAS_ZDJZYJ = "领导意见";
	public static final String ALIAS_LY = "理由";
	public static final String ALIAS_QZCS = "强制措施";
	public static final String ALIAS_LDYJ = "领导意见";
	public static final String ALIAS_SCBZ = "上传标志";
	public static final String ALIAS_STATE = "删除状态(YWSTATE)";
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

	private String rybh;

	private String tbr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date tbrq;

	private String cpr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date cpsj;

	private String xm;

	private String jsh;

	private String jgcs;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date szrq;

	private String rygllb;

	private String zdzyj;

	private String zdjzyj;

	private String ly;

	private String qzcs;

	private String ldyj;

	private String scbz;

	private String state;

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



	public ZdryModel(){
	}
	public ZdryModel(String id) {
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
	public String getRybh() {
		return this.rybh;
	}

	public void setRybh(String value) {
		this.rybh = value;
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

	public String getCpr() {
		return this.cpr;
	}

	public void setCpr(String value) {
		this.cpr = value;
	}

	public java.util.Date getCpsj() {
		return this.cpsj;
	}

	public void setCpsj(java.util.Date value) {
		this.cpsj = value;
	}

	public String getXm() {
		return this.xm;
	}

	public void setXm(String value) {
		this.xm = value;
	}
	public String getJsh() {
		return this.jsh;
	}

	public void setJsh(String value) {
		this.jsh = value;
	}
	public String getJgcs() {
		return this.jgcs;
	}

	public void setJgcs(String value) {
		this.jgcs = value;
	}

	public java.util.Date getSzrq() {
		return this.szrq;
	}

	public void setSzrq(java.util.Date value) {
		this.szrq = value;
	}

	public String getRygllb() {
		return this.rygllb;
	}

	public void setRygllb(String value) {
		this.rygllb = value;
	}
	public String getZdzyj() {
		return this.zdzyj;
	}

	public void setZdzyj(String value) {
		this.zdzyj = value;
	}
	public String getZdjzyj() {
		return this.zdjzyj;
	}

	public void setZdjzyj(String value) {
		this.zdjzyj = value;
	}
	public String getLy() {
		return this.ly;
	}

	public void setLy(String value) {
		this.ly = value;
	}
	public String getQzcs() {
		return this.qzcs;
	}

	public void setQzcs(String value) {
		this.qzcs = value;
	}
	public String getLdyj() {
		return this.ldyj;
	}

	public void setLdyj(String value) {
		this.ldyj = value;
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

