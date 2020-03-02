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


public class Kss_TjdjModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String tjdw;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date tjsj;
	
	private java.lang.String tjyy;
	
	private java.lang.String tjjtyy;
	
	private java.lang.String tjry1;
	
	private java.lang.String tjry2;
	
	private java.lang.String tjkh1;
	
	private java.lang.String tjkh2;
	
	private java.lang.String qttjry;
	
	private java.lang.String pzr;
	
	private java.lang.String lxdh;
	
	private java.lang.String zbmj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date tjkssj;
	
	private java.lang.String jcry;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jcsj;
	
	private java.lang.String jcjg;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date tjjssj;
	
	private java.lang.String ycqkdj;
	
	private java.lang.String bz;
	
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
	
	private java.lang.String lfrzjh;
	
	private java.lang.String lfrpzh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lfsj;
	
	private java.lang.String lfrxm;
	//columns END

	 

	public Kss_TjdjModel(){
	}
	public Kss_TjdjModel(String id) {
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
	public java.lang.String getRybh() {
		return this.rybh;
	}
	
	public void setRybh(java.lang.String value) {
		this.rybh = value;
	}
	public java.lang.String getTjdw() {
		return this.tjdw;
	}
	
	public void setTjdw(java.lang.String value) {
		this.tjdw = value;
	}
	
	public java.util.Date getTjsj() {
		return this.tjsj;
	}
	
	public void setTjsj(java.util.Date value) {
		this.tjsj = value;
	}
	
	public java.lang.String getTjyy() {
		return this.tjyy;
	}
	
	public void setTjyy(java.lang.String value) {
		this.tjyy = value;
	}
	public java.lang.String getTjjtyy() {
		return this.tjjtyy;
	}
	
	public void setTjjtyy(java.lang.String value) {
		this.tjjtyy = value;
	}
	public java.lang.String getTjry1() {
		return this.tjry1;
	}
	
	public void setTjry1(java.lang.String value) {
		this.tjry1 = value;
	}
	public java.lang.String getTjry2() {
		return this.tjry2;
	}
	
	public void setTjry2(java.lang.String value) {
		this.tjry2 = value;
	}
	public java.lang.String getTjkh1() {
		return this.tjkh1;
	}
	
	public void setTjkh1(java.lang.String value) {
		this.tjkh1 = value;
	}
	public java.lang.String getTjkh2() {
		return this.tjkh2;
	}
	
	public void setTjkh2(java.lang.String value) {
		this.tjkh2 = value;
	}
	public java.lang.String getQttjry() {
		return this.qttjry;
	}
	
	public void setQttjry(java.lang.String value) {
		this.qttjry = value;
	}
	public java.lang.String getPzr() {
		return this.pzr;
	}
	
	public void setPzr(java.lang.String value) {
		this.pzr = value;
	}
	public java.lang.String getLxdh() {
		return this.lxdh;
	}
	
	public void setLxdh(java.lang.String value) {
		this.lxdh = value;
	}
	public java.lang.String getZbmj() {
		return this.zbmj;
	}
	
	public void setZbmj(java.lang.String value) {
		this.zbmj = value;
	}
	
	public java.util.Date getTjkssj() {
		return this.tjkssj;
	}
	
	public void setTjkssj(java.util.Date value) {
		this.tjkssj = value;
	}
	
	public java.lang.String getJcry() {
		return this.jcry;
	}
	
	public void setJcry(java.lang.String value) {
		this.jcry = value;
	}
	
	public java.util.Date getJcsj() {
		return this.jcsj;
	}
	
	public void setJcsj(java.util.Date value) {
		this.jcsj = value;
	}
	
	public java.lang.String getJcjg() {
		return this.jcjg;
	}
	
	public void setJcjg(java.lang.String value) {
		this.jcjg = value;
	}
	
	public java.util.Date getTjjssj() {
		return this.tjjssj;
	}
	
	public void setTjjssj(java.util.Date value) {
		this.tjjssj = value;
	}
	
	public java.lang.String getYcqkdj() {
		return this.ycqkdj;
	}
	
	public void setYcqkdj(java.lang.String value) {
		this.ycqkdj = value;
	}
	public java.lang.String getBz() {
		return this.bz;
	}
	
	public void setBz(java.lang.String value) {
		this.bz = value;
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
	
	public java.lang.String getLfrzjh() {
		return this.lfrzjh;
	}
	
	public void setLfrzjh(java.lang.String value) {
		this.lfrzjh = value;
	}
	public java.lang.String getLfrpzh() {
		return this.lfrpzh;
	}
	
	public void setLfrpzh(java.lang.String value) {
		this.lfrpzh = value;
	}
	
	public java.util.Date getLfsj() {
		return this.lfsj;
	}
	
	public void setLfsj(java.util.Date value) {
		this.lfsj = value;
	}
	
	public java.lang.String getLfrxm() {
		return this.lfrxm;
	}
	
	public void setLfrxm(java.lang.String value) {
		this.lfrxm = value;
	}
	 
}

