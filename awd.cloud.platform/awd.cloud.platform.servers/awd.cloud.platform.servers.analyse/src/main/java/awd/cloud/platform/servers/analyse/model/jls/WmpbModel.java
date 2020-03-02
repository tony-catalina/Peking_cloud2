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
public class WmpbModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Wmpb";
	public static final String ALIAS_ZJ = "主键";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_TBR = "填表人";
	public static final String ALIAS_TBRQ = "填表日期";
	public static final String ALIAS_PXSJ = "评选时间";
	public static final String ALIAS_GJMJ = "管教民警";
	public static final String ALIAS_PSR = "评审人";
	public static final String ALIAS_SFPDWMGRBZ = "是否佩戴“文明个人”标志(SHFO)";
	public static final String ALIAS_SFXGWMJSBZ = "是否悬挂“文明拘室”标志(SHFO)";
	public static final String ALIAS_PXYY = "评选原因";
	public static final String ALIAS_JLCS = "奖励措施";
	public static final String ALIAS_JSH = "拘室号";
	public static final String ALIAS_STATE = "删除状态(YWSTATE)";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_OPERATOR = "操作人";
	public static final String ALIAS_OPERATETIME = "操作时间";
	public static final String ALIAS_QXSJ = "取消时间";
	public static final String ALIAS_QXYY = "取消原因";
	public static final String ALIAS_SPR = "审批人";
	public static final String ALIAS_CLFS = "处理方式";
	public static final String ALIAS_DWDM = "单位代码";
	public static final String ALIAS_WMGR = "文明个人";
	public static final String ALIAS_WMJS = "文明拘室";
	
	//columns START

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date starttime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date endtime;

	private String zj;

	private String id;


	private String rybh;

	private String jsbh;

	private String tbr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date tbrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date pxsj;

	private String gjmj;

	private String psr;

	private String sfpdwmgrbz;

	private String sfxgwmjsbz;

	private String pxyy;

	private String jlcs;

	private String jsh;

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

	private String operator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date operatetime;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date qxsj;

	private String qxyy;

	private String spr;

	private String clfs;

	private String dwdm;

	private String wmgr;

	private String wmjs;
	//columns END
	private String stateString;
    private String sfpdwmgrbzString;
    private String sfxgwmjsbzString;


	public WmpbModel(){
	}
	public WmpbModel(String id) {
		this.id = id;
	}



	public String getStateString() {
		return stateString;
	}
	public String getSfpdwmgrbzString() {
		return sfpdwmgrbzString;
	}
	public String getSfxgwmjsbzString() {
		return sfxgwmjsbzString;
	}
	public void setId(String value) {
		this.id = value;
	}

	public String getId() {
		return this.id;
	}

	public String getZj() {
		return this.zj;
	}

	public void setZj(String value) {
		this.zj = value;
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


	public java.util.Date getPxsj() {
		return this.pxsj;
	}

	public void setPxsj(java.util.Date value) {
		this.pxsj = value;
	}

	public String getGjmj() {
		return this.gjmj;
	}

	public void setGjmj(String value) {
		this.gjmj = value;
	}
	public String getPsr() {
		return this.psr;
	}

	public void setPsr(String value) {
		this.psr = value;
	}
	public String getSfpdwmgrbz() {
		return this.sfpdwmgrbz;
	}

	public void setSfpdwmgrbz(String value) {
		this.sfpdwmgrbz = value;
	}
	public String getSfxgwmjsbz() {
		return this.sfxgwmjsbz;
	}

	public void setSfxgwmjsbz(String value) {
		this.sfxgwmjsbz = value;
	}
	public String getPxyy() {
		return this.pxyy;
	}

	public void setPxyy(String value) {
		this.pxyy = value;
	}
	public String getJlcs() {
		return this.jlcs;
	}

	public void setJlcs(String value) {
		this.jlcs = value;
	}
	public String getJsh() {
		return this.jsh;
	}

	public void setJsh(String value) {
		this.jsh = value;
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
	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String value) {
		this.operator = value;
	}

	public java.util.Date getOperatetime() {
		return this.operatetime;
	}

	public void setOperatetime(java.util.Date value) {
		this.operatetime = value;
	}


	public java.util.Date getQxsj() {
		return this.qxsj;
	}

	public void setQxsj(java.util.Date value) {
		this.qxsj = value;
	}

	public String getQxyy() {
		return this.qxyy;
	}

	public void setQxyy(String value) {
		this.qxyy = value;
	}
	public String getSpr() {
		return this.spr;
	}

	public void setSpr(String value) {
		this.spr = value;
	}
	public String getClfs() {
		return this.clfs;
	}

	public void setClfs(String value) {
		this.clfs = value;
	}
	public String getDwdm() {
		return this.dwdm;
	}

	public void setDwdm(String value) {
		this.dwdm = value;
	}
	public String getWmgr() {
		return this.wmgr;
	}

	public void setWmgr(String value) {
		this.wmgr = value;
	}
	public String getWmjs() {
		return this.wmjs;
	}

	public void setWmjs(String value) {
		this.wmjs = value;
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

