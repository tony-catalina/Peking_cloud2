/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.model.kss;

import awd.cloud.platform.model.Model;
import javax.validation.constraints.NotNull;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class Kss_WgsjclModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String dxbh;
	
	private java.lang.String clzt;
	
	private java.lang.String clr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date clsj;
	
	private java.lang.String clqk;
	
	private java.lang.String xsjlid;
	
	private java.lang.String wglx;
	
	private java.lang.String wgqk;
	
	private java.lang.String wgqkcon;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date wgsj;
	
	private java.lang.String bz;
	
	private java.lang.String state;
	
	private java.lang.String creator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
	
	private java.lang.String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
	
	private java.lang.String ycqk;
	
	private java.lang.String ycqkcon;
	
	private java.lang.String ywyc;
	//columns END

	 

	public Kss_WgsjclModel(){
	}
	public Kss_WgsjclModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	public java.lang.String getDxbh() {
		return this.dxbh;
	}
	
	public void setDxbh(java.lang.String value) {
		this.dxbh = value;
	}
	public java.lang.String getClzt() {
		return this.clzt;
	}
	
	public void setClzt(java.lang.String value) {
		this.clzt = value;
	}
	public java.lang.String getClr() {
		return this.clr;
	}
	
	public void setClr(java.lang.String value) {
		this.clr = value;
	}
	
	public java.util.Date getClsj() {
		return this.clsj;
	}
	
	public void setClsj(java.util.Date value) {
		this.clsj = value;
	}
	
	public java.lang.String getClqk() {
		return this.clqk;
	}
	
	public void setClqk(java.lang.String value) {
		this.clqk = value;
	}
	public java.lang.String getXsjlid() {
		return this.xsjlid;
	}
	
	public void setXsjlid(java.lang.String value) {
		this.xsjlid = value;
	}
	public java.lang.String getWglx() {
		return this.wglx;
	}
	
	public void setWglx(java.lang.String value) {
		this.wglx = value;
	}
	public java.lang.String getWgqk() {
		return this.wgqk;
	}
	
	public void setWgqk(java.lang.String value) {
		this.wgqk = value;
	}
	public java.lang.String getWgqkcon() {
		return this.wgqkcon;
	}
	
	public void setWgqkcon(java.lang.String value) {
		this.wgqkcon = value;
	}
	
	public java.util.Date getWgsj() {
		return this.wgsj;
	}
	
	public void setWgsj(java.util.Date value) {
		this.wgsj = value;
	}
	
	public java.lang.String getBz() {
		return this.bz;
	}
	
	public void setBz(java.lang.String value) {
		this.bz = value;
	}
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	public java.lang.String getCreator() {
		return this.creator;
	}
	
	public void setCreator(java.lang.String value) {
		this.creator = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.lang.String getUpdator() {
		return this.updator;
	}
	
	public void setUpdator(java.lang.String value) {
		this.updator = value;
	}
	
	public java.util.Date getUpdatetime() {
		return this.updatetime;
	}
	
	public void setUpdatetime(java.util.Date value) {
		this.updatetime = value;
	}
	
	public java.lang.String getYcqk() {
		return this.ycqk;
	}
	
	public void setYcqk(java.lang.String value) {
		this.ycqk = value;
	}
	public java.lang.String getYcqkcon() {
		return this.ycqkcon;
	}
	
	public void setYcqkcon(java.lang.String value) {
		this.ycqkcon = value;
	}
	public java.lang.String getYwyc() {
		return this.ywyc;
	}
	
	public void setYwyc(java.lang.String value) {
		this.ywyc = value;
	}
	 
}

