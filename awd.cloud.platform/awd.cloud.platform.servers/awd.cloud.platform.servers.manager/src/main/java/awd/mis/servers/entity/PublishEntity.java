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
import awd.framework.common.utils.DateTimeUtils;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PublishEntity extends SimpleGenericEntity<String> {
	
	//alias
	public static final String TABLE_ALIAS = "发布";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_PUBLISHER = "发布人";
	public static final String ALIAS_PUBTIME = "发布时间";
	public static final String ALIAS_VERSION = "版本";
	public static final String ALIAS_STATE = "状态";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_APPCODE = "应用编号";
	
	//date formats
	public static final String FORMAT_PUBTIME = DATE_FORMAT;
	
	//保存组（不需要id验证）
	public static interface SaveGroup {};  
	//新增组（需要id验证）
	public static interface UpdateGroup {};
	//所有组
	@GroupSequence({SaveGroup.class, UpdateGroup.class})  
	public interface All {       
	} 

	//columns START

    @Length(max=50,message="发布人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String publisher;
	
	@NotNull(message="发布时间不能为空")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date pubtime;

    @Length(max=20,message="版本最大长度20位" ,groups=SaveGroup.class)
	private java.lang.String version;
    @Length(max=2,message="状态最大长度2位" ,groups=SaveGroup.class)
	private java.lang.String state;
    @Length(max=50,message="创建人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String creator;
    @Length(max=11,message="创建时间最大长度11位" ,groups=SaveGroup.class)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
    @Length(max=50,message="更新人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String updator;
    @Length(max=11,message="更新时间最大长度11位" ,groups=SaveGroup.class)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
    @Length(max=50,message="应用编号最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String appcode;
	//columns END


	public PublishEntity(){
	}

	public PublishEntity(
		java.lang.String id
	){
		super.setId(id);
	}
	
	public java.lang.String getPublisher() {
		return this.publisher;
	}
	
	public void setPublisher(java.lang.String value) {
		this.publisher = value;
	}
	
	public java.util.Date getPubtime() {
		return this.pubtime;
	}
	
	public void setPubtime(java.util.Date value) {
		this.pubtime = value;
	}
	
	public java.lang.String getVersion() {
		return this.version;
	}
	
	public void setVersion(java.lang.String value) {
		this.version = value;
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
	
	public java.lang.String getAppcode() {
		return this.appcode;
	}
	
	public void setAppcode(java.lang.String value) {
		this.appcode = value;
	}
	
	
}

