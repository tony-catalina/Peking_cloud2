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
public class XjzcModel implements Model {

	
	//columns START
	
	private String id;


	private String rybh;

	private String jsbh;

	private String currency;

	private java.math.BigDecimal xfje;

	private String xflx;

	private java.math.BigDecimal xfqje;

	private java.math.BigDecimal xfhye;

	private String zy;

	private String blr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date blsj;

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



	public XjzcModel(){
	}
	public XjzcModel(String id) {
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
	public String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(String value) {
		this.jsbh = value;
	}
	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String value) {
		this.currency = value;
	}
	public java.math.BigDecimal getXfje() {
		return this.xfje;
	}

	public void setXfje(java.math.BigDecimal value) {
		this.xfje = value;
	}
	public String getXflx() {
		return this.xflx;
	}

	public void setXflx(String value) {
		this.xflx = value;
	}
	public java.math.BigDecimal getXfqje() {
		return this.xfqje;
	}

	public void setXfqje(java.math.BigDecimal value) {
		this.xfqje = value;
	}
	public java.math.BigDecimal getXfhye() {
		return this.xfhye;
	}

	public void setXfhye(java.math.BigDecimal value) {
		this.xfhye = value;
	}
	public String getZy() {
		return this.zy;
	}

	public void setZy(String value) {
		this.zy = value;
	}
	public String getBlr() {
		return this.blr;
	}

	public void setBlr(String value) {
		this.blr = value;
	}

	public java.util.Date getBlsj() {
		return this.blsj;
	}

	public void setBlsj(java.util.Date value) {
		this.blsj = value;
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

