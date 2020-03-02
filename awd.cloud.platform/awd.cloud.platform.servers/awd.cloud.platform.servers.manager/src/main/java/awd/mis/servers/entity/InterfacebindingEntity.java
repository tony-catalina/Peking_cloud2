/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.entity;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.utils.DateTimeUtils;



/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class InterfacebindingEntity extends SimpleGenericEntity<String> {
	
	//alias
	public static final String TABLE_ALIAS = "Interfacebinding";
	public static final String ALIAS_APPCODE = "app标识符";
	public static final String ALIAS_INTERFACE_ID = "接口ID";
	public static final String ALIAS_BDZT = "绑定状态(BDZT)";
	
	//保存组（不需要id验证）
	public static interface SaveGroup {};  
	//新增组（需要id验证）
	public static interface UpdateGroup {};
	//所有组
	@GroupSequence({SaveGroup.class, UpdateGroup.class})  
	public interface All {       
	} 

	//columns START

    @Length(max=255,message="app标识符最大长度255位" ,groups=SaveGroup.class)
	private java.lang.String appcode;

    @Length(max=50,message="接口ID最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String interfaceId;

    @Length(max=1,message="绑定状态(BDZT)最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String bdzt;

	//columns END


	public InterfacebindingEntity(){
	}

	public InterfacebindingEntity(
		java.lang.String id
	){
		super.setId(id);
	}

	
	

	public java.lang.String getAppcode() {
		return this.appcode;
	}
	
	public void setAppcode(java.lang.String value) {
		this.appcode = value;
	}

	public java.lang.String getInterfaceId() {
		return this.interfaceId;
	}
	
	public void setInterfaceId(java.lang.String value) {
		this.interfaceId = value;
	}

	public java.lang.String getBdzt() {
		return this.bdzt;
	}
	
	public void setBdzt(java.lang.String value) {
		this.bdzt = value;
	}
	
}

