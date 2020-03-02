/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.entity;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.utils.DateTimeUtils;
import awd.mis.servers.tools.CacheUtils;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class AppEntity extends SimpleGenericEntity<String> {
	
	//alias
	public static final String TABLE_ALIAS = "App";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_APPCODE = "应用编号";
	public static final String ALIAS_NAME = "应用名称";
	public static final String ALIAS_VERSION = "版本号";
	public static final String ALIAS_TYPE = "应用类型";
	public static final String ALIAS_ZONE = "使用范围";
	public static final String ALIAS_URL = "链接地址获取下载地址";
	public static final String ALIAS_MEMO = "描述";
	public static final String ALIAS_PIC1 = "图片1URL";
	public static final String ALIAS_PIC2 = "图片2URL";
	public static final String ALIAS_PIC3 = "图片3URL";
	public static final String ALIAS_FLAG = "应用状态";
	public static final String ALIAS_STATE = "删除状态";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_ROLE = "角色";
	public static final String ALIAS_FRUIT = "星级";
	public static final String ALIAS_ICON = "图标";
	public static final String ALIAS_P1 = "图片1";
	public static final String ALIAS_P2 = "图片2";
	public static final String ALIAS_P3 = "图片3";
	public static final String ALIAS_sysversion = "系统版本";
	public static final String ALIAS_browversion = "浏览器版本";
	
	//保存组（不需要id验证）
	public static interface SaveGroup {};  
	//新增组（需要id验证）
	public static interface UpdateGroup {};
	//所有组
	@GroupSequence({SaveGroup.class, UpdateGroup.class})  
	public interface All {       
	} 

	//columns START

	@NotNull(message="应用编号不能为空",groups=SaveGroup.class)
    @Length(max=65535,message="应用编号最大长度65535位" ,groups=SaveGroup.class)
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

	@NotNull(message="使用范围不能为空",groups=SaveGroup.class)
    @Length(max=200,message="使用范围最大长度200位" ,groups=SaveGroup.class)
	private java.lang.String zone;

	@NotNull(message="链接地址获取下载地址不能为空",groups=SaveGroup.class)
    @Length(max=65535,message="链接地址获取下载地址最大长度65535位" ,groups=SaveGroup.class)
	private java.lang.String url;

    @Length(max=65535,message="描述最大长度65535位" ,groups=SaveGroup.class)
	private java.lang.String memo;

    @Length(max=200,message="图片1URL最大长度200位" ,groups=SaveGroup.class)
	private java.lang.String pic1;

    @Length(max=200,message="图片2URL最大长度200位" ,groups=SaveGroup.class)
	private java.lang.String pic2;

    @Length(max=200,message="图片3URL最大长度200位" ,groups=SaveGroup.class)
	private java.lang.String pic3;

	@NotNull(message="应用状态不能为空",groups=SaveGroup.class)
    @Length(max=2,message="应用状态(APPSTATE)最大长度2位" ,groups=SaveGroup.class)
	private java.lang.String flag;

	@NotNull(message="删除状态不能为空",groups=SaveGroup.class)
    @Length(max=2,message="删除状态最大长度2位" ,groups=SaveGroup.class)
	private java.lang.String state;

	@NotNull(message="创建人不能为空",groups=SaveGroup.class)
    @Length(max=50,message="创建人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String creator;

	
	@NotNull(message="创建时间不能为空")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;

    @Length(max=50,message="更新人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String updator;

	
	@NotNull(message="更新时间不能为空")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;

    @Length(max=50,message="角色最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String role;

    @Length(max=1,message="星级最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String fruit;

    @Length(max=50,message="图标最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String icon;

    @Length(max=65535,message="图片1最大长度65535位" ,groups=SaveGroup.class)
	private byte[] p1;

    @Length(max=65535,message="图片2最大长度65535位" ,groups=SaveGroup.class)
	private byte[] p2;

    @Length(max=65535,message="图片3最大长度65535位" ,groups=SaveGroup.class)
	private byte[] p3;
    
    private String exeurl;
    
   	private byte[] exefile;
   	
   	private String sysversion;
   	
   	private String browversion;
   	
   	
    private String info;
   	
	//columns END
   	
   	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	private int mount;

	public int getMount() {
		return mount;
	}

	public void setMount(int mount) {
		this.mount = mount;
	}

	public String getSysversion() {
		return sysversion;
	}

	public String getBrowversion() {
		return browversion;
	}

	public void setSysversion(String sysversion) {
		this.sysversion = sysversion;
	}

	public void setBrowversion(String browversion) {
		this.browversion = browversion;
	}

	public AppEntity(){
	}

	public AppEntity(
		java.lang.String id
	){
		super.setId(id);
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

	public java.lang.String getRole() {
		return this.role;
	}
	
	public void setRole(java.lang.String value) {
		this.role = value;
	}

	public java.lang.String getFruit() {
		return this.fruit;
	}
	
	public void setFruit(java.lang.String value) {
		this.fruit = value;
	}

	public java.lang.String getIcon() {
		return this.icon;
	}
	
	public void setIcon(java.lang.String value) {
		this.icon = value;
	}

	public byte[] getP1() {
		return this.p1;
	}
	
	public void setP1(byte[] value) {
		this.p1 = value;
	}

	public byte[] getP2() {
		return this.p2;
	}
	
	public void setP2(byte[] value) {
		this.p2 = value;
	}

	public byte[] getP3() {
		return this.p3;
	}
	
	public void setP3(byte[] value) {
		this.p3 = value;
	}
	
	@ApiModelProperty(hidden = true)
	public java.lang.String getTypeString() {
		 String str="";
		if(StringUtils.isNotBlank(this.getType()) && StringUtils.isNotEmpty(this.getType())) {
			str=CacheUtils.get().getDictionary("APPLX", this.getType());
		}
		return str;
	}

	@ApiModelProperty(hidden = true)
	public java.lang.String getZoneString() {
		StringBuffer sb = new StringBuffer();
		String str="";
		if(StringUtils.isNotBlank(this.getZone()) && StringUtils.isNotEmpty(this.getZone())) {
			String[] s = this.getZone().split(",");
			for(int i=0;i<s.length;i++) {
				String x = CacheUtils.get().getDictionary("ZONE", s[i]);
				sb.append(x+",");
			}
			if(sb.toString().endsWith(",")) {
				 str=sb.deleteCharAt(sb.length() - 1).toString();
			}
		}
		return str;
	}

	public String getRoleString() {
		if(!awd.framework.common.utils.StringUtils.isNullOrEmpty(this.getRole())) {
			return CacheUtils.get().getDictionary("KSSROLE", this.getRole());
		}else {
			return "";
		}
		
	}
	
	
}

