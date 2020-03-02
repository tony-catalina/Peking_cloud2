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
public class SzjdjlModel implements Model {
	
	//columns START
	
	private String id;


	private String jsbh;

	private String sldxm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jdsj;

	private String zlfxm;

	private String lfxb;

	private String lfnl;

	private String lfgzdw;

	private String lxfs;

	private String fywt;

	private String dfyj;

	private String clqk;

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

	private String zjlx;

	private String zjh;

	private String lfrzw;

	private String jbr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date djsj;
	//columns END



	public SzjdjlModel(){
	}
	public SzjdjlModel(String id) {
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
	public String getSldxm() {
		return this.sldxm;
	}

	public void setSldxm(String value) {
		this.sldxm = value;
	}

	public java.util.Date getJdsj() {
		return this.jdsj;
	}

	public void setJdsj(java.util.Date value) {
		this.jdsj = value;
	}

	public String getZlfxm() {
		return this.zlfxm;
	}

	public void setZlfxm(String value) {
		this.zlfxm = value;
	}
	public String getLfxb() {
		return this.lfxb;
	}

	public void setLfxb(String value) {
		this.lfxb = value;
	}
	public String getLfnl() {
		return this.lfnl;
	}

	public void setLfnl(String value) {
		this.lfnl = value;
	}
	public String getLfgzdw() {
		return this.lfgzdw;
	}

	public void setLfgzdw(String value) {
		this.lfgzdw = value;
	}
	public String getLxfs() {
		return this.lxfs;
	}

	public void setLxfs(String value) {
		this.lxfs = value;
	}
	public String getFywt() {
		return this.fywt;
	}

	public void setFywt(String value) {
		this.fywt = value;
	}
	public String getDfyj() {
		return this.dfyj;
	}

	public void setDfyj(String value) {
		this.dfyj = value;
	}
	public String getClqk() {
		return this.clqk;
	}

	public void setClqk(String value) {
		this.clqk = value;
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

	public String getZjlx() {
		return this.zjlx;
	}

	public void setZjlx(String value) {
		this.zjlx = value;
	}
	public String getZjh() {
		return this.zjh;
	}

	public void setZjh(String value) {
		this.zjh = value;
	}
	public String getLfrzw() {
		return this.lfrzw;
	}

	public void setLfrzw(String value) {
		this.lfrzw = value;
	}
	public String getJbr() {
		return this.jbr;
	}

	public void setJbr(String value) {
		this.jbr = value;
	}
	
	public java.util.Date getLssj() {
		return this.lssj;
	}
	
	public void setLssj(java.util.Date value) {
		this.lssj = value;
	}
	
	
	public java.util.Date getDjsj() {
		return this.djsj;
	}
	
	public void setDjsj(java.util.Date value) {
		this.djsj = value;
	}
	
	 
}

