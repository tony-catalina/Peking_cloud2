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
import awd.mis.servers.tools.CacheUtils;



/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class InterfaceEntity extends SimpleGenericEntity<String> {
	
	//alias
	public static final String TABLE_ALIAS = "Interface";
	public static final String ALIAS_INTERFACE_NAME = "接口名称";
	public static final String ALIAS_INTERFACE_DESCRIPTION = "接口描述";
	public static final String ALIAS_INTERFACE_TYPE = "接口类别(DM)";
	public static final String ALIAS_INTERFACE_ID = "接口ID";
	public static final String ALIAS_METHOD = "方法类型";
	public static final String ALIAS_SFQY = "是否启用(SHFO)";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	
	//保存组（不需要id验证）
	public static interface SaveGroup {};  
	//新增组（需要id验证）
	public static interface UpdateGroup {};
	//所有组
	@GroupSequence({SaveGroup.class, UpdateGroup.class})  
	public interface All {       
	} 

	//columns START


    @Length(max=255,message="接口名称最大长度255位" ,groups=SaveGroup.class)
	private java.lang.String interfaceName;

    @Length(max=255,message="接口描述最大长度255位" ,groups=SaveGroup.class)
	private java.lang.String interfaceDescription;

    @Length(max=1,message="接口类别(DM)最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String interfaceType;

    @Length(max=50,message="接口ID最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String interfaceId;

    @Length(max=10,message="方法类型最大长度10位" ,groups=SaveGroup.class)
	private java.lang.String method;

    @Length(max=1,message="是否启用(SHFO)最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String sfqy;

    @Length(max=50,message="创建人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String creator;

	
	@NotNull(message="创建时间不能为空")
	private java.util.Date createtime;

    @Length(max=50,message="更新人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String updator;

	
	@NotNull(message="更新时间不能为空")
	private java.util.Date updatetime;
	
	private java.lang.String kfzd;

	private java.lang.String tablename;
	//columns END


	public InterfaceEntity(){
	}

	public InterfaceEntity(
		java.lang.String id
	){
		super.setId(id);
	}

	


	public java.lang.String getInterfaceName() {
		return this.interfaceName;
	}
	
	public void setInterfaceName(java.lang.String value) {
		this.interfaceName = value;
	}

	public java.lang.String getInterfaceDescription() {
		return this.interfaceDescription;
	}
	
	public void setInterfaceDescription(java.lang.String value) {
		this.interfaceDescription = value;
	}

	public java.lang.String getInterfaceType() {
		return this.interfaceType;
	}
	
	public void setInterfaceType(java.lang.String value) {
		this.interfaceType = value;
	}

	public java.lang.String getInterfaceId() {
		return this.interfaceId;
	}
	
	public void setInterfaceId(java.lang.String value) {
		this.interfaceId = value;
	}

	public java.lang.String getMethod() {
		return this.method;
	}
	
	public void setMethod(java.lang.String value) {
		this.method = value;
	}

	public java.lang.String getSfqy() {
		return this.sfqy;
	}
	
	public void setSfqy(java.lang.String value) {
		this.sfqy = value;
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
	

	 public String getInterfaceTypeString() {
		return CacheUtils.get().getDictionary("DM", this.getInterfaceType());
	 }

	public java.lang.String getKfzd() {
		return kfzd;
	}

	public void setKfzd(java.lang.String kfzd) {
		this.kfzd = kfzd;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
}

