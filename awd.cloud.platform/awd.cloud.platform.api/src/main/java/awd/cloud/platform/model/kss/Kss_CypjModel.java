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


public class Kss_CypjModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String rybh;
	
	private java.lang.String jsbh;
	
	private java.lang.String jsh;
	
	private java.lang.String kpsx;
	
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
	
	private java.lang.String zhpdyj;
	
	private java.lang.String dqssjd;
	
	private java.lang.String jkfjl;
	
	private java.lang.String bz;
	
	private java.lang.String state;
	
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
	//columns END

	 

	public Kss_CypjModel(){
	}
	public Kss_CypjModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getRybh() {
		return this.rybh;
	}
	
	public void setRybh(java.lang.String value) {
		this.rybh = value;
	}
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	public java.lang.String getJsh() {
		return this.jsh;
	}
	
	public void setJsh(java.lang.String value) {
		this.jsh = value;
	}
	public java.lang.String getKpsx() {
		return this.kpsx;
	}
	
	public void setKpsx(java.lang.String value) {
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
	
	public java.lang.String getZhpdyj() {
		return this.zhpdyj;
	}
	
	public void setZhpdyj(java.lang.String value) {
		this.zhpdyj = value;
	}
	public java.lang.String getDqssjd() {
		return this.dqssjd;
	}
	
	public void setDqssjd(java.lang.String value) {
		this.dqssjd = value;
	}
	public java.lang.String getJkfjl() {
		return this.jkfjl;
	}
	
	public void setJkfjl(java.lang.String value) {
		this.jkfjl = value;
	}
	public java.lang.String getBz() {
		return this.bz;
	}
	
	public void setBz(java.lang.String value) {
		this.bz = value;
	}
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
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
	
	 
}

