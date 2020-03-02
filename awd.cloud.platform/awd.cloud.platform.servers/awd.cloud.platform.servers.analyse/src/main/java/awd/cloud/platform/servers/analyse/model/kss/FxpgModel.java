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
public class FxpgModel implements Model {

	
	//columns START
	
	private String id;


	private String jsbh;

	private String rybh;

	private String jtbg;

	private String ffrz;

	private String ddcjdjl;

	private String dddhyzk;

	private String dqjtqk;

	private String dqgqsjqk;

	private String dqshsczk;

	private String dxdqxtqk;

	private String dxdqstzk;

	private String ddzsjsycqk;

	private String dazdxjkf;

	private String sjyz;

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

	private String pglx;

	private String yfxdj;

	private String xfxdj;

	private String yfxzb;

	private String xfxzb;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date pgsj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ypgsj;
	//columns END



	public FxpgModel(){
	}
	public FxpgModel(String id) {
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
	public String getJtbg() {
		return this.jtbg;
	}

	public void setJtbg(String value) {
		this.jtbg = value;
	}
	public String getFfrz() {
		return this.ffrz;
	}

	public void setFfrz(String value) {
		this.ffrz = value;
	}
	public String getDdcjdjl() {
		return this.ddcjdjl;
	}

	public void setDdcjdjl(String value) {
		this.ddcjdjl = value;
	}
	public String getDddhyzk() {
		return this.dddhyzk;
	}

	public void setDddhyzk(String value) {
		this.dddhyzk = value;
	}
	public String getDqjtqk() {
		return this.dqjtqk;
	}

	public void setDqjtqk(String value) {
		this.dqjtqk = value;
	}
	public String getDqgqsjqk() {
		return this.dqgqsjqk;
	}

	public void setDqgqsjqk(String value) {
		this.dqgqsjqk = value;
	}
	public String getDqshsczk() {
		return this.dqshsczk;
	}

	public void setDqshsczk(String value) {
		this.dqshsczk = value;
	}
	public String getDxdqxtqk() {
		return this.dxdqxtqk;
	}

	public void setDxdqxtqk(String value) {
		this.dxdqxtqk = value;
	}
	public String getDxdqstzk() {
		return this.dxdqstzk;
	}

	public void setDxdqstzk(String value) {
		this.dxdqstzk = value;
	}
	public String getDdzsjsycqk() {
		return this.ddzsjsycqk;
	}

	public void setDdzsjsycqk(String value) {
		this.ddzsjsycqk = value;
	}
	public String getDazdxjkf() {
		return this.dazdxjkf;
	}

	public void setDazdxjkf(String value) {
		this.dazdxjkf = value;
	}
	public String getSjyz() {
		return this.sjyz;
	}

	public void setSjyz(String value) {
		this.sjyz = value;
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

	public String getPglx() {
		return this.pglx;
	}

	public void setPglx(String value) {
		this.pglx = value;
	}
	public String getYfxdj() {
		return this.yfxdj;
	}

	public void setYfxdj(String value) {
		this.yfxdj = value;
	}
	public String getXfxdj() {
		return this.xfxdj;
	}

	public void setXfxdj(String value) {
		this.xfxdj = value;
	}
	public String getYfxzb() {
		return this.yfxzb;
	}

	public void setYfxzb(String value) {
		this.yfxzb = value;
	}
	public String getXfxzb() {
		return this.xfxzb;
	}

	public void setXfxzb(String value) {
		this.xfxzb = value;
	}
	
	public java.util.Date getPgsj() {
		return this.pgsj;
	}
	
	public void setPgsj(java.util.Date value) {
		this.pgsj = value;
	}
	
	
	public java.util.Date getYpgsj() {
		return this.ypgsj;
	}
	
	public void setYpgsj(java.util.Date value) {
		this.ypgsj = value;
	}
	
	 
}

