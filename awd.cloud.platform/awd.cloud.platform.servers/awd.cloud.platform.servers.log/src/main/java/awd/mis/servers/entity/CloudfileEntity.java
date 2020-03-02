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
public class CloudfileEntity extends SimpleGenericEntity<String> {
	RedisUtils redisUtils =(RedisUtils) ApplicationContextHolder.get().getBean("redisUtils");
	//alias
	public static final String TABLE_ALIAS = "云文件";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_FDIR = "父目录";
	public static final String ALIAS_DIR = "文件目录";
	public static final String ALIAS_FILENAME = "文件名";
	public static final String ALIAS_FILETYPE = "文件类型";
	public static final String ALIAS_FILEICON = "文件图标";
	public static final String ALIAS_SCBZ = "是否可删除";
	public static final String ALIAS_SHARE = "是否共享";
	public static final String ALIAS_ISDIR = "是否是目录";
	public static final String ALIAS_UPLOADER = "创建人";
	public static final String ALIAS_UPTIME = "创建时间";
	public static final String ALIAS_DOWNNUM = "下载次数";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	
	//date formats
	public static final String FORMAT_UPTIME = DATE_FORMAT;
	public static final String FORMAT_TIMESTAMP = TIMESTAMP_FORMAT;
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
	
	
	@NotNull(message="人员编号不能为空",groups=SaveGroup.class)
    @Length(max=21,message="人员编号最大长度21位" ,groups=SaveGroup.class)
	private java.lang.String rybh;
	
	
	@NotNull(message="父目录不能为空",groups=SaveGroup.class)
    @Length(max=50,message="父目录最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String fdir;
	
	
	@NotNull(message="文件目录不能为空",groups=SaveGroup.class)
    @Length(max=50,message="文件目录最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String dir;
	
	
	@NotNull(message="文件名不能为空",groups=SaveGroup.class)
    @Length(max=50,message="文件名最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String filename;
	
	
	@NotNull(message="文件类型不能为空",groups=SaveGroup.class)
    @Length(max=10,message="文件类型最大长度10位" ,groups=SaveGroup.class)
	private java.lang.String filetype;
	
	
    @Length(max=20,message="文件图标最大长度20位" ,groups=SaveGroup.class)
	private java.lang.String fileicon;
	
	
    @Length(max=1,message="是否可删除最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String scbz;
	
	
    @Length(max=1,message="是否共享最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String share;
	
	
    @Length(max=1,message="是否是目录最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String isdir;
	
	
    @Length(max=50,message="创建人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String uploader;
	
	//创建时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date uptime;
	
    @Length(max=8,message="下载次数最大长度8位" ,groups=SaveGroup.class)
	private java.lang.Integer downnum;
	
	
    @Length(max=500,message="备注最大长度500位" ,groups=SaveGroup.class)
	private java.lang.String bz;
	
	
	@NotNull(message="创建人不能为空",groups=SaveGroup.class)
    @Length(max=50,message="创建人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String creator;
	
	
	@NotNull(message="创建时间不能为空",groups=SaveGroup.class)
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
    
    
    @Length(max=500,message="更新人最大长度500位" ,groups=SaveGroup.class)
	private java.lang.String groupname;
    
    @Length(max=1000,message="更新人最大长度1000位" ,groups=SaveGroup.class)
	private java.lang.String remotefilename;
	
	//columns END


	public CloudfileEntity(){
	}

	public CloudfileEntity(
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
	public java.lang.String getRybh() {
		return this.rybh;
	}
	
	public void setRybh(java.lang.String value) {
		this.rybh = value;
	}
	public java.lang.String getFdir() {
		return this.fdir;
	}
	
	public void setFdir(java.lang.String value) {
		this.fdir = value;
	}
	public java.lang.String getDir() {
		return this.dir;
	}
	
	public void setDir(java.lang.String value) {
		this.dir = value;
	}
	public java.lang.String getFilename() {
		return this.filename;
	}
	
	public void setFilename(java.lang.String value) {
		this.filename = value;
	}
	public java.lang.String getFiletype() {
		return this.filetype;
	}
	
	public void setFiletype(java.lang.String value) {
		this.filetype = value;
	}
	public java.lang.String getFileicon() {
		return this.fileicon;
	}
	
	public void setFileicon(java.lang.String value) {
		this.fileicon = value;
	}
	public java.lang.String getScbz() {
		return this.scbz;
	}
	
	public void setScbz(java.lang.String value) {
		this.scbz = value;
	}
	public java.lang.String getShare() {
		return this.share;
	}
	
	public void setShare(java.lang.String value) {
		this.share = value;
	}
	public java.lang.String getIsdir() {
		return this.isdir;
	}
	
	public void setIsdir(java.lang.String value) {
		this.isdir = value;
	}
	public java.lang.String getUploader() {
		return this.uploader;
	}
	
	public void setUploader(java.lang.String value) {
		this.uploader = value;
	}
			
	public String getUptimeString() {
		return date2String(getUptime(), FORMAT_UPTIME);
	}
	
	public void setUptimeString(String value) {
		setUptime(string2Date(value, FORMAT_UPTIME,java.util.Date.class));
	}
	
	public java.util.Date getUptime() {
		return this.uptime;
	}
	
	public void setUptime(java.util.Date value) {
		this.uptime = value;
	}
	
	public java.lang.Integer getDownnum() {
		return this.downnum;
	}
	
	public void setDownnum(java.lang.Integer value) {
		this.downnum = value;
	}
	public java.lang.String getBz() {
		return this.bz;
	}
	
	public void setBz(java.lang.String value) {
		this.bz = value;
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
	

	public java.lang.String getGroupname() {
		return groupname;
	}

	public void setGroupname(java.lang.String groupname) {
		this.groupname = groupname;
	}

	public java.lang.String getRemotefilename() {
		return remotefilename;
	}

	public void setRemotefilename(java.lang.String remotefilename) {
		this.remotefilename = remotefilename;
	}
	
	
	
}

