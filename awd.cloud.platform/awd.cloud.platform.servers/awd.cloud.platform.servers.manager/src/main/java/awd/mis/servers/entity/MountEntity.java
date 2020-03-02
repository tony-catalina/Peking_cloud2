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
import awd.mis.servers.entity.AppEntity.SaveGroup;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MountEntity extends SimpleGenericEntity<String> {

	// alias
	public static final String TABLE_ALIAS = "安装卸载记录";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_APPCODE = "应用编号";
	public static final String ALIAS_STATE = "状态(STATE)";
	public static final String ALIAS_MOUNTOR = "安装人";
	public static final String ALIAS_MOUNTIME = "安装时间";
	public static final String ALIAS_UNMOUNTOR = "卸载人";
	public static final String ALIAS_UNMOUNTIME = "卸载时间";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";

	// date formats
	public static final String FORMAT_MOUNTIME = DATE_FORMAT;
	public static final String FORMAT_UNMOUNTIME = DATE_FORMAT;

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

	@NotNull(message = "监所编号不能为空", groups = SaveGroup.class)
	@Length(max = 9, message = "监所编号最大长度9位", groups = SaveGroup.class)
	private java.lang.String jsbh;
	@NotNull(message="应用编号不能为空",groups=SaveGroup.class)
    @Length(max=65535,message="应用编号最大长度65535位" ,groups=SaveGroup.class)
	private java.lang.String appcode;
	@NotNull(message = "状态(STATE)不能为空", groups = SaveGroup.class)
	@Length(max = 2, message = "状态(STATE)最大长度2位", groups = SaveGroup.class)
	private java.lang.String state;
	@Length(max = 50, message = "安装人最大长度50位", groups = SaveGroup.class)
	private java.lang.String mountor;

	@NotNull(message = "安装时间不能为空")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date mountime;

	@Length(max = 50, message = "卸载人最大长度50位", groups = SaveGroup.class)
	private java.lang.String unmountor;

	@NotNull(message = "卸载时间不能为空")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date unmountime;

	@NotNull(message = "创建人不能为空", groups = SaveGroup.class)
	@Length(max = 50, message = "创建人最大长度50位", groups = SaveGroup.class)
	private java.lang.String creator;
	@NotNull(message = "创建时间不能为空", groups = SaveGroup.class)
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

	public MountEntity() {
	}

	public MountEntity(java.lang.String id) {
		super.setId(id);
	}

	public java.lang.String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}

	public java.lang.String getAppcode() {
		return this.appcode;
	}

	public void setAppcode(java.lang.String value) {
		this.appcode = value;
	}

	public java.lang.String getState() {
		return this.state;
	}

	public void setState(java.lang.String value) {
		this.state = value;
	}

	public java.lang.String getMountor() {
		return this.mountor;
	}

	public void setMountor(java.lang.String value) {
		this.mountor = value;
	}

	public java.util.Date getMountime() {
		return this.mountime;
	}

	public void setMountime(java.util.Date value) {
		this.mountime = value;
	}

	public java.lang.String getUnmountor() {
		return this.unmountor;
	}

	public void setUnmountor(java.lang.String value) {
		this.unmountor = value;
	}

	public java.util.Date getUnmountime() {
		return this.unmountime;
	}

	public void setUnmountime(java.util.Date value) {
		this.unmountime = value;
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
