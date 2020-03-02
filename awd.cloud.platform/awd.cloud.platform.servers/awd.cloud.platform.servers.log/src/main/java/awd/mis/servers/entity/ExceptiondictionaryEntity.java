/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.entity;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.utils.DateTimeUtils;
import awd.mis.servers.utils.CacheUtils;



/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExceptiondictionaryEntity extends SimpleGenericEntity<String> {
	
	//alias
	public static final String TABLE_ALIAS = "Exceptiondictionary";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "jsbh";
	public static final String ALIAS_KEY = "字典键值";
	public static final String ALIAS_DESCRIBE = "描述";
	public static final String ALIAS_CREATETIME = "创建时间";
	
	//保存组（不需要id验证）
	public static interface SaveGroup {};  
	//新增组（需要id验证）
	public static interface UpdateGroup {};
	//所有组
	@GroupSequence({SaveGroup.class, UpdateGroup.class})  
	public interface All {       
	} 

	//columns START

	@NotNull(message="id不能为空",groups=SaveGroup.class)
    @Length(max=23,message="id最大长度23位" ,groups=SaveGroup.class)
	private String id;

    @Length(max=9,message="jsbh最大长度9位" ,groups=SaveGroup.class)
	private String jsbh;

    @Length(max=255,message="字典键值最大长度255位" ,groups=SaveGroup.class)
	private String key;

    @Length(max=255,message="描述最大长度255位" ,groups=SaveGroup.class)
	private String describe;

	
	@NotNull(message="创建时间不能为空")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;

	//columns END


	public ExceptiondictionaryEntity(){
	}

	public ExceptiondictionaryEntity(
		String id
	){
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
	
	public String getJsbhString() {
    	return CacheUtils.get().getJsbhString(this.jsbh);
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

