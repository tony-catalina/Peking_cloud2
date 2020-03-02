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


public class Kss_GwjjbModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String jbgw;
	
	private java.lang.String jbry;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jbsj;
	
	private java.lang.String gzjl;
	
	private java.lang.String zysx;
	
	private java.lang.String ldyj;
	
	private java.lang.String ldps;
	
	private java.lang.String ldqm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date qrsj;
	
	private java.lang.String jjbgw;
	
	private java.lang.String jjbry;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jjbsj;
	
	private java.lang.String jgzjl;
	
	private java.lang.String jzysx;
	
	private java.lang.String jbldyj;
	
	private java.lang.String jbldps;
	
	private java.lang.String jbldqm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jbqrsj;
	
	private java.lang.String ywlcid;
	
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

	 

	public Kss_GwjjbModel(){
	}
	public Kss_GwjjbModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	public java.lang.String getJbgw() {
		return this.jbgw;
	}
	
	public void setJbgw(java.lang.String value) {
		this.jbgw = value;
	}
	public java.lang.String getJbry() {
		return this.jbry;
	}
	
	public void setJbry(java.lang.String value) {
		this.jbry = value;
	}
	
	public java.util.Date getJbsj() {
		return this.jbsj;
	}
	
	public void setJbsj(java.util.Date value) {
		this.jbsj = value;
	}
	
	public java.lang.String getGzjl() {
		return this.gzjl;
	}
	
	public void setGzjl(java.lang.String value) {
		this.gzjl = value;
	}
	public java.lang.String getZysx() {
		return this.zysx;
	}
	
	public void setZysx(java.lang.String value) {
		this.zysx = value;
	}
	public java.lang.String getLdyj() {
		return this.ldyj;
	}
	
	public void setLdyj(java.lang.String value) {
		this.ldyj = value;
	}
	public java.lang.String getLdps() {
		return this.ldps;
	}
	
	public void setLdps(java.lang.String value) {
		this.ldps = value;
	}
	public java.lang.String getLdqm() {
		return this.ldqm;
	}
	
	public void setLdqm(java.lang.String value) {
		this.ldqm = value;
	}
	
	public java.util.Date getQrsj() {
		return this.qrsj;
	}
	
	public void setQrsj(java.util.Date value) {
		this.qrsj = value;
	}
	
	public java.lang.String getJjbgw() {
		return this.jjbgw;
	}
	
	public void setJjbgw(java.lang.String value) {
		this.jjbgw = value;
	}
	public java.lang.String getJjbry() {
		return this.jjbry;
	}
	
	public void setJjbry(java.lang.String value) {
		this.jjbry = value;
	}
	
	public java.util.Date getJjbsj() {
		return this.jjbsj;
	}
	
	public void setJjbsj(java.util.Date value) {
		this.jjbsj = value;
	}
	
	public java.lang.String getJgzjl() {
		return this.jgzjl;
	}
	
	public void setJgzjl(java.lang.String value) {
		this.jgzjl = value;
	}
	public java.lang.String getJzysx() {
		return this.jzysx;
	}
	
	public void setJzysx(java.lang.String value) {
		this.jzysx = value;
	}
	public java.lang.String getJbldyj() {
		return this.jbldyj;
	}
	
	public void setJbldyj(java.lang.String value) {
		this.jbldyj = value;
	}
	public java.lang.String getJbldps() {
		return this.jbldps;
	}
	
	public void setJbldps(java.lang.String value) {
		this.jbldps = value;
	}
	public java.lang.String getJbldqm() {
		return this.jbldqm;
	}
	
	public void setJbldqm(java.lang.String value) {
		this.jbldqm = value;
	}
	
	public java.util.Date getJbqrsj() {
		return this.jbqrsj;
	}
	
	public void setJbqrsj(java.util.Date value) {
		this.jbqrsj = value;
	}
	
	public java.lang.String getYwlcid() {
		return this.ywlcid;
	}
	
	public void setYwlcid(java.lang.String value) {
		this.ywlcid = value;
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

