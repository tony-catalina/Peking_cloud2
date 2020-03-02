/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.model;

import awd.framework.common.model.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class ContrastipModel implements Model {

	//alias
	public static final String TABLE_ALIAS = "Contrastip";
	public static final String ALIAS_EQUIPMENTIP = "设备IP ";
	public static final String ALIAS_USERIP = "用户IP";
	public static final String ALIAS_DESCRIBE = "描述";

	//columns START

	private String id;

	private String equipmentip;

	private String userip;

	private String describe;
	//columns END
	//开始生成字典的String属性
	//字典的get方法生成结束


	public ContrastipModel(){
	}
	public ContrastipModel(String id) {
		this.id = id;
	}

	public String getEquipmentip() {
		return this.equipmentip;
	}

	public void setEquipmentip(String value) {
		this.equipmentip = value;
	}
	public String getUserip() {
		return this.userip;
	}

	public void setUserip(String value) {
		this.userip = value;
	}
	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String value) {
		this.describe = value;
	}
	//开始生成字典的getString方法
	//字典的get方法生成结束

}

