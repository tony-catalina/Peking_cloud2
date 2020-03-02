/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.model.jwp;

import awd.bj.base.model.Model;
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
public class YjdjModel implements Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4314745615200963962L;
	// alias
	public static final String TABLE_ALIAS = "Yjdj";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_JSH = "监室号";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_XM = "姓名";
	public static final String ALIAS_SFFFYJ = "是否发放眼镜(SHFO)";
	public static final String ALIAS_FFMJ = "发放民警";
	public static final String ALIAS_FFSJ = "发放时间";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_STATE = "删除状态";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CRETETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";

	// columns START

	private String id;

	private String jsbh;

	private String jsh;

	private String rybh;

	private String xm;

	private String sfffyj;

	private String ffmj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ffsj;

	private String bz;

	private String state;

	private String creator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;

	public java.util.Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}

	private String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;

	// columns END
	// 开始生成字典的String属性
	private String jsbhString;

	private String sfffyjString;
	// 字典的get方法生成结束

	private String bllx;

	public YjdjModel() {
	}

	public YjdjModel(String id) {
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

	public String getXm() {
		return this.xm;
	}

	public void setXm(String value) {
		this.xm = value;
	}

	public String getSfffyj() {
		return this.sfffyj;
	}

	public void setSfffyj(String value) {
		this.sfffyj = value;
	}

	public String getFfmj() {
		return this.ffmj;
	}

	public void setFfmj(String value) {
		this.ffmj = value;
	}

	public java.util.Date getFfsj() {
		return this.ffsj;
	}

	public void setFfsj(java.util.Date value) {
		this.ffsj = value;
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

	// 开始生成字典的getString方法
	public String getJsbhString() {
		return this.jsbhString;
	}

	public String getSfffyjString() {
		return this.sfffyjString;
	}

	// 字典的get方法生成结束
	public String getBllx() {
		return bllx;
	}

	public void setBllx(String bllx) {
		this.bllx = bllx;
	}

	public void setJsbhString(String jsbhString) {
		this.jsbhString = jsbhString;
	}

	public void setSfffyjString(String sfffyjString) {
		this.sfffyjString = sfffyjString;
	}

}
