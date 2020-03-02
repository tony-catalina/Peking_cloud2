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
import awd.framework.common.utils.ApplicationContextHolder;
import awd.framework.expands.redisclient.RedisUtils;


/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class FlownodeEntity extends SimpleGenericEntity<String> {
	//alias
	public static final String TABLE_ALIAS = "流程节点";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_NODECODE = "节点代码";
	public static final String ALIAS_NODENAME = "节点名称";
	public static final String ALIAS_FLOWMAPID = "FlowMap表的ID";
	public static final String ALIAS_JDLX = "节点类型";
	public static final String ALIAS_ROLE = "操作角色";
	public static final String ALIAS_MEMO = "描述";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	
	//date formats
	public static final String FORMAT_TIMETS = TIMESTAMP_FORMAT;

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
	private java.lang.String jsbh;
	
	
	@NotNull(message="节点代码不能为空",groups=SaveGroup.class)
    @Length(max=8,message="节点代码最大长度8位" ,groups=SaveGroup.class)
	private java.lang.String nodecode;
	
	
	@NotNull(message="节点名称不能为空",groups=SaveGroup.class)
    @Length(max=200,message="节点名称最大长度200位" ,groups=SaveGroup.class)
	private java.lang.String nodename;
	
	
	@NotNull(message="FlowMap表的ID不能为空",groups=SaveGroup.class)
    @Length(max=23,message="FlowMap表的ID最大长度23位" ,groups=SaveGroup.class)
	private java.lang.String flowmapid;
	
	
    @Length(max=100,message="节点类型最大长度100位" ,groups=SaveGroup.class)
	private java.lang.String jdlx;
	
	
    @Length(max=100,message="操作角色最大长度100位" ,groups=SaveGroup.class)
	private java.lang.String role;
	
	
    @Length(max=500,message="描述最大长度500位" ,groups=SaveGroup.class)
	private java.lang.String memo;
	
	
	@NotNull(message="创建人不能为空",groups=SaveGroup.class)
    @Length(max=50,message="创建人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String creator;
	
    @Length(max=11,message="创建时间最大长度11位" ,groups=SaveGroup.class)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
	
	
    @Length(max=50,message="更新人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String updator;
	
	
    @Length(max=11,message="更新时间最大长度11位" ,groups=SaveGroup.class)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
    
    @Length(max=10,message="更新人最大长度10位" ,groups=SaveGroup.class)
	private java.lang.String menu;
    
	
	//columns END


	public FlownodeEntity(){
	}

	public FlownodeEntity(
		java.lang.String id
	){
		super.setId(id);
	}
	
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	public java.lang.String getNodecode() {
		return this.nodecode;
	}
	
	public void setNodecode(java.lang.String value) {
		this.nodecode = value;
	}
	public java.lang.String getNodename() {
		return this.nodename;
	}
	
	public void setNodename(java.lang.String value) {
		this.nodename = value;
	}
	public java.lang.String getFlowmapid() {
		return this.flowmapid;
	}
	
	public void setFlowmapid(java.lang.String value) {
		this.flowmapid = value;
	}
	public java.lang.String getJdlx() {
		return this.jdlx;
	}
	
	public void setJdlx(java.lang.String value) {
		this.jdlx = value;
	}
	public java.lang.String getRole() {
		return this.role;
	}
	
	public void setRole(java.lang.String value) {
		this.role = value;
	}
	public java.lang.String getMemo() {
		return this.memo;
	}
	
	public void setMemo(java.lang.String value) {
		this.memo = value;
	}
	public java.lang.String getCreator() {
		return this.creator;
	}
	
	public void setCreator(java.lang.String value) {
		this.creator = value;
	}
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	public java.lang.String getUpdator() {
		return this.updator;
	}
	
	public void setUpdator(java.lang.String value) {
		this.updator = value;
	}
	public java.util.Date getUpdatetime() {
		return this.updatetime;
	}
	
	public void setUpdatetime(java.util.Date value) {
		this.updatetime = value;
	}

	public java.lang.String getMenu() {
		return menu;
	}

	public void setMenu(java.lang.String menu) {
		this.menu = menu;
	}
	
	
	
}

