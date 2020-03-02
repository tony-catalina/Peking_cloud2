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
public class CypjModel implements Model {

	
	//columns START
	
	private String id;


	private String rybh;

	private String jsbh;

	private String jsh;

	private String kpsx;

	private java.math.BigDecimal ljkhzf;

	private java.math.BigDecimal ljkhpjf;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date khksrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date pjrq;

	private String zhpdyj;

	private String dqssjd;

	private String jkfjl;

	private String bz;

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



	public CypjModel(){
	}
	public CypjModel(String id) {
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
	public String getJsh() {
		return this.jsh;
	}

	public void setJsh(String value) {
		this.jsh = value;
	}
	public String getKpsx() {
		return this.kpsx;
	}

	public void setKpsx(String value) {
		this.kpsx = value;
	}
	public java.math.BigDecimal getLjkhzf() {
		return this.ljkhzf;
	}

	public void setLjkhzf(java.math.BigDecimal value) {
		this.ljkhzf = value;
	}
	public java.math.BigDecimal getLjkhpjf() {
		return this.ljkhpjf;
	}

	public void setLjkhpjf(java.math.BigDecimal value) {
		this.ljkhpjf = value;
	}

	public java.util.Date getKhksrq() {
		return this.khksrq;
	}

	public void setKhksrq(java.util.Date value) {
		this.khksrq = value;
	}


	public java.util.Date getPjrq() {
		return this.pjrq;
	}

	public void setPjrq(java.util.Date value) {
		this.pjrq = value;
	}

	public String getZhpdyj() {
		return this.zhpdyj;
	}

	public void setZhpdyj(String value) {
		this.zhpdyj = value;
	}
	public String getDqssjd() {
		return this.dqssjd;
	}

	public void setDqssjd(String value) {
		this.dqssjd = value;
	}
	public String getJkfjl() {
		return this.jkfjl;
	}

	public void setJkfjl(String value) {
		this.jkfjl = value;
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

