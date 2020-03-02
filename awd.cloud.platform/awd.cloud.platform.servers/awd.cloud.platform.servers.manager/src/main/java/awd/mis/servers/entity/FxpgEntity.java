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
public class FxpgEntity extends SimpleGenericEntity<String> {
	// alias
	public static final String TABLE_ALIAS = "风险评估";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_JSLX = "监所类型";
	public static final String ALIAS_CLASSID = "分类编号";
	public static final String ALIAS_CODE = "代码";
	public static final String ALIAS_CONTENT = "内容";
	public static final String ALIAS_CYGLFZ = "处遇管理分值";
	public static final String ALIAS_FXPGFZ = "风险评估分值";
	public static final String ALIAS_PY = "拼音";
	public static final String ALIAS_WGHF = "违规汇分";
	public static final String ALIAS_HFSX = "回分上限";
	public static final String ALIAS_ISUNIQUE = "是否只能被扣一次";
	public static final String ALIAS_MINVALUE = "最小值";
	public static final String ALIAS_MAXVALUE = "最大值";
	public static final String ALIAS_STATE = "状态";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";

	// date formats
	public static final String FORMAT_TIMETS = TIMESTAMP_FORMAT;

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

	@Length(max = 1, message = "监所类型最大长度1位", groups = SaveGroup.class)
	private java.lang.String jslx;

	@Length(max = 20, message = "分类编号最大长度20位", groups = SaveGroup.class)
	private java.lang.String classid;

	@Length(max = 20, message = "代码最大长度20位", groups = SaveGroup.class)
	private java.lang.String code;

	@Length(max = 500, message = "内容最大长度500位", groups = SaveGroup.class)
	private java.lang.String content;

	@Length(max = 8, message = "处遇管理分值最大长度8位", groups = SaveGroup.class)
	private java.math.BigDecimal cyglfz;

	@Length(max = 8, message = "风险评估分值最大长度8位", groups = SaveGroup.class)
	private java.math.BigDecimal fxpgfz;

	@Length(max = 500, message = "拼音最大长度500位", groups = SaveGroup.class)
	private java.lang.String py;

	@Length(max = 8, message = "违规汇分最大长度8位", groups = SaveGroup.class)
	private java.math.BigDecimal wghf;

	@Length(max = 8, message = "回分上限最大长度8位", groups = SaveGroup.class)
	private java.math.BigDecimal hfsx;

	@Length(max = 1, message = "是否只能被扣一次最大长度1位", groups = SaveGroup.class)
	private java.lang.String isunique;

	@Length(max = 8, message = "最小值最大长度8位", groups = SaveGroup.class)
	private java.math.BigDecimal minvalue;

	@Length(max = 8, message = "最大值最大长度8位", groups = SaveGroup.class)
	private java.math.BigDecimal maxvalue;

	@Length(max = 2, message = "状态最大长度2位", groups = SaveGroup.class)
	private java.lang.String state;

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

	public FxpgEntity() {
	}

	public FxpgEntity(java.lang.String id) {
		super.setId(id);
	}

	public java.lang.String getJslx() {
		return this.jslx;
	}
	public java.lang.String getJslxString() {
		return CacheUtils.get().getDictionary("JSTYPE", this.jslx);
	}
	

	public void setJslx(java.lang.String value) {
		this.jslx = value;
	}

	public java.lang.String getClassid() {
		return this.classid;
	}

	public java.lang.String getClassidString() {
		return CacheUtils.get().getClassfic("FXPG",this.classid);
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
	public java.lang.String getCodeString() {
		return CacheUtils.get().getContentByFxpg("1", "031701", this.getCode());
	}

	public java.lang.String getContent() {
		return this.content;
	}

	public void setContent(java.lang.String value) {
		this.content = value;
	}

	public java.math.BigDecimal getCyglfz() {
		return this.cyglfz;
	}

	public void setCyglfz(java.math.BigDecimal value) {
		this.cyglfz = value;
	}

	public java.math.BigDecimal getFxpgfz() {
		return this.fxpgfz;
	}

	public void setFxpgfz(java.math.BigDecimal value) {
		this.fxpgfz = value;
	}

	public java.lang.String getPy() {
		return this.py;
	}

	public void setPy(java.lang.String value) {
		this.py = value;
	}

	public java.math.BigDecimal getWghf() {
		return this.wghf;
	}

	public void setWghf(java.math.BigDecimal value) {
		this.wghf = value;
	}

	public java.math.BigDecimal getHfsx() {
		return this.hfsx;
	}

	public void setHfsx(java.math.BigDecimal value) {
		this.hfsx = value;
	}

	public java.lang.String getIsunique() {
		return this.isunique;
	}
	
	public java.lang.String getIsuniqueString() {
		return CacheUtils.get().getDictionary("SHFO", this.isunique);
	}

	public void setIsunique(java.lang.String value) {
		this.isunique = value;
	}

	public java.math.BigDecimal getMinvalue() {
		return this.minvalue;
	}

	public void setMinvalue(java.math.BigDecimal value) {
		this.minvalue = value;
	}

	public java.math.BigDecimal getMaxvalue() {
		return this.maxvalue;
	}

	public void setMaxvalue(java.math.BigDecimal value) {
		this.maxvalue = value;
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

}
