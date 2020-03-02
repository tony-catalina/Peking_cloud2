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


public class Kss_ZrapModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date apsj;
	
	private java.lang.String week;
	
	private java.lang.String zbr1;
	
	private java.lang.String zbr2;
	
	private java.lang.String zbr3;
	
	private java.lang.String zbr4;
	
	private java.lang.String zbr5;
	
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
	
	private java.lang.String jsh;
	//columns END

	 

	public Kss_ZrapModel(){
	}
	public Kss_ZrapModel(String id) {
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
	
	public java.util.Date getApsj() {
		return this.apsj;
	}
	
	public void setApsj(java.util.Date value) {
		this.apsj = value;
	}
	
	public java.lang.String getWeek() {
		return this.week;
	}
	
	public void setWeek(java.lang.String value) {
		this.week = value;
	}
	public java.lang.String getZbr1() {
		return this.zbr1;
	}
	
	public void setZbr1(java.lang.String value) {
		this.zbr1 = value;
	}
	public java.lang.String getZbr2() {
		return this.zbr2;
	}
	
	public void setZbr2(java.lang.String value) {
		this.zbr2 = value;
	}
	public java.lang.String getZbr3() {
		return this.zbr3;
	}
	
	public void setZbr3(java.lang.String value) {
		this.zbr3 = value;
	}
	public java.lang.String getZbr4() {
		return this.zbr4;
	}
	
	public void setZbr4(java.lang.String value) {
		this.zbr4 = value;
	}
	public java.lang.String getZbr5() {
		return this.zbr5;
	}
	
	public void setZbr5(java.lang.String value) {
		this.zbr5 = value;
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
	
	public java.lang.String getJsh() {
		return this.jsh;
	}
	
	public void setJsh(java.lang.String value) {
		this.jsh = value;
	}
	 
}

