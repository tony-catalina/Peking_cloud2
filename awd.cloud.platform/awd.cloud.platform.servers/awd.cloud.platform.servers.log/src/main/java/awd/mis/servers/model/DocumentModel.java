/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.model;

import awd.framework.common.model.Model;


/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class DocumentModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Document";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TITLE = "标题";
	public static final String ALIAS_THEME = "主题";
	public static final String ALIAS_ZSDW = "主送单位";
	public static final String ALIAS_CSDW = "抄送单位";
	public static final String ALIAS_CBDW = "呈报单位";
	public static final String ALIAS_QFR = "签发人";
	public static final String ALIAS_QFRQ = "签发日期";
	public static final String ALIAS_WJZH = "文件字号";
	public static final String ALIAS_MJ = "保密级别";
	public static final String ALIAS_HJ = "缓急";
	public static final String ALIAS_NGR = "拟稿人";
	public static final String ALIAS_NGDW = "拟稿单位";
	public static final String ALIAS_FILE = "文件";
	public static final String ALIAS_FILEPATH = "文件地址";
	public static final String ALIAS_STATE = "删除状态";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String title;
	
	private java.lang.String theme;
	
	private java.lang.String zsdw;
	
	private java.lang.String csdw;
	
	private java.lang.String cbdw;
	
	private java.lang.String qfr;
	//签发日期
	private java.util.Date qfrq;
	
	private java.lang.String wjzh;
	
	private java.lang.String mj;
	
	private java.lang.String hj;
	
	private java.lang.String ngr;
	
	private java.lang.String ngdw;
	
	private byte[] file;
	
	private java.lang.String filepath;
	
	private java.lang.String state;
	
	private java.lang.String creator;
	//创建时间
	private java.util.Date createtime;
	
	private java.lang.String updator;
	//更新时间
	private java.util.Date updatetime;
	//columns END


	public DocumentModel(){
	}
	public DocumentModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public java.lang.String getZsdw() {
		return this.zsdw;
	}
	
	public void setZsdw(java.lang.String value) {
		this.zsdw = value;
	}
	public java.lang.String getCsdw() {
		return this.csdw;
	}
	
	public void setCsdw(java.lang.String value) {
		this.csdw = value;
	}
	public java.lang.String getCbdw() {
		return this.cbdw;
	}
	
	public void setCbdw(java.lang.String value) {
		this.cbdw = value;
	}
	public java.lang.String getQfr() {
		return this.qfr;
	}
	
	public void setQfr(java.lang.String value) {
		this.qfr = value;
	}
	
	public java.util.Date getQfrq() {
		return this.qfrq;
	}
	
	public void setQfrq(java.util.Date value) {
		this.qfrq = value;
	}
	
	public java.lang.String getWjzh() {
		return this.wjzh;
	}
	
	public void setWjzh(java.lang.String value) {
		this.wjzh = value;
	}
	public java.lang.String getMj() {
		return this.mj;
	}
	
	public void setMj(java.lang.String value) {
		this.mj = value;
	}
	public java.lang.String getHj() {
		return this.hj;
	}
	
	public void setHj(java.lang.String value) {
		this.hj = value;
	}
	public java.lang.String getNgr() {
		return this.ngr;
	}
	
	public void setNgr(java.lang.String value) {
		this.ngr = value;
	}
	public java.lang.String getNgdw() {
		return this.ngdw;
	}
	
	public void setNgdw(java.lang.String value) {
		this.ngdw = value;
	}
	public byte[] getFile() {
		return this.file;
	}
	
	public void setFile(byte[] value) {
		this.file = value;
	}
	public java.lang.String getFilepath() {
		return this.filepath;
	}
	
	public void setFilepath(java.lang.String value) {
		this.filepath = value;
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
	
	
}

