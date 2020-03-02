/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.model;

import awd.framework.common.model.Model;
import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


/**
 * @author
 * @version 1.0
 * @since 1.0
 */


public class ExceptiondictionaryModel implements Model{

	//alias
	public static final String TABLE_ALIAS = "Exceptiondictionary";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "jsbh";
	public static final String ALIAS_KEY = "字典键值";
	public static final String ALIAS_DESCRIBE = "描述";
	public static final String ALIAS_CREATETIME = "创建时间";

	//columns START

	private String id;


	private String jsbh;

	private String key;

	private String describe;
	//创建时间
	private java.util.Date createtime;
	//columns END


	public ExceptiondictionaryModel(){
	}
	public ExceptiondictionaryModel(String id) {
		this.id = id;
	}


	public void setId(String value) {
		this.id = value;
	}

	public String getId() {
		return this.id;
	}

	public String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(String value) {
		this.jsbh = value;
	}
	public String getKey() {
		return this.key;
	}

	public void setKey(String value) {
		this.key = value;
	}
	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String value) {
		this.describe = value;
	}

	public java.util.Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}


}

