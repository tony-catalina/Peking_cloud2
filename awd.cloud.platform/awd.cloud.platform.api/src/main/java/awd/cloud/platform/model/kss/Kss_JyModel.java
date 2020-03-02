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


public class Kss_JyModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsh;
	
	private java.lang.String rybh;
	
	private java.lang.String jsbh;
	
	private java.lang.String ysxm;
	
	private java.lang.String ly;
	
	private java.lang.String xl;
	
	private java.lang.String xy;
	
	private java.lang.String brbq;
	
	private java.lang.String zdqk;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date zdrq;
	
	private java.lang.String cljg;
	
	private java.lang.String bhlx;
	
	private java.lang.String ptmj;
	
	private java.lang.String jzyy;
	
	private java.lang.String brzs;
	
	private java.lang.String swjy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date yyzdsj;
	
	private java.lang.String yyzdjg;
	
	private java.lang.String yyzlcs;
	
	private java.lang.String csjylx;
	
	private java.math.BigDecimal zlfy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hssj;
	
	private java.lang.String ldxm;
	
	private java.lang.String ldyj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ldpssj;
	
	private java.lang.String sfffyp;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date fyksrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date fyjsrq;
	
	private java.lang.String sfyxxx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date xxkssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date xxjssj;
	
	private java.lang.String sfjs;
	
	private java.lang.String psbz;
	
	private java.lang.String bz;
	
	private java.lang.String ywlcid;
	
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
	private java.util.Date cssj;
	
	private java.lang.String shjb;
	//columns END

	 

	public Kss_JyModel(){
	}
	public Kss_JyModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getJsh() {
		return this.jsh;
	}
	
	public void setJsh(java.lang.String value) {
		this.jsh = value;
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
	public java.lang.String getYsxm() {
		return this.ysxm;
	}
	
	public void setYsxm(java.lang.String value) {
		this.ysxm = value;
	}
	public java.lang.String getLy() {
		return this.ly;
	}
	
	public void setLy(java.lang.String value) {
		this.ly = value;
	}
	public java.lang.String getXl() {
		return this.xl;
	}
	
	public void setXl(java.lang.String value) {
		this.xl = value;
	}
	public java.lang.String getXy() {
		return this.xy;
	}
	
	public void setXy(java.lang.String value) {
		this.xy = value;
	}
	public java.lang.String getBrbq() {
		return this.brbq;
	}
	
	public void setBrbq(java.lang.String value) {
		this.brbq = value;
	}
	public java.lang.String getZdqk() {
		return this.zdqk;
	}
	
	public void setZdqk(java.lang.String value) {
		this.zdqk = value;
	}
	
	public java.util.Date getZdrq() {
		return this.zdrq;
	}
	
	public void setZdrq(java.util.Date value) {
		this.zdrq = value;
	}
	
	public java.lang.String getCljg() {
		return this.cljg;
	}
	
	public void setCljg(java.lang.String value) {
		this.cljg = value;
	}
	public java.lang.String getBhlx() {
		return this.bhlx;
	}
	
	public void setBhlx(java.lang.String value) {
		this.bhlx = value;
	}
	public java.lang.String getPtmj() {
		return this.ptmj;
	}
	
	public void setPtmj(java.lang.String value) {
		this.ptmj = value;
	}
	public java.lang.String getJzyy() {
		return this.jzyy;
	}
	
	public void setJzyy(java.lang.String value) {
		this.jzyy = value;
	}
	public java.lang.String getBrzs() {
		return this.brzs;
	}
	
	public void setBrzs(java.lang.String value) {
		this.brzs = value;
	}
	public java.lang.String getSwjy() {
		return this.swjy;
	}
	
	public void setSwjy(java.lang.String value) {
		this.swjy = value;
	}
	
	public java.util.Date getYyzdsj() {
		return this.yyzdsj;
	}
	
	public void setYyzdsj(java.util.Date value) {
		this.yyzdsj = value;
	}
	
	public java.lang.String getYyzdjg() {
		return this.yyzdjg;
	}
	
	public void setYyzdjg(java.lang.String value) {
		this.yyzdjg = value;
	}
	public java.lang.String getYyzlcs() {
		return this.yyzlcs;
	}
	
	public void setYyzlcs(java.lang.String value) {
		this.yyzlcs = value;
	}
	public java.lang.String getCsjylx() {
		return this.csjylx;
	}
	
	public void setCsjylx(java.lang.String value) {
		this.csjylx = value;
	}
	public java.math.BigDecimal getZlfy() {
		return this.zlfy;
	}
	
	public void setZlfy(java.math.BigDecimal value) {
		this.zlfy = value;
	}
	
	public java.util.Date getHssj() {
		return this.hssj;
	}
	
	public void setHssj(java.util.Date value) {
		this.hssj = value;
	}
	
	public java.lang.String getLdxm() {
		return this.ldxm;
	}
	
	public void setLdxm(java.lang.String value) {
		this.ldxm = value;
	}
	public java.lang.String getLdyj() {
		return this.ldyj;
	}
	
	public void setLdyj(java.lang.String value) {
		this.ldyj = value;
	}
	
	public java.util.Date getLdpssj() {
		return this.ldpssj;
	}
	
	public void setLdpssj(java.util.Date value) {
		this.ldpssj = value;
	}
	
	public java.lang.String getSfffyp() {
		return this.sfffyp;
	}
	
	public void setSfffyp(java.lang.String value) {
		this.sfffyp = value;
	}
	
	public java.util.Date getFyksrq() {
		return this.fyksrq;
	}
	
	public void setFyksrq(java.util.Date value) {
		this.fyksrq = value;
	}
	
	
	public java.util.Date getFyjsrq() {
		return this.fyjsrq;
	}
	
	public void setFyjsrq(java.util.Date value) {
		this.fyjsrq = value;
	}
	
	public java.lang.String getSfyxxx() {
		return this.sfyxxx;
	}
	
	public void setSfyxxx(java.lang.String value) {
		this.sfyxxx = value;
	}
	
	public java.util.Date getXxkssj() {
		return this.xxkssj;
	}
	
	public void setXxkssj(java.util.Date value) {
		this.xxkssj = value;
	}
	
	
	public java.util.Date getXxjssj() {
		return this.xxjssj;
	}
	
	public void setXxjssj(java.util.Date value) {
		this.xxjssj = value;
	}
	
	public java.lang.String getSfjs() {
		return this.sfjs;
	}
	
	public void setSfjs(java.lang.String value) {
		this.sfjs = value;
	}
	public java.lang.String getPsbz() {
		return this.psbz;
	}
	
	public void setPsbz(java.lang.String value) {
		this.psbz = value;
	}
	public java.lang.String getBz() {
		return this.bz;
	}
	
	public void setBz(java.lang.String value) {
		this.bz = value;
	}
	public java.lang.String getYwlcid() {
		return this.ywlcid;
	}
	
	public void setYwlcid(java.lang.String value) {
		this.ywlcid = value;
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
	
	
	public java.util.Date getCssj() {
		return this.cssj;
	}
	
	public void setCssj(java.util.Date value) {
		this.cssj = value;
	}
	
	public java.lang.String getShjb() {
		return this.shjb;
	}
	
	public void setShjb(java.lang.String value) {
		this.shjb = value;
	}
	 
}

