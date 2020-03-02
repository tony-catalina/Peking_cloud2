/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.desktop.model;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class DocumentfileModel implements Model {
	
	//alias
	public static final String TABLE_ALIAS = "Documentfile";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_UUID = "uuid";
	public static final String ALIAS_FILE = "文件";
	public static final String ALIAS_FILEPATH = "文件地址";


	private String id;
	private String uuid;

	private byte[] file;

	private String filepath;



	public DocumentfileModel(){
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String value) {
		this.uuid = value;
	}

	public byte[] getFile() {
		return this.file;
	}

	public void setFile(byte[] value) {
		this.file = value;
	}

	public String getFilepath() {
		return this.filepath;
	}

	public void setFilepath(String value) {
		this.filepath = value;
	}
	
}

