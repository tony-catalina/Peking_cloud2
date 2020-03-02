/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.model;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class MsgboxModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Msgbox";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_FSRXM = "发送人姓名 ";
	public static final String ALIAS_FSRJH = "发送人编号";
	public static final String ALIAS_XXJB = "信息级别(XXJB)";
	public static final String ALIAS_FSSJ = "发送时间";
	public static final String ALIAS_FSNR = "发送内容";
	public static final String ALIAS_JSRJH = "接收人编号";
	public static final String ALIAS_JSSJ = "接收时间";
	public static final String ALIAS_STATE = "消息状态";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_UPDATETIME = "updatetime";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_JSRXM = "接收人姓名";
	public static final String ALIAS_MSGID = "消息队列ID";
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String fsrxm;
	
	private java.lang.String fsrjh;
	
	private java.lang.String xxjb;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date fssj;
		
	
	private java.lang.String fsnr;
	
	private java.lang.String jsrjh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jssj;
		
	
	private java.lang.String state;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
		
	
	private java.lang.String creator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
		
	
	private java.lang.String updator;
	
	private java.lang.String jsrxm;
	
	private java.lang.String msgid;
	//columns END
	//开始生成字典的String属性
	private java.lang.String jsbhString;
	
		
	private java.lang.String xxjbString;
	//字典的get方法生成结束


	public MsgboxModel(){
	}
	public MsgboxModel(String id) {
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
	public java.lang.String getFsrxm() {
		return this.fsrxm;
	}
	
	public void setFsrxm(java.lang.String value) {
		this.fsrxm = value;
	}
	public java.lang.String getFsrjh() {
		return this.fsrjh;
	}
	
	public void setFsrjh(java.lang.String value) {
		this.fsrjh = value;
	}
	public java.lang.String getXxjb() {
		return this.xxjb;
	}
	
	public void setXxjb(java.lang.String value) {
		this.xxjb = value;
	}
	
	public java.util.Date getFssj() {
		return this.fssj;
	}
	
	public void setFssj(java.util.Date value) {
		this.fssj = value;
	}
	
	public java.lang.String getFsnr() {
		return this.fsnr;
	}
	
	public void setFsnr(java.lang.String value) {
		this.fsnr = value;
	}
	public java.lang.String getJsrjh() {
		return this.jsrjh;
	}
	
	public void setJsrjh(java.lang.String value) {
		this.jsrjh = value;
	}
	
	public java.util.Date getJssj() {
		return this.jssj;
	}
	
	public void setJssj(java.util.Date value) {
		this.jssj = value;
	}
	
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.lang.String getCreator() {
		return this.creator;
	}
	
	public void setCreator(java.lang.String value) {
		this.creator = value;
	}
	
	public java.util.Date getUpdatetime() {
		return this.updatetime;
	}
	
	public void setUpdatetime(java.util.Date value) {
		this.updatetime = value;
	}
	
	public java.lang.String getUpdator() {
		return this.updator;
	}
	
	public void setUpdator(java.lang.String value) {
		this.updator = value;
	}
	public java.lang.String getJsrxm() {
		return this.jsrxm;
	}
	
	public void setJsrxm(java.lang.String value) {
		this.jsrxm = value;
	}
	public java.lang.String getMsgid() {
		return this.msgid;
	}
	
	public void setMsgid(java.lang.String value) {
		this.msgid = value;
	}
	//开始生成字典的getString方法
	public String getJsbhString() {
		return this.jsbhString;
	}
		
	public java.lang.String getXxjbString() {
		return this.xxjbString;
	}
	//字典的get方法生成结束
	 
}

