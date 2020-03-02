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
public class MjshgxModel implements Model {

	
	//columns START
	
	private String id;


	private String jsbh;

	private String jh;

	private String gx;

	private String gxrxm;

	private String gxrzzmm;

	private String gxrlxdh;

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

	private String gxrgzdw;
	//columns END



	public MjshgxModel(){
	}
	public MjshgxModel(String id) {
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
	public String getGx() {
		return this.gx;
	}

	public void setGx(String value) {
		this.gx = value;
	}
	public String getGxrxm() {
		return this.gxrxm;
	}

	public void setGxrxm(String value) {
		this.gxrxm = value;
	}
	public String getGxrzzmm() {
		return this.gxrzzmm;
	}

	public void setGxrzzmm(String value) {
		this.gxrzzmm = value;
	}
	public String getGxrlxdh() {
		return this.gxrlxdh;
	}

	public void setGxrlxdh(String value) {
		this.gxrlxdh = value;
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

	public String getGxrgzdw() {
		return this.gxrgzdw;
	}

	public void setGxrgzdw(String value) {
		this.gxrgzdw = value;
	}
	 
}

