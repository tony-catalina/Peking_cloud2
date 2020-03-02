/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.model.logs;

import awd.cloud.platform.model.Model;
import javax.validation.constraints.NotNull;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class Logs_DocumentfileModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String uuid;
	
	private byte[] file;
	
	private java.lang.String filepath;
	//columns END

	 

	public Logs_DocumentfileModel(){
	}
	public Logs_DocumentfileModel(String id) {
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

