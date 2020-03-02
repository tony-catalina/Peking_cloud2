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
public class KssModel implements Model {
	

	//columns START
	
	private String id;


	private String jsbh;

	private String kssmc;

	private String kssmcpy;

	private String dz;

	private String dq;

	private String dh;

	private String cz;

	private String yzbm;

	private String email;

	private String net;

	private String ldxm;

	private String dj;

	private String gm;

	private Short rs;

	private Short bzrs;

	private Short jss;

	private Short rl;

	private String version;

	private String kssjc;

	private String enable;

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

	private String maxsnbh;

	private Short xjnum;

	private Short zgnum;

	private Short wznum;

	private String syjb;

	private String jsxzjb;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date qysj;

	private Short drjsnum;

	private java.math.BigDecimal zjzmj;

	private java.math.BigDecimal jqmj;

	private String bqszxs;

	private Short zswjnum;

	private String ssxs;

	private String abdoor;

	private Short bhfcrj;

	private Short xwsnum;

	private Short lshjsnum;

	private Short jshjsnum;

	private Short doctornum;

	private Short nursenum;

	private String longitude;

	private String latitude;

	private byte[] gatephoto;
	/**
	 * 传递条件使用
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date starttime;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date endtime;
	//columns END



	public KssModel(){
	}
	public KssModel(String id) {
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
	public String getKssmc() {
		return this.kssmc;
	}

	public void setKssmc(String value) {
		this.kssmc = value;
	}
	public String getKssmcpy() {
		return this.kssmcpy;
	}

	public void setKssmcpy(String value) {
		this.kssmcpy = value;
	}
	public String getDz() {
		return this.dz;
	}

	public void setDz(String value) {
		this.dz = value;
	}
	public String getDq() {
		return this.dq;
	}

	public void setDq(String value) {
		this.dq = value;
	}
	public String getDh() {
		return this.dh;
	}

	public void setDh(String value) {
		this.dh = value;
	}
	public String getCz() {
		return this.cz;
	}

	public void setCz(String value) {
		this.cz = value;
	}
	public String getYzbm() {
		return this.yzbm;
	}

	public void setYzbm(String value) {
		this.yzbm = value;
	}
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String value) {
		this.email = value;
	}
	public String getNet() {
		return this.net;
	}

	public void setNet(String value) {
		this.net = value;
	}
	public String getLdxm() {
		return this.ldxm;
	}

	public void setLdxm(String value) {
		this.ldxm = value;
	}
	public String getDj() {
		return this.dj;
	}

	public void setDj(String value) {
		this.dj = value;
	}
	public String getGm() {
		return this.gm;
	}

	public void setGm(String value) {
		this.gm = value;
	}
	public Short getRs() {
		return this.rs;
	}

	public void setRs(Short value) {
		this.rs = value;
	}
	public Short getBzrs() {
		return this.bzrs;
	}

	public void setBzrs(Short value) {
		this.bzrs = value;
	}
	public Short getJss() {
		return this.jss;
	}

	public void setJss(Short value) {
		this.jss = value;
	}
	public Short getRl() {
		return this.rl;
	}

	public void setRl(Short value) {
		this.rl = value;
	}
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String value) {
		this.version = value;
	}
	public String getKssjc() {
		return this.kssjc;
	}

	public void setKssjc(String value) {
		this.kssjc = value;
	}
	public String getEnable() {
		return this.enable;
	}

	public void setEnable(String value) {
		this.enable = value;
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

	public String getMaxsnbh() {
		return this.maxsnbh;
	}

	public void setMaxsnbh(String value) {
		this.maxsnbh = value;
	}
	public Short getXjnum() {
		return this.xjnum;
	}

	public void setXjnum(Short value) {
		this.xjnum = value;
	}
	public Short getZgnum() {
		return this.zgnum;
	}

	public void setZgnum(Short value) {
		this.zgnum = value;
	}
	public Short getWznum() {
		return this.wznum;
	}

	public void setWznum(Short value) {
		this.wznum = value;
	}
	public String getSyjb() {
		return this.syjb;
	}

	public void setSyjb(String value) {
		this.syjb = value;
	}
	public String getJsxzjb() {
		return this.jsxzjb;
	}

	public void setJsxzjb(String value) {
		this.jsxzjb = value;
	}

	public java.util.Date getJssj() {
		return this.jssj;
	}

	public void setJssj(java.util.Date value) {
		this.jssj = value;
	}


	public java.util.Date getQysj() {
		return this.qysj;
	}

	public void setQysj(java.util.Date value) {
		this.qysj = value;
	}

	public Short getDrjsnum() {
		return this.drjsnum;
	}

	public void setDrjsnum(Short value) {
		this.drjsnum = value;
	}
	public java.math.BigDecimal getZjzmj() {
		return this.zjzmj;
	}

	public void setZjzmj(java.math.BigDecimal value) {
		this.zjzmj = value;
	}
	public java.math.BigDecimal getJqmj() {
		return this.jqmj;
	}

	public void setJqmj(java.math.BigDecimal value) {
		this.jqmj = value;
	}
	public String getBqszxs() {
		return this.bqszxs;
	}

	public void setBqszxs(String value) {
		this.bqszxs = value;
	}
	public Short getZswjnum() {
		return this.zswjnum;
	}

	public void setZswjnum(Short value) {
		this.zswjnum = value;
	}
	public String getSsxs() {
		return this.ssxs;
	}

	public void setSsxs(String value) {
		this.ssxs = value;
	}
	public String getAbdoor() {
		return this.abdoor;
	}

	public void setAbdoor(String value) {
		this.abdoor = value;
	}
	public Short getBhfcrj() {
		return this.bhfcrj;
	}

	public void setBhfcrj(Short value) {
		this.bhfcrj = value;
	}
	public Short getXwsnum() {
		return this.xwsnum;
	}

	public void setXwsnum(Short value) {
		this.xwsnum = value;
	}
	public Short getLshjsnum() {
		return this.lshjsnum;
	}

	public void setLshjsnum(Short value) {
		this.lshjsnum = value;
	}
	public Short getJshjsnum() {
		return this.jshjsnum;
	}

	public void setJshjsnum(Short value) {
		this.jshjsnum = value;
	}
	public Short getDoctornum() {
		return this.doctornum;
	}

	public void setDoctornum(Short value) {
		this.doctornum = value;
	}
	public Short getNursenum() {
		return this.nursenum;
	}

	public void setNursenum(Short value) {
		this.nursenum = value;
	}
	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String value) {
		this.longitude = value;
	}
	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String value) {
		this.latitude = value;
	}
	public byte[] getGatephoto() {
		return this.gatephoto;
	}
	
	public void setGatephoto(byte[] value) {
		this.gatephoto = value;
	}
	 
}

