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
public class JwzxjlModel implements Model {

	
	//columns START
	
	private String id;


	private String jsbh;

	private String rybh;

	private String sqyy;

	private String sqrxm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date sqsj;

	private String sqrzjh;

	private String spjg;

	private String ldxm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ldpssj;

	private String ldyj;

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

	private String sqrlx;

	private String bz;

	private String ywlcid;
	//columns END



	public JwzxjlModel(){
	}
	public JwzxjlModel(String id) {
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
	public String getSqyy() {
		return this.sqyy;
	}

	public void setSqyy(String value) {
		this.sqyy = value;
	}
	public String getSqrxm() {
		return this.sqrxm;
	}

	public void setSqrxm(String value) {
		this.sqrxm = value;
	}

	public java.util.Date getSqsj() {
		return this.sqsj;
	}

	public void setSqsj(java.util.Date value) {
		this.sqsj = value;
	}

	public String getSqrzjh() {
		return this.sqrzjh;
	}

	public void setSqrzjh(String value) {
		this.sqrzjh = value;
	}
	public String getSpjg() {
		return this.spjg;
	}

	public void setSpjg(String value) {
		this.spjg = value;
	}
	public String getLdxm() {
		return this.ldxm;
	}

	public void setLdxm(String value) {
		this.ldxm = value;
	}

	public java.util.Date getLdpssj() {
		return this.ldpssj;
	}

	public void setLdpssj(java.util.Date value) {
		this.ldpssj = value;
	}

	public String getLdyj() {
		return this.ldyj;
	}

	public void setLdyj(String value) {
		this.ldyj = value;
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

	public String getSqrlx() {
		return this.sqrlx;
	}

	public void setSqrlx(String value) {
		this.sqrlx = value;
	}
	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}
	public String getYwlcid() {
		return this.ywlcid;
	}

	public void setYwlcid(String value) {
		this.ywlcid = value;
	}
	 
}

