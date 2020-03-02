/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.entity;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import awd.framework.common.service.web.group.CreateGroup;
import org.hibernate.validator.constraints.Length;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.utils.DateTimeUtils;



/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class DocumentfileEntity extends SimpleGenericEntity<String> {
	
	//alias
	public static final String TABLE_ALIAS = "Documentfile";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_UUID = "uuid";
	public static final String ALIAS_FILE = "文件";
	public static final String ALIAS_FILEPATH = "文件地址";
	
	//保存组（不需要id验证）
	public static interface SaveGroup {};  
	//新增组（需要id验证）
	public static interface UpdateGroup {};
	//所有组
	@GroupSequence({SaveGroup.class, UpdateGroup.class})  
	public interface All {       
	} 


    @Length(max=50,message="uuid最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String uuid;

	@NotNull(message = "文件不能为空",groups = CreateGroup.class)
	private byte[] file;

    @Length(max=65535,message="文件地址最大长度65535位" ,groups=SaveGroup.class)
	private java.lang.String filepath;

	//columns END


	public DocumentfileEntity(){
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

