/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.entity;

import awd.framework.common.entity.SimpleGenericEntity;


/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class PyEntity extends SimpleGenericEntity<String> {
	
	//alias
	public static final String TABLE_ALIAS = "拼音字典";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_WORD = "汉字";
	public static final String ALIAS_PY = "拼音";
	
	//date formats
	

	//columns START
	private java.lang.String word;
	private java.lang.String py;
	//columns END


	public PyEntity(){
	}

	public PyEntity(
		java.lang.String id
	){
		super.setId(id);
	}
	
	public java.lang.String getWord() {
		return this.word;
	}
	
	public void setWord(java.lang.String value) {
		this.word = value;
	}
	
	public java.lang.String getPy() {
		return this.py;
	}
	
	public void setPy(java.lang.String value) {
		this.py = value;
	}
	
	
}

