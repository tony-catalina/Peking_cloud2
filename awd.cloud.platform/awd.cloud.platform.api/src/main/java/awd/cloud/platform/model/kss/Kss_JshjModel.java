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


public class Kss_JshjModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String jszjh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date yyhjsj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hjsj;
	
	private java.lang.String hjsjcd;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jssj;
	
	private java.lang.String ly;
	
	private java.lang.Byte rs;
	
	private java.lang.String dcmj;
	
	private java.lang.String sjmj;
	
	private java.lang.String bllx;
	
	private java.lang.String ldxm;
	
	private java.lang.String ldyj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ldpssj;
	
	private java.lang.String cxly;
	
	private java.lang.String cxr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date cxsj;
	
	private java.lang.String jsbz;
	
	private java.lang.String pastable;
	
	private java.lang.String ywlcid;
	
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
	
	private java.lang.String psbz;
	
	private java.lang.String hjs;
	
	private java.lang.String tbjcjg;
	
	private java.lang.String wjpjcjg;
	
	private java.lang.String zjlx;
	
	private java.lang.String bhjrgx;
	
	private java.lang.String lxfs;
	
	private java.lang.String xm;
	
	private java.lang.String detfrxm;
	
	private java.lang.String detfrgx;
	
	private java.lang.String detfrsfsw;
	
	private java.lang.String dstfrxm;
	
	private java.lang.String dstfrgx;
	
	private java.lang.String dstfrsfsw;
	
	private java.lang.String tfrs;
	
	private java.lang.String badwyj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date zxtzssdsj;
	
	private java.lang.String cbjcjg;
	
	private java.lang.String jcr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jcsj;
	
	private java.lang.String ycqksm;
	//columns END

	 

	public Kss_JshjModel(){
	}
	public Kss_JshjModel(String id) {
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
	public java.lang.String getJszjh() {
		return this.jszjh;
	}
	
	public void setJszjh(java.lang.String value) {
		this.jszjh = value;
	}
	
	public java.util.Date getYyhjsj() {
		return this.yyhjsj;
	}
	
	public void setYyhjsj(java.util.Date value) {
		this.yyhjsj = value;
	}
	
	
	public java.util.Date getHjsj() {
		return this.hjsj;
	}
	
	public void setHjsj(java.util.Date value) {
		this.hjsj = value;
	}
	
	public java.lang.String getHjsjcd() {
		return this.hjsjcd;
	}
	
	public void setHjsjcd(java.lang.String value) {
		this.hjsjcd = value;
	}
	
	public java.util.Date getJssj() {
		return this.jssj;
	}
	
	public void setJssj(java.util.Date value) {
		this.jssj = value;
	}
	
	public java.lang.String getLy() {
		return this.ly;
	}
	
	public void setLy(java.lang.String value) {
		this.ly = value;
	}
	public java.lang.Byte getRs() {
		return this.rs;
	}
	
	public void setRs(java.lang.Byte value) {
		this.rs = value;
	}
	public java.lang.String getDcmj() {
		return this.dcmj;
	}
	
	public void setDcmj(java.lang.String value) {
		this.dcmj = value;
	}
	public java.lang.String getSjmj() {
		return this.sjmj;
	}
	
	public void setSjmj(java.lang.String value) {
		this.sjmj = value;
	}
	public java.lang.String getBllx() {
		return this.bllx;
	}
	
	public void setBllx(java.lang.String value) {
		this.bllx = value;
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
	
	public java.lang.String getCxly() {
		return this.cxly;
	}
	
	public void setCxly(java.lang.String value) {
		this.cxly = value;
	}
	public java.lang.String getCxr() {
		return this.cxr;
	}
	
	public void setCxr(java.lang.String value) {
		this.cxr = value;
	}
	
	public java.util.Date getCxsj() {
		return this.cxsj;
	}
	
	public void setCxsj(java.util.Date value) {
		this.cxsj = value;
	}
	
	public java.lang.String getJsbz() {
		return this.jsbz;
	}
	
	public void setJsbz(java.lang.String value) {
		this.jsbz = value;
	}
	public java.lang.String getPastable() {
		return this.pastable;
	}
	
	public void setPastable(java.lang.String value) {
		this.pastable = value;
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
	
	public java.lang.String getPsbz() {
		return this.psbz;
	}
	
	public void setPsbz(java.lang.String value) {
		this.psbz = value;
	}
	public java.lang.String getHjs() {
		return this.hjs;
	}
	
	public void setHjs(java.lang.String value) {
		this.hjs = value;
	}
	public java.lang.String getTbjcjg() {
		return this.tbjcjg;
	}
	
	public void setTbjcjg(java.lang.String value) {
		this.tbjcjg = value;
	}
	public java.lang.String getWjpjcjg() {
		return this.wjpjcjg;
	}
	
	public void setWjpjcjg(java.lang.String value) {
		this.wjpjcjg = value;
	}
	public java.lang.String getZjlx() {
		return this.zjlx;
	}
	
	public void setZjlx(java.lang.String value) {
		this.zjlx = value;
	}
	public java.lang.String getBhjrgx() {
		return this.bhjrgx;
	}
	
	public void setBhjrgx(java.lang.String value) {
		this.bhjrgx = value;
	}
	public java.lang.String getLxfs() {
		return this.lxfs;
	}
	
	public void setLxfs(java.lang.String value) {
		this.lxfs = value;
	}
	public java.lang.String getXm() {
		return this.xm;
	}
	
	public void setXm(java.lang.String value) {
		this.xm = value;
	}
	public java.lang.String getDetfrxm() {
		return this.detfrxm;
	}
	
	public void setDetfrxm(java.lang.String value) {
		this.detfrxm = value;
	}
	public java.lang.String getDetfrgx() {
		return this.detfrgx;
	}
	
	public void setDetfrgx(java.lang.String value) {
		this.detfrgx = value;
	}
	public java.lang.String getDetfrsfsw() {
		return this.detfrsfsw;
	}
	
	public void setDetfrsfsw(java.lang.String value) {
		this.detfrsfsw = value;
	}
	public java.lang.String getDstfrxm() {
		return this.dstfrxm;
	}
	
	public void setDstfrxm(java.lang.String value) {
		this.dstfrxm = value;
	}
	public java.lang.String getDstfrgx() {
		return this.dstfrgx;
	}
	
	public void setDstfrgx(java.lang.String value) {
		this.dstfrgx = value;
	}
	public java.lang.String getDstfrsfsw() {
		return this.dstfrsfsw;
	}
	
	public void setDstfrsfsw(java.lang.String value) {
		this.dstfrsfsw = value;
	}
	public java.lang.String getTfrs() {
		return this.tfrs;
	}
	
	public void setTfrs(java.lang.String value) {
		this.tfrs = value;
	}
	public java.lang.String getBadwyj() {
		return this.badwyj;
	}
	
	public void setBadwyj(java.lang.String value) {
		this.badwyj = value;
	}
	
	public java.util.Date getZxtzssdsj() {
		return this.zxtzssdsj;
	}
	
	public void setZxtzssdsj(java.util.Date value) {
		this.zxtzssdsj = value;
	}
	
	public java.lang.String getCbjcjg() {
		return this.cbjcjg;
	}
	
	public void setCbjcjg(java.lang.String value) {
		this.cbjcjg = value;
	}
	public java.lang.String getJcr() {
		return this.jcr;
	}
	
	public void setJcr(java.lang.String value) {
		this.jcr = value;
	}
	
	public java.util.Date getJcsj() {
		return this.jcsj;
	}
	
	public void setJcsj(java.util.Date value) {
		this.jcsj = value;
	}
	
	public java.lang.String getYcqksm() {
		return this.ycqksm;
	}
	
	public void setYcqksm(java.lang.String value) {
		this.ycqksm = value;
	}
	 
}

