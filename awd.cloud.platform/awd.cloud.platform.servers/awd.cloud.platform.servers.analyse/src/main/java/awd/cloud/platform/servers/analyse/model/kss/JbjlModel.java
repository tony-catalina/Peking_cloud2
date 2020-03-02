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
public class JbjlModel implements Model {
	
	//columns START
	
	private String id;


	private String jsbh;

	private String sid;

	private String jq;

	private String jbr;

	private String jsr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jjbsj;

	private String jbqk;

	private String gjly;

	private String ysly;

	private String zdzly;

	private String jbly;

	private Short lastnum;

	private Short mustnum;

	private Short currnum;

	private Short todayinnum;

	private Short todayoutnum;

	private Short jstzinnum;

	private Short jstzoutnum;

	private Short tsnum;

	private Short lshjnum;

	private Short jshjnum;

	private Short lslsnum;

	private Short swjynum;

	private Short todaypjnum;

	private Short zdrynum;

	private Short zdbhnum;

	private Short syjjnum;

	private Short jbnum;

	private Short ygnum;

	private Short sknum;

	private Short jlnum;

	private Short jsnum;

	private Short ysnum;

	private Short ssdnum;

	private Short dtnum;

	private Short jjnum;

	private String flag;

	private String ywyc;

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



	public JbjlModel(){
	}
	public JbjlModel(String id) {
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
	public String getSid() {
		return this.sid;
	}

	public void setSid(String value) {
		this.sid = value;
	}
	public String getJq() {
		return this.jq;
	}

	public void setJq(String value) {
		this.jq = value;
	}
	public String getJbr() {
		return this.jbr;
	}

	public void setJbr(String value) {
		this.jbr = value;
	}
	public String getJsr() {
		return this.jsr;
	}

	public void setJsr(String value) {
		this.jsr = value;
	}

	public java.util.Date getJjbsj() {
		return this.jjbsj;
	}

	public void setJjbsj(java.util.Date value) {
		this.jjbsj = value;
	}

	public String getJbqk() {
		return this.jbqk;
	}

	public void setJbqk(String value) {
		this.jbqk = value;
	}
	public String getGjly() {
		return this.gjly;
	}

	public void setGjly(String value) {
		this.gjly = value;
	}
	public String getYsly() {
		return this.ysly;
	}

	public void setYsly(String value) {
		this.ysly = value;
	}
	public String getZdzly() {
		return this.zdzly;
	}

	public void setZdzly(String value) {
		this.zdzly = value;
	}
	public String getJbly() {
		return this.jbly;
	}

	public void setJbly(String value) {
		this.jbly = value;
	}
	public Short getLastnum() {
		return this.lastnum;
	}

	public void setLastnum(Short value) {
		this.lastnum = value;
	}
	public Short getMustnum() {
		return this.mustnum;
	}

	public void setMustnum(Short value) {
		this.mustnum = value;
	}
	public Short getCurrnum() {
		return this.currnum;
	}

	public void setCurrnum(Short value) {
		this.currnum = value;
	}
	public Short getTodayinnum() {
		return this.todayinnum;
	}

	public void setTodayinnum(Short value) {
		this.todayinnum = value;
	}
	public Short getTodayoutnum() {
		return this.todayoutnum;
	}

	public void setTodayoutnum(Short value) {
		this.todayoutnum = value;
	}
	public Short getJstzinnum() {
		return this.jstzinnum;
	}

	public void setJstzinnum(Short value) {
		this.jstzinnum = value;
	}
	public Short getJstzoutnum() {
		return this.jstzoutnum;
	}

	public void setJstzoutnum(Short value) {
		this.jstzoutnum = value;
	}
	public Short getTsnum() {
		return this.tsnum;
	}

	public void setTsnum(Short value) {
		this.tsnum = value;
	}
	public Short getLshjnum() {
		return this.lshjnum;
	}

	public void setLshjnum(Short value) {
		this.lshjnum = value;
	}
	public Short getJshjnum() {
		return this.jshjnum;
	}

	public void setJshjnum(Short value) {
		this.jshjnum = value;
	}
	public Short getLslsnum() {
		return this.lslsnum;
	}

	public void setLslsnum(Short value) {
		this.lslsnum = value;
	}
	public Short getSwjynum() {
		return this.swjynum;
	}

	public void setSwjynum(Short value) {
		this.swjynum = value;
	}
	public Short getTodaypjnum() {
		return this.todaypjnum;
	}

	public void setTodaypjnum(Short value) {
		this.todaypjnum = value;
	}
	public Short getZdrynum() {
		return this.zdrynum;
	}

	public void setZdrynum(Short value) {
		this.zdrynum = value;
	}
	public Short getZdbhnum() {
		return this.zdbhnum;
	}

	public void setZdbhnum(Short value) {
		this.zdbhnum = value;
	}
	public Short getSyjjnum() {
		return this.syjjnum;
	}

	public void setSyjjnum(Short value) {
		this.syjjnum = value;
	}
	public Short getJbnum() {
		return this.jbnum;
	}

	public void setJbnum(Short value) {
		this.jbnum = value;
	}
	public Short getYgnum() {
		return this.ygnum;
	}

	public void setYgnum(Short value) {
		this.ygnum = value;
	}
	public Short getSknum() {
		return this.sknum;
	}

	public void setSknum(Short value) {
		this.sknum = value;
	}
	public Short getJlnum() {
		return this.jlnum;
	}

	public void setJlnum(Short value) {
		this.jlnum = value;
	}
	public Short getJsnum() {
		return this.jsnum;
	}

	public void setJsnum(Short value) {
		this.jsnum = value;
	}
	public Short getYsnum() {
		return this.ysnum;
	}

	public void setYsnum(Short value) {
		this.ysnum = value;
	}
	public Short getSsdnum() {
		return this.ssdnum;
	}

	public void setSsdnum(Short value) {
		this.ssdnum = value;
	}
	public Short getDtnum() {
		return this.dtnum;
	}

	public void setDtnum(Short value) {
		this.dtnum = value;
	}
	public Short getJjnum() {
		return this.jjnum;
	}

	public void setJjnum(Short value) {
		this.jjnum = value;
	}
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String value) {
		this.flag = value;
	}
	public String getYwyc() {
		return this.ywyc;
	}

	public void setYwyc(String value) {
		this.ywyc = value;
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

