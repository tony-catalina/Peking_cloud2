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
public class HfEntity extends SimpleGenericEntity<String> {
	
	//alias
	public static final String TABLE_ALIAS = "Hf";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_HFLBID = "回复列表ID ";
	public static final String ALIAS_HFR = "回复人";
	public static final String ALIAS_BHFR = "被回复人";
	public static final String ALIAS_HFSJ = "恢复时间";
	public static final String ALIAS_HFNR = "回复内容";
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

    @Length(max=23,message="回复列表ID 最大长度23位" ,groups=SaveGroup.class)
	private java.lang.String hflbid;

	@NotNull(message="回复人不能为空",groups=SaveGroup.class)
    @Length(max=50,message="回复人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String hfr;

	@NotNull(message="被回复人不能为空",groups=SaveGroup.class)
    @Length(max=50,message="被回复人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String bhfr;

	
	@NotNull(message="恢复时间不能为空")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hfsj;

	@NotNull(message="回复内容不能为空",groups=SaveGroup.class)
    @Length(max=500,message="回复内容最大长度500位" ,groups=SaveGroup.class)
	private java.lang.String hfnr;

	@NotNull(message="数据状态不能为空",groups=SaveGroup.class)
    @Length(max=2,message="数据状态最大长度2位" ,groups=SaveGroup.class)
	private java.lang.String state;

	@NotNull(message="创建人不能为空",groups=SaveGroup.class)
    @Length(max=50,message="创建人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String creator;

	
	@NotNull(message="创建时间不能为空")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;

	//columns END


	public HfEntity(){
	}

	public HfEntity(
		java.lang.String id
	){
		super.setId(id);
	}
	

	public java.lang.String getHflbid() {
		return this.hflbid;
	}
	
	public void setHflbid(java.lang.String value) {
		this.hflbid = value;
	}

	public java.lang.String getHfr() {
		return this.hfr;
	}
	
	public void setHfr(java.lang.String value) {
		this.hfr = value;
	}

	public java.lang.String getBhfr() {
		return this.bhfr;
	}
	
	public void setBhfr(java.lang.String value) {
		this.bhfr = value;
	}
	@ApiModelProperty(hidden = true)
	public String getHfsjString() {
		return DateTimeUtils.format(getHfsj(), DateTimeUtils.YEAR_MONTH_DAY);
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

