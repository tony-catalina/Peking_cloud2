/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.entity;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.utils.ApplicationContextHolder;
import awd.framework.expands.redisclient.RedisUtils;
import awd.mis.servers.tools.CacheUtils;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CcicEntity extends SimpleGenericEntity<String> {
	// alias
	public static final String TABLE_ALIAS = "CCIC";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_LSH = "流水号";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_BDSJ = "比对时间";
	public static final String ALIAS_BDR = "比对人";
	public static final String ALIAS_XM = "姓名";
	public static final String ALIAS_CSRQ = "出生日期";
	public static final String ALIAS_ZJH = "证件号";
	public static final String ALIAS_BDJG = "比对结果";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";

	// date formats
	public static final String FORMAT_BDSJ = DATE_FORMAT;
	public static final String FORMAT_CSRQ = DATE_FORMAT;
	public static final String FORMAT_TIMESTAMP = TIMESTAMP_FORMAT;

	// 保存组（不需要id验证）
	public static interface SaveGroup {
	};

	// 新增组（需要id验证）
	public static interface UpdateGroup {
	};

	// 所有组
	@GroupSequence({ SaveGroup.class, UpdateGroup.class })
	public interface All {
	}

	// columns START

	@Length(max = 21, message = "流水号最大长度21位", groups = SaveGroup.class)
	private java.lang.String lsh;
	@Length(max = 9, message = "监所编号最大长度9位", groups = SaveGroup.class)
	private java.lang.String jsbh;

	@NotNull(message = "比对时间不能为空")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date bdsj;

	@Length(max = 30, message = "比对人最大长度30位", groups = SaveGroup.class)
	private java.lang.String bdr;
	@Length(max = 255, message = "姓名最大长度255位", groups = SaveGroup.class)
	private java.lang.String xm;

	@NotNull(message = "出生日期不能为空")
	@JSONField(format = "yyyy-MM-dd")  
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date csrq;

	@Length(max = 30, message = "证件号最大长度30位", groups = SaveGroup.class)
	private java.lang.String zjh;
	@Length(max = 1, message = "比对结果最大长度1位", groups = SaveGroup.class)
	private java.lang.String bdjg;
	@Length(max = 21, message = "人员编号最大长度21位", groups = SaveGroup.class)
	private java.lang.String rybh;
	@Length(max = 50, message = "创建人最大长度50位", groups = SaveGroup.class)
	private java.lang.String creator;
	@Length(max = 11, message = "创建时间最大长度11位", groups = SaveGroup.class)
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
	@Length(max = 50, message = "更新人最大长度50位", groups = SaveGroup.class)
	private java.lang.String updator;
	@Length(max = 11, message = "更新时间最大长度11位", groups = SaveGroup.class)
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
	// columns END

	public CcicEntity() {
	}

	public CcicEntity(java.lang.String id) {
		super.setId(id);
	}

	public java.lang.String getLsh() {
		return this.lsh;
	}

	public void setLsh(java.lang.String value) {
		this.lsh = value;
	}

	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public java.lang.String getJsbhString() {
		return CacheUtils.get().getJsbhString(this.jsbh);
	}

	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}

	public java.util.Date getBdsj() {
		return this.bdsj;
	}

	public void setBdsj(java.util.Date value) {
		this.bdsj = value;
	}

	public java.lang.String getBdr() {
		return this.bdr;
	}

	public void setBdr(java.lang.String value) {
		this.bdr = value;
	}

	public java.lang.String getXm() {
		return this.xm;
	}

	public void setXm(java.lang.String value) {
		this.xm = value;
	}

	public java.util.Date getCsrq() {
		return this.csrq;
	}

	public void setCsrq(java.util.Date value) {
		this.csrq = value;
	}

	public java.lang.String getZjh() {
		return this.zjh;
	}

	public void setZjh(java.lang.String value) {
		this.zjh = value;
	}

	public java.lang.String getBdjg() {
		return this.bdjg;
	}

	public void setBdjg(java.lang.String value) {
		this.bdjg = value;
	}

	public java.lang.String getRybh() {
		return this.rybh;
	}

	public void setRybh(java.lang.String value) {
		this.rybh = value;
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
