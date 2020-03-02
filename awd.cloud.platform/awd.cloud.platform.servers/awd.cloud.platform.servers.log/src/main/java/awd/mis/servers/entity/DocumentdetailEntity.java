/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.entity;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.service.web.group.CreateGroup;
import awd.framework.common.service.web.group.UpdateGroup;
import awd.framework.common.utils.DateTimeUtils;
import awd.mis.servers.utils.CacheUtils;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class DocumentdetailEntity extends SimpleGenericEntity<String> {
	
	//alias
	public static final String TABLE_ALIAS = "Documentdetail";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_HFSJ = "回复时间";
	public static final String ALIAS_HFNR = "回复内容";
	public static final String ALIAS_HFR = "回复人";
	public static final String ALIAS_HFRJH = "回复人警号";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_FLAG = "是否已回复(SHFO)";
	
	//所有组
	@GroupSequence({CreateGroup.class, UpdateGroup.class})  
	public interface All {       
	} 

	//columns START

	@NotNull(message="监所编号不能为空",groups=CreateGroup.class)
    @Length(max=9,message="监所编号最大长度9位" ,groups=CreateGroup.class)
	private java.lang.String jsbh;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hfsj;

    @Length(max=65535,message="回复内容最大长度65535位" ,groups=CreateGroup.class)
	private java.lang.String hfnr;

	@NotNull(message="回复人不能为空",groups=CreateGroup.class)
    @Length(max=50,message="回复人最大长度50位" ,groups=CreateGroup.class)
	private java.lang.String hfr;

	@NotNull(message="回复人警号不能为空",groups=CreateGroup.class)
    @Length(max=50,message="回复人警号最大长度50位" ,groups=CreateGroup.class)
	private java.lang.String hfrjh;

	@NotNull(message="创建人不能为空",groups=CreateGroup.class)
    @Length(max=30,message="创建人最大长度30位" ,groups=CreateGroup.class)
	private java.lang.String creator;


	@NotNull(message="创建时间不能为空",groups=CreateGroup.class)
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;

	@Length(max=1,message="是否已回复(SHFO)最大长度1位" ,groups=CreateGroup.class)
	@NotNull(message = "是否已回复不能为空",groups = CreateGroup.class)
	private java.lang.String flag;
	@Length(max=50,message="UUID最大长度50位" ,groups=CreateGroup.class)
	@NotNull(message = "UUID不能为空",groups = CreateGroup.class)
	private java.lang.String uuid;

	@Length(max = 30, message = "更新人最大长度30位", groups = CreateGroup.class)
	private java.lang.String updator;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;

	//columns END


	public DocumentdetailEntity(){
	}

	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	public java.util.Date getHfsj() {
			return this.hfsj;
			}

	public void setHfsj(java.util.Date value) {
			this.hfsj = value;
			}

	public java.lang.String getHfnr() {
		return this.hfnr;
	}
	
	public void setHfnr(java.lang.String value) {
		this.hfnr = value;
	}

	public java.lang.String getHfr() {
		return this.hfr;
	}
	
	public void setHfr(java.lang.String value) {
		this.hfr = value;
	}

	public java.lang.String getHfrjh() {
		return this.hfrjh;
	}
	
	public void setHfrjh(java.lang.String value) {
		this.hfrjh = value;
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

	public java.lang.String getFlag() {
		return this.flag;
	}
	public java.lang.String getFlagString() {
		return CacheUtils.get().getDictionary("SHFO",this.flag);
	}

	public void setFlag(java.lang.String value) {
		this.flag = value;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

}

