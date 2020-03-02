/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.suppers.interfaces.entity;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.service.web.group.CreateGroup;
import awd.framework.common.service.web.group.UpdateGroup;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class UserappEntity extends SimpleGenericEntity<String>  {

	//alias
	public static final String TABLE_ALIAS = "Userapp";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_USERID = "用户编号";
	public static final String ALIAS_APPCODE = "应用编号";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";

	//所有组
	@GroupSequence({CreateGroup.class, UpdateGroup.class})
	public interface All {
	}

	//columns START
		/*@NotNull(message="ID不能为空",groups=CreateGroup.class)
	    @Length(max=23,message="ID最大长度23位" ,groups=CreateGroup.class)
		private java.lang.String id;*/
	
		@NotNull(message="用户编号不能为空",groups=CreateGroup.class)
		@Length(max=23,message="用户编号最大长度23位" ,groups=CreateGroup.class)
		private java.lang.String userid;
	
		@NotNull(message="应用编号不能为空",groups=CreateGroup.class)
		@Length(max=65535,message="应用编号最大长度65535位" ,groups=CreateGroup.class)
		private java.lang.String appcode;
	
		@Length(max=65535,message="备注最大长度65535位" ,groups=CreateGroup.class)
		private java.lang.String bz;
	
		@NotNull(message="创建人不能为空",groups=CreateGroup.class)
		@Length(max=50,message="创建人最大长度50位" ,groups=CreateGroup.class)
		private java.lang.String creator;
	
		@JSONField(format = "yyyy-MM-dd HH:mm:ss")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
		@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private java.util.Date createtime;
			
		@Length(max=50,message="更新人最大长度50位" ,groups=CreateGroup.class)
		private java.lang.String updator;
	
		@JSONField(format = "yyyy-MM-dd HH:mm:ss")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
		@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private java.util.Date updatetime;
			
	//columns END


	public UserappEntity(){
	}

	/*public UserappEntity(
		java.lang.String id
	){
		this.id = id;
	}*/



	/*public void setId(java.lang.String value) {
		this.id = value;
	}

	public java.lang.String getId() {
		return this.id;
	}*/


	public java.lang.String getUserid() {
		return this.userid;
	}

	public void setUserid(java.lang.String value) {
		this.userid = value;
	}

	public java.lang.String getAppcode() {
		return this.appcode;
	}

	public void setAppcode(java.lang.String value) {
		this.appcode = value;
	}

	public java.lang.String getBz() {
		return this.bz;
	}

	public void setBz(java.lang.String value) {
		this.bz = value;
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
	//开始生成字典的getString方法
	//字典的get方法生成结束
}

