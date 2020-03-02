/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.model;

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
public class MirisModel implements Model {
	
	//alias
	public static final String TABLE_ALIAS = "Miris";
	public static final String ALIAS_ID = "编号";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_JH = "警号";
	public static final String ALIAS_ZJH = "证件号";
	public static final String ALIAS_HMWZBH = "虹膜位置编号（HMWZBH）";
	public static final String ALIAS_HMTX = "虹膜图像";
	public static final String ALIAS_HMTZ = "虹膜特征";
	public static final String ALIAS_HMTXURL = "虹膜图像地址";
	public static final String ALIAS_HMTZURL = "虹膜特征地址";
	public static final String ALIAS_STATE = "状态  R3删除  R2正常";
	public static final String ALIAS_SCBZ = "上传标志";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String jh;
	
	private java.lang.String zjh;
	
	private java.lang.String hmwzbh;
	
	private byte[] hmtx;
	
	private byte[] hmtz;
	
	private java.lang.String hmtxurl;
	
	private java.lang.String hmtzurl;
	
	private java.lang.String state;
	
	private java.lang.String scbz;
	
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
	
	//字典的get方法生成结束


	public MirisModel(){
	}
	public MirisModel(String id) {
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
	public java.lang.String getJh() {
		return this.jh;
	}
	
	public void setJh(java.lang.String value) {
		this.jh = value;
	}
	public java.lang.String getZjh() {
		return this.zjh;
	}
	
	public void setZjh(java.lang.String value) {
		this.zjh = value;
	}
	public java.lang.String getHmwzbh() {
		return this.hmwzbh;
	}
	
	public void setHmwzbh(java.lang.String value) {
		this.hmwzbh = value;
	}
	public byte[] getHmtx() {
		return this.hmtx;
	}
	
	public void setHmtx(byte[] value) {
		this.hmtx = value;
	}
	public byte[] getHmtz() {
		return this.hmtz;
	}
	
	public void setHmtz(byte[] value) {
		this.hmtz = value;
	}
	public java.lang.String getHmtxurl() {
		return this.hmtxurl;
	}
	
	public void setHmtxurl(java.lang.String value) {
		this.hmtxurl = value;
	}
	public java.lang.String getHmtzurl() {
		return this.hmtzurl;
	}
	
	public void setHmtzurl(java.lang.String value) {
		this.hmtzurl = value;
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
	//字典的get方法生成结束
	 
}

