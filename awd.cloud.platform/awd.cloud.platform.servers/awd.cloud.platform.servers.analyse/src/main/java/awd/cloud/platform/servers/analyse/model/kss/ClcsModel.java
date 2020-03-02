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

import java.util.Date;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClcsModel implements Model {
	
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

	private String csyy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date cssj;

	private String csqx;

	private String pzdw;

	private String pzr;

	private String blr;

	private String dbr;

	private String dbdw;

	private String jddw;

	private String zcdwszd;

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

	private String qzcs;

	private String cswsh;
	//columns END



	public ClcsModel(){
	}
	public ClcsModel(String id) {
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

	public String getCsqx() {
		return this.csqx;
	}

	public void setCsqx(String value) {
		this.csqx = value;
	}
	public String getPzdw() {
		return this.pzdw;
	}

	public void setPzdw(String value) {
		this.pzdw = value;
	}
	public String getPzr() {
		return this.pzr;
	}

	public void setPzr(String value) {
		this.pzr = value;
	}
	public String getBlr() {
		return this.blr;
	}

	public void setBlr(String value) {
		this.blr = value;
	}
	public String getDbr() {
		return this.dbr;
	}

	public void setDbr(String value) {
		this.dbr = value;
	}
	public String getDbdw() {
		return this.dbdw;
	}

	public void setDbdw(String value) {
		this.dbdw = value;
	}
	public String getJddw() {
		return this.jddw;
	}

	public void setJddw(String value) {
		this.jddw = value;
	}
	public String getZcdwszd() {
		return this.zcdwszd;
	}

	public void setZcdwszd(String value) {
		this.zcdwszd = value;
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

	public String getQzcs() {
		return this.qzcs;
	}

	public void setQzcs(String value) {
		this.qzcs = value;
	}
	public String getCswsh() {
		return this.cswsh;
	}

	public void setCswsh(String value) {
		this.cswsh = value;
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

