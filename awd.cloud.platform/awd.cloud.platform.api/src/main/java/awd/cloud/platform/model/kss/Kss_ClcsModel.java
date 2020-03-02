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


public class Kss_ClcsModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String csyy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date cssj;
	
	private java.lang.String csqx;
	
	private java.lang.String pzdw;
	
	private java.lang.String pzr;
	
	private java.lang.String blr;
	
	private java.lang.String blrzjh;
	
	private java.lang.String blrlxdh;
	
	private java.lang.String pzws;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date blsj;
	
	private java.lang.String dbr;
	
	private java.lang.String dbdw;
	
	private java.lang.String jddw;
	
	private java.lang.String zcdwszd;
	
	private java.lang.String qzcs;
	
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
	
	private java.lang.String tzdw;
	
	private java.lang.String dbrhxyrgx;
	
	private java.lang.String dbrdz;
	
	private java.lang.String snbx;
	
	private java.lang.String ywlcid;
	//columns END

	 

	public Kss_ClcsModel(){
	}
	public Kss_ClcsModel(String id) {
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
	public java.lang.String getRybh() {
		return this.rybh;
	}
	
	public void setRybh(java.lang.String value) {
		this.rybh = value;
	}
	public java.lang.String getCsyy() {
		return this.csyy;
	}
	
	public void setCsyy(java.lang.String value) {
		this.csyy = value;
	}
	
	public java.util.Date getCssj() {
		return this.cssj;
	}
	
	public void setCssj(java.util.Date value) {
		this.cssj = value;
	}
	
	public java.lang.String getCsqx() {
		return this.csqx;
	}
	
	public void setCsqx(java.lang.String value) {
		this.csqx = value;
	}
	public java.lang.String getPzdw() {
		return this.pzdw;
	}
	
	public void setPzdw(java.lang.String value) {
		this.pzdw = value;
	}
	public java.lang.String getPzr() {
		return this.pzr;
	}
	
	public void setPzr(java.lang.String value) {
		this.pzr = value;
	}
	public java.lang.String getBlr() {
		return this.blr;
	}
	
	public void setBlr(java.lang.String value) {
		this.blr = value;
	}
	public java.lang.String getBlrzjh() {
		return this.blrzjh;
	}
	
	public void setBlrzjh(java.lang.String value) {
		this.blrzjh = value;
	}
	public java.lang.String getBlrlxdh() {
		return this.blrlxdh;
	}
	
	public void setBlrlxdh(java.lang.String value) {
		this.blrlxdh = value;
	}
	public java.lang.String getPzws() {
		return this.pzws;
	}
	
	public void setPzws(java.lang.String value) {
		this.pzws = value;
	}
	
	public java.util.Date getBlsj() {
		return this.blsj;
	}
	
	public void setBlsj(java.util.Date value) {
		this.blsj = value;
	}
	
	public java.lang.String getDbr() {
		return this.dbr;
	}
	
	public void setDbr(java.lang.String value) {
		this.dbr = value;
	}
	public java.lang.String getDbdw() {
		return this.dbdw;
	}
	
	public void setDbdw(java.lang.String value) {
		this.dbdw = value;
	}
	public java.lang.String getJddw() {
		return this.jddw;
	}
	
	public void setJddw(java.lang.String value) {
		this.jddw = value;
	}
	public java.lang.String getZcdwszd() {
		return this.zcdwszd;
	}
	
	public void setZcdwszd(java.lang.String value) {
		this.zcdwszd = value;
	}
	public java.lang.String getQzcs() {
		return this.qzcs;
	}
	
	public void setQzcs(java.lang.String value) {
		this.qzcs = value;
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
	
	public java.lang.String getTzdw() {
		return this.tzdw;
	}
	
	public void setTzdw(java.lang.String value) {
		this.tzdw = value;
	}
	public java.lang.String getDbrhxyrgx() {
		return this.dbrhxyrgx;
	}
	
	public void setDbrhxyrgx(java.lang.String value) {
		this.dbrhxyrgx = value;
	}
	public java.lang.String getDbrdz() {
		return this.dbrdz;
	}
	
	public void setDbrdz(java.lang.String value) {
		this.dbrdz = value;
	}
	public java.lang.String getSnbx() {
		return this.snbx;
	}
	
	public void setSnbx(java.lang.String value) {
		this.snbx = value;
	}
	public java.lang.String getYwlcid() {
		return this.ywlcid;
	}
	
	public void setYwlcid(java.lang.String value) {
		this.ywlcid = value;
	}
	 
}

