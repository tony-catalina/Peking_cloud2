/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.model.manager;

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
public class Manager_MfingerModel implements Model {
	
	//alias
	public static final String TABLE_ALIAS = "Mfinger";
	public static final String ALIAS_ID = "编号";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_ZJH = "证件号";
	public static final String ALIAS_JH = "警号";
	public static final String ALIAS_SZBH = "手指编号（RYSZBH）";
	public static final String ALIAS_ZWTX = "指纹图像";
	public static final String ALIAS_ZWTZ = "指纹特征";
	public static final String ALIAS_ZWTXURL = "指纹图像地址";
	public static final String ALIAS_ZWTZURL = "指纹特征地址";
	public static final String ALIAS_STATE = "状态  R3删除  R2正常";
	public static final String ALIAS_SCBZ = "上传标志";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	
	//columns START
	
	private String id;


	private String jsbh;

	private String zjh;

	private String jh;

	private String szbh;

	private byte[] zwtx;

	private byte[] zwtz;

	private String zwtxurl;

	private String zwtzurl;

	private String state;

	private String scbz;

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
	//开始生成字典的String属性
	private String jsbhString;

	//字典的get方法生成结束


	public Manager_MfingerModel(){
	}
	public Manager_MfingerModel(String id) {
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
	public String getZjh() {
		return this.zjh;
	}

	public void setZjh(String value) {
		this.zjh = value;
	}
	public String getJh() {
		return this.jh;
	}

	public void setJh(String value) {
		this.jh = value;
	}
	public String getSzbh() {
		return this.szbh;
	}

	public void setSzbh(String value) {
		this.szbh = value;
	}
	public byte[] getZwtx() {
		return this.zwtx;
	}

	public void setZwtx(byte[] value) {
		this.zwtx = value;
	}
	public byte[] getZwtz() {
		return this.zwtz;
	}

	public void setZwtz(byte[] value) {
		this.zwtz = value;
	}
	public String getZwtxurl() {
		return this.zwtxurl;
	}

	public void setZwtxurl(String value) {
		this.zwtxurl = value;
	}
	public String getZwtzurl() {
		return this.zwtzurl;
	}

	public void setZwtzurl(String value) {
		this.zwtzurl = value;
	}
	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
	}
	public String getScbz() {
		return this.scbz;
	}

	public void setScbz(String value) {
		this.scbz = value;
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
	
	//开始生成字典的getString方法
	public String getJsbhString() {
		return this.jsbhString;
	}
	//字典的get方法生成结束
	 
}

