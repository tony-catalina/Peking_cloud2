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
public class Manager_MirisModel implements Model {
	
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
	
	private String id;


	private String jsbh;

	private String jh;

	private String zjh;

	private String hmwzbh;

	private byte[] hmtx;

	private byte[] hmtz;

	private String hmtxurl;

	private String hmtzurl;

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


	public Manager_MirisModel(){
	}
	public Manager_MirisModel(String id) {
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
	public String getJh() {
		return this.jh;
	}

	public void setJh(String value) {
		this.jh = value;
	}
	public String getZjh() {
		return this.zjh;
	}

	public void setZjh(String value) {
		this.zjh = value;
	}
	public String getHmwzbh() {
		return this.hmwzbh;
	}

	public void setHmwzbh(String value) {
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
	public String getHmtxurl() {
		return this.hmtxurl;
	}

	public void setHmtxurl(String value) {
		this.hmtxurl = value;
	}
	public String getHmtzurl() {
		return this.hmtzurl;
	}

	public void setHmtzurl(String value) {
		this.hmtzurl = value;
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

