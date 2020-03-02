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


public class Kss_JbjlModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String sid;
	
	private java.lang.String jq;
	
	private java.lang.String jbr;
	
	private java.lang.String jsr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jjbsj;
	
	private java.lang.String jbqk;
	
	private java.lang.String gjly;
	
	private java.lang.String ysly;
	
	private java.lang.String zdzly;
	
	private java.lang.String jbly;
	
	private java.lang.Short lastnum;
	
	private java.lang.Short mustnum;
	
	private java.lang.Short currnum;
	
	private java.lang.Short todayinnum;
	
	private java.lang.Short todayoutnum;
	
	private java.lang.Short jstzinnum;
	
	private java.lang.Short jstzoutnum;
	
	private java.lang.Short tsnum;
	
	private java.lang.Short lshjnum;
	
	private java.lang.Short jshjnum;
	
	private java.lang.Short lslsnum;
	
	private java.lang.Short swjynum;
	
	private java.lang.Short todaypjnum;
	
	private java.lang.Short zdrynum;
	
	private java.lang.Short zdbhnum;
	
	private java.lang.Short syjjnum;
	
	private java.lang.Short jbnum;
	
	private java.lang.Short ygnum;
	
	private java.lang.Short sknum;
	
	private java.lang.Short jlnum;
	
	private java.lang.Short jsnum;
	
	private java.lang.Short ysnum;
	
	private java.lang.Short ssdnum;
	
	private java.lang.Short dtnum;
	
	private java.lang.Short jjnum;
	
	private java.lang.String flag;
	
	private java.lang.String ywyc;
	
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

	 

	public Kss_JbjlModel(){
	}
	public Kss_JbjlModel(String id) {
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
	public java.lang.String getSid() {
		return this.sid;
	}
	
	public void setSid(java.lang.String value) {
		this.sid = value;
	}
	public java.lang.String getJq() {
		return this.jq;
	}
	
	public void setJq(java.lang.String value) {
		this.jq = value;
	}
	public java.lang.String getJbr() {
		return this.jbr;
	}
	
	public void setJbr(java.lang.String value) {
		this.jbr = value;
	}
	public java.lang.String getJsr() {
		return this.jsr;
	}
	
	public void setJsr(java.lang.String value) {
		this.jsr = value;
	}
	
	public java.util.Date getJjbsj() {
		return this.jjbsj;
	}
	
	public void setJjbsj(java.util.Date value) {
		this.jjbsj = value;
	}
	
	public java.lang.String getJbqk() {
		return this.jbqk;
	}
	
	public void setJbqk(java.lang.String value) {
		this.jbqk = value;
	}
	public java.lang.String getGjly() {
		return this.gjly;
	}
	
	public void setGjly(java.lang.String value) {
		this.gjly = value;
	}
	public java.lang.String getYsly() {
		return this.ysly;
	}
	
	public void setYsly(java.lang.String value) {
		this.ysly = value;
	}
	public java.lang.String getZdzly() {
		return this.zdzly;
	}
	
	public void setZdzly(java.lang.String value) {
		this.zdzly = value;
	}
	public java.lang.String getJbly() {
		return this.jbly;
	}
	
	public void setJbly(java.lang.String value) {
		this.jbly = value;
	}
	public java.lang.Short getLastnum() {
		return this.lastnum;
	}
	
	public void setLastnum(java.lang.Short value) {
		this.lastnum = value;
	}
	public java.lang.Short getMustnum() {
		return this.mustnum;
	}
	
	public void setMustnum(java.lang.Short value) {
		this.mustnum = value;
	}
	public java.lang.Short getCurrnum() {
		return this.currnum;
	}
	
	public void setCurrnum(java.lang.Short value) {
		this.currnum = value;
	}
	public java.lang.Short getTodayinnum() {
		return this.todayinnum;
	}
	
	public void setTodayinnum(java.lang.Short value) {
		this.todayinnum = value;
	}
	public java.lang.Short getTodayoutnum() {
		return this.todayoutnum;
	}
	
	public void setTodayoutnum(java.lang.Short value) {
		this.todayoutnum = value;
	}
	public java.lang.Short getJstzinnum() {
		return this.jstzinnum;
	}
	
	public void setJstzinnum(java.lang.Short value) {
		this.jstzinnum = value;
	}
	public java.lang.Short getJstzoutnum() {
		return this.jstzoutnum;
	}
	
	public void setJstzoutnum(java.lang.Short value) {
		this.jstzoutnum = value;
	}
	public java.lang.Short getTsnum() {
		return this.tsnum;
	}
	
	public void setTsnum(java.lang.Short value) {
		this.tsnum = value;
	}
	public java.lang.Short getLshjnum() {
		return this.lshjnum;
	}
	
	public void setLshjnum(java.lang.Short value) {
		this.lshjnum = value;
	}
	public java.lang.Short getJshjnum() {
		return this.jshjnum;
	}
	
	public void setJshjnum(java.lang.Short value) {
		this.jshjnum = value;
	}
	public java.lang.Short getLslsnum() {
		return this.lslsnum;
	}
	
	public void setLslsnum(java.lang.Short value) {
		this.lslsnum = value;
	}
	public java.lang.Short getSwjynum() {
		return this.swjynum;
	}
	
	public void setSwjynum(java.lang.Short value) {
		this.swjynum = value;
	}
	public java.lang.Short getTodaypjnum() {
		return this.todaypjnum;
	}
	
	public void setTodaypjnum(java.lang.Short value) {
		this.todaypjnum = value;
	}
	public java.lang.Short getZdrynum() {
		return this.zdrynum;
	}
	
	public void setZdrynum(java.lang.Short value) {
		this.zdrynum = value;
	}
	public java.lang.Short getZdbhnum() {
		return this.zdbhnum;
	}
	
	public void setZdbhnum(java.lang.Short value) {
		this.zdbhnum = value;
	}
	public java.lang.Short getSyjjnum() {
		return this.syjjnum;
	}
	
	public void setSyjjnum(java.lang.Short value) {
		this.syjjnum = value;
	}
	public java.lang.Short getJbnum() {
		return this.jbnum;
	}
	
	public void setJbnum(java.lang.Short value) {
		this.jbnum = value;
	}
	public java.lang.Short getYgnum() {
		return this.ygnum;
	}
	
	public void setYgnum(java.lang.Short value) {
		this.ygnum = value;
	}
	public java.lang.Short getSknum() {
		return this.sknum;
	}
	
	public void setSknum(java.lang.Short value) {
		this.sknum = value;
	}
	public java.lang.Short getJlnum() {
		return this.jlnum;
	}
	
	public void setJlnum(java.lang.Short value) {
		this.jlnum = value;
	}
	public java.lang.Short getJsnum() {
		return this.jsnum;
	}
	
	public void setJsnum(java.lang.Short value) {
		this.jsnum = value;
	}
	public java.lang.Short getYsnum() {
		return this.ysnum;
	}
	
	public void setYsnum(java.lang.Short value) {
		this.ysnum = value;
	}
	public java.lang.Short getSsdnum() {
		return this.ssdnum;
	}
	
	public void setSsdnum(java.lang.Short value) {
		this.ssdnum = value;
	}
	public java.lang.Short getDtnum() {
		return this.dtnum;
	}
	
	public void setDtnum(java.lang.Short value) {
		this.dtnum = value;
	}
	public java.lang.Short getJjnum() {
		return this.jjnum;
	}
	
	public void setJjnum(java.lang.Short value) {
		this.jjnum = value;
	}
	public java.lang.String getFlag() {
		return this.flag;
	}
	
	public void setFlag(java.lang.String value) {
		this.flag = value;
	}
	public java.lang.String getYwyc() {
		return this.ywyc;
	}
	
	public void setYwyc(java.lang.String value) {
		this.ywyc = value;
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

