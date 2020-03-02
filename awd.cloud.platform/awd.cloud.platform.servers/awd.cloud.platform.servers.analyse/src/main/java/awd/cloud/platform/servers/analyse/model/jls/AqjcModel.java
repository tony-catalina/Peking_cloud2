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
public class AqjcModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Aqjc";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_SJ = "时间";
	public static final String ALIAS_FZR = "负责人";
	public static final String ALIAS_FW = "检查范围";
	public static final String ALIAS_NR = "检查内容";
	public static final String ALIAS_RS = "检查人数";
	public static final String ALIAS_JCJG = "检查结果";
	public static final String ALIAS_CLJG = "处理结果";
	public static final String ALIAS_PSBZ = "批示标志";
	public static final String ALIAS_WJCJRS = "武警参加人数";
	public static final String ALIAS_JSKCJRS = "监所科参加人数";
	public static final String ALIAS_ZSJCRY = "驻所检查人员";
	public static final String ALIAS_JCXS = "检查类型";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_SPYJ = "审批意见";
	public static final String ALIAS_BLLX = "办理类型";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_STATE = "删除状态";
	
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
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date sj;

	private String fzr;

	private String fw;

	private String nr;

	private String rs;

	private String jcjg;

	private String cljg;

	private String psbz;

	private String wjcjrs;

	private String jskcjrs;

	private String zsjcry;

	private String jcxs;

	private String bz;

	private String spyj;

	private String bllx;

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

	private String state;
	//columns END


	//字典
	private String fwString;
	private String cljgString;
	private String psbzString;
	private String jcxsString;
	private String bllxString;
	private String stateString;

	public String getFwString() {
		return fwString;
	}

	public String getCljgString() {
		return cljgString;
	}

	public String getPsbzString() {
		return psbzString;
	}

	public String getJcxsString() {
		return jcxsString;
	}

	public String getBllxString() {
		return bllxString;
	}

	public String getStateString() {
		return stateString;
	}

	public AqjcModel(){
	}
	public AqjcModel(String id) {
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

	public java.util.Date getSj() {
		return this.sj;
	}

	public void setSj(java.util.Date value) {
		this.sj = value;
	}

	public String getFzr() {
		return this.fzr;
	}

	public void setFzr(String value) {
		this.fzr = value;
	}
	public String getFw() {
		return this.fw;
	}

	public void setFw(String value) {
		this.fw = value;
	}
	public String getNr() {
		return this.nr;
	}

	public void setNr(String value) {
		this.nr = value;
	}
	public String getRs() {
		return this.rs;
	}

	public void setRs(String value) {
		this.rs = value;
	}
	public String getJcjg() {
		return this.jcjg;
	}

	public void setJcjg(String value) {
		this.jcjg = value;
	}
	public String getCljg() {
		return this.cljg;
	}

	public void setCljg(String value) {
		this.cljg = value;
	}
	public String getPsbz() {
		return this.psbz;
	}

	public void setPsbz(String value) {
		this.psbz = value;
	}
	public String getWjcjrs() {
		return this.wjcjrs;
	}

	public void setWjcjrs(String value) {
		this.wjcjrs = value;
	}
	public String getJskcjrs() {
		return this.jskcjrs;
	}

	public void setJskcjrs(String value) {
		this.jskcjrs = value;
	}
	public String getZsjcry() {
		return this.zsjcry;
	}

	public void setZsjcry(String value) {
		this.zsjcry = value;
	}
	public String getJcxs() {
		return this.jcxs;
	}

	public void setJcxs(String value) {
		this.jcxs = value;
	}
	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}
	public String getSpyj() {
		return this.spyj;
	}

	public void setSpyj(String value) {
		this.spyj = value;
	}
	public String getBllx() {
		return this.bllx;
	}

	public void setBllx(String value) {
		this.bllx = value;
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

	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
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

