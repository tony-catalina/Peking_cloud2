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
import awd.mis.servers.tools.CacheUtils;



/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassficEntity extends SimpleGenericEntity<String> {
	//alias
	public static final String TABLE_ALIAS = "分类表";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_LX = "类型";
	public static final String ALIAS_PARENTID = "父类";
	public static final String ALIAS_CLASSID = "编号";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	
	//date formats
	public static final String FORMAT_TIMESTAMP = TIMESTAMP_FORMAT;
	//保存组（不需要id验证）
	public static interface SaveGroup {};  
	//新增组（需要id验证）
	public static interface UpdateGroup {};
	//所有组
	@GroupSequence({SaveGroup.class, UpdateGroup.class})  
	public interface All {       
	} 

	//columns START

    @Length(max=10,message="类型最大长度10位" ,groups=SaveGroup.class)
	private java.lang.String lx;
	@NotNull(message="父类不能为空",groups=SaveGroup.class)
    @Length(max=4000,message="父类最大长度4000位" ,groups=SaveGroup.class)
	private java.lang.String parentid;
	@NotNull(message="编号不能为空",groups=SaveGroup.class)
    @Length(max=40,message="编号最大长度40位" ,groups=SaveGroup.class)
	private java.lang.String classid;
	@NotNull(message="名称不能为空",groups=SaveGroup.class)
    @Length(max=200,message="名称最大长度200位" ,groups=SaveGroup.class)
	private java.lang.String name;
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


	public ClassficEntity(){
	}

	public ClassficEntity(
		java.lang.String id
	){
		super.setId(id);
	}

	
	public java.lang.String getLx() {
		return this.lx;
	}
	
	public void setLx(java.lang.String value) {
		this.lx = value;
	}
	
	public java.lang.String getParentid() {
		return this.parentid;
	}
	
	public void setParentid(java.lang.String value) {
		this.parentid = value;
	}
	
	public java.lang.String getClassid() {
		return this.classid;
	}
	
	public java.lang.String getClassidString() {
		return CacheUtils.get().getClassfic(this.lx,this.classid);
	}
	
	public void setClassid(java.lang.String value) {
		this.classid = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
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

