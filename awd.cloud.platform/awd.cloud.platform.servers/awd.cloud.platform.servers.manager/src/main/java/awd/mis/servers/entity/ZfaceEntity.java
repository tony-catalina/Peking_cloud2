/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.entity;

import java.util.Map;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Max;

import awd.mis.servers.tools.CacheUtils;
import org.hibernate.validator.constraints.Length;
import awd.framework.common.entity.SimpleGenericEntity;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;
import awd.framework.common.service.web.group.CreateGroup;
import awd.framework.common.service.web.group.UpdateGroup;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class ZfaceEntity extends SimpleGenericEntity<String>  {

	//alias
	public static final String TABLE_ALIAS = "Zface";
	public static final String ALIAS_ID = "编号";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_CJFS = "采集方式（RLCJFS）";
	public static final String ALIAS_RLTX = "人脸图像";
	public static final String ALIAS_RLTZ = "人脸特征";
	public static final String ALIAS_RLTXURL = "人脸图像地址";
	public static final String ALIAS_RLTZURL = "人脸特征地址";
	public static final String ALIAS_STATE = "状态  R3删除  R2正常";
	public static final String ALIAS_SCBZ = "上传标志";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";

	//所有组
	@GroupSequence({CreateGroup.class, UpdateGroup.class})
	public interface All {
	}

	//columns START
		/*@NotNull(message="编号不能为空",groups=CreateGroup.class)
	    @Length(max=23,message="编号最大长度23位" ,groups=CreateGroup.class)
		private java.lang.String id;*/
	
		@NotNull(message="监所编号不能为空",groups=CreateGroup.class)
		@Length(max=9,message="监所编号最大长度9位" ,groups=CreateGroup.class)
		private java.lang.String jsbh;
	
		@NotNull(message="人员编号不能为空",groups=CreateGroup.class)
		@Length(max=21,message="人员编号最大长度21位" ,groups=CreateGroup.class)
		private java.lang.String rybh;
	
		@Length(max=1,message="采集方式（RLCJFS）最大长度1位" ,groups=CreateGroup.class)
		private java.lang.String cjfs;
	
//		@Length(max=2147483647,message="人脸图像最大长度2147483647位" ,groups=CreateGroup.class)
		private byte[] rltx;
	
//		@Length(max=2147483647,message="人脸特征最大长度2147483647位" ,groups=CreateGroup.class)
		private byte[] rltz;
	
		@Length(max=255,message="人脸图像地址最大长度255位" ,groups=CreateGroup.class)
		private java.lang.String rltxurl;
	
		@Length(max=255,message="人脸特征地址最大长度255位" ,groups=CreateGroup.class)
		private java.lang.String rltzurl;
	
		@Length(max=2,message="状态  R3删除  R2正常最大长度2位" ,groups=CreateGroup.class)
		private java.lang.String state;
	
		@Length(max=1,message="上传标志最大长度1位" ,groups=CreateGroup.class)
		private java.lang.String scbz;
	
		@NotNull(message="创建人不能为空",groups=CreateGroup.class)
		@Length(max=50,message="创建人最大长度50位" ,groups=CreateGroup.class)
		private java.lang.String creator;
	
		@JSONField(format = "yyyy-MM-dd HH:mm:ss")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
		@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private java.util.Date createtime;
			
		@Length(max=50,message="更新人最大长度50位" ,groups=CreateGroup.class)
		private java.lang.String updator;
	
		@JSONField(format = "yyyy-MM-dd HH:mm:ss")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
		@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private java.util.Date updatetime;
			
	//columns END


	public ZfaceEntity(){
	}

	/*public ZfaceEntity(
		java.lang.String id
	){
		this.id = id;
	}*/



	/*public void setId(java.lang.String value) {
		this.id = value;
	}

	public java.lang.String getId() {
		return this.id;
	}*/


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

	public java.lang.String getCjfs() {
		return this.cjfs;
	}

	public void setCjfs(java.lang.String value) {
		this.cjfs = value;
	}

	public byte[] getRltx() {
		return this.rltx;
	}

	public void setRltx(byte[] value) {
		this.rltx = value;
	}

	public byte[] getRltz() {
		return this.rltz;
	}

	public void setRltz(byte[] value) {
		this.rltz = value;
	}

	public java.lang.String getRltxurl() {
		return this.rltxurl;
	}

	public void setRltxurl(java.lang.String value) {
		this.rltxurl = value;
	}

	public java.lang.String getRltzurl() {
		return this.rltzurl;
	}

	public void setRltzurl(java.lang.String value) {
		this.rltzurl = value;
	}

	public java.lang.String getState() {
		return this.state;
	}

	public void setState(java.lang.String value) {
		this.state = value;
	}

	public java.lang.String getScbz() {
		return this.scbz;
	}

	public void setScbz(java.lang.String value) {
		this.scbz = value;
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
	//开始生成字典的getString方法
		public String getJsbhString() {
			return CacheUtils.get().getJsbhString(this.jsbh);
		}
	//字典的get方法生成结束
}

