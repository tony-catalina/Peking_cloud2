/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.desktop.model;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class WgzdModel implements Model {
	private java.lang.String id;
	private java.lang.String jslx;
	private java.lang.String jslxString;
	private java.lang.String classid;
    private java.lang.String classidString;
	private java.lang.String code;
	private java.lang.String content;
	private java.lang.String dj;
	private java.lang.String editable;
	private java.lang.String editableString;
	private java.math.BigDecimal kffz;
	private java.lang.String py;
	private java.lang.String state;
	private java.lang.String stateString;
	private java.lang.String creator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
	private java.lang.String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
	//columns END


	public void WgzdEntity(){
	}

	public void WgzdEntity(java.lang.String id
	){
		this.id = id;
	}

	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getJslx() {
		return this.jslx;
	}
	
	public void setJslx(java.lang.String value) {
		this.jslx = value;
	}
	
	public java.lang.String getClassid() {
		return this.classid;
	}
	
	public void setClassid(java.lang.String value) {
		this.classid = value;
	}
	
	public java.lang.String getCode() {
		return this.code;
	}
	
	public void setCode(java.lang.String value) {
		this.code = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getDj() {
		return this.dj;
	}
	
	public void setDj(java.lang.String value) {
		this.dj = value;
	}
	
	public java.math.BigDecimal getKffz() {
		return this.kffz;
	}
	
	public void setKffz(java.math.BigDecimal value) {
		this.kffz = value;
	}
	
	public java.lang.String getPy() {
		return this.py;
	}
	
	public void setPy(java.lang.String value) {
		this.py = value;
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

	public java.lang.String getJslxString() {
		return jslxString;
	}

	public void setJslxString(java.lang.String jslxString) {
		this.jslxString = jslxString;
	}

	public java.lang.String getStateString() {
		return stateString;
	}

	public void setStateString(java.lang.String stateString) {
		this.stateString = stateString;
	}

	public java.lang.String getClassidString() {
		return classidString;
	}

	public void setClassidString(java.lang.String classidString) {
		this.classidString = classidString;
	}

	public String getEditable() {
		return editable;
	}

	public void setEditable(String editable) {
		this.editable = editable;
	}

	public String getEditableString() {
		return editableString;
	}

	public void setEditableString(String editableString) {
		this.editableString = editableString;
	}

	@Override
	public String toString() {
		return "WgzdModel{" +
				"id='" + id + '\'' +
				", jslx='" + jslx + '\'' +
				", jslxString='" + jslxString + '\'' +
				", classid='" + classid + '\'' +
				", classidString='" + classidString + '\'' +
				", code='" + code + '\'' +
				", content='" + content + '\'' +
				", dj='" + dj + '\'' +
				", editable='" + editable + '\'' +
				", editableString='" + editableString + '\'' +
				", kffz=" + kffz +
				", py='" + py + '\'' +
				", state='" + state + '\'' +
				", stateString='" + stateString + '\'' +
				", creator='" + creator + '\'' +
				", createtime=" + createtime +
				", updator='" + updator + '\'' +
				", updatetime=" + updatetime +
				'}';
	}
}

