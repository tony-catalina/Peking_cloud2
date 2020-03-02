/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.model.manager;

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


public class Manager_FxpgModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jslx;
	
	private java.lang.String classid;
	
	private java.lang.String code;
	
	private java.lang.String content;
	
	private java.math.BigDecimal cyglfz;
	
	private java.math.BigDecimal fxpgfz;
	
	private java.lang.String py;
	
	private java.math.BigDecimal wghf;
	
	private java.math.BigDecimal hfsx;
	
	private java.lang.String isunique;
	
	private java.math.BigDecimal minvalue;
	
	private java.math.BigDecimal maxvalue;
	
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

	 

	public Manager_FxpgModel(){
	}
	public Manager_FxpgModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getJslx() {
		return this.jslx;
	}
	
	public void setJslx(java.lang.String value) {
		this.jslx = value;
	}
	public java.lang.String getClassid() {
		return this.classid;
	}
	
	public void setClassid(java.lang.String value) {
		this.classid = value;
	}
	public java.lang.String getCode() {
		return this.code;
	}
	
	public void setCode(java.lang.String value) {
		this.code = value;
	}
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	public java.math.BigDecimal getCyglfz() {
		return this.cyglfz;
	}
	
	public void setCyglfz(java.math.BigDecimal value) {
		this.cyglfz = value;
	}
	public java.math.BigDecimal getFxpgfz() {
		return this.fxpgfz;
	}
	
	public void setFxpgfz(java.math.BigDecimal value) {
		this.fxpgfz = value;
	}
	public java.lang.String getPy() {
		return this.py;
	}
	
	public void setPy(java.lang.String value) {
		this.py = value;
	}
	public java.math.BigDecimal getWghf() {
		return this.wghf;
	}
	
	public void setWghf(java.math.BigDecimal value) {
		this.wghf = value;
	}
	public java.math.BigDecimal getHfsx() {
		return this.hfsx;
	}
	
	public void setHfsx(java.math.BigDecimal value) {
		this.hfsx = value;
	}
	public java.lang.String getIsunique() {
		return this.isunique;
	}
	
	public void setIsunique(java.lang.String value) {
		this.isunique = value;
	}
	public java.math.BigDecimal getMinvalue() {
		return this.minvalue;
	}
	
	public void setMinvalue(java.math.BigDecimal value) {
		this.minvalue = value;
	}
	public java.math.BigDecimal getMaxvalue() {
		return this.maxvalue;
	}
	
	public void setMaxvalue(java.math.BigDecimal value) {
		this.maxvalue = value;
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

