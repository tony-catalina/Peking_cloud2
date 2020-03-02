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
public class JjModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Jj";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_JSBH = "拘室编号";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_LY = "来源";
	public static final String ALIAS_XJMC = "戒具名称";
	public static final String ALIAS_JJSL = "戒具数量";
	public static final String ALIAS_BLR = "办理人";
	public static final String ALIAS_BLRQ = "办理日期";
	public static final String ALIAS_KSSJ = "开始时间";
	public static final String ALIAS_SYTS = "使用天数";
	public static final String ALIAS_JSSJ = "结束时间";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_PSBZ = "批示标识  0 未批示  1 批示成功  2 批示失败";
	public static final String ALIAS_BLLX = "办理类型  1 设置  2 撤销";
	public static final String ALIAS_YWLCSYID = "业务流程ID";
	public static final String ALIAS_PASTABLE = "过期标志";
	public static final String ALIAS_SCBZ = "上传标志";
	public static final String ALIAS_OPERATOR = "操作人";
	public static final String ALIAS_STATE = "删除状态";
	public static final String ALIAS_YSYJ = "医生意见";
	public static final String ALIAS_ZDZYJ = "警长意见";
	public static final String ALIAS_LDYJ = "所长意见";
	public static final String ALIAS_LSH = "流水号";
	public static final String ALIAS_SAVETIME = "保存时间";
	public static final String ALIAS_SAVEUSER = "保存人";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	
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

	private String ly;

	private String xjmc;

	private Byte jjsl;

	private String blr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date blrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date kssj;

	private Short syts;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jssj;

	private String bz;

	private String psbz;

	private String bllx;

	private String ywlcsyid;

	private String pastable;

	private String scbz;

	private String operator;

	private String state;

	private String ysyj;

	private String zdzyj;

	private String ldyj;

	private String lsh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date savetime;

	private String saveuser;

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
	//columns END

	//字典
	private String bllxString;
	private String stateString;

	public String getBllxString() {
		return bllxString;
	}

	public String getStateString() {
		return stateString;
	}

	public JjModel(){
	}
	public JjModel(String id) {
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
	public String getLy() {
		return this.ly;
	}

	public void setLy(String value) {
		this.ly = value;
	}
	public String getXjmc() {
		return this.xjmc;
	}

	public void setXjmc(String value) {
		this.xjmc = value;
	}
	public Byte getJjsl() {
		return this.jjsl;
	}

	public void setJjsl(Byte value) {
		this.jjsl = value;
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


	public java.util.Date getKssj() {
		return this.kssj;
	}

	public void setKssj(java.util.Date value) {
		this.kssj = value;
	}

	public Short getSyts() {
		return this.syts;
	}

	public void setSyts(Short value) {
		this.syts = value;
	}

	public java.util.Date getJssj() {
		return this.jssj;
	}

	public void setJssj(java.util.Date value) {
		this.jssj = value;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}
	public String getPsbz() {
		return this.psbz;
	}

	public void setPsbz(String value) {
		this.psbz = value;
	}
	public String getBllx() {
		return this.bllx;
	}

	public void setBllx(String value) {
		this.bllx = value;
	}
	public String getYwlcsyid() {
		return this.ywlcsyid;
	}

	public void setYwlcsyid(String value) {
		this.ywlcsyid = value;
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
	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
	}
	public String getYsyj() {
		return this.ysyj;
	}

	public void setYsyj(String value) {
		this.ysyj = value;
	}
	public String getZdzyj() {
		return this.zdzyj;
	}

	public void setZdzyj(String value) {
		this.zdzyj = value;
	}
	public String getLdyj() {
		return this.ldyj;
	}

	public void setLdyj(String value) {
		this.ldyj = value;
	}
	public String getLsh() {
		return this.lsh;
	}

	public void setLsh(String value) {
		this.lsh = value;
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

