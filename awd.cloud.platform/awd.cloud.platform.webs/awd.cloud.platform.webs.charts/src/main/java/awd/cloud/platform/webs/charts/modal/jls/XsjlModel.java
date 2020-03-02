/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */


package awd.cloud.platform.webs.charts.modal.jls;

import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.bj.base.model.Model;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class XsjlModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Xsjl";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_TBR = "填表人";
	public static final String ALIAS_TBRQ = "填表日期";
	public static final String ALIAS_XM = "姓名";
	public static final String ALIAS_JSH = "拘室号";
	public static final String ALIAS_YFH = "衣服号";
	public static final String ALIAS_FSSJ = "发生时间";
	public static final String ALIAS_WGSJ = "违规时间";
	public static final String ALIAS_WGRS = "违规人数";
	public static final String ALIAS_WGDD = "违规地点";
	public static final String ALIAS_WGQK = "违规情况";
	public static final String ALIAS_WGLX = "违规类型(WGLX)";
	public static final String ALIAS_JYQK = "简要情况";
	public static final String ALIAS_JJCD = "紧急程度(HJCD)";
	public static final String ALIAS_FBGW = "发布岗位(JLSROLE)";
	public static final String ALIAS_ZBR = "值班人";
	public static final String ALIAS_JLSJ = "记录时间";
	public static final String ALIAS_CLQK = "处理情况(WGCLQK)";
	public static final String ALIAS_XXFBFW = "信息发布范围(JLSROLE)";
	public static final String ALIAS_CLWCQK = "处理完成情况";
	public static final String ALIAS_PHOTOURL = "现场截图URL";
	public static final String ALIAS_WGQKCON = "违规情况内容";
	public static final String ALIAS_CLQKCON = "处理情况内容";
	public static final String ALIAS_XSR = "巡视人";
	public static final String ALIAS_CLR = "处理人";
	public static final String ALIAS_CLJG = "处理结果";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_TYPE = "类型";
	public static final String ALIAS_GJCLSJ = "管教处理时间";
	public static final String ALIAS_CLZT = "处理状态(WGCLZT)";
	public static final String ALIAS_CLSJ = "处理时间";
	public static final String ALIAS_YWYC = "有无异常(YWYC)";
	public static final String ALIAS_STATE = "删除状态(YWSTATE)";
	public static final String ALIAS_SCBZ = "上传标志(SHFO)";
	public static final String ALIAS_OPERATOR = "操作人";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String tbr;
	@JSONField(format = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date tbrq;
		
	
	private java.lang.String xm;
	
	private java.lang.String jsh;
	
	private java.lang.String yfh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date fssj;
		
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date wgsj;
		
	
	private java.lang.String wgrs;
	
	private java.lang.String wgdd;
	
	private java.lang.String wgqk;
	
	private java.lang.String wglx;
	
	private java.lang.String jyqk;
	
	private java.lang.String jjcd;
	
	private java.lang.String fbgw;
	
	private java.lang.String zbr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jlsj;
		
	
	private java.lang.String clqk;
	
	private java.lang.String xxfbfw;
	
	private java.lang.String clwcqk;
	
	private java.lang.String photourl;
	
	private java.lang.String wgqkcon;
	
	private java.lang.String clqkcon;
	
	private java.lang.String xsr;
	
	private java.lang.String clr;
	
	private java.lang.String cljg;
	
	private java.lang.String bz;
	
	private java.lang.String type;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date gjclsj;
		
	
	private java.lang.String clzt;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date clsj;
		
	
	private java.lang.String ywyc;
	
	private java.lang.String state;
	
	private java.lang.String scbz;
	
	private java.lang.String operator;
	
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
		
	//columns END
	//开始生成字典的String属性
	private java.lang.String jsbhString;
	
		
	private java.lang.String wglxString;
		
	private java.lang.String jjcdString;
		
	private java.lang.String fbgwString;
		
	private java.lang.String clqkString;
		
	private java.lang.String xxfbfwString;
		
	private java.lang.String clztString;
		
	private java.lang.String ywycString;
		
	private java.lang.String stateString;
		
	private java.lang.String scbzString;
	//字典的get方法生成结束


	public XsjlModel(){
	}
	public XsjlModel(String id) {
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
	public java.lang.String getTbr() {
		return this.tbr;
	}
	
	public void setTbr(java.lang.String value) {
		this.tbr = value;
	}
	
	public java.util.Date getTbrq() {
		return this.tbrq;
	}
	
	public void setTbrq(java.util.Date value) {
		this.tbrq = value;
	}
	
	public java.lang.String getXm() {
		return this.xm;
	}
	
	public void setXm(java.lang.String value) {
		this.xm = value;
	}
	public java.lang.String getJsh() {
		return this.jsh;
	}
	
	public void setJsh(java.lang.String value) {
		this.jsh = value;
	}
	public java.lang.String getYfh() {
		return this.yfh;
	}
	
	public void setYfh(java.lang.String value) {
		this.yfh = value;
	}
	
	public java.util.Date getFssj() {
		return this.fssj;
	}
	
	public void setFssj(java.util.Date value) {
		this.fssj = value;
	}
	
	
	public java.util.Date getWgsj() {
		return this.wgsj;
	}
	
	public void setWgsj(java.util.Date value) {
		this.wgsj = value;
	}
	
	public java.lang.String getWgrs() {
		return this.wgrs;
	}
	
	public void setWgrs(java.lang.String value) {
		this.wgrs = value;
	}
	public java.lang.String getWgdd() {
		return this.wgdd;
	}
	
	public void setWgdd(java.lang.String value) {
		this.wgdd = value;
	}
	public java.lang.String getWgqk() {
		return this.wgqk;
	}
	
	public void setWgqk(java.lang.String value) {
		this.wgqk = value;
	}
	public java.lang.String getWglx() {
		return this.wglx;
	}
	
	public void setWglx(java.lang.String value) {
		this.wglx = value;
	}
	public java.lang.String getJyqk() {
		return this.jyqk;
	}
	
	public void setJyqk(java.lang.String value) {
		this.jyqk = value;
	}
	public java.lang.String getJjcd() {
		return this.jjcd;
	}
	
	public void setJjcd(java.lang.String value) {
		this.jjcd = value;
	}
	public java.lang.String getFbgw() {
		return this.fbgw;
	}
	
	public void setFbgw(java.lang.String value) {
		this.fbgw = value;
	}
	public java.lang.String getZbr() {
		return this.zbr;
	}
	
	public void setZbr(java.lang.String value) {
		this.zbr = value;
	}
	
	public java.util.Date getJlsj() {
		return this.jlsj;
	}
	
	public void setJlsj(java.util.Date value) {
		this.jlsj = value;
	}
	
	public java.lang.String getClqk() {
		return this.clqk;
	}
	
	public void setClqk(java.lang.String value) {
		this.clqk = value;
	}
	public java.lang.String getXxfbfw() {
		return this.xxfbfw;
	}
	
	public void setXxfbfw(java.lang.String value) {
		this.xxfbfw = value;
	}
	public java.lang.String getClwcqk() {
		return this.clwcqk;
	}
	
	public void setClwcqk(java.lang.String value) {
		this.clwcqk = value;
	}
	public java.lang.String getPhotourl() {
		return this.photourl;
	}
	
	public void setPhotourl(java.lang.String value) {
		this.photourl = value;
	}
	public java.lang.String getWgqkcon() {
		return this.wgqkcon;
	}
	
	public void setWgqkcon(java.lang.String value) {
		this.wgqkcon = value;
	}
	public java.lang.String getClqkcon() {
		return this.clqkcon;
	}
	
	public void setClqkcon(java.lang.String value) {
		this.clqkcon = value;
	}
	public java.lang.String getXsr() {
		return this.xsr;
	}
	
	public void setXsr(java.lang.String value) {
		this.xsr = value;
	}
	public java.lang.String getClr() {
		return this.clr;
	}
	
	public void setClr(java.lang.String value) {
		this.clr = value;
	}
	public java.lang.String getCljg() {
		return this.cljg;
	}
	
	public void setCljg(java.lang.String value) {
		this.cljg = value;
	}
	public java.lang.String getBz() {
		return this.bz;
	}
	
	public void setBz(java.lang.String value) {
		this.bz = value;
	}
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String value) {
		this.type = value;
	}
	
	public java.util.Date getGjclsj() {
		return this.gjclsj;
	}
	
	public void setGjclsj(java.util.Date value) {
		this.gjclsj = value;
	}
	
	public java.lang.String getClzt() {
		return this.clzt;
	}
	
	public void setClzt(java.lang.String value) {
		this.clzt = value;
	}
	
	public java.util.Date getClsj() {
		return this.clsj;
	}
	
	public void setClsj(java.util.Date value) {
		this.clsj = value;
	}
	
	public java.lang.String getYwyc() {
		return this.ywyc;
	}
	
	public void setYwyc(java.lang.String value) {
		this.ywyc = value;
	}
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	public java.lang.String getScbz() {
		return this.scbz;
	}
	
	public void setScbz(java.lang.String value) {
		this.scbz = value;
	}
	public java.lang.String getOperator() {
		return this.operator;
	}
	
	public void setOperator(java.lang.String value) {
		this.operator = value;
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
	
	//开始生成字典的getString方法
	public String getJsbhString() {
		return this.jsbhString;
	}
		
	public java.lang.String getWglxString() {
		return this.wglxString;
	}

	public void setWglxString(java.lang.String value) {
		this.wglxString = value;
	}
		
	public java.lang.String getJjcdString() {
		return this.jjcdString;
	}

	public void setJjcdString(java.lang.String value) {
		this.jjcdString = value;
	}
		
	public java.lang.String getFbgwString() {
		return this.fbgwString;
	}

	public void setFbgwString(java.lang.String value) {
		this.fbgwString = value;
	}
		
	public java.lang.String getClqkString() {
		return this.clqkString;
	}

	public void setClqkString(java.lang.String value) {
		this.clqkString = value;
	}
		
	public java.lang.String getXxfbfwString() {
		return this.xxfbfwString;
	}

	public void setXxfbfwString(java.lang.String value) {
		this.xxfbfwString = value;
	}
		
	public java.lang.String getClztString() {
		return this.clztString;
	}

	public void setClztString(java.lang.String value) {
		this.clztString = value;
	}
		
	public java.lang.String getYwycString() {
		return this.ywycString;
	}

	public void setYwycString(java.lang.String value) {
		this.ywycString = value;
	}
		
	public java.lang.String getStateString() {
		return this.stateString;
	}

	public void setStateString(java.lang.String value) {
		this.stateString = value;
	}
		
	public java.lang.String getScbzString() {
		return this.scbzString;
	}

	public void setScbzString(java.lang.String value) {
		this.scbzString = value;
	}
	//字典的get方法生成结束
	 
}

