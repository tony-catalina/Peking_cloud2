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
public class SwjyModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Swjy";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_LY = "入院/出院诊断";
	public static final String ALIAS_XM = "姓名";
	public static final String ALIAS_BLRQ = "入院/出院日期";
	public static final String ALIAS_YSQM = "医生签名";
	public static final String ALIAS_FY = "费用";
	public static final String ALIAS_PTMJ = "陪同民警";
	public static final String ALIAS_JZYY = "就诊医院";
	public static final String ALIAS_YSYJ = "医生意见";
	public static final String ALIAS_ZLCS = "治疗措施";
	public static final String ALIAS_SPYJ = "审批意见";
	public static final String ALIAS_SCBZ = "上传标志";
	public static final String ALIAS_PSBZ = "批示标识(PSBZ)";
	public static final String ALIAS_OPERATOR = "操作人";
	public static final String ALIAS_STATE = "删除状态(STATE)";
	public static final String ALIAS_PASTABLE = "是否失效";
	public static final String ALIAS_BLLX = "办理类型";
	public static final String ALIAS_SNBH = "户籍地详址";
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

	private String ly;

	private String xm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date blrq;

	private String ysqm;

	private java.math.BigDecimal fy;

	private String ptmj;

	private String jzyy;

	private String ysyj;

	private String zlcs;

	private String spyj;

	private String scbz;

	private String psbz;

	private String operator;

	private String state;

	private String pastable;

	private String bllx;

	private String snbh;

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

	private String psbzString;
	private String stateString;
	private String bllxString;
	//columns END



	public SwjyModel(){
	}
	public SwjyModel(String id) {
		this.id = id;
	}

	public String getPsbzString() {
		return psbzString;
	}

	public String getStateString() {
		return stateString;
	}

	public String getBllxString() {
		return bllxString;
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
	public String getXm() {
		return this.xm;
	}

	public void setXm(String value) {
		this.xm = value;
	}

	public java.util.Date getBlrq() {
		return this.blrq;
	}

	public void setBlrq(java.util.Date value) {
		this.blrq = value;
	}

	public String getYsqm() {
		return this.ysqm;
	}

	public void setYsqm(String value) {
		this.ysqm = value;
	}
	public java.math.BigDecimal getFy() {
		return this.fy;
	}

	public void setFy(java.math.BigDecimal value) {
		this.fy = value;
	}
	public String getPtmj() {
		return this.ptmj;
	}

	public void setPtmj(String value) {
		this.ptmj = value;
	}
	public String getJzyy() {
		return this.jzyy;
	}

	public void setJzyy(String value) {
		this.jzyy = value;
	}
	public String getYsyj() {
		return this.ysyj;
	}

	public void setYsyj(String value) {
		this.ysyj = value;
	}
	public String getZlcs() {
		return this.zlcs;
	}

	public void setZlcs(String value) {
		this.zlcs = value;
	}
	public String getSpyj() {
		return this.spyj;
	}

	public void setSpyj(String value) {
		this.spyj = value;
	}
	public String getScbz() {
		return this.scbz;
	}

	public void setScbz(String value) {
		this.scbz = value;
	}
	public String getPsbz() {
		return this.psbz;
	}

	public void setPsbz(String value) {
		this.psbz = value;
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
	public String getPastable() {
		return this.pastable;
	}

	public void setPastable(String value) {
		this.pastable = value;
	}
	public String getBllx() {
		return this.bllx;
	}

	public void setBllx(String value) {
		this.bllx = value;
	}
	public String getSnbh() {
		return this.snbh;
	}

	public void setSnbh(String value) {
		this.snbh = value;
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

