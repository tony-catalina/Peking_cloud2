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


public class Kss_XjzcModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String rybh;
	
	private java.lang.String jsbh;
	
	private java.lang.String currency;
	
	private java.math.BigDecimal xfje;
	
	private java.lang.String xflx;
	
	private java.math.BigDecimal xfqje;
	
	private java.math.BigDecimal xfhye;
	
	private java.lang.String zy;
	
	private java.lang.String blr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date blsj;
	
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
	//columns END

	 

	public Kss_XjzcModel(){
	}
	public Kss_XjzcModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getRybh() {
		return this.rybh;
	}
	
	public void setRybh(java.lang.String value) {
		this.rybh = value;
	}
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	public java.lang.String getCurrency() {
		return this.currency;
	}
	
	public void setCurrency(java.lang.String value) {
		this.currency = value;
	}
	public java.math.BigDecimal getXfje() {
		return this.xfje;
	}
	
	public void setXfje(java.math.BigDecimal value) {
		this.xfje = value;
	}
	public java.lang.String getXflx() {
		return this.xflx;
	}
	
	public void setXflx(java.lang.String value) {
		this.xflx = value;
	}
	public java.math.BigDecimal getXfqje() {
		return this.xfqje;
	}
	
	public void setXfqje(java.math.BigDecimal value) {
		this.xfqje = value;
	}
	public java.math.BigDecimal getXfhye() {
		return this.xfhye;
	}
	
	public void setXfhye(java.math.BigDecimal value) {
		this.xfhye = value;
	}
	public java.lang.String getZy() {
		return this.zy;
	}
	
	public void setZy(java.lang.String value) {
		this.zy = value;
	}
	public java.lang.String getBlr() {
		return this.blr;
	}
	
	public void setBlr(java.lang.String value) {
		this.blr = value;
	}
	
	public java.util.Date getBlsj() {
		return this.blsj;
	}
	
	public void setBlsj(java.util.Date value) {
		this.blsj = value;
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
	
	 
}

