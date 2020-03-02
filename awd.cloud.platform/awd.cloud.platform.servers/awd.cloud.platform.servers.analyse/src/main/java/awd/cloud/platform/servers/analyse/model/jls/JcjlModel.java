/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.model.jls;

import awd.framework.common.model.Model;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class JcjlModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Jcjl";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_JSBH = "拘所编号";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_JCRQ = "奖惩日期";
	public static final String ALIAS_GJMJ = "管教民警";
	public static final String ALIAS_LY = "奖惩原因";
	public static final String ALIAS_GJYJ = "管教意见";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_SCBZ = "上传标志";
	public static final String ALIAS_OPERATOR = "操作人";
	public static final String ALIAS_STATE = "删除状态";
	public static final String ALIAS_JKF = "加扣分";
	public static final String ALIAS_LYDM = "理由代码";
	public static final String ALIAS_JCXS = "奖惩形式(JCXS)";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_ZXR = "执行人";
	public static final String ALIAS_SLDSPRQ = "所领导审批日期";
	public static final String ALIAS_CPGJ = "呈批管教";
	public static final String ALIAS_CPRQ = "呈批日期";


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date starttime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date endtime;

	private String cpgj;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date cprq;

	//columns START

	private String zxr;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date sldsprq;

	private String id;


	private String jsbh;

	private String rybh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jcrq;

	private String gjmj;

	private String ly;

	private String gjyj;

	private String bz;

	private String scbz;

	private String operator;

	private String state;

	private java.math.BigDecimal jkf;

	private String lydm;

	private String jcxs;

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

	//字典
	private String jcxsString;
	private String stateString;

	public String getJcxsString() {
		return jcxsString;
	}

	public String getStateString() {
		return stateString;
	}

	//columns END

	public JcjlModel(){
	}
	public JcjlModel(String id) {
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

	public java.util.Date getJcrq() {
		return this.jcrq;
	}

	public void setJcrq(java.util.Date value) {
		this.jcrq = value;
	}

	public String getGjmj() {
		return this.gjmj;
	}

	public void setGjmj(String value) {
		this.gjmj = value;
	}
	public String getLy() {
		return this.ly;
	}

	public void setLy(String value) {
		this.ly = value;
	}
	public String getGjyj() {
		return this.gjyj;
	}

	public void setGjyj(String value) {
		this.gjyj = value;
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
	public java.math.BigDecimal getJkf() {
		return this.jkf;
	}
	public void setJkf(java.math.BigDecimal value) {
		this.jkf = value;
	}

	public String getLydm() {
		return this.lydm;
	}

	public void setLydm(String value) {
		this.lydm = value;
	}
	public String getJcxs() {
		return this.jcxs;
	}

	public void setJcxs(String value) {
		this.jcxs = value;
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


	public String getZxr() {
		return zxr;
	}

	public void setZxr(String zxr) {
		this.zxr = zxr;
	}

	public Date getSldsprq() {
		return sldsprq;
	}

	public void setSldsprq(Date sldsprq) {
		this.sldsprq = sldsprq;
	}


	public String getCpgj() {
		return cpgj;
	}

	public void setCpgj(String cpgj) {
		this.cpgj = cpgj;
	}

	public Date getCprq() {
		return cprq;
	}

	public void setCprq(Date cprq) {
		this.cprq = cprq;
	}
}

