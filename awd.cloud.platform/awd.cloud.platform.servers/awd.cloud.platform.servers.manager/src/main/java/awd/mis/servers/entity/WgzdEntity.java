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
import awd.framework.common.utils.StringUtils;
import awd.mis.servers.tools.CacheUtils;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WgzdEntity extends SimpleGenericEntity<String> {
	
	// alias
	public static final String TABLE_ALIAS = "违规字典";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_JSLX = "监所编号";
	public static final String ALIAS_CLASSID = "分类代码";
	public static final String ALIAS_CODE = "代码";
	public static final String ALIAS_CONTENT = "内容";
	public static final String ALIAS_DJ = "等级";
	public static final String ALIAS_KFFZ = "扣分分值";
	public static final String ALIAS_PY = "拼音";
	public static final String ALIAS_STATE = "删除状态(STATE)";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_EDITABLE = "是否可编辑";

	// date formats
	
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

	@NotNull(message = "监所编号不能为空", groups = SaveGroup.class)
	@Length(max = 1, message = "监所编号最大长度1位", groups = SaveGroup.class)
	private java.lang.String jslx;
	@Length(max = 20, message = "分类代码最大长度20位", groups = SaveGroup.class)
	private java.lang.String classid;
	@NotNull(message = "代码不能为空", groups = SaveGroup.class)
	@Length(max = 20, message = "代码最大长度20位", groups = SaveGroup.class)
	private java.lang.String code;
	@NotNull(message = "内容不能为空", groups = SaveGroup.class)
	@Length(max = 200, message = "内容最大长度200位", groups = SaveGroup.class)
	private java.lang.String content;
	@NotNull(message = "等级不能为空", groups = SaveGroup.class)
	@Length(max = 1, message = "等级最大长度1位", groups = SaveGroup.class)
	private java.lang.String dj;
	@Length(max = 4, message = "扣分分值最大长度4位", groups = SaveGroup.class)
	private java.math.BigDecimal kffz;
	@Length(max = 600, message = "拼音最大长度600位", groups = SaveGroup.class)
	private java.lang.String py;
	@NotNull(message = "删除状态(STATE)不能为空", groups = SaveGroup.class)
	@Length(max = 2, message = "删除状态(STATE)最大长度2位", groups = SaveGroup.class)
	private java.lang.String state;
	@NotNull(message = "是否可编辑", groups = SaveGroup.class)
	@Length(max = 1, message = "可编辑状态最大长度1位", groups = SaveGroup.class)
	private java.lang.String editable;
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

	public WgzdEntity() {
	}

	public WgzdEntity(java.lang.String id) {
		super.setId(id);
	}

	public java.lang.String getJslx() {
		return this.jslx;
	}
	
	public java.lang.String getJslxString() {
		return CacheUtils.get().getDictionary("DM", this.jslx);
	}

	public void setJslx(java.lang.String value) {
		this.jslx = value;
	}

	public java.lang.String getClassid() {
		return this.classid;
	}

	public void setClassid(java.lang.String value) {
		this.classid = value;
	}

	public java.lang.String getCode() {
		return this.code;
	}

	public void setCode(java.lang.String value) {
		this.code = value;
	}

	public java.lang.String getContent() {
		return this.content;
	}

	public void setContent(java.lang.String value) {
		this.content = value;
	}

	public java.lang.String getDj() {
		return this.dj;
	}

	public void setDj(java.lang.String value) {
		this.dj = value;
	}

	public java.math.BigDecimal getKffz() {
		return this.kffz;
	}

	public void setKffz(java.math.BigDecimal value) {
		this.kffz = value;
	}

	public java.lang.String getPy() {
		return this.py;
	}

	public void setPy(java.lang.String value) {
		this.py = value;
	}

	public java.lang.String getState() {
		return this.state;
	}
	public java.lang.String getStateString() {
		return CacheUtils.get().getDictionary("YWSTATE", this.state);
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
	

	public java.lang.String getClassidString() {
		return CacheUtils.get().getClassfic("WG",this.classid);
	}


	public String getEditable() {
		return editable;
	}

	public void setEditable(String editable) {
		this.editable = editable;
	}

	public String getEditableString() {
		if(!StringUtils.isNullOrEmpty(this.editable)) {
			return CacheUtils.get().getDictionary("SHFO", this.editable);
		}
		return "";
	}

}
