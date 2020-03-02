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
public class SnjyModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Snjy";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_ZDRQ = "诊断日期";
	public static final String ALIAS_YS = "医生";
	public static final String ALIAS_BQ = "病因";
	public static final String ALIAS_CLQK = "处理情况";
	public static final String ALIAS_XM = "姓名";
	public static final String ALIAS_SCBZ = "上传标志";
	public static final String ALIAS_OPERATOR = "操作人";
	public static final String ALIAS_SNBH = "所内编号";
	public static final String ALIAS_WJLSH = "文件流水号";
	public static final String ALIAS_ZZ = "症状";
	public static final String ALIAS_TZ = "体征";
	public static final String ALIAS_JCJG = "检查结果";
	public static final String ALIAS_ZZJG = "诊治结果";
	public static final String ALIAS_STATE = "删除状态(STATE)";
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


	private String rybh;

	private String jsbh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date zdrq;

	private String ys;

	private String bq;

	private String clqk;

	private String xm;

	private String scbz;

	private String operator;

	private String snbh;

	private String wjlsh;

	private String zz;

	private String tz;

	private String jcjg;

	private String zzjg;

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

	private String stateString;
	//columns END



	public SnjyModel(){
	}
	public SnjyModel(String id) {
		this.id = id;
	}

	public String getStateString() {
		return stateString;
	}

	public void setId(String value) {
		this.id = value;
	}

	public String getId() {
		return this.id;
	}

	public String getRybh() {
		return this.rybh;
	}

	public void setRybh(String value) {
		this.rybh = value;
	}
	public String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(String value) {
		this.jsbh = value;
	}

	public java.util.Date getZdrq() {
		return this.zdrq;
	}

	public void setZdrq(java.util.Date value) {
		this.zdrq = value;
	}

	public String getYs() {
		return this.ys;
	}

	public void setYs(String value) {
		this.ys = value;
	}
	public String getBq() {
		return this.bq;
	}

	public void setBq(String value) {
		this.bq = value;
	}
	public String getClqk() {
		return this.clqk;
	}

	public void setClqk(String value) {
		this.clqk = value;
	}
	public String getXm() {
		return this.xm;
	}

	public void setXm(String value) {
		this.xm = value;
	}
	public String getScbz() {
		return this.scbz;
	}

	public void setScbz(String value) {
		this.scbz = value;
	}
	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String value) {
		this.operator = value;
	}
	public String getSnbh() {
		return this.snbh;
	}

	public void setSnbh(String value) {
		this.snbh = value;
	}
	public String getWjlsh() {
		return this.wjlsh;
	}

	public void setWjlsh(String value) {
		this.wjlsh = value;
	}
	public String getZz() {
		return this.zz;
	}

	public void setZz(String value) {
		this.zz = value;
	}
	public String getTz() {
		return this.tz;
	}

	public void setTz(String value) {
		this.tz = value;
	}
	public String getJcjg() {
		return this.jcjg;
	}

	public void setJcjg(String value) {
		this.jcjg = value;
	}
	public String getZzjg() {
		return this.zzjg;
	}

	public void setZzjg(String value) {
		this.zzjg = value;
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

