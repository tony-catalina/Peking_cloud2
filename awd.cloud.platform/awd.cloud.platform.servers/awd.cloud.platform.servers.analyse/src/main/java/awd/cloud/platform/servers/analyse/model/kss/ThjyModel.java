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
public class ThjyModel implements Model {
	
	//columns START
	
	private String id;


	private String jsbh;

	private String rybh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date kssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jssj;

	private String thyy;

	private String thnr;

	private String fzmj;

	private String ywqxyc;

	private String qxycqk;

	private String ywzssb;

	private String sbqk;

	private String sfljjs;

	private String bljjsyy;

	private String bllbz;

	private String sjzlJsbz;

	private String barcode;

	private String usefinger;

	private String nozhiwenyy;

	private String skth;

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

	private String thdd;

	private String sfls;

	private String ywqyxx;
	//columns END



	public ThjyModel(){
	}
	public ThjyModel(String id) {
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

	public java.util.Date getKssj() {
		return this.kssj;
	}

	public void setKssj(java.util.Date value) {
		this.kssj = value;
	}


	public java.util.Date getJssj() {
		return this.jssj;
	}

	public void setJssj(java.util.Date value) {
		this.jssj = value;
	}

	public String getThyy() {
		return this.thyy;
	}

	public void setThyy(String value) {
		this.thyy = value;
	}
	public String getThnr() {
		return this.thnr;
	}

	public void setThnr(String value) {
		this.thnr = value;
	}
	public String getFzmj() {
		return this.fzmj;
	}

	public void setFzmj(String value) {
		this.fzmj = value;
	}
	public String getYwqxyc() {
		return this.ywqxyc;
	}

	public void setYwqxyc(String value) {
		this.ywqxyc = value;
	}
	public String getQxycqk() {
		return this.qxycqk;
	}

	public void setQxycqk(String value) {
		this.qxycqk = value;
	}
	public String getYwzssb() {
		return this.ywzssb;
	}

	public void setYwzssb(String value) {
		this.ywzssb = value;
	}
	public String getSbqk() {
		return this.sbqk;
	}

	public void setSbqk(String value) {
		this.sbqk = value;
	}
	public String getSfljjs() {
		return this.sfljjs;
	}

	public void setSfljjs(String value) {
		this.sfljjs = value;
	}
	public String getBljjsyy() {
		return this.bljjsyy;
	}

	public void setBljjsyy(String value) {
		this.bljjsyy = value;
	}
	public String getBllbz() {
		return this.bllbz;
	}

	public void setBllbz(String value) {
		this.bllbz = value;
	}
	public String getSjzlJsbz() {
		return this.sjzlJsbz;
	}

	public void setSjzlJsbz(String value) {
		this.sjzlJsbz = value;
	}
	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String value) {
		this.barcode = value;
	}
	public String getUsefinger() {
		return this.usefinger;
	}

	public void setUsefinger(String value) {
		this.usefinger = value;
	}
	public String getNozhiwenyy() {
		return this.nozhiwenyy;
	}

	public void setNozhiwenyy(String value) {
		this.nozhiwenyy = value;
	}
	public String getSkth() {
		return this.skth;
	}

	public void setSkth(String value) {
		this.skth = value;
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

	public String getThdd() {
		return this.thdd;
	}

	public void setThdd(String value) {
		this.thdd = value;
	}
	public String getSfls() {
		return this.sfls;
	}

	public void setSfls(String value) {
		this.sfls = value;
	}
	public String getYwqyxx() {
		return this.ywqyxx;
	}

	public void setYwqyxx(String value) {
		this.ywqyxx = value;
	}
	 
}

