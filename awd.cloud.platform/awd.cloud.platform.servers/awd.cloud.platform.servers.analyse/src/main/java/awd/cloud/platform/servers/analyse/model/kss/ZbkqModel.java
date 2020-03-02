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
public class ZbkqModel implements Model {
	
	//columns START
	
	private String id;


	private String jsbh;

	private String jh;

	private String xm;

	private String zbrs;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date zbsj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jbsj;

	private String bdqk;

	private String cljs;

	private String xsjl;

	private String jjsx;

	private String bz;

	private String state;

	private String jbr;

	private String jbrs;

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



	public ZbkqModel(){
	}
	public ZbkqModel(String id) {
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
	public String getJh() {
		return this.jh;
	}

	public void setJh(String value) {
		this.jh = value;
	}
	public String getXm() {
		return this.xm;
	}

	public void setXm(String value) {
		this.xm = value;
	}
	public String getZbrs() {
		return this.zbrs;
	}

	public void setZbrs(String value) {
		this.zbrs = value;
	}

	public java.util.Date getZbsj() {
		return this.zbsj;
	}

	public void setZbsj(java.util.Date value) {
		this.zbsj = value;
	}


	public java.util.Date getJbsj() {
		return this.jbsj;
	}

	public void setJbsj(java.util.Date value) {
		this.jbsj = value;
	}

	public String getBdqk() {
		return this.bdqk;
	}

	public void setBdqk(String value) {
		this.bdqk = value;
	}
	public String getCljs() {
		return this.cljs;
	}

	public void setCljs(String value) {
		this.cljs = value;
	}
	public String getXsjl() {
		return this.xsjl;
	}

	public void setXsjl(String value) {
		this.xsjl = value;
	}
	public String getJjsx() {
		return this.jjsx;
	}

	public void setJjsx(String value) {
		this.jjsx = value;
	}
	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}
	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
	}
	public String getJbr() {
		return this.jbr;
	}

	public void setJbr(String value) {
		this.jbr = value;
	}
	public String getJbrs() {
		return this.jbrs;
	}

	public void setJbrs(String value) {
		this.jbrs = value;
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

