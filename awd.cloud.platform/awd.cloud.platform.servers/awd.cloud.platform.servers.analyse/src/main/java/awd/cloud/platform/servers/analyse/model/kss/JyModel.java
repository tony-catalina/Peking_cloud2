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
public class JyModel implements Model {

	//columns START
	
	private String id;


	private String jsh;

	private String rybh;

	private String jsbh;

	private String ysxm;

	private String ly;

	private String xl;

	private String xy;

	private String brbq;

	private String zdqk;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date zdrq;

	private String cljg;

	private String bhlx;

	private String ptmj;

	private String jzyy;

	private String brzs;

	private String swjy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date yyzdsj;

	private String yyzdjg;

	private String yyzlcs;

	private String csjylx;

	private java.math.BigDecimal zlfy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hssj;

	private String ldxm;

	private String ldyj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ldpssj;

	private String sfffyp;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date fyksrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date fyjsrq;

	private String sfyxxx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date xxkssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date xxjssj;

	private String sfjs;

	private String psbz;

	private String bz;

	private String ywlcid;

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
	private java.util.Date cssj;

	private String shjb;
	//columns END



	public JyModel(){
	}
	public JyModel(String id) {
		this.id = id;
	}


	public void setId(String value) {
		this.id = value;
	}

	public String getId() {
		return this.id;
	}

	public String getJsh() {
		return this.jsh;
	}

	public void setJsh(String value) {
		this.jsh = value;
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
	public String getYsxm() {
		return this.ysxm;
	}

	public void setYsxm(String value) {
		this.ysxm = value;
	}
	public String getLy() {
		return this.ly;
	}

	public void setLy(String value) {
		this.ly = value;
	}
	public String getXl() {
		return this.xl;
	}

	public void setXl(String value) {
		this.xl = value;
	}
	public String getXy() {
		return this.xy;
	}

	public void setXy(String value) {
		this.xy = value;
	}
	public String getBrbq() {
		return this.brbq;
	}

	public void setBrbq(String value) {
		this.brbq = value;
	}
	public String getZdqk() {
		return this.zdqk;
	}

	public void setZdqk(String value) {
		this.zdqk = value;
	}

	public java.util.Date getZdrq() {
		return this.zdrq;
	}

	public void setZdrq(java.util.Date value) {
		this.zdrq = value;
	}

	public String getCljg() {
		return this.cljg;
	}

	public void setCljg(String value) {
		this.cljg = value;
	}
	public String getBhlx() {
		return this.bhlx;
	}

	public void setBhlx(String value) {
		this.bhlx = value;
	}
	public String getPtmj() {
		return this.ptmj;
	}

	public void setPtmj(String value) {
		this.ptmj = value;
	}
	public String getJzyy() {
		return this.jzyy;
	}

	public void setJzyy(String value) {
		this.jzyy = value;
	}
	public String getBrzs() {
		return this.brzs;
	}

	public void setBrzs(String value) {
		this.brzs = value;
	}
	public String getSwjy() {
		return this.swjy;
	}

	public void setSwjy(String value) {
		this.swjy = value;
	}

	public java.util.Date getYyzdsj() {
		return this.yyzdsj;
	}

	public void setYyzdsj(java.util.Date value) {
		this.yyzdsj = value;
	}

	public String getYyzdjg() {
		return this.yyzdjg;
	}

	public void setYyzdjg(String value) {
		this.yyzdjg = value;
	}
	public String getYyzlcs() {
		return this.yyzlcs;
	}

	public void setYyzlcs(String value) {
		this.yyzlcs = value;
	}
	public String getCsjylx() {
		return this.csjylx;
	}

	public void setCsjylx(String value) {
		this.csjylx = value;
	}
	public java.math.BigDecimal getZlfy() {
		return this.zlfy;
	}

	public void setZlfy(java.math.BigDecimal value) {
		this.zlfy = value;
	}

	public java.util.Date getHssj() {
		return this.hssj;
	}

	public void setHssj(java.util.Date value) {
		this.hssj = value;
	}

	public String getLdxm() {
		return this.ldxm;
	}

	public void setLdxm(String value) {
		this.ldxm = value;
	}
	public String getLdyj() {
		return this.ldyj;
	}

	public void setLdyj(String value) {
		this.ldyj = value;
	}

	public java.util.Date getLdpssj() {
		return this.ldpssj;
	}

	public void setLdpssj(java.util.Date value) {
		this.ldpssj = value;
	}

	public String getSfffyp() {
		return this.sfffyp;
	}

	public void setSfffyp(String value) {
		this.sfffyp = value;
	}

	public java.util.Date getFyksrq() {
		return this.fyksrq;
	}

	public void setFyksrq(java.util.Date value) {
		this.fyksrq = value;
	}


	public java.util.Date getFyjsrq() {
		return this.fyjsrq;
	}

	public void setFyjsrq(java.util.Date value) {
		this.fyjsrq = value;
	}

	public String getSfyxxx() {
		return this.sfyxxx;
	}

	public void setSfyxxx(String value) {
		this.sfyxxx = value;
	}

	public java.util.Date getXxkssj() {
		return this.xxkssj;
	}

	public void setXxkssj(java.util.Date value) {
		this.xxkssj = value;
	}


	public java.util.Date getXxjssj() {
		return this.xxjssj;
	}

	public void setXxjssj(java.util.Date value) {
		this.xxjssj = value;
	}

	public String getSfjs() {
		return this.sfjs;
	}

	public void setSfjs(String value) {
		this.sfjs = value;
	}
	public String getPsbz() {
		return this.psbz;
	}

	public void setPsbz(String value) {
		this.psbz = value;
	}
	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}
	public String getYwlcid() {
		return this.ywlcid;
	}

	public void setYwlcid(String value) {
		this.ywlcid = value;
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


	public java.util.Date getCssj() {
		return this.cssj;
	}

	public void setCssj(java.util.Date value) {
		this.cssj = value;
	}

	public String getShjb() {
		return this.shjb;
	}

	public void setShjb(String value) {
		this.shjb = value;
	}
	 
}

