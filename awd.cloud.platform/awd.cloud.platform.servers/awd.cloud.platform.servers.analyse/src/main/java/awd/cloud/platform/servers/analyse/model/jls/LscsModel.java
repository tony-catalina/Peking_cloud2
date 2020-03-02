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
public class LscsModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Lscs";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_BLSJ = "办理时间";
	public static final String ALIAS_LRMJ = "录入民警";
	public static final String ALIAS_CSYY = "出所原因(LSCS)";
	public static final String ALIAS_LSQX = "离所去向";
	public static final String ALIAS_CSSJ = "出所时间";
	public static final String ALIAS_HSSJ = "回所时间";
	public static final String ALIAS_PZR = "批准人";
	public static final String ALIAS_BADW = "办案单位";
	public static final String ALIAS_DCMJ = "带出民警";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_SCBZ = "上传标志";
	public static final String ALIAS_SJMJ = "收监民警";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_OPERATOR = "操作人";
	public static final String ALIAS_YWSHWJWPQK = "有无伤病或带入物品情况";
	public static final String ALIAS_YCQK = "异常情况";
	public static final String ALIAS_STATE = "删除状态(YWSTATE)";
	public static final String ALIAS_FLAG = "标志";
	public static final String ALIAS_YWLCSYID = "业务流程使用ID";
	public static final String ALIAS_BLLX = "办理类型";
	public static final String ALIAS_PASTABLE = "是否有效(SHFO)";
	public static final String ALIAS_PSBZ = "批示标志(PSBZ)";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_LDYJ = "领导意见";
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


	private String rybh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date blsj;

	private String lrmj;

	private String csyy;

	private String lsqx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date cssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hssj;

	private String pzr;

	private String badw;

	private String dcmj;

	private String bz;

	private String scbz;

	private String sjmj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;

	private String operator;

	private String ywshwjwpqk;

	private String ycqk;

	private String state;

	private String flag;

	private String ywlcsyid;

	private String bllx;

	private String pastable;

	private String psbz;

	private String jsbh;

	private String ldyj;

	private String creator;

	private String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;

	private String csyyString;
	private String stateString;
	private String pastableString;
	private String psbzString;
	//columns END



	public LscsModel(){
	}
	public LscsModel(String id) {
		this.id = id;
	}

	public String getCsyyString() {
		return csyyString;
	}

	public String getStateString() {
		return stateString;
	}

	public String getPastableString() {
		return pastableString;
	}

	public String getPsbzString() {
		return psbzString;
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

	public java.util.Date getBlsj() {
		return this.blsj;
	}

	public void setBlsj(java.util.Date value) {
		this.blsj = value;
	}

	public String getLrmj() {
		return this.lrmj;
	}

	public void setLrmj(String value) {
		this.lrmj = value;
	}
	public String getCsyy() {
		return this.csyy;
	}

	public void setCsyy(String value) {
		this.csyy = value;
	}
	public String getLsqx() {
		return this.lsqx;
	}

	public void setLsqx(String value) {
		this.lsqx = value;
	}

	public java.util.Date getCssj() {
		return this.cssj;
	}

	public void setCssj(java.util.Date value) {
		this.cssj = value;
	}


	public java.util.Date getHssj() {
		return this.hssj;
	}

	public void setHssj(java.util.Date value) {
		this.hssj = value;
	}

	public String getPzr() {
		return this.pzr;
	}

	public void setPzr(String value) {
		this.pzr = value;
	}
	public String getBadw() {
		return this.badw;
	}

	public void setBadw(String value) {
		this.badw = value;
	}
	public String getDcmj() {
		return this.dcmj;
	}

	public void setDcmj(String value) {
		this.dcmj = value;
	}
	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}
	public String getScbz() {
		return this.scbz;
	}

	public void setScbz(String value) {
		this.scbz = value;
	}
	public String getSjmj() {
		return this.sjmj;
	}

	public void setSjmj(String value) {
		this.sjmj = value;
	}

	public java.util.Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String value) {
		this.operator = value;
	}
	public String getYwshwjwpqk() {
		return this.ywshwjwpqk;
	}

	public void setYwshwjwpqk(String value) {
		this.ywshwjwpqk = value;
	}
	public String getYcqk() {
		return this.ycqk;
	}

	public void setYcqk(String value) {
		this.ycqk = value;
	}
	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
	}
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String value) {
		this.flag = value;
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
	public String getPastable() {
		return this.pastable;
	}

	public void setPastable(String value) {
		this.pastable = value;
	}
	public String getPsbz() {
		return this.psbz;
	}

	public void setPsbz(String value) {
		this.psbz = value;
	}
	public String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(String value) {
		this.jsbh = value;
	}
	public String getLdyj() {
		return this.ldyj;
	}

	public void setLdyj(String value) {
		this.ldyj = value;
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

