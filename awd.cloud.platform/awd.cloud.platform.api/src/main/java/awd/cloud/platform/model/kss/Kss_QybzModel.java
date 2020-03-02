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


public class Kss_QybzModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String jsh;
	
	private java.lang.String bzlx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date kssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date dcsj;
	
	private java.lang.String dcmj;
	
	private java.lang.String dcrs;
	
	private java.lang.String qy;
	
	private java.lang.String dd;
	
	private java.lang.String dlr;
	
	private java.lang.String dlys;
	
	private java.lang.String jtqk;
	
	private java.lang.String mjxm;
	
	private java.lang.String state;
	
	private java.lang.String bz;
	
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

	 

	public Kss_QybzModel(){
	}
	public Kss_QybzModel(String id) {
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
	public java.lang.String getJsh() {
		return this.jsh;
	}
	
	public void setJsh(java.lang.String value) {
		this.jsh = value;
	}
	public java.lang.String getBzlx() {
		return this.bzlx;
	}
	
	public void setBzlx(java.lang.String value) {
		this.bzlx = value;
	}
	
	public java.util.Date getKssj() {
		return this.kssj;
	}
	
	public void setKssj(java.util.Date value) {
		this.kssj = value;
	}
	
	
	public java.util.Date getJssj() {
		return this.jssj;
	}
	
	public void setJssj(java.util.Date value) {
		this.jssj = value;
	}
	
	
	public java.util.Date getDcsj() {
		return this.dcsj;
	}
	
	public void setDcsj(java.util.Date value) {
		this.dcsj = value;
	}
	
	public java.lang.String getDcmj() {
		return this.dcmj;
	}
	
	public void setDcmj(java.lang.String value) {
		this.dcmj = value;
	}
	public java.lang.String getDcrs() {
		return this.dcrs;
	}
	
	public void setDcrs(java.lang.String value) {
		this.dcrs = value;
	}
	public java.lang.String getQy() {
		return this.qy;
	}
	
	public void setQy(java.lang.String value) {
		this.qy = value;
	}
	public java.lang.String getDd() {
		return this.dd;
	}
	
	public void setDd(java.lang.String value) {
		this.dd = value;
	}
	public java.lang.String getDlr() {
		return this.dlr;
	}
	
	public void setDlr(java.lang.String value) {
		this.dlr = value;
	}
	public java.lang.String getDlys() {
		return this.dlys;
	}
	
	public void setDlys(java.lang.String value) {
		this.dlys = value;
	}
	public java.lang.String getJtqk() {
		return this.jtqk;
	}
	
	public void setJtqk(java.lang.String value) {
		this.jtqk = value;
	}
	public java.lang.String getMjxm() {
		return this.mjxm;
	}
	
	public void setMjxm(java.lang.String value) {
		this.mjxm = value;
	}
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	public java.lang.String getBz() {
		return this.bz;
	}
	
	public void setBz(java.lang.String value) {
		this.bz = value;
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

