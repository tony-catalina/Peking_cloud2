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


public class Kss_JwzxjlModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String sqyy;
	
	private java.lang.String sqrxm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date sqsj;
	
	private java.lang.String sqrzjh;
	
	private java.lang.String spjg;
	
	private java.lang.String ldxm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ldpssj;
	
	private java.lang.String ldyj;
	
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
	
	private java.lang.String sqrlx;
	
	private java.lang.String bz;
	
	private java.lang.String ywlcid;
	
	private java.lang.String flws;
	
	private java.lang.String pzh;
	
	private java.lang.String sqlx;
	
	private java.lang.String clsx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ncssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ncsqx;
	
	private java.lang.String dbr;
	
	private java.lang.String dbrdz;
	
	private java.lang.String dbrdw;
	
	private java.lang.String dbryxyrgx;
	
	private java.lang.String gzqjbx;
	
	private java.lang.String tfr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date tfrq;
	
	private java.lang.String kssyj;
	
	private java.lang.String zsjcrymd;
	
	private java.lang.String jdjg;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jdsj;
	
	private java.lang.String nr;
	
	private java.lang.String shdbr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date cssj;
	
	private java.lang.String pzjg;
	
	private java.lang.String pzr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date pzsj;
	
	private java.lang.String xb;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date csrq;
	
	private java.lang.String zjlx;
	
	private java.lang.String zjhm;
	
	private java.lang.String lxfs;
	
	private java.lang.String zyjwzxly;
	
	private java.lang.String jlbbh;
	
	private java.lang.String shr;
	
	private java.lang.String shyj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date shsj;
	
	private java.lang.String jdresult;
	//columns END

	 

	public Kss_JwzxjlModel(){
	}
	public Kss_JwzxjlModel(String id) {
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
	public java.lang.String getSqyy() {
		return this.sqyy;
	}
	
	public void setSqyy(java.lang.String value) {
		this.sqyy = value;
	}
	public java.lang.String getSqrxm() {
		return this.sqrxm;
	}
	
	public void setSqrxm(java.lang.String value) {
		this.sqrxm = value;
	}
	
	public java.util.Date getSqsj() {
		return this.sqsj;
	}
	
	public void setSqsj(java.util.Date value) {
		this.sqsj = value;
	}
	
	public java.lang.String getSqrzjh() {
		return this.sqrzjh;
	}
	
	public void setSqrzjh(java.lang.String value) {
		this.sqrzjh = value;
	}
	public java.lang.String getSpjg() {
		return this.spjg;
	}
	
	public void setSpjg(java.lang.String value) {
		this.spjg = value;
	}
	public java.lang.String getLdxm() {
		return this.ldxm;
	}
	
	public void setLdxm(java.lang.String value) {
		this.ldxm = value;
	}
	
	public java.util.Date getLdpssj() {
		return this.ldpssj;
	}
	
	public void setLdpssj(java.util.Date value) {
		this.ldpssj = value;
	}
	
	public java.lang.String getLdyj() {
		return this.ldyj;
	}
	
	public void setLdyj(java.lang.String value) {
		this.ldyj = value;
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
	
	public java.lang.String getSqrlx() {
		return this.sqrlx;
	}
	
	public void setSqrlx(java.lang.String value) {
		this.sqrlx = value;
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
	public java.lang.String getFlws() {
		return this.flws;
	}
	
	public void setFlws(java.lang.String value) {
		this.flws = value;
	}
	public java.lang.String getPzh() {
		return this.pzh;
	}
	
	public void setPzh(java.lang.String value) {
		this.pzh = value;
	}
	public java.lang.String getSqlx() {
		return this.sqlx;
	}
	
	public void setSqlx(java.lang.String value) {
		this.sqlx = value;
	}
	public java.lang.String getClsx() {
		return this.clsx;
	}
	
	public void setClsx(java.lang.String value) {
		this.clsx = value;
	}
	
	public java.util.Date getNcssj() {
		return this.ncssj;
	}
	
	public void setNcssj(java.util.Date value) {
		this.ncssj = value;
	}
	
	
	public java.util.Date getNcsqx() {
		return this.ncsqx;
	}
	
	public void setNcsqx(java.util.Date value) {
		this.ncsqx = value;
	}
	
	public java.lang.String getDbr() {
		return this.dbr;
	}
	
	public void setDbr(java.lang.String value) {
		this.dbr = value;
	}
	public java.lang.String getDbrdz() {
		return this.dbrdz;
	}
	
	public void setDbrdz(java.lang.String value) {
		this.dbrdz = value;
	}
	public java.lang.String getDbrdw() {
		return this.dbrdw;
	}
	
	public void setDbrdw(java.lang.String value) {
		this.dbrdw = value;
	}
	public java.lang.String getDbryxyrgx() {
		return this.dbryxyrgx;
	}
	
	public void setDbryxyrgx(java.lang.String value) {
		this.dbryxyrgx = value;
	}
	public java.lang.String getGzqjbx() {
		return this.gzqjbx;
	}
	
	public void setGzqjbx(java.lang.String value) {
		this.gzqjbx = value;
	}
	public java.lang.String getTfr() {
		return this.tfr;
	}
	
	public void setTfr(java.lang.String value) {
		this.tfr = value;
	}
	
	public java.util.Date getTfrq() {
		return this.tfrq;
	}
	
	public void setTfrq(java.util.Date value) {
		this.tfrq = value;
	}
	
	public java.lang.String getKssyj() {
		return this.kssyj;
	}
	
	public void setKssyj(java.lang.String value) {
		this.kssyj = value;
	}
	public java.lang.String getZsjcrymd() {
		return this.zsjcrymd;
	}
	
	public void setZsjcrymd(java.lang.String value) {
		this.zsjcrymd = value;
	}
	public java.lang.String getJdjg() {
		return this.jdjg;
	}
	
	public void setJdjg(java.lang.String value) {
		this.jdjg = value;
	}
	
	public java.util.Date getJdsj() {
		return this.jdsj;
	}
	
	public void setJdsj(java.util.Date value) {
		this.jdsj = value;
	}
	
	public java.lang.String getNr() {
		return this.nr;
	}
	
	public void setNr(java.lang.String value) {
		this.nr = value;
	}
	public java.lang.String getShdbr() {
		return this.shdbr;
	}
	
	public void setShdbr(java.lang.String value) {
		this.shdbr = value;
	}
	
	public java.util.Date getCssj() {
		return this.cssj;
	}
	
	public void setCssj(java.util.Date value) {
		this.cssj = value;
	}
	
	public java.lang.String getPzjg() {
		return this.pzjg;
	}
	
	public void setPzjg(java.lang.String value) {
		this.pzjg = value;
	}
	public java.lang.String getPzr() {
		return this.pzr;
	}
	
	public void setPzr(java.lang.String value) {
		this.pzr = value;
	}
	
	public java.util.Date getPzsj() {
		return this.pzsj;
	}
	
	public void setPzsj(java.util.Date value) {
		this.pzsj = value;
	}
	
	public java.lang.String getXb() {
		return this.xb;
	}
	
	public void setXb(java.lang.String value) {
		this.xb = value;
	}
	
	public java.util.Date getCsrq() {
		return this.csrq;
	}
	
	public void setCsrq(java.util.Date value) {
		this.csrq = value;
	}
	
	public java.lang.String getZjlx() {
		return this.zjlx;
	}
	
	public void setZjlx(java.lang.String value) {
		this.zjlx = value;
	}
	public java.lang.String getZjhm() {
		return this.zjhm;
	}
	
	public void setZjhm(java.lang.String value) {
		this.zjhm = value;
	}
	public java.lang.String getLxfs() {
		return this.lxfs;
	}
	
	public void setLxfs(java.lang.String value) {
		this.lxfs = value;
	}
	public java.lang.String getZyjwzxly() {
		return this.zyjwzxly;
	}
	
	public void setZyjwzxly(java.lang.String value) {
		this.zyjwzxly = value;
	}
	public java.lang.String getJlbbh() {
		return this.jlbbh;
	}
	
	public void setJlbbh(java.lang.String value) {
		this.jlbbh = value;
	}
	public java.lang.String getShr() {
		return this.shr;
	}
	
	public void setShr(java.lang.String value) {
		this.shr = value;
	}
	public java.lang.String getShyj() {
		return this.shyj;
	}
	
	public void setShyj(java.lang.String value) {
		this.shyj = value;
	}
	
	public java.util.Date getShsj() {
		return this.shsj;
	}
	
	public void setShsj(java.util.Date value) {
		this.shsj = value;
	}
	
	public java.lang.String getJdresult() {
		return this.jdresult;
	}
	
	public void setJdresult(java.lang.String value) {
		this.jdresult = value;
	}
	 
}

