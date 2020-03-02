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
import awd.framework.common.utils.DateTimeUtils;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlEntity extends SimpleGenericEntity<String> {
	
	//alias
	public static final String TABLE_ALIAS = "Pl";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_APPCODE = "应用编号";
	public static final String ALIAS_PLR = "评论人";
	public static final String ALIAS_PLSJ = "评论时间";
	public static final String ALIAS_CONTENT = "内容";
	public static final String ALIAS_DZ = "点赞数";
	public static final String ALIAS_CLZT = "处理状态";
	public static final String ALIAS_HFLBID = "回复列表ID";
	public static final String ALIAS_STATE = "数据状态";
	public static final String ALIAS_CREATOR = "创建人";
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

    @Length(max=65535,message="应用编号最大长度65535位" ,groups=SaveGroup.class)
	private java.lang.String appcode;

    @Length(max=200,message="评论人最大长度200位" ,groups=SaveGroup.class)
	private java.lang.String plr;

	
	@NotNull(message="评论时间不能为空")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date plsj;
	
	@Length(max=65535,message="内容最大长度65535位" ,groups=SaveGroup.class)
	private java.lang.String content;

    @Length(max=10,message="点赞数最大长度10位" ,groups=SaveGroup.class)
	private java.lang.Integer dz;

    @Length(max=1,message="处理状态最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String clzt;

    @Length(max=23,message="回复列表ID最大长度23位" ,groups=SaveGroup.class)
	private java.lang.String hflbid;

    @Length(max=2,message="数据状态最大长度2位" ,groups=SaveGroup.class)
	private java.lang.String state;

    @Length(max=50,message="创建人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String creator;

	
	@NotNull(message="创建时间不能为空")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;

	//columns END


	public PlEntity(){
	}

	public PlEntity(
		java.lang.String id
	){
		super.setId(id);
	}
	

	public java.lang.String getAppcode() {
		return this.appcode;
	}
	
	public void setAppcode(java.lang.String value) {
		this.appcode = value;
	}

	public java.lang.String getPlr() {
		return this.plr;
	}
	
	public void setPlr(java.lang.String value) {
		this.plr = value;
	}
	@ApiModelProperty(hidden = true)
	public String getPlsjString() {
		return DateTimeUtils.format(getPlsj(), DateTimeUtils.YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);
	}
	public java.util.Date getPlsj() {
			return this.plsj;
			}

	public void setPlsj(java.util.Date value) {
			this.plsj = value;
			}

	public java.lang.String getContent() {
		return content;
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}

	public java.lang.Integer getDz() {
		return this.dz;
	}
	
	public void setDz(java.lang.Integer value) {
		this.dz = value;
	}

	public java.lang.String getClzt() {
		return this.clzt;
	}
	
	public void setClzt(java.lang.String value) {
		this.clzt = value;
	}

	public java.lang.String getHflbid() {
		return this.hflbid;
	}
	
	public void setHflbid(java.lang.String value) {
		this.hflbid = value;
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
	
}

