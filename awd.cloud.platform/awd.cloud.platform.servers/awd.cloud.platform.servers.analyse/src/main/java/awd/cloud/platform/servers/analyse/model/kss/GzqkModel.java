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
public class GzqkModel implements Model {

	
	//columns START
	
	private String id;


	private String jsbh;

	private String bbzmbxm;

	private String bbzmbrybh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date bzsj;

	private String sbzrw;

	private String wcqk;

	private String bzr;

	private String bzdx;

	private String bzdxbh;

	private String jtsm;

	private String state;

	private String yxzt;

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
	//columns END



	public GzqkModel(){
	}
	public GzqkModel(String id) {
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
	public String getBbzmbxm() {
		return this.bbzmbxm;
	}

	public void setBbzmbxm(String value) {
		this.bbzmbxm = value;
	}
	public String getBbzmbrybh() {
		return this.bbzmbrybh;
	}

	public void setBbzmbrybh(String value) {
		this.bbzmbrybh = value;
	}

	public java.util.Date getBzsj() {
		return this.bzsj;
	}

	public void setBzsj(java.util.Date value) {
		this.bzsj = value;
	}

	public String getSbzrw() {
		return this.sbzrw;
	}

	public void setSbzrw(String value) {
		this.sbzrw = value;
	}
	public String getWcqk() {
		return this.wcqk;
	}

	public void setWcqk(String value) {
		this.wcqk = value;
	}
	public String getBzr() {
		return this.bzr;
	}

	public void setBzr(String value) {
		this.bzr = value;
	}
	public String getBzdx() {
		return this.bzdx;
	}

	public void setBzdx(String value) {
		this.bzdx = value;
	}
	public String getBzdxbh() {
		return this.bzdxbh;
	}

	public void setBzdxbh(String value) {
		this.bzdxbh = value;
	}
	public String getJtsm() {
		return this.jtsm;
	}

	public void setJtsm(String value) {
		this.jtsm = value;
	}
	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
	}
	public String getYxzt() {
		return this.yxzt;
	}

	public void setYxzt(String value) {
		this.yxzt = value;
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
	
	 
}

