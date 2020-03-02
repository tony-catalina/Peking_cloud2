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
public class ZyrytsclModel implements Model {
	
	//columns START
	
	private String id;


	private String jsbh;

	private String rybh;

	private String tsrxm;

	private String gx;

	private String btsdx;

	private String tsdx;

	private String tslx;

	private String tsnr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date tssj;

	private String slr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date slsj;

	private String spr;

	private String spyj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date spsj;

	private String ywlcid;

	private String state;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;

	private String bz;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date clsj;

	private String cljg;

	private String clr;

	private String creator;

	private String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
	//columns END



	public ZyrytsclModel(){
	}
	public ZyrytsclModel(String id) {
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
	public String getTsrxm() {
		return this.tsrxm;
	}

	public void setTsrxm(String value) {
		this.tsrxm = value;
	}
	public String getGx() {
		return this.gx;
	}

	public void setGx(String value) {
		this.gx = value;
	}
	public String getBtsdx() {
		return this.btsdx;
	}

	public void setBtsdx(String value) {
		this.btsdx = value;
	}
	public String getTsdx() {
		return this.tsdx;
	}

	public void setTsdx(String value) {
		this.tsdx = value;
	}
	public String getTslx() {
		return this.tslx;
	}

	public void setTslx(String value) {
		this.tslx = value;
	}
	public String getTsnr() {
		return this.tsnr;
	}

	public void setTsnr(String value) {
		this.tsnr = value;
	}

	public java.util.Date getTssj() {
		return this.tssj;
	}

	public void setTssj(java.util.Date value) {
		this.tssj = value;
	}

	public String getSlr() {
		return this.slr;
	}

	public void setSlr(String value) {
		this.slr = value;
	}

	public java.util.Date getSlsj() {
		return this.slsj;
	}

	public void setSlsj(java.util.Date value) {
		this.slsj = value;
	}

	public String getSpr() {
		return this.spr;
	}

	public void setSpr(String value) {
		this.spr = value;
	}
	public String getSpyj() {
		return this.spyj;
	}

	public void setSpyj(String value) {
		this.spyj = value;
	}

	public java.util.Date getSpsj() {
		return this.spsj;
	}

	public void setSpsj(java.util.Date value) {
		this.spsj = value;
	}

	public String getYwlcid() {
		return this.ywlcid;
	}

	public void setYwlcid(String value) {
		this.ywlcid = value;
	}
	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
	}

	public java.util.Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}

	public java.util.Date getClsj() {
		return this.clsj;
	}

	public void setClsj(java.util.Date value) {
		this.clsj = value;
	}

	public String getCljg() {
		return this.cljg;
	}

	public void setCljg(String value) {
		this.cljg = value;
	}
	public String getClr() {
		return this.clr;
	}

	public void setClr(String value) {
		this.clr = value;
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
	
	 
}

