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
public class WgsjclModel implements Model {

	//columns START
	
	private String id;


	private String jsbh;

	private String dxbh;

	private String clzt;

	private String clr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date clsj;

	private String clqk;

	private String xsjlid;

	private String wglx;

	private String wgqk;

	private String wgqkcon;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date wgsj;

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
	//columns START
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date starttime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date endtime;

	private String ycqk;

	private String ycqkcon;

	private String ywyc;
	//columns END



	public WgsjclModel(){
	}
	public WgsjclModel(String id) {
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
	public String getDxbh() {
		return this.dxbh;
	}

	public void setDxbh(String value) {
		this.dxbh = value;
	}
	public String getClzt() {
		return this.clzt;
	}

	public void setClzt(String value) {
		this.clzt = value;
	}
	public String getClr() {
		return this.clr;
	}

	public void setClr(String value) {
		this.clr = value;
	}

	public java.util.Date getClsj() {
		return this.clsj;
	}

	public void setClsj(java.util.Date value) {
		this.clsj = value;
	}

	public String getClqk() {
		return this.clqk;
	}

	public void setClqk(String value) {
		this.clqk = value;
	}
	public String getXsjlid() {
		return this.xsjlid;
	}

	public void setXsjlid(String value) {
		this.xsjlid = value;
	}
	public String getWglx() {
		return this.wglx;
	}

	public void setWglx(String value) {
		this.wglx = value;
	}
	public String getWgqk() {
		return this.wgqk;
	}

	public void setWgqk(String value) {
		this.wgqk = value;
	}
	public String getWgqkcon() {
		return this.wgqkcon;
	}

	public void setWgqkcon(String value) {
		this.wgqkcon = value;
	}

	public java.util.Date getWgsj() {
		return this.wgsj;
	}

	public void setWgsj(java.util.Date value) {
		this.wgsj = value;
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

	public String getYcqk() {
		return this.ycqk;
	}

	public void setYcqk(String value) {
		this.ycqk = value;
	}
	public String getYcqkcon() {
		return this.ycqkcon;
	}

	public void setYcqkcon(String value) {
		this.ycqkcon = value;
	}
	public String getYwyc() {
		return this.ywyc;
	}

	public void setYwyc(String value) {
		this.ywyc = value;
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

