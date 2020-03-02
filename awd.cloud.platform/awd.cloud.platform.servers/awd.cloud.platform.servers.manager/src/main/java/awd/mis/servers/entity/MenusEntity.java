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
public class MenusEntity extends SimpleGenericEntity<String> {
	//alias
	public static final String TABLE_ALIAS = "应用菜单";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_APPCODE = "应用编号";
	public static final String ALIAS_MENU = "菜单编号";
	public static final String ALIAS_PARENT = "父菜单";
	public static final String ALIAS_MENUNAME = "菜单名";
	public static final String ALIAS_URL = "路径";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = " 更新时间";
	
	//date formats
	
	//保存组（不需要id验证）
	public static interface SaveGroup {};  
	//新增组（需要id验证）
	public static interface UpdateGroup {};
	//所有组
	@GroupSequence({SaveGroup.class, UpdateGroup.class})  
	public interface All {       
	} 

	//columns START

	@NotNull(message="应用编号不能为空",groups=SaveGroup.class)
    @Length(max=50,message="应用编号最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String appcode;
	@NotNull(message="菜单编号不能为空",groups=SaveGroup.class)
    @Length(max=10,message="菜单编号最大长度10位" ,groups=SaveGroup.class)
	private java.lang.String menu;
	@NotNull(message="父菜单不能为空",groups=SaveGroup.class)
    @Length(max=50,message="父菜单最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String parent;
	@NotNull(message="菜单名不能为空",groups=SaveGroup.class)
    @Length(max=200,message="菜单名最大长度200位" ,groups=SaveGroup.class)
	private java.lang.String menuname;
	@NotNull(message="路径不能为空",groups=SaveGroup.class)
    @Length(max=500,message="路径最大长度500位" ,groups=SaveGroup.class)
	private java.lang.String url;
	@NotNull(message="创建人不能为空",groups=SaveGroup.class)
    @Length(max=50,message="创建人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String creator;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date createtime;
    @Length(max=50,message="更新人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String updator;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date updatetime;
	//columns END


	public MenusEntity(){
	}

	public MenusEntity(
		java.lang.String id
	){
		super.setId(id);
	}

	
	public java.lang.String getAppcode() {
		return this.appcode;
	}
	
	public java.lang.String getAppcodeString() {
		return CacheUtils.get().getApp(this.appcode)==null?CacheUtils.get().getApp(this.appcode):"";
	} 
	
	public void setAppcode(java.lang.String value) {
		this.appcode = value;
	}
	
	public java.lang.String getMenu() {
		return this.menu;
	}
	
	
	public void setMenu(java.lang.String value) {
		this.menu = value;
	}
	
	public java.lang.String getParent() {
		return this.parent;
	}
	
	public void setParent(java.lang.String value) {
		this.parent = value;
	}
	
	public java.lang.String getMenuname() {
		return this.menuname;
	}
	
	public void setMenuname(java.lang.String value) {
		this.menuname = value;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}
	
	public void setUrl(java.lang.String value) {
		this.url = value;
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

