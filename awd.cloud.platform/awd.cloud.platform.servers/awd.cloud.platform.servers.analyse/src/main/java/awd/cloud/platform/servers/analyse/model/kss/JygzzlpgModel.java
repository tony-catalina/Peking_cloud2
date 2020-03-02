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
public class JygzzlpgModel implements Model {
	
	//columns START
	
	private String id;


	private String jsbh;

	private String rybh;

	private String cjhywzs;

	private String cjhknjzd;

	private String rzrcqk;

	private String jsnbx;

	private String wffzs;

	private String jstc;

	private String zyyx;

	private String czwt;

	private String tskn;

	private String mxfzqx;

	private String sjry;

	private String swry;

	private String ybbjdx;

	private String sfylrhlw;

	private String azbjgzjy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date djrq;

	private String djr;

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



	public JygzzlpgModel(){
	}
	public JygzzlpgModel(String id) {
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
	public String getRybh() {
		return this.rybh;
	}

	public void setRybh(String value) {
		this.rybh = value;
	}
	public String getCjhywzs() {
		return this.cjhywzs;
	}

	public void setCjhywzs(String value) {
		this.cjhywzs = value;
	}
	public String getCjhknjzd() {
		return this.cjhknjzd;
	}

	public void setCjhknjzd(String value) {
		this.cjhknjzd = value;
	}
	public String getRzrcqk() {
		return this.rzrcqk;
	}

	public void setRzrcqk(String value) {
		this.rzrcqk = value;
	}
	public String getJsnbx() {
		return this.jsnbx;
	}

	public void setJsnbx(String value) {
		this.jsnbx = value;
	}
	public String getWffzs() {
		return this.wffzs;
	}

	public void setWffzs(String value) {
		this.wffzs = value;
	}
	public String getJstc() {
		return this.jstc;
	}

	public void setJstc(String value) {
		this.jstc = value;
	}
	public String getZyyx() {
		return this.zyyx;
	}

	public void setZyyx(String value) {
		this.zyyx = value;
	}
	public String getCzwt() {
		return this.czwt;
	}

	public void setCzwt(String value) {
		this.czwt = value;
	}
	public String getTskn() {
		return this.tskn;
	}

	public void setTskn(String value) {
		this.tskn = value;
	}
	public String getMxfzqx() {
		return this.mxfzqx;
	}

	public void setMxfzqx(String value) {
		this.mxfzqx = value;
	}
	public String getSjry() {
		return this.sjry;
	}

	public void setSjry(String value) {
		this.sjry = value;
	}
	public String getSwry() {
		return this.swry;
	}

	public void setSwry(String value) {
		this.swry = value;
	}
	public String getYbbjdx() {
		return this.ybbjdx;
	}

	public void setYbbjdx(String value) {
		this.ybbjdx = value;
	}
	public String getSfylrhlw() {
		return this.sfylrhlw;
	}

	public void setSfylrhlw(String value) {
		this.sfylrhlw = value;
	}
	public String getAzbjgzjy() {
		return this.azbjgzjy;
	}

	public void setAzbjgzjy(String value) {
		this.azbjgzjy = value;
	}

	public java.util.Date getDjrq() {
		return this.djrq;
	}

	public void setDjrq(java.util.Date value) {
		this.djrq = value;
	}

	public String getDjr() {
		return this.djr;
	}

	public void setDjr(String value) {
		this.djr = value;
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

