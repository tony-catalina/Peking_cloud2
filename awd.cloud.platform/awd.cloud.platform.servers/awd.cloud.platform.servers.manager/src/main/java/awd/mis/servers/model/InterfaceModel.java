/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.model;

import awd.framework.common.model.Model;
import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import awd.framework.common.utils.StringUtils;
import awd.mis.servers.tools.CacheUtils;
import org.hibernate.validator.constraints.Length;

import java.util.Date;


/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class InterfaceModel implements Model{
	
	//columns START

	private java.lang.String id;

	private java.lang.String interfaceName;

	private java.lang.String interfaceDescription;

	private java.lang.String interfaceType;

	private java.lang.String interfaceId;

	private java.lang.String method;

	private java.lang.String sfqy;

	private java.lang.String creator;

	private java.util.Date createtime;

	private java.lang.String updator;

	private java.util.Date updatetime;

	private java.lang.String bdzt;

	private java.lang.String ibdId;

	private java.lang.String tablename;
	//columns END


	public InterfaceModel(){
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getInterfaceDescription() {
		return interfaceDescription;
	}

	public void setInterfaceDescription(String interfaceDescription) {
		this.interfaceDescription = interfaceDescription;
	}

	public String getInterfaceType() {
		return interfaceType;
	}

	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
	}

	public String getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(String interfaceId) {
		this.interfaceId = interfaceId;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getSfqy() {
		return sfqy;
	}

	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getBdzt() {
		return bdzt;
	}

	public void setBdzt(String bdzt) {
		this.bdzt = bdzt;
	}

	public String getInterfaceTypeString() {
		return CacheUtils.get().getDictionary("DM", this.getInterfaceType());
	}
	public String getBdztString() {
		if (!StringUtils.isNullOrEmpty(this.getBdzt())){
			return CacheUtils.get().getDictionary("BDZT", this.getBdzt());
		}else{
			return null;
		}
	}

	public String getIbdId() {
		return ibdId;
	}

	public void setIbdId(String ibdId) {
		this.ibdId = ibdId;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
}

