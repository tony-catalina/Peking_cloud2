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




/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdatelogsEntity extends SimpleGenericEntity<String> {
	
	//alias
	public static final String TABLE_ALIAS = "修改日志";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_OPID = "操作ID";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_USERNAME = "用户名";
	public static final String ALIAS_TABLENAME = "表名";
	public static final String ALIAS_FIELDNAME = "字段名";
	public static final String ALIAS_OLDVALUE = "原值";
	public static final String ALIAS_NEWVALUE = "新值";
	public static final String ALIAS_OPERATOR = "修改人";
	public static final String ALIAS_OPTIME = "修改时间";
	public static final String ALIAS_OPIP = "修改IP";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	
	//date formats
	public static final String FORMAT_OPTIME = DATE_TIME_FORMAT;
	
	//保存组（不需要id验证）
	public static interface SaveGroup {};  
	//新增组（需要id验证）
	public static interface UpdateGroup {};
	//所有组
	@GroupSequence({SaveGroup.class, UpdateGroup.class})  
	public interface All {       
	} 

	//columns START

	@NotNull(message="监所编号不能为空",groups=SaveGroup.class)
    @Length(max=9,message="监所编号最大长度9位" ,groups=SaveGroup.class)
	private java.lang.String jsbh;
	@NotNull(message="用户名不能为空",groups=SaveGroup.class)
    @Length(max=50,message="用户名最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String username;
	@NotNull(message="表名不能为空",groups=SaveGroup.class)
    @Length(max=50,message="表名最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String servername;
	@NotNull(message="表名不能为空",groups=SaveGroup.class)
    @Length(max=50,message="表名最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String httpheads;
	@NotNull(message="字段名不能为空",groups=SaveGroup.class)
    @Length(max=50,message="字段名最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String httpmethod;
    @Length(max=4000,message="原值最大长度4000位" ,groups=SaveGroup.class)
	private java.lang.String url;
    @Length(max=4000,message="新值最大长度4000位" ,groups=SaveGroup.class)
	private java.lang.String response;
	@NotNull(message="修改人不能为空",groups=SaveGroup.class)
    @Length(max=30,message="修改人最大长度30位" ,groups=SaveGroup.class)
	private java.lang.String operator;	
	@NotNull(message="修改时间不能为空")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date optime;
	@NotNull(message="修改IP不能为空",groups=SaveGroup.class)
    @Length(max=30,message="修改IP最大长度30位" ,groups=SaveGroup.class)
	private java.lang.String opip;
	@NotNull(message="创建人不能为空",groups=SaveGroup.class)
    @Length(max=50,message="创建人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String creator;
	@NotNull(message="创建时间不能为空",groups=SaveGroup.class)
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
	//columns END


	public UpdatelogsEntity(){
	}

	public UpdatelogsEntity(
		java.lang.String id
	){
		super.setId(id);
	}
	
	
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	
	public java.lang.String getUsername() {
		return this.username;
	}
	
	public void setUsername(java.lang.String value) {
		this.username = value;
	}
	
	public java.lang.String getServername() {
		return servername;
	}

	public void setServername(java.lang.String servername) {
		this.servername = servername;
	}

	public java.lang.String getHttpheads() {
		return httpheads;
	}

	public void setHttpheads(java.lang.String httpheads) {
		this.httpheads = httpheads;
	}

	public java.lang.String getHttpmethod() {
		return httpmethod;
	}

	public void setHttpmethod(java.lang.String httpmethod) {
		this.httpmethod = httpmethod;
	}

	public java.lang.String getUrl() {
		return url;
	}

	public void setUrl(java.lang.String url) {
		this.url = url;
	}

	public java.lang.String getResponse() {
		return response;
	}

	public void setResponse(java.lang.String response) {
		this.response = response;
	}

	public java.lang.String getOperator() {
		return this.operator;
	}
	
	public void setOperator(java.lang.String value) {
		this.operator = value;
	}
	
	public String getOptimeString() {
		return date2String(getOptime(), FORMAT_OPTIME);
	}
	
	public java.util.Date getOptime() {
		return this.optime;
	}
	
	public void setOptime(java.util.Date value) {
		this.optime = value;
	}
	
	public java.lang.String getOpip() {
		return this.opip;
	}
	
	public void setOpip(java.lang.String value) {
		this.opip = value;
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

