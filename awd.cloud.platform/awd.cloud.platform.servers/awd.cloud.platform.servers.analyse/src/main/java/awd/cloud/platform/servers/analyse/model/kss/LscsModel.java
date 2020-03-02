/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.model.kss;

import awd.framework.common.model.Model;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class LscsModel implements Model {
	
	//columns START
	
	private String id;


	private String jsbh;

	private String rybh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date blsj;

	private String lrmj;

	private String csyy;
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

	private String sjmj;

	private String ldxm;

	private String ldyj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ldpssj;

	private String wczt;

	private String ywlcid;

	private String psbz;

	private String pastable;

	private String bz;

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

	private String tbjcqk;

	private String wjpjcqk;
	//columns END



	public LscsModel(){
	}
	public LscsModel(String id) {
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
	public String getSjmj() {
		return this.sjmj;
	}

	public void setSjmj(String value) {
		this.sjmj = value;
	}
	public String getLdxm() {
		return this.ldxm;
	}

	public void setLdxm(String value) {
		this.ldxm = value;
	}
	public String getLdyj() {
		return this.ldyj;
	}

	public void setLdyj(String value) {
		this.ldyj = value;
	}

	public java.util.Date getLdpssj() {
		return this.ldpssj;
	}

	public void setLdpssj(java.util.Date value) {
		this.ldpssj = value;
	}

	public String getWczt() {
		return this.wczt;
	}

	public void setWczt(String value) {
		this.wczt = value;
	}
	public String getYwlcid() {
		return this.ywlcid;
	}

	public void setYwlcid(String value) {
		this.ywlcid = value;
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
	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
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

	public String getTbjcqk() {
		return this.tbjcqk;
	}

	public void setTbjcqk(String value) {
		this.tbjcqk = value;
	}
	public String getWjpjcqk() {
		return this.wjpjcqk;
	}

	public void setWjpjcqk(String value) {
		this.wjpjcqk = value;
	}
	 
}

