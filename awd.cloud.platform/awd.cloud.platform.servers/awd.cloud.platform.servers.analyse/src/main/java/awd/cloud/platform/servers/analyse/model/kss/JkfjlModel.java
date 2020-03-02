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
public class JkfjlModel implements Model {

	
	//columns START
	
	private String id;


	private String rybh;

	private String type;

	private String jkffs;

	private String jkfz;

	private String jkfsy;

	private String bz;

	private String jsbh;

	private String state;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;

	private String scbz;

	private String zdzyj;

	private String ldyj;

	private String ywlcid;

	private String psbz;

	private String zdzxm;

	private String ldxm;

	private String creator;

	private String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;

	private String jsh;

	private String ly;

	private String jbr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date szrq;

	private String gjxm;

	private String gjyj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date gjpssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ldpssj;

	private String qtqk;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date zdzpssj;
	//columns END



	public JkfjlModel(){
	}
	public JkfjlModel(String id) {
		this.id = id;
	}


	public void setId(String value) {
		this.id = value;
	}

	public String getId() {
		return this.id;
	}

	public String getRybh() {
		return this.rybh;
	}

	public void setRybh(String value) {
		this.rybh = value;
	}
	public String getType() {
		return this.type;
	}

	public void setType(String value) {
		this.type = value;
	}
	public String getJkffs() {
		return this.jkffs;
	}

	public void setJkffs(String value) {
		this.jkffs = value;
	}
	public String getJkfz() {
		return this.jkfz;
	}

	public void setJkfz(String value) {
		this.jkfz = value;
	}
	public String getJkfsy() {
		return this.jkfsy;
	}

	public void setJkfsy(String value) {
		this.jkfsy = value;
	}
	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}
	public String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(String value) {
		this.jsbh = value;
	}
	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
	}

	public java.util.Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}

	public String getScbz() {
		return this.scbz;
	}

	public void setScbz(String value) {
		this.scbz = value;
	}
	public String getZdzyj() {
		return this.zdzyj;
	}

	public void setZdzyj(String value) {
		this.zdzyj = value;
	}
	public String getLdyj() {
		return this.ldyj;
	}

	public void setLdyj(String value) {
		this.ldyj = value;
	}
	public String getYwlcid() {
		return this.ywlcid;
	}

	public void setYwlcid(String value) {
		this.ywlcid = value;
	}
	public String getPsbz() {
		return this.psbz;
	}

	public void setPsbz(String value) {
		this.psbz = value;
	}
	public String getZdzxm() {
		return this.zdzxm;
	}

	public void setZdzxm(String value) {
		this.zdzxm = value;
	}
	public String getLdxm() {
		return this.ldxm;
	}

	public void setLdxm(String value) {
		this.ldxm = value;
	}
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String value) {
		this.creator = value;
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

	public String getJsh() {
		return this.jsh;
	}

	public void setJsh(String value) {
		this.jsh = value;
	}
	public String getLy() {
		return this.ly;
	}

	public void setLy(String value) {
		this.ly = value;
	}
	public String getJbr() {
		return this.jbr;
	}

	public void setJbr(String value) {
		this.jbr = value;
	}

	public java.util.Date getSzrq() {
		return this.szrq;
	}

	public void setSzrq(java.util.Date value) {
		this.szrq = value;
	}

	public String getGjxm() {
		return this.gjxm;
	}

	public void setGjxm(String value) {
		this.gjxm = value;
	}
	public String getGjyj() {
		return this.gjyj;
	}

	public void setGjyj(String value) {
		this.gjyj = value;
	}

	public java.util.Date getGjpssj() {
		return this.gjpssj;
	}

	public void setGjpssj(java.util.Date value) {
		this.gjpssj = value;
	}


	public java.util.Date getLdpssj() {
		return this.ldpssj;
	}

	public void setLdpssj(java.util.Date value) {
		this.ldpssj = value;
	}

	public String getQtqk() {
		return this.qtqk;
	}

	public void setQtqk(String value) {
		this.qtqk = value;
	}
	
	public java.util.Date getZdzpssj() {
		return this.zdzpssj;
	}
	
	public void setZdzpssj(java.util.Date value) {
		this.zdzpssj = value;
	}
	
	 
}

