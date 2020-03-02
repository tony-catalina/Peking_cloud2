/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.entity;


import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import awd.mis.servers.tools.CacheUtils;
import org.hibernate.validator.constraints.Length;
import awd.framework.common.entity.SimpleGenericEntity;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;
import awd.framework.common.service.web.group.CreateGroup;
import awd.framework.common.service.web.group.UpdateGroup;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class ApidocEntity extends SimpleGenericEntity<String>  {

	//alias
	public static final String TABLE_ALIAS = "Apidoc";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_APPCODE = "应用编号";
	public static final String ALIAS_STATE = "删除状态";
	public static final String ALIAS_RESTFUL = "请求方式";
	public static final String ALIAS_PARAM = "请求参数";
	public static final String ALIAS_RESPONSE = "相应参数";

	//所有组
	@GroupSequence({CreateGroup.class, UpdateGroup.class})
	public interface All {
	}

	//columns START
		/*@NotNull(message="id不能为空",groups=CreateGroup.class)
	    @Length(max=23,message="id最大长度23位" ,groups=CreateGroup.class)
		private java.lang.String id;*/
	
		@Length(max=9,message="监所编号最大长度9位" ,groups=CreateGroup.class)
		private String jsbh;
	
		@Length(max=50,message="创建人最大长度50位" ,groups=CreateGroup.class)
		private String creator;
	
		@JSONField(format = "yyyy-MM-dd HH:mm:ss")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
		@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private java.util.Date createtime;
			
		@Length(max=50,message="更新人最大长度50位" ,groups=CreateGroup.class)
		private String updator;
	
		@JSONField(format = "yyyy-MM-dd HH:mm:ss")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
		@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private java.util.Date updatetime;
			
		@NotNull(message="应用编号不能为空",groups=CreateGroup.class)
		@Length(max=65535,message="应用编号最大长度65535位" ,groups=CreateGroup.class)
		private String appcode;
	
		@NotNull(message="删除状态不能为空",groups=CreateGroup.class)
		@Length(max=2,message="删除状态最大长度2位" ,groups=CreateGroup.class)
		private String state;
	
		@Length(max=50,message="请求方式最大长度50位" ,groups=CreateGroup.class)
		private String restful;
	
		@Length(max=65535,message="请求参数最大长度65535位" ,groups=CreateGroup.class)
		private String param;
	
		@Length(max=65535,message="相应参数最大长度65535位" ,groups=CreateGroup.class)
		private String response;
	
	//columns END


	public ApidocEntity(){
	}

	/*public ApidocEntity(
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


	public String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(String value) {
		this.jsbh = value;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String value) {
		this.creator = value;
	}
	public java.util.Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
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

	public String getAppcode() {
		return this.appcode;
	}

	public void setAppcode(String value) {
		this.appcode = value;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
	}

	public String getRestful() {
		return this.restful;
	}

	public void setRestful(String value) {
		this.restful = value;
	}

	public String getParam() {
		return this.param;
	}

	public void setParam(String value) {
		this.param = value;
	}

	public String getResponse() {
		return this.response;
	}

	public void setResponse(String value) {
		this.response = value;
	}
	//开始生成字典的getString方法
		public String getJsbhString() {
			return CacheUtils.get().getJsbhString(this.jsbh);
		}
	//字典的get方法生成结束
}

