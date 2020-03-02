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
public class QybzModel implements Model {

	
	//columns START
	
	private String id;


	private String jsbh;

	private String jsh;

	private String bzlx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date kssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date dcsj;

	private String dcmj;

	private String dcrs;

	private String qy;

	private String dd;

	private String dlr;

	private String dlys;

	private String jtqk;

	private String mjxm;

	private String state;

	private String bz;

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



	public QybzModel(){
	}
	public QybzModel(String id) {
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
	public String getJsh() {
		return this.jsh;
	}

	public void setJsh(String value) {
		this.jsh = value;
	}
	public String getBzlx() {
		return this.bzlx;
	}

	public void setBzlx(String value) {
		this.bzlx = value;
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


	public java.util.Date getDcsj() {
		return this.dcsj;
	}

	public void setDcsj(java.util.Date value) {
		this.dcsj = value;
	}

	public String getDcmj() {
		return this.dcmj;
	}

	public void setDcmj(String value) {
		this.dcmj = value;
	}
	public String getDcrs() {
		return this.dcrs;
	}

	public void setDcrs(String value) {
		this.dcrs = value;
	}
	public String getQy() {
		return this.qy;
	}

	public void setQy(String value) {
		this.qy = value;
	}
	public String getDd() {
		return this.dd;
	}

	public void setDd(String value) {
		this.dd = value;
	}
	public String getDlr() {
		return this.dlr;
	}

	public void setDlr(String value) {
		this.dlr = value;
	}
	public String getDlys() {
		return this.dlys;
	}

	public void setDlys(String value) {
		this.dlys = value;
	}
	public String getJtqk() {
		return this.jtqk;
	}

	public void setJtqk(String value) {
		this.jtqk = value;
	}
	public String getMjxm() {
		return this.mjxm;
	}

	public void setMjxm(String value) {
		this.mjxm = value;
	}
	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
	}
	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
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

