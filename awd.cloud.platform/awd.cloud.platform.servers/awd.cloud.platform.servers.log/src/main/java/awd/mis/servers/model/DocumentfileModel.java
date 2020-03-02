/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.model;

import awd.framework.common.model.Model;


/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class DocumentfileModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Documentfile";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_UUID = "uuid";
	public static final String ALIAS_FILE = "文件";
	public static final String ALIAS_FILEPATH = "文件地址";
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String uuid;
	
	private byte[] file;
	
	private java.lang.String filepath;
	//columns END


	public DocumentfileModel(){
	}
	public DocumentfileModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getUuid() {
		return this.uuid;
	}
	
	public void setUuid(java.lang.String value) {
		this.uuid = value;
	}
	public byte[] getFile() {
		return this.file;
	}
	
	public void setFile(byte[] value) {
		this.file = value;
	}
	public java.lang.String getFilepath() {
		return this.filepath;
	}
	
	public void setFilepath(java.lang.String value) {
		this.filepath = value;
	}
	
}

