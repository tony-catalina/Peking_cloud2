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


public class Kss_PjdjModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String rybh;
	
	private java.lang.String jsbh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date pjrq;
	
	private java.lang.String pjdw;
	
	private java.lang.String wsh;
	
	private java.lang.String cljg;
	
	private java.lang.String xq;
	
	private java.lang.String fjx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date qsrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date xmrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jarq;
	
	private java.lang.String hx;
	
	private java.lang.String hxqx;
	
	private java.lang.String fjje;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hxksrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hxjsrq;
	
	private java.lang.String bdzzqlsj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date bdzzqlkssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date bdzzqljssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date pjsxrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date sdrq;
	
	private java.lang.String ywlcid;
	
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
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jyqx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ygyqx;
	
	private java.lang.String wspzlx;
	
	private java.lang.String ycljg;
	
	private java.lang.String qzcs;
	
	private java.lang.String pjzm;
	//columns END

	 

	public Kss_PjdjModel(){
	}
	public Kss_PjdjModel(String id) {
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
	
	public java.util.Date getPjrq() {
		return this.pjrq;
	}
	
	public void setPjrq(java.util.Date value) {
		this.pjrq = value;
	}
	
	public java.lang.String getPjdw() {
		return this.pjdw;
	}
	
	public void setPjdw(java.lang.String value) {
		this.pjdw = value;
	}
	public java.lang.String getWsh() {
		return this.wsh;
	}
	
	public void setWsh(java.lang.String value) {
		this.wsh = value;
	}
	public java.lang.String getCljg() {
		return this.cljg;
	}
	
	public void setCljg(java.lang.String value) {
		this.cljg = value;
	}
	public java.lang.String getXq() {
		return this.xq;
	}
	
	public void setXq(java.lang.String value) {
		this.xq = value;
	}
	public java.lang.String getFjx() {
		return this.fjx;
	}
	
	public void setFjx(java.lang.String value) {
		this.fjx = value;
	}
	
	public java.util.Date getQsrq() {
		return this.qsrq;
	}
	
	public void setQsrq(java.util.Date value) {
		this.qsrq = value;
	}
	
	
	public java.util.Date getXmrq() {
		return this.xmrq;
	}
	
	public void setXmrq(java.util.Date value) {
		this.xmrq = value;
	}
	
	
	public java.util.Date getJarq() {
		return this.jarq;
	}
	
	public void setJarq(java.util.Date value) {
		this.jarq = value;
	}
	
	public java.lang.String getHx() {
		return this.hx;
	}
	
	public void setHx(java.lang.String value) {
		this.hx = value;
	}
	public java.lang.String getHxqx() {
		return this.hxqx;
	}
	
	public void setHxqx(java.lang.String value) {
		this.hxqx = value;
	}
	public java.lang.String getFjje() {
		return this.fjje;
	}
	
	public void setFjje(java.lang.String value) {
		this.fjje = value;
	}
	
	public java.util.Date getHxksrq() {
		return this.hxksrq;
	}
	
	public void setHxksrq(java.util.Date value) {
		this.hxksrq = value;
	}
	
	
	public java.util.Date getHxjsrq() {
		return this.hxjsrq;
	}
	
	public void setHxjsrq(java.util.Date value) {
		this.hxjsrq = value;
	}
	
	public java.lang.String getBdzzqlsj() {
		return this.bdzzqlsj;
	}
	
	public void setBdzzqlsj(java.lang.String value) {
		this.bdzzqlsj = value;
	}
	
	public java.util.Date getBdzzqlkssj() {
		return this.bdzzqlkssj;
	}
	
	public void setBdzzqlkssj(java.util.Date value) {
		this.bdzzqlkssj = value;
	}
	
	
	public java.util.Date getBdzzqljssj() {
		return this.bdzzqljssj;
	}
	
	public void setBdzzqljssj(java.util.Date value) {
		this.bdzzqljssj = value;
	}
	
	
	public java.util.Date getPjsxrq() {
		return this.pjsxrq;
	}
	
	public void setPjsxrq(java.util.Date value) {
		this.pjsxrq = value;
	}
	
	
	public java.util.Date getSdrq() {
		return this.sdrq;
	}
	
	public void setSdrq(java.util.Date value) {
		this.sdrq = value;
	}
	
	public java.lang.String getYwlcid() {
		return this.ywlcid;
	}
	
	public void setYwlcid(java.lang.String value) {
		this.ywlcid = value;
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
	
	
	public java.util.Date getJyqx() {
		return this.jyqx;
	}
	
	public void setJyqx(java.util.Date value) {
		this.jyqx = value;
	}
	
	
	public java.util.Date getYgyqx() {
		return this.ygyqx;
	}
	
	public void setYgyqx(java.util.Date value) {
		this.ygyqx = value;
	}
	
	public java.lang.String getWspzlx() {
		return this.wspzlx;
	}
	
	public void setWspzlx(java.lang.String value) {
		this.wspzlx = value;
	}
	public java.lang.String getYcljg() {
		return this.ycljg;
	}
	
	public void setYcljg(java.lang.String value) {
		this.ycljg = value;
	}
	public java.lang.String getQzcs() {
		return this.qzcs;
	}
	
	public void setQzcs(java.lang.String value) {
		this.qzcs = value;
	}
	public java.lang.String getPjzm() {
		return this.pjzm;
	}
	
	public void setPjzm(java.lang.String value) {
		this.pjzm = value;
	}
	 
}

