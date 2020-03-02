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
public class LshjModel implements Model {
	
	//columns START
	
	private String id;


	private String jsbh;

	private String rybh;

	private String ywlcid;

	private String lsxm;

	private String zjlx;

	private String zjh;

	private String dw;

	private Short rs;

	private String hjsy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hjsj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jssj;

	private String lrmj;

	private String pzr;

	private String fzmj;

	private String jsr;

	private String pastable;

	private String hjlx;

	private String state;

	private String bz;

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

	private String hjs;

	private String lszl;

	private String lsfy;

	private String hjpzjg;

	private String xkjdwsh;

	private String tbjcqk;

	private String wjpjcqk;
	//columns END



	public LshjModel(){
	}
	public LshjModel(String id) {
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
	public String getYwlcid() {
		return this.ywlcid;
	}

	public void setYwlcid(String value) {
		this.ywlcid = value;
	}
	public String getLsxm() {
		return this.lsxm;
	}

	public void setLsxm(String value) {
		this.lsxm = value;
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
	public String getDw() {
		return this.dw;
	}

	public void setDw(String value) {
		this.dw = value;
	}
	public Short getRs() {
		return this.rs;
	}

	public void setRs(Short value) {
		this.rs = value;
	}
	public String getHjsy() {
		return this.hjsy;
	}

	public void setHjsy(String value) {
		this.hjsy = value;
	}

	public java.util.Date getHjsj() {
		return this.hjsj;
	}

	public void setHjsj(java.util.Date value) {
		this.hjsj = value;
	}


	public java.util.Date getJssj() {
		return this.jssj;
	}

	public void setJssj(java.util.Date value) {
		this.jssj = value;
	}

	public String getLrmj() {
		return this.lrmj;
	}

	public void setLrmj(String value) {
		this.lrmj = value;
	}
	public String getPzr() {
		return this.pzr;
	}

	public void setPzr(String value) {
		this.pzr = value;
	}
	public String getFzmj() {
		return this.fzmj;
	}

	public void setFzmj(String value) {
		this.fzmj = value;
	}
	public String getJsr() {
		return this.jsr;
	}

	public void setJsr(String value) {
		this.jsr = value;
	}
	public String getPastable() {
		return this.pastable;
	}

	public void setPastable(String value) {
		this.pastable = value;
	}
	public String getHjlx() {
		return this.hjlx;
	}

	public void setHjlx(String value) {
		this.hjlx = value;
	}
	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
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

	public String getHjs() {
		return this.hjs;
	}

	public void setHjs(String value) {
		this.hjs = value;
	}
	public String getLszl() {
		return this.lszl;
	}

	public void setLszl(String value) {
		this.lszl = value;
	}
	public String getLsfy() {
		return this.lsfy;
	}

	public void setLsfy(String value) {
		this.lsfy = value;
	}
	public String getHjpzjg() {
		return this.hjpzjg;
	}

	public void setHjpzjg(String value) {
		this.hjpzjg = value;
	}
	public String getXkjdwsh() {
		return this.xkjdwsh;
	}

	public void setXkjdwsh(String value) {
		this.xkjdwsh = value;
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

