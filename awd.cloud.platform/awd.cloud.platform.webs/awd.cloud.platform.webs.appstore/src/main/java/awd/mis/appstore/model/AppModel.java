/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.appstore.model;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.framework.common.utils.StringUtils;


/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class AppModel implements Model  {
	
	//alias
	public static final String TABLE_ALIAS = "应用";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_APPCODE = "应用编号";
	public static final String ALIAS_NAME = "应用名称";
	public static final String ALIAS_VERSION = "版本号";
	public static final String ALIAS_TYPE = "应用类型（APPTYPE）";
	public static final String ALIAS_ZONE = "使用范围(ZONE)";
	public static final String ALIAS_URL = "链接地址获取下载地址";
	public static final String ALIAS_MEMO = "描述";
	public static final String ALIAS_PIC1 = "图片1URL";
	public static final String ALIAS_PIC2 = "图片2URL";
	public static final String ALIAS_PIC3 = "图片3URL";
	public static final String ALIAS_FLAG = "应用状态(APPSTATE)";
	public static final String ALIAS_STATE = "删除状态";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_P1 = "图片1";
	public static final String ALIAS_P2 = "图片2";
	public static final String ALIAS_P3 = "图片3";
	public static final String ALIAS_exeurl = "exe地址";
	public static final String ALIAS_exefile = "exe应用程序";
	
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

	@NotNull(message="ID不能为空" ,groups=UpdateGroup.class)
    @Length(max=23,message="ID最大长度23位" ,groups=SaveGroup.class)
	private java.lang.String id;

	@NotNull(message="应用编号不能为空",groups=SaveGroup.class)
    @Length(max=50,message="应用编号最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String appcode;
	@NotNull(message="应用名称不能为空",groups=SaveGroup.class)
    @Length(max=100,message="应用名称最大长度100位" ,groups=SaveGroup.class)
	private java.lang.String name;
	@NotNull(message="版本号不能为空",groups=SaveGroup.class)
    @Length(max=50,message="版本号最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String version;
	@NotNull(message="应用类型（APPTYPE）不能为空",groups=SaveGroup.class)
    @Length(max=1,message="应用类型（APPTYPE）最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String type;
	@NotNull(message="使用范围(ZONE)不能为空",groups=SaveGroup.class)
    @Length(max=20,message="使用范围(ZONE)最大长度20位" ,groups=SaveGroup.class)
	private java.lang.String zone;
	@NotNull(message="链接地址获取下载地址不能为空",groups=SaveGroup.class)
    @Length(max=2000,message="链接地址获取下载地址最大长度2000位" ,groups=SaveGroup.class)
	private java.lang.String url;
    @Length(max=4000,message="描述最大长度4000位" ,groups=SaveGroup.class)
	private java.lang.String memo;
    @Length(max=200,message="图片1最大长度200位" ,groups=SaveGroup.class)
	private java.lang.String pic1;
    @Length(max=200,message="图片2最大长度200位" ,groups=SaveGroup.class)
	private java.lang.String pic2;
    @Length(max=200,message="图片3最大长度200位" ,groups=SaveGroup.class)
	private java.lang.String pic3;
	@NotNull(message="应用状态(APPSTATE)不能为空",groups=SaveGroup.class)
    @Length(max=2,message="应用状态(APPSTATE)最大长度2位" ,groups=SaveGroup.class)
	private java.lang.String flag;
	@NotNull(message="删除状态不能为空",groups=SaveGroup.class)
    @Length(max=2,message="删除状态最大长度2位" ,groups=SaveGroup.class)
	private java.lang.String state;
	@NotNull(message="创建人不能为空",groups=SaveGroup.class)
    @Length(max=50,message="创建人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String creator;
	@NotNull(message="创建时间不能为空",groups=SaveGroup.class)
    @Length(max=11,message="创建时间最大长度11位" ,groups=SaveGroup.class)
	@JSONField(format = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date createtime;
	private java.lang.String createtimeString;
    @Length(max=50,message="更新人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String updator;
    @Length(max=11,message="更新时间最大长度11位" ,groups=SaveGroup.class)
    @Length(max=65535,message="图片1最大长度65535位" ,groups=SaveGroup.class)
 	private byte[] p1;
     @Length(max=65535,message="图片2最大长度65535位" ,groups=SaveGroup.class)
 	private byte[] p2;
     @Length(max=65535,message="图片3最大长度65535位" ,groups=SaveGroup.class)
 	private byte[] p3;
     
    private String exeurl;
     
 	private byte[] exefile;
     
    private java.lang.String typeString;
    private java.lang.String zoneString;
    @JSONField(format = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date updatetime;
    private java.lang.String updatetimeString;
    private java.lang.String role;
    private java.lang.String fruit;
    private java.lang.String icon;
    private String roleString;
    private String sysversion;
    private String browversion;
    
    //应用安装次数
    private  String mount;
    private String info;
    
    
	//columns END



    

	public String getMount() {
		return mount;
	}


	public void setMount(String mount) {
		this.mount = mount;
	}


	public AppModel(){
		
	}
	
	
	public String getSysversion() {
		return sysversion;
	}


	public void setSysversion(String sysversion) {
		this.sysversion = sysversion;
	}


	public String getBrowversion() {
		return browversion;
	}


	public void setBrowversion(String browversion) {
		this.browversion = browversion;
	}


	public String getRoleString() {
		return roleString;
	}


	public void setRoleString(String roleString) {
		this.roleString = roleString;
	}

	public String getExeurl() {
		return exeurl;
	}

	public byte[] getExefile() {
		return exefile;
	}


	public void setExeurl(String exeurl) {
		this.exeurl = exeurl;
	}


	public void setExefile(byte[] exefile) {
		this.exefile = exefile;
	}


	public java.lang.String getTypeString() {
		return typeString;
	}

	public java.lang.String getZoneString() {
		return zoneString;
	}

	public void setTypeString(java.lang.String typeString) {
		this.typeString = typeString;
	}

	public void setZoneString(java.lang.String zoneString) {
		this.zoneString = zoneString;
	}


	public byte[] getP1() {
		return p1;
	}

	public byte[] getP2() {
		return p2;
	}

	public byte[] getP3() {
		return p3;
	}

	public void setP1(byte[] p1) {
		this.p1 = p1;
	}

	public void setP2(byte[] p2) {
		this.p2 = p2;
	}

	public void setP3(byte[] p3) {
		this.p3 = p3;
	}

	public AppModel(
		java.lang.String id
	){
		this.id = id;
	}

	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getAppcode() {
		return this.appcode;
	}
	
	public void setAppcode(java.lang.String value) {
		this.appcode = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getVersion() {
		return this.version;
	}
	
	public void setVersion(java.lang.String value) {
		this.version = value;
	}
	
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String value) {
		this.type = value;
	}
	
	public java.lang.String getZone() {
		return this.zone;
	}
	
	public void setZone(java.lang.String value) {
		this.zone = value;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}
	
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	
	public java.lang.String getMemo() {
		return this.memo;
	}
	
	public void setMemo(java.lang.String value) {
		this.memo = value;
	}
	
	public java.lang.String getPic1() {
		return this.pic1;
	}
	
	public void setPic1(java.lang.String value) {
		this.pic1 = value;
	}
	
	public java.lang.String getPic2() {
		return this.pic2;
	}
	
	public void setPic2(java.lang.String value) {
		this.pic2 = value;
	}
	
	public java.lang.String getPic3() {
		return this.pic3;
	}
	
	public void setPic3(java.lang.String value) {
		this.pic3 = value;
	}
	
	public java.lang.String getFlag() {
		return this.flag;
	}
	
	public void setFlag(java.lang.String value) {
		this.flag = value;
	}
	
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	
	public java.lang.String getCreator() {
		return this.creator;
	}
	
	public void setCreator(java.lang.String value) {
		this.creator = value;
	}
	
	public java.util.Date  getCreatetime() {
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
	
	public java.util.Date  getUpdatetime() {
		return this.updatetime;
	}
	
	public void setUpdatetime(java.util.Date value) {
		this.updatetime = value;
	}

	public java.lang.String getCreatetimeString() {
		return createtimeString;
	}

	public void setCreatetimeString(java.lang.String createtimeString) {
		this.createtimeString = createtimeString;
	}

	public java.lang.String getUpdatetimeString() {
		return updatetimeString;
	}

	public void setUpdatetimeString(java.lang.String updatetimeString) {
		this.updatetimeString = updatetimeString;
	}

	public java.lang.String getRole() {
		return role;
	}

	public void setRole(java.lang.String role) {
		this.role = role;
	}

	public java.lang.String getFruit() {
		return fruit;
	}

	public void setFruit(java.lang.String fruit) {
		this.fruit = fruit;
	}

	public java.lang.String getIcon() {
		return icon;
	}

	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "名称"+this.getName()+",版本"+this.getVersion();
	}


	public String getInfo() {
		String info = "";
		if(!StringUtils.isNullOrEmpty(this.getName())) {
			if(this.getName().equals("应用商城")) {
				info = "应用商城，发现更多精彩应用。";
			}
			if(this.getName().equals("流程配置管理工具")) {
				info = "流程配置管理工具，管理和配置应用的流程。";
			}
			if(this.getName().equals("日志查询工具")) {
				info = "日志查询工具，查询应用的日志。";
			}
			if(this.getName().equals("收押平台")) {
				info = "收押平台，主要处理人员的 出入所。";
			}
			if(this.getName().equals("管教系统")) {
				info = "管教系统，适用于管教岗位。";
			}
			if(this.getName().equals("巡控管理")) {
				info = "巡控管理，记录和处理监所日常巡视和监控。";
			}
			if(this.getName().equals("医务管理")) {
				info = "医务管理，处理监所内人员医务相关工作。";
			}
			if(this.getName().equals("财务管理")) {
				info = "财务管理，管理监所人员的财务。";
			}
			if(this.getName().equals("领导办公")) {
				info = "领导办公，所领导用于管理监所。";
			}
			if(this.getName().equals("综合岗位")) {
				info = "综合岗位，用于管理所内事务。";
			}
			if(this.getName().equals("查询统计")) {
				info = "查询统计，查询和统计所内数据。";
			}
		}
		
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}
	
	
	
	
}

