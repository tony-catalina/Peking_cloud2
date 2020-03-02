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
public class SuplierEntity extends SimpleGenericEntity<String> {
	
	//alias
	public static final String TABLE_ALIAS = "供应商";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_DQ = "地区(XZQH)";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_ADDRESS = "地址";
	public static final String ALIAS_PHONE = "电话";
	public static final String ALIAS_FZR = "负责人";
	public static final String ALIAS_FZRDH = "负责人电话";
	public static final String ALIAS_DESC = "描述";
	public static final String ALIAS_ICON = "图标";
	public static final String ALIAS_PIC1 = "图片1";
	public static final String ALIAS_PIC2 = "图片二";
	public static final String ALIAS_PIC3 = "图片3";
	public static final String ALIAS_STATE = "状态（STATE）";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	
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

	@NotNull(message="地区(XZQH)不能为空",groups=SaveGroup.class)
    @Length(max=6,message="地区(XZQH)最大长度6位" ,groups=SaveGroup.class)
	private java.lang.String dq;
	@NotNull(message="名称不能为空",groups=SaveGroup.class)
    @Length(max=200,message="名称最大长度200位" ,groups=SaveGroup.class)
	private java.lang.String name;
    @Length(max=200,message="地址最大长度200位" ,groups=SaveGroup.class)
	private java.lang.String address;
	@NotNull(message="电话不能为空",groups=SaveGroup.class)
    @Length(max=20,message="电话最大长度20位" ,groups=SaveGroup.class)
	private java.lang.String phone;
    @Length(max=50,message="负责人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String fzr;
    @Length(max=20,message="负责人电话最大长度20位" ,groups=SaveGroup.class)
	private java.lang.String fzrdh;
    @Length(max=500,message="描述最大长度500位" ,groups=SaveGroup.class)
	private java.lang.String desc;
    @Length(max=100,message="图标最大长度100位" ,groups=SaveGroup.class)
	private java.lang.String icon;
    @Length(max=100,message="图片1最大长度100位" ,groups=SaveGroup.class)
	private java.lang.String pic1;
    @Length(max=100,message="图片二最大长度100位" ,groups=SaveGroup.class)
	private java.lang.String pic2;
    @Length(max=100,message="图片3最大长度100位" ,groups=SaveGroup.class)
	private java.lang.String pic3;
	@NotNull(message="状态（STATE）不能为空",groups=SaveGroup.class)
    @Length(max=2,message="状态（STATE）最大长度2位" ,groups=SaveGroup.class)
	private java.lang.String state;
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


	public SuplierEntity(){
	}

	public SuplierEntity(
		java.lang.String id
	){
		super.setId(id);
	}
	
	public java.lang.String getDq() {
		return this.dq;
	}
	
	public void setDq(java.lang.String value) {
		this.dq = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	public java.lang.String getPhone() {
		return this.phone;
	}
	
	public void setPhone(java.lang.String value) {
		this.phone = value;
	}
	
	public java.lang.String getFzr() {
		return this.fzr;
	}
	
	public void setFzr(java.lang.String value) {
		this.fzr = value;
	}
	
	public java.lang.String getFzrdh() {
		return this.fzrdh;
	}
	
	public void setFzrdh(java.lang.String value) {
		this.fzrdh = value;
	}
	
	public java.lang.String getDesc() {
		return this.desc;
	}
	
	public void setDesc(java.lang.String value) {
		this.desc = value;
	}
	
	public java.lang.String getIcon() {
		return this.icon;
	}
	
	public void setIcon(java.lang.String value) {
		this.icon = value;
	}
	
	public java.lang.String getPic1() {
		return this.pic1;
	}
	
	public void setPic1(java.lang.String value) {
		this.pic1 = value;
	}
	
	public java.lang.String getPic2() {
		return this.pic2;
	}
	
	public void setPic2(java.lang.String value) {
		this.pic2 = value;
	}
	
	public java.lang.String getPic3() {
		return this.pic3;
	}
	
	public void setPic3(java.lang.String value) {
		this.pic3 = value;
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
	
	
}

