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
public class PurchaseModel implements Model {

	
	//columns START
	
	private String id;


	private String jsbh;

	private String jhdh;

	private String bh;

	private String hwlx;

	private String splx;

	private String yplx;

	private Short sl;

	private String dw;

	private Short hwdj;

	private Integer zj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jhrq;

	private String sfyx;

	private String state;

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



	public PurchaseModel(){
	}
	public PurchaseModel(String id) {
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
	public String getJhdh() {
		return this.jhdh;
	}

	public void setJhdh(String value) {
		this.jhdh = value;
	}
	public String getBh() {
		return this.bh;
	}

	public void setBh(String value) {
		this.bh = value;
	}
	public String getHwlx() {
		return this.hwlx;
	}

	public void setHwlx(String value) {
		this.hwlx = value;
	}
	public String getSplx() {
		return this.splx;
	}

	public void setSplx(String value) {
		this.splx = value;
	}
	public String getYplx() {
		return this.yplx;
	}

	public void setYplx(String value) {
		this.yplx = value;
	}
	public Short getSl() {
		return this.sl;
	}

	public void setSl(Short value) {
		this.sl = value;
	}
	public String getDw() {
		return this.dw;
	}

	public void setDw(String value) {
		this.dw = value;
	}
	public Short getHwdj() {
		return this.hwdj;
	}

	public void setHwdj(Short value) {
		this.hwdj = value;
	}
	public Integer getZj() {
		return this.zj;
	}

	public void setZj(Integer value) {
		this.zj = value;
	}

	public java.util.Date getJhrq() {
		return this.jhrq;
	}

	public void setJhrq(java.util.Date value) {
		this.jhrq = value;
	}

	public String getSfyx() {
		return this.sfyx;
	}

	public void setSfyx(String value) {
		this.sfyx = value;
	}
	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
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

