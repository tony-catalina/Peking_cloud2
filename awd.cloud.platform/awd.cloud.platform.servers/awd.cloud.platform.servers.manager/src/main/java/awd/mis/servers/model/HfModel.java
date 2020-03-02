/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.model;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.model.Model;
import awd.framework.common.utils.DateTimeUtils;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class HfModel implements Model {
	
	
	private java.lang.String id;
	private java.lang.String hflbid;
	private java.lang.String hfr;
	private java.lang.String bhfr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hfsj;
	private java.lang.String hfnr;
	private java.lang.String state;
	private java.lang.String creator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;

	//columns END


	public HfModel(){
	}

	public HfModel(
		java.lang.String id
	){
		setId(id);
	}
	
	

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
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

