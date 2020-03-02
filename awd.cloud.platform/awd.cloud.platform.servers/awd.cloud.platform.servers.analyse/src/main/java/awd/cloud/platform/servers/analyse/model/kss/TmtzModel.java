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
public class TmtzModel implements Model {

	//columns START
	
	private String id;


	private String jsbh;

	private String gcbh;

	private String zjh;

	private String sg;

	private String tz;

	private String zc;

	private String tb;

	private String jb;

	private String xb;

	private String fb;

	private String bb;

	private String db;

	private String sz;

	private String xz;

	private String tmtz1;

	private String rtbw1;

	private String fw1;

	private String sl1;

	private String tmtz2;

	private String rtbw2;

	private String fw2;

	private String sl2;

	private String tmtz3;

	private String rtbw3;

	private String fw3;

	private String sl3;

	private String tmtz4;

	private String rtbw4;

	private String fw4;

	private String sl4;

	private String tmtz5;

	private String rtbw5;

	private String fw5;

	private String sl5;

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

	private String jkqkid;
	//columns END



	public TmtzModel(){
	}
	public TmtzModel(String id) {
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
	public String getGcbh() {
		return this.gcbh;
	}

	public void setGcbh(String value) {
		this.gcbh = value;
	}
	public String getZjh() {
		return this.zjh;
	}

	public void setZjh(String value) {
		this.zjh = value;
	}
	public String getSg() {
		return this.sg;
	}

	public void setSg(String value) {
		this.sg = value;
	}
	public String getTz() {
		return this.tz;
	}

	public void setTz(String value) {
		this.tz = value;
	}
	public String getZc() {
		return this.zc;
	}

	public void setZc(String value) {
		this.zc = value;
	}
	public String getTb() {
		return this.tb;
	}

	public void setTb(String value) {
		this.tb = value;
	}
	public String getJb() {
		return this.jb;
	}

	public void setJb(String value) {
		this.jb = value;
	}
	public String getXb() {
		return this.xb;
	}

	public void setXb(String value) {
		this.xb = value;
	}
	public String getFb() {
		return this.fb;
	}

	public void setFb(String value) {
		this.fb = value;
	}
	public String getBb() {
		return this.bb;
	}

	public void setBb(String value) {
		this.bb = value;
	}
	public String getDb() {
		return this.db;
	}

	public void setDb(String value) {
		this.db = value;
	}
	public String getSz() {
		return this.sz;
	}

	public void setSz(String value) {
		this.sz = value;
	}
	public String getXz() {
		return this.xz;
	}

	public void setXz(String value) {
		this.xz = value;
	}
	public String getTmtz1() {
		return this.tmtz1;
	}

	public void setTmtz1(String value) {
		this.tmtz1 = value;
	}
	public String getRtbw1() {
		return this.rtbw1;
	}

	public void setRtbw1(String value) {
		this.rtbw1 = value;
	}
	public String getFw1() {
		return this.fw1;
	}

	public void setFw1(String value) {
		this.fw1 = value;
	}
	public String getSl1() {
		return this.sl1;
	}

	public void setSl1(String value) {
		this.sl1 = value;
	}
	public String getTmtz2() {
		return this.tmtz2;
	}

	public void setTmtz2(String value) {
		this.tmtz2 = value;
	}
	public String getRtbw2() {
		return this.rtbw2;
	}

	public void setRtbw2(String value) {
		this.rtbw2 = value;
	}
	public String getFw2() {
		return this.fw2;
	}

	public void setFw2(String value) {
		this.fw2 = value;
	}
	public String getSl2() {
		return this.sl2;
	}

	public void setSl2(String value) {
		this.sl2 = value;
	}
	public String getTmtz3() {
		return this.tmtz3;
	}

	public void setTmtz3(String value) {
		this.tmtz3 = value;
	}
	public String getRtbw3() {
		return this.rtbw3;
	}

	public void setRtbw3(String value) {
		this.rtbw3 = value;
	}
	public String getFw3() {
		return this.fw3;
	}

	public void setFw3(String value) {
		this.fw3 = value;
	}
	public String getSl3() {
		return this.sl3;
	}

	public void setSl3(String value) {
		this.sl3 = value;
	}
	public String getTmtz4() {
		return this.tmtz4;
	}

	public void setTmtz4(String value) {
		this.tmtz4 = value;
	}
	public String getRtbw4() {
		return this.rtbw4;
	}

	public void setRtbw4(String value) {
		this.rtbw4 = value;
	}
	public String getFw4() {
		return this.fw4;
	}

	public void setFw4(String value) {
		this.fw4 = value;
	}
	public String getSl4() {
		return this.sl4;
	}

	public void setSl4(String value) {
		this.sl4 = value;
	}
	public String getTmtz5() {
		return this.tmtz5;
	}

	public void setTmtz5(String value) {
		this.tmtz5 = value;
	}
	public String getRtbw5() {
		return this.rtbw5;
	}

	public void setRtbw5(String value) {
		this.rtbw5 = value;
	}
	public String getFw5() {
		return this.fw5;
	}

	public void setFw5(String value) {
		this.fw5 = value;
	}
	public String getSl5() {
		return this.sl5;
	}

	public void setSl5(String value) {
		this.sl5 = value;
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

	public String getJkqkid() {
		return this.jkqkid;
	}

	public void setJkqkid(String value) {
		this.jkqkid = value;
	}
	 
}

