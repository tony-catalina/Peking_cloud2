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

import java.util.Date;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PjdjModel implements Model {
	
	//columns START
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date starttime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date endtime;

	private String id;


	private String rybh;

	private String jsbh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date pjrq;

	private String pjdw;

	private String wsh;

	private String cljg;

	private String xq;

	private String fjx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date qsrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date xmrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jarq;

	private String hx;

	private String hxqx;

	private String fjje;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hxksrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hxjsrq;

	private String bdzzqlsj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date bdzzqlkssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date bdzzqljssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date pjsxrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date sdrq;

	private String ywlcid;

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
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jyqx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ygyqx;

	private String wspzlx;

	private String ycljg;

	private String qzcs;

	private String fjcl;

	private String pjzm;
	//columns END



	public PjdjModel(){
	}
	public PjdjModel(String id) {
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

	public java.util.Date getPjrq() {
		return this.pjrq;
	}

	public void setPjrq(java.util.Date value) {
		this.pjrq = value;
	}

	public String getPjdw() {
		return this.pjdw;
	}

	public void setPjdw(String value) {
		this.pjdw = value;
	}
	public String getWsh() {
		return this.wsh;
	}

	public void setWsh(String value) {
		this.wsh = value;
	}
	public String getCljg() {
		return this.cljg;
	}

	public void setCljg(String value) {
		this.cljg = value;
	}
	public String getXq() {
		return this.xq;
	}

	public void setXq(String value) {
		this.xq = value;
	}
	public String getFjx() {
		return this.fjx;
	}

	public void setFjx(String value) {
		this.fjx = value;
	}

	public java.util.Date getQsrq() {
		return this.qsrq;
	}

	public void setQsrq(java.util.Date value) {
		this.qsrq = value;
	}


	public java.util.Date getXmrq() {
		return this.xmrq;
	}

	public void setXmrq(java.util.Date value) {
		this.xmrq = value;
	}


	public java.util.Date getJarq() {
		return this.jarq;
	}

	public void setJarq(java.util.Date value) {
		this.jarq = value;
	}

	public String getHx() {
		return this.hx;
	}

	public void setHx(String value) {
		this.hx = value;
	}
	public String getHxqx() {
		return this.hxqx;
	}

	public void setHxqx(String value) {
		this.hxqx = value;
	}
	public String getFjje() {
		return this.fjje;
	}

	public void setFjje(String value) {
		this.fjje = value;
	}

	public java.util.Date getHxksrq() {
		return this.hxksrq;
	}

	public void setHxksrq(java.util.Date value) {
		this.hxksrq = value;
	}


	public java.util.Date getHxjsrq() {
		return this.hxjsrq;
	}

	public void setHxjsrq(java.util.Date value) {
		this.hxjsrq = value;
	}

	public String getBdzzqlsj() {
		return this.bdzzqlsj;
	}

	public void setBdzzqlsj(String value) {
		this.bdzzqlsj = value;
	}

	public java.util.Date getBdzzqlkssj() {
		return this.bdzzqlkssj;
	}

	public void setBdzzqlkssj(java.util.Date value) {
		this.bdzzqlkssj = value;
	}


	public java.util.Date getBdzzqljssj() {
		return this.bdzzqljssj;
	}

	public void setBdzzqljssj(java.util.Date value) {
		this.bdzzqljssj = value;
	}


	public java.util.Date getPjsxrq() {
		return this.pjsxrq;
	}

	public void setPjsxrq(java.util.Date value) {
		this.pjsxrq = value;
	}


	public java.util.Date getSdrq() {
		return this.sdrq;
	}

	public void setSdrq(java.util.Date value) {
		this.sdrq = value;
	}

	public String getYwlcid() {
		return this.ywlcid;
	}

	public void setYwlcid(String value) {
		this.ywlcid = value;
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


	public java.util.Date getJyqx() {
		return this.jyqx;
	}

	public void setJyqx(java.util.Date value) {
		this.jyqx = value;
	}


	public java.util.Date getYgyqx() {
		return this.ygyqx;
	}

	public void setYgyqx(java.util.Date value) {
		this.ygyqx = value;
	}

	public String getWspzlx() {
		return this.wspzlx;
	}

	public void setWspzlx(String value) {
		this.wspzlx = value;
	}
	public String getYcljg() {
		return this.ycljg;
	}

	public void setYcljg(String value) {
		this.ycljg = value;
	}
	public String getQzcs() {
		return this.qzcs;
	}

	public void setQzcs(String value) {
		this.qzcs = value;
	}
	public String getFjcl() {
		return this.fjcl;
	}

	public void setFjcl(String value) {
		this.fjcl = value;
	}
	public String getPjzm() {
		return this.pjzm;
	}

	public void setPjzm(String value) {
		this.pjzm = value;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
}

