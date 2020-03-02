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
public class FxpgModel implements Model{
	public static final String TABLE_ALIAS = "Fxpg";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_YFXDJ = "原风险等级(FXDJ)";
	public static final String ALIAS_XFXDJ = "现风险等级(FXDJ)";
	public static final String ALIAS_PGLX = "评估类型";
	public static final String ALIAS_FXQK = "风险指标(LKLY)";
	public static final String ALIAS_PGR = "评估人";
	public static final String ALIAS_PGRQ = "评估日期";
	public static final String ALIAS_TZLY = "调整理由";
	public static final String ALIAS_LKCS = "列控措施";
	public static final String ALIAS_SZYJ = "领导意见(PSBZ)";
	public static final String ALIAS_SZYJNR = "领导意见内容";
	public static final String ALIAS_SZQM = "领导签名";
	public static final String ALIAS_SZPSSJ = "领导批示时间";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_YWLCID = "业务流程ID";
	public static final String ALIAS_TASKID = "业务进程ID";
	public static final String ALIAS_STATE = "删除状态(YWSTATE)";
	public static final String ALIAS_SCBZ = "上传标志(SHFO)";
	public static final String ALIAS_OPERATOR = "操作人";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	private String id;
	private String jsbh;
	private String rybh;
	private String yfxdj;
	private String xfxdj;
	private String pglx;
	private String fxqk;
	private String pgr;
	@JSONField(
			format = "yyyy-MM-dd"
	)
	@JsonFormat(
			pattern = "yyyy-MM-dd",
			timezone = "GMT+8"
	)
	@DateTimeFormat(
			pattern = "yyyy-MM-dd"
	)
	private Date pgrq;
	private String tzly;
	private String lkcs;
	private String szyj;
	private String szyjnr;
	private String szqm;
	@JSONField(
			format = "yyyy-MM-dd HH:mm:ss"
	)
	@JsonFormat(
			pattern = "yyyy-MM-dd HH:mm:ss",
			timezone = "GMT+8"
	)
	@DateTimeFormat(
			pattern = "yyyy-MM-dd HH:mm:ss"
	)
	private Date szpssj;
	private String bz;
	private String ywlcid;
	private String taskid;
	private String state;
	private String scbz;
	private String operator;
	private String creator;
	@JSONField(
			format = "yyyy-MM-dd HH:mm:ss"
	)
	@JsonFormat(
			pattern = "yyyy-MM-dd HH:mm:ss",
			timezone = "GMT+8"
	)
	@DateTimeFormat(
			pattern = "yyyy-MM-dd HH:mm:ss"
	)
	private Date createtime;
	private String updator;
	@JSONField(
			format = "yyyy-MM-dd HH:mm:ss"
	)
	@JsonFormat(
			pattern = "yyyy-MM-dd HH:mm:ss",
			timezone = "GMT+8"
	)
	@DateTimeFormat(
			pattern = "yyyy-MM-dd HH:mm:ss"
	)
	private Date updatetime;
	private String jsbhString;
	private String yfxdjString;
	private String xfxdjString;
	private String fxqkString;
	private String szyjString;
	private String stateString;
	private String scbzString;

	public FxpgModel() {
	}

	public FxpgModel(String id) {
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

	public String getYfxdj() {
		return this.yfxdj;
	}

	public void setYfxdj(String value) {
		this.yfxdj = value;
	}

	public String getXfxdj() {
		return this.xfxdj;
	}

	public void setXfxdj(String value) {
		this.xfxdj = value;
	}

	public String getPglx() {
		return this.pglx;
	}

	public void setPglx(String value) {
		this.pglx = value;
	}

	public String getFxqk() {
		return this.fxqk;
	}

	public void setFxqk(String value) {
		this.fxqk = value;
	}

	public String getPgr() {
		return this.pgr;
	}

	public void setPgr(String value) {
		this.pgr = value;
	}

	public Date getPgrq() {
		return this.pgrq;
	}

	public void setPgrq(Date value) {
		this.pgrq = value;
	}

	public String getTzly() {
		return this.tzly;
	}

	public void setTzly(String value) {
		this.tzly = value;
	}

	public String getLkcs() {
		return this.lkcs;
	}

	public void setLkcs(String value) {
		this.lkcs = value;
	}

	public String getSzyj() {
		return this.szyj;
	}

	public void setSzyj(String value) {
		this.szyj = value;
	}

	public String getSzyjnr() {
		return this.szyjnr;
	}

	public void setSzyjnr(String value) {
		this.szyjnr = value;
	}

	public String getSzqm() {
		return this.szqm;
	}

	public void setSzqm(String value) {
		this.szqm = value;
	}

	public Date getSzpssj() {
		return this.szpssj;
	}

	public void setSzpssj(Date value) {
		this.szpssj = value;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}

	public String getYwlcid() {
		return this.ywlcid;
	}

	public void setYwlcid(String value) {
		this.ywlcid = value;
	}

	public String getTaskid() {
		return this.taskid;
	}

	public void setTaskid(String value) {
		this.taskid = value;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
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

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String value) {
		this.creator = value;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date value) {
		this.createtime = value;
	}

	public String getUpdator() {
		return this.updator;
	}

	public void setUpdator(String value) {
		this.updator = value;
	}

	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date value) {
		this.updatetime = value;
	}

	public String getJsbhString() {
		return this.jsbhString;
	}

	public String getYfxdjString() {
		return this.yfxdjString;
	}

	public String getXfxdjString() {
		return this.xfxdjString;
	}

	public String getFxqkString() {
		return this.fxqkString;
	}

	public String getSzyjString() {
		return this.szyjString;
	}

	public String getStateString() {
		return this.stateString;
	}

	public String getScbzString() {
		return this.scbzString;
	}
}

