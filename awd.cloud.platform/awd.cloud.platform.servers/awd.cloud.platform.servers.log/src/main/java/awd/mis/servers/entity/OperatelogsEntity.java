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
public class OperatelogsEntity extends SimpleGenericEntity<String> {
	
	//alias
	public static final String TABLE_ALIAS = "操作日志";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_OPTYPE = "操作类型";
	public static final String ALIAS_TABLENAME = "表名";
	public static final String ALIAS_OPTIME = "操作时间";
	public static final String ALIAS_OPERATOR = "操作人";
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
	@NotNull(message="操作类型不能为空",groups=SaveGroup.class)
    @Length(max=1,message="操作类型最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String optype;
	@NotNull(message="表名不能为空",groups=SaveGroup.class)
    @Length(max=50,message="表名最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String opcontent;
	@NotNull(message="表名不能为空",groups=SaveGroup.class)
    @Length(max=50,message="表名最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String opresult;
	
	@NotNull(message="操作时间不能为空")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date optime;

	@NotNull(message="操作人不能为空",groups=SaveGroup.class)
    @Length(max=30,message="操作人最大长度30位" ,groups=SaveGroup.class)
	private java.lang.String operator;
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


	public OperatelogsEntity(){
	}

	public OperatelogsEntity(
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
	
	public java.lang.String getOptype() {
		return this.optype;
	}
	
	public void setOptype(java.lang.String value) {
		this.optype = value;
	}
	
	
	public java.lang.String getOpcontent() {
		return opcontent;
	}

	public void setOpcontent(java.lang.String opcontent) {
		this.opcontent = opcontent;
	}

	public java.lang.String getOpresult() {
		return opresult;
	}

	public void setOpresult(java.lang.String opresult) {
		this.opresult = opresult;
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
	
	public java.lang.String getOperator() {
		return this.operator;
	}
	
	public void setOperator(java.lang.String value) {
		this.operator = value;
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

