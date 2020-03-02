/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.entity;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.mis.servers.tools.CacheUtils;



/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class JkfzdEntity extends SimpleGenericEntity<String> {
	
	//alias
	public static final String TABLE_ALIAS = "加扣分字典";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_CODE = "code";
	public static final String ALIAS_CYGLFZ = "分值";
	public static final String ALIAS_CONTENT = "content";
	public static final String ALIAS_PY = "拼音";
	public static final String ALIAS_STATE = "删除状态";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	
	//date formats
	
	//保存组（不需要id验证）
	public static interface SaveGroup {};  
	//新增组（需要id验证）
	public static interface UpdateGroup {};
	//所有组
	@GroupSequence({SaveGroup.class, UpdateGroup.class})  
	public interface All {       
	} 

	//columns START

	@NotNull(message="监所编号不能为空",groups=SaveGroup.class)
    @Length(max=9,message="监所编号最大长度9位" ,groups=SaveGroup.class)
	private String jsbh;
    @Length(max=21,message="code最大长度21位" ,groups=SaveGroup.class)
	private String code;
	private java.math.BigDecimal cyglfz;
    @Length(max=200,message="content最大长度200位" ,groups=SaveGroup.class)
	private String content;
    @Length(max=100,message="拼音最大长度100位" ,groups=SaveGroup.class)
	private String py;
    @Length(max=2,message="删除状态最大长度2位" ,groups=SaveGroup.class)
	private String state;
    @Length(max=30,message="创建人最大长度30位" ,groups=SaveGroup.class)
	private String creator;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
    @Length(max=30,message="更新人最大长度30位" ,groups=SaveGroup.class)
	private String updator;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
	//columns END


	public JkfzdEntity(){
	}

	public JkfzdEntity(
		String id
	){
		super.setId(id);
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
	
	public String getCode() {
		return this.code;
	}
	
	public void setCode(String value) {
		this.code = value;
	}
	
	public java.math.BigDecimal getCyglfz() {
		return this.cyglfz;
	}
	
	public void setCyglfz(java.math.BigDecimal value) {
		this.cyglfz = value;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String value) {
		this.content = value;
	}
	
	public String getPy() {
		return this.py;
	}
	
	public void setPy(String value) {
		this.py = value;
	}
	
	public String getState() {
		return this.state;
	}
	
	public String getStateString() {
        return CacheUtils.get().getDictionary("YWSTATE", this.state);
    }
	
	public void setState(String value) {
		this.state = value;
	}
	
	public String getCreator() {
		return this.creator;
	}
	
	public void setCreator(String value) {
		this.creator = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public String getUpdator() {
		return this.updator;
	}
	
	public void setUpdator(String value) {
		this.updator = value;
	}
	
	public java.util.Date getUpdatetime() {
		return this.updatetime;
	}
	
	public void setUpdatetime(java.util.Date value) {
		this.updatetime = value;
	}
	
	
}

