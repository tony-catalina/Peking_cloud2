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
public class YgryModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Ygry";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_TBR = "填表人";
	public static final String ALIAS_TBRQ = "填表日期";
	public static final String ALIAS_YGLY = "严管理由";
	public static final String ALIAS_KGCS = "看管措施";
	public static final String ALIAS_QZCS = "强制措施";
	public static final String ALIAS_CPR = "呈批人";
	public static final String ALIAS_CPSJ = "呈批时间";
	public static final String ALIAS_KSSJ = "严管开始时间";
	public static final String ALIAS_JSSJ = "严管结束时间";
	public static final String ALIAS_SZYJ = "所长意见";
	public static final String ALIAS_SZYJNR = "所长意见内容";
	public static final String ALIAS_LY = "理由";
	public static final String ALIAS_KZCS = "控制措施";
	public static final String ALIAS_BLR = "办理人";
	public static final String ALIAS_BLRQ = "办理日期";
	public static final String ALIAS_SZQM = "所长签名";
	public static final String ALIAS_QMSJ = "签名时间";
	public static final String ALIAS_JCLY = "解除理由";
	public static final String ALIAS_JCCPR = "解除呈批人";
	public static final String ALIAS_JCRQ = "解除日期";
	public static final String ALIAS_STATE = "删除状态(YWSTATE)";
	public static final String ALIAS_YWLCSYID = "业务流程使用id";
	public static final String ALIAS_BLLX = "办理类型(BLLX)";
	public static final String ALIAS_LDYJ = "领导意见（所长意见）";
	public static final String ALIAS_ZDZYJ = "中队长意见（警长意见）";
	public static final String ALIAS_PSBZ = "批示标志(PSBZ)";
	public static final String ALIAS_PASTABLE = "有效期";
	public static final String ALIAS_SCBZ = "上传标志";
	public static final String ALIAS_OPERATOR = "操作人";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_SAVETIME = "保存时间";
	public static final String ALIAS_SAVEUSER = "保存人";
	
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

	private String tbr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date tbrq;

	private String ygly;

	private String kgcs;

	private String qzcs;

	private String cpr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date cpsj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date kssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jssj;

	private String szyj;

	private String szyjnr;

	private String ly;

	private String kzcs;

	private String blr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date blrq;

	private String szqm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date qmsj;

	private String jcly;

	private String jccpr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jcrq;

	private String state;

	private String ywlcsyid;

	private String bllx;

	private String ldyj;

	private String zdzyj;

	private String psbz;

	private String pastable;

	private String scbz;

	private String operator;

	private String bz;

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
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date savetime;

	private String saveuser;
	//columns END
	private String psbzString;
	private String bllxString;
	private String stateString;


	public YgryModel(){
	}
	public YgryModel(String id) {
		this.id = id;
	}


	public String getPsbzString() {
		return psbzString;
	}
	public String getBllxString() {
		return bllxString;
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

	public String getYgly() {
		return this.ygly;
	}

	public void setYgly(String value) {
		this.ygly = value;
	}
	public String getKgcs() {
		return this.kgcs;
	}

	public void setKgcs(String value) {
		this.kgcs = value;
	}
	public String getQzcs() {
		return this.qzcs;
	}

	public void setQzcs(String value) {
		this.qzcs = value;
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


	public java.util.Date getKssj() {
		return this.kssj;
	}

	public void setKssj(java.util.Date value) {
		this.kssj = value;
	}


	public java.util.Date getJssj() {
		return this.jssj;
	}

	public void setJssj(java.util.Date value) {
		this.jssj = value;
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
	public String getLy() {
		return this.ly;
	}

	public void setLy(String value) {
		this.ly = value;
	}
	public String getKzcs() {
		return this.kzcs;
	}

	public void setKzcs(String value) {
		this.kzcs = value;
	}
	public String getBlr() {
		return this.blr;
	}

	public void setBlr(String value) {
		this.blr = value;
	}

	public java.util.Date getBlrq() {
		return this.blrq;
	}

	public void setBlrq(java.util.Date value) {
		this.blrq = value;
	}

	public String getSzqm() {
		return this.szqm;
	}

	public void setSzqm(String value) {
		this.szqm = value;
	}

	public java.util.Date getQmsj() {
		return this.qmsj;
	}

	public void setQmsj(java.util.Date value) {
		this.qmsj = value;
	}

	public String getJcly() {
		return this.jcly;
	}

	public void setJcly(String value) {
		this.jcly = value;
	}
	public String getJccpr() {
		return this.jccpr;
	}

	public void setJccpr(String value) {
		this.jccpr = value;
	}

	public java.util.Date getJcrq() {
		return this.jcrq;
	}

	public void setJcrq(java.util.Date value) {
		this.jcrq = value;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
	}
	public String getYwlcsyid() {
		return this.ywlcsyid;
	}

	public void setYwlcsyid(String value) {
		this.ywlcsyid = value;
	}
	public String getBllx() {
		return this.bllx;
	}

	public void setBllx(String value) {
		this.bllx = value;
	}
	public String getLdyj() {
		return this.ldyj;
	}

	public void setLdyj(String value) {
		this.ldyj = value;
	}
	public String getZdzyj() {
		return this.zdzyj;
	}

	public void setZdzyj(String value) {
		this.zdzyj = value;
	}
	public String getPsbz() {
		return this.psbz;
	}

	public void setPsbz(String value) {
		this.psbz = value;
	}
	public String getPastable() {
		return this.pastable;
	}

	public void setPastable(String value) {
		this.pastable = value;
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
	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
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


	public java.util.Date getSavetime() {
		return this.savetime;
	}

	public void setSavetime(java.util.Date value) {
		this.savetime = value;
	}

	public String getSaveuser() {
		return this.saveuser;
	}

	public void setSaveuser(String value) {
		this.saveuser = value;
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

