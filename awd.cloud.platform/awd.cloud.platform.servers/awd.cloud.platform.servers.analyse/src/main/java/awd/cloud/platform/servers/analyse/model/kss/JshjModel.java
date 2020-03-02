/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.model.kss;

import awd.framework.common.model.Model;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class JshjModel implements Model {
	

	
	//columns START
	
	private String id;


	private String jsbh;

	private String rybh;

	private String jszjh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date yyhjsj;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hjsj;

	private String hjsjcd;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jssj;

	private String ly;

	private Byte rs;

	private String dcmj;

	private String sjmj;

	private String bllx;

	private String ldxm;

	private String ldyj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ldpssj;

	private String cxly;

	private String cxr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date cxsj;

	private String jsbz;

	private String pastable;

	private String ywlcid;

	private String state;

	private String bz;

	private String creator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;

	private String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;

	private String psbz;

	private String hjs;

	private String tbjcjg;

	private String wjpjcjg;

	private String zjlx;

	private String bhjrgx;

	private String lxfs;

	private String xm;
	//columns END



	public JshjModel(){
	}
	public JshjModel(String id) {
		this.id = id;
	}


	public void setId(String value) {
		this.id = value;
	}

	public String getId() {
		return this.id;
	}

	public String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(String value) {
		this.jsbh = value;
	}
	public String getRybh() {
		return this.rybh;
	}

	public void setRybh(String value) {
		this.rybh = value;
	}
	public String getJszjh() {
		return this.jszjh;
	}

	public void setJszjh(String value) {
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

	public String getHjsjcd() {
		return this.hjsjcd;
	}

	public void setHjsjcd(String value) {
		this.hjsjcd = value;
	}

	public java.util.Date getJssj() {
		return this.jssj;
	}

	public void setJssj(java.util.Date value) {
		this.jssj = value;
	}

	public String getLy() {
		return this.ly;
	}

	public void setLy(String value) {
		this.ly = value;
	}
	public Byte getRs() {
		return this.rs;
	}

	public void setRs(Byte value) {
		this.rs = value;
	}
	public String getDcmj() {
		return this.dcmj;
	}

	public void setDcmj(String value) {
		this.dcmj = value;
	}
	public String getSjmj() {
		return this.sjmj;
	}

	public void setSjmj(String value) {
		this.sjmj = value;
	}
	public String getBllx() {
		return this.bllx;
	}

	public void setBllx(String value) {
		this.bllx = value;
	}
	public String getLdxm() {
		return this.ldxm;
	}

	public void setLdxm(String value) {
		this.ldxm = value;
	}
	public String getLdyj() {
		return this.ldyj;
	}

	public void setLdyj(String value) {
		this.ldyj = value;
	}

	public java.util.Date getLdpssj() {
		return this.ldpssj;
	}

	public void setLdpssj(java.util.Date value) {
		this.ldpssj = value;
	}

	public String getCxly() {
		return this.cxly;
	}

	public void setCxly(String value) {
		this.cxly = value;
	}
	public String getCxr() {
		return this.cxr;
	}

	public void setCxr(String value) {
		this.cxr = value;
	}

	public java.util.Date getCxsj() {
		return this.cxsj;
	}

	public void setCxsj(java.util.Date value) {
		this.cxsj = value;
	}

	public String getJsbz() {
		return this.jsbz;
	}

	public void setJsbz(String value) {
		this.jsbz = value;
	}
	public String getPastable() {
		return this.pastable;
	}

	public void setPastable(String value) {
		this.pastable = value;
	}
	public String getYwlcid() {
		return this.ywlcid;
	}

	public void setYwlcid(String value) {
		this.ywlcid = value;
	}
	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
	}
	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String value) {
		this.creator = value;
	}

	public java.util.Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}

	public String getUpdator() {
		return this.updator;
	}

	public void setUpdator(String value) {
		this.updator = value;
	}

	public java.util.Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(java.util.Date value) {
		this.updatetime = value;
	}

	public String getPsbz() {
		return this.psbz;
	}

	public void setPsbz(String value) {
		this.psbz = value;
	}
	public String getHjs() {
		return this.hjs;
	}

	public void setHjs(String value) {
		this.hjs = value;
	}
	public String getTbjcjg() {
		return this.tbjcjg;
	}

	public void setTbjcjg(String value) {
		this.tbjcjg = value;
	}
	public String getWjpjcjg() {
		return this.wjpjcjg;
	}

	public void setWjpjcjg(String value) {
		this.wjpjcjg = value;
	}
	public String getZjlx() {
		return this.zjlx;
	}

	public void setZjlx(String value) {
		this.zjlx = value;
	}
	public String getBhjrgx() {
		return this.bhjrgx;
	}

	public void setBhjrgx(String value) {
		this.bhjrgx = value;
	}
	public String getLxfs() {
		return this.lxfs;
	}

	public void setLxfs(String value) {
		this.lxfs = value;
	}
	public String getXm() {
		return this.xm;
	}

	public void setXm(String value) {
		this.xm = value;
	}
	 
}

