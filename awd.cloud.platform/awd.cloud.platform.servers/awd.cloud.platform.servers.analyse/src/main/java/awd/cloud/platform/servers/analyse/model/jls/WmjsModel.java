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
public class WmjsModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Wmjs";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_JSH = "拘室号";
	public static final String ALIAS_LY = "理由";
	public static final String ALIAS_DJRQ = "登记日期";
	public static final String ALIAS_RS = "民警总数";
	public static final String ALIAS_JZYJ = "解除警长意见";
	public static final String ALIAS_PSBZ = "批示标识(PSBZ)";
	public static final String ALIAS_PASTABLE = "是否失效(SHFO)";
	public static final String ALIAS_YWLCSYID = "业务流程使用ID";
	public static final String ALIAS_SCBZ = "上传标志";
	public static final String ALIAS_OPERATOR = "操作人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_STATE = "删除状态(YWSTATE）";
	public static final String ALIAS_BLLX = "办理类型(WMJSBLLX)";
	public static final String ALIAS_BLR = "办理人";
	public static final String ALIAS_LDYJ = "领导意见";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_CREATOR = "创建人";
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

	private String jsh;

	private String ly;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date djrq;

	private String rs;

	private String jzyj;

	private String psbz;

	private String pastable;

	private String ywlcsyid;

	private String scbz;

	private String operator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;

	private String state;

	private String bllx;

	private String blr;

	private String ldyj;

	private String bz;

	private String creator;

	private String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
	//columns END
	private String psbzString;
	private String pastableString;
	private String stateString;
	private String bllxString;


	public WmjsModel(){
	}
	public WmjsModel(String id) {
		this.id = id;
	}


	public String getPsbzString() {
		return psbzString;
	}
	public String getPastableString() {
		return pastableString;
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
	public String getJsh() {
		return this.jsh;
	}

	public void setJsh(String value) {
		this.jsh = value;
	}
	public String getLy() {
		return this.ly;
	}

	public void setLy(String value) {
		this.ly = value;
	}

	public java.util.Date getDjrq() {
		return this.djrq;
	}

	public void setDjrq(java.util.Date value) {
		this.djrq = value;
	}

	public String getRs() {
		return this.rs;
	}

	public void setRs(String value) {
		this.rs = value;
	}
	public String getJzyj() {
		return this.jzyj;
	}

	public void setJzyj(String value) {
		this.jzyj = value;
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
	public String getYwlcsyid() {
		return this.ywlcsyid;
	}

	public void setYwlcsyid(String value) {
		this.ywlcsyid = value;
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

	public java.util.Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
	}
	public String getBllx() {
		return this.bllx;
	}

	public void setBllx(String value) {
		this.bllx = value;
	}
	public String getBlr() {
		return this.blr;
	}

	public void setBlr(String value) {
		this.blr = value;
	}
	public String getLdyj() {
		return this.ldyj;
	}

	public void setLdyj(String value) {
		this.ldyj = value;
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

