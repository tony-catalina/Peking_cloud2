/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.model.jls;

import awd.framework.common.model.Model;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class JtjyModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Jtjy";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_TBR = "填表人";
	public static final String ALIAS_TBRQ = "填表日期";
	public static final String ALIAS_SJ = "时间";
	public static final String ALIAS_STATE = "删除状态";
	public static final String ALIAS_CJRS = "参加人数";
	public static final String ALIAS_JYKSSJ = "教育开始时间";
	public static final String ALIAS_JYJSSJ = "教育结束时间";
	public static final String ALIAS_JYR = "主讲人";
	public static final String ALIAS_ZW = "主讲人职务";
	public static final String ALIAS_FW = "教育范围";
	public static final String ALIAS_NR = "教育内容";
	public static final String ALIAS_XS = "教育形式";
	public static final String ALIAS_BJRYFY = "被拘人员反映";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_SCBZ = "上传标志";
	public static final String ALIAS_OPERATOR = "操作人";
	public static final String ALIAS_PZR = "批准人";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";



	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date starttime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date endtime;



	//columns START
	
	private java.lang.String id;


	private java.lang.String rybh;

	private java.lang.String jsbh;

	private java.lang.String tbr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date tbrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date sj;

	private java.lang.String state;

	private java.lang.String cjrs;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jykssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jyjssj;

	private java.lang.String jyr;

	private java.lang.String zw;

	private java.lang.String fw;

	private java.lang.String nr;

	private java.lang.String xs;

	private java.lang.String bjryfy;

	private java.lang.String bz;

	private java.lang.String scbz;

	private java.lang.String operator;

	private java.lang.String pzr;

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

	//字典
	private String stateString;

	public String getStateString() {
		return stateString;
	}


	public JtjyModel(){
	}
	public JtjyModel(String id) {
		this.id = id;
	}


	public void setId(java.lang.String value) {
		this.id = value;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public java.lang.String getRybh() {
		return this.rybh;
	}

	public void setRybh(java.lang.String value) {
		this.rybh = value;
	}
	public java.lang.String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
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


	public java.util.Date getSj() {
		return this.sj;
	}

	public void setSj(java.util.Date value) {
		this.sj = value;
	}

	public java.lang.String getState() {
		return this.state;
	}

	public void setState(java.lang.String value) {
		this.state = value;
	}
	public java.lang.String getCjrs() {
		return this.cjrs;
	}

	public void setCjrs(java.lang.String value) {
		this.cjrs = value;
	}

	public java.util.Date getJykssj() {
		return this.jykssj;
	}

	public void setJykssj(java.util.Date value) {
		this.jykssj = value;
	}


	public java.util.Date getJyjssj() {
		return this.jyjssj;
	}

	public void setJyjssj(java.util.Date value) {
		this.jyjssj = value;
	}

	public java.lang.String getJyr() {
		return this.jyr;
	}

	public void setJyr(java.lang.String value) {
		this.jyr = value;
	}
	public java.lang.String getZw() {
		return this.zw;
	}

	public void setZw(java.lang.String value) {
		this.zw = value;
	}
	public java.lang.String getFw() {
		return this.fw;
	}

	public void setFw(java.lang.String value) {
		this.fw = value;
	}
	public java.lang.String getNr() {
		return this.nr;
	}

	public void setNr(java.lang.String value) {
		this.nr = value;
	}
	public java.lang.String getXs() {
		return this.xs;
	}

	public void setXs(java.lang.String value) {
		this.xs = value;
	}
	public java.lang.String getBjryfy() {
		return this.bjryfy;
	}

	public void setBjryfy(java.lang.String value) {
		this.bjryfy = value;
	}
	public java.lang.String getBz() {
		return this.bz;
	}

	public void setBz(java.lang.String value) {
		this.bz = value;
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
	public java.lang.String getPzr() {
		return this.pzr;
	}

	public void setPzr(java.lang.String value) {
		this.pzr = value;
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

