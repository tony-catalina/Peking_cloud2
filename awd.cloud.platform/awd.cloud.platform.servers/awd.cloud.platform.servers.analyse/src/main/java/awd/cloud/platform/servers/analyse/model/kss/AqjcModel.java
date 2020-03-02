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
public class AqjcModel implements Model {
	
	
	//columns START
	
	private String id;
	private String jsbh;
	private String jcxs;
	private Byte ksscjrs;
	private Byte wjcjrs;
	private Byte jskcjrs;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date sj;
	private String fzr;
	private String zsjcry;
	private String fw;
	private String nr;
	private String jcjg;
	private String cljg;
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



	public AqjcModel(){
	}
	public AqjcModel(String id) {
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
	public String getJcxs() {
		return this.jcxs;
	}

	public void setJcxs(String value) {
		this.jcxs = value;
	}
	public Byte getKsscjrs() {
		return this.ksscjrs;
	}

	public void setKsscjrs(Byte value) {
		this.ksscjrs = value;
	}
	public Byte getWjcjrs() {
		return this.wjcjrs;
	}

	public void setWjcjrs(Byte value) {
		this.wjcjrs = value;
	}
	public Byte getJskcjrs() {
		return this.jskcjrs;
	}

	public void setJskcjrs(Byte value) {
		this.jskcjrs = value;
	}

	public java.util.Date getSj() {
		return this.sj;
	}

	public void setSj(java.util.Date value) {
		this.sj = value;
	}

	public String getFzr() {
		return this.fzr;
	}

	public void setFzr(String value) {
		this.fzr = value;
	}
	public String getZsjcry() {
		return this.zsjcry;
	}

	public void setZsjcry(String value) {
		this.zsjcry = value;
	}
	public String getFw() {
		return this.fw;
	}

	public void setFw(String value) {
		this.fw = value;
	}
	public String getNr() {
		return this.nr;
	}

	public void setNr(String value) {
		this.nr = value;
	}
	public String getJcjg() {
		return this.jcjg;
	}

	public void setJcjg(String value) {
		this.jcjg = value;
	}
	public String getCljg() {
		return this.cljg;
	}

	public void setCljg(String value) {
		this.cljg = value;
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

