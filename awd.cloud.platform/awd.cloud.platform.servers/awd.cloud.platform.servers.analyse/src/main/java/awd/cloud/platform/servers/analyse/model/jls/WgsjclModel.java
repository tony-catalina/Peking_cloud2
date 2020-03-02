/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.model.jls;

import awd.framework.common.model.Model;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class WgsjclModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Wgsjcl";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_DXBH = "对象编号";
	public static final String ALIAS_CLR = "处理人";
	public static final String ALIAS_CLSJ = "处理时间";
	public static final String ALIAS_CLQK = "处理情况";
	public static final String ALIAS_TJSJ = "提交时间";
	public static final String ALIAS_TJR = "提交人";
	public static final String ALIAS_XSJLID = "巡视记录ID";
	public static final String ALIAS_WGQK = "违规情况";
	public static final String ALIAS_WGQKCON = "违规详细情况";
	public static final String ALIAS_WGSJ = "违规时间";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_SCBZ = "上传标志";
	public static final String ALIAS_STATE = "删除状态(YWSTATE)";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_OPERATOR = "操作人";
	public static final String ALIAS_CLZT = "处理状态(CLZT)";
	public static final String ALIAS_SAVETIME = "保存时间";
	public static final String ALIAS_SAVEUSER = "保存人";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_CREATOR = "创建人";
	
	//columns START

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date starttime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date endtime;


	private String id;


	private String jsbh;

	private String dxbh;

	private String clr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date clsj;

	private String clqk;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date tjsj;

	private String tjr;

	private String xsjlid;

	private String wgqk;

	private String wgqkcon;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date wgsj;

	private String bz;

	private String scbz;

	private String state;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;

	private String operator;

	private String clzt;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date savetime;

	private String saveuser;

	private String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;

	private String creator;
	//columns END
	private String stateString;
	private String clztString;


	public WgsjclModel(){
	}
	public WgsjclModel(String id) {
		this.id = id;
	}


	public String getStateString() {
		return stateString;
	}
	public String getClztString() {
		return clztString;
	}
	public void setId(String value) {
		this.id = value;
	}

	public String getId() {
		return this.id;
	}

	public String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(String value) {
		this.jsbh = value;
	}
	public String getDxbh() {
		return this.dxbh;
	}

	public void setDxbh(String value) {
		this.dxbh = value;
	}
	public String getClr() {
		return this.clr;
	}

	public void setClr(String value) {
		this.clr = value;
	}

	public java.util.Date getClsj() {
		return this.clsj;
	}

	public void setClsj(java.util.Date value) {
		this.clsj = value;
	}

	public String getClqk() {
		return this.clqk;
	}

	public void setClqk(String value) {
		this.clqk = value;
	}

	public java.util.Date getTjsj() {
		return this.tjsj;
	}

	public void setTjsj(java.util.Date value) {
		this.tjsj = value;
	}

	public String getTjr() {
		return this.tjr;
	}

	public void setTjr(String value) {
		this.tjr = value;
	}
	public String getXsjlid() {
		return this.xsjlid;
	}

	public void setXsjlid(String value) {
		this.xsjlid = value;
	}
	public String getWgqk() {
		return this.wgqk;
	}

	public void setWgqk(String value) {
		this.wgqk = value;
	}
	public String getWgqkcon() {
		return this.wgqkcon;
	}

	public void setWgqkcon(String value) {
		this.wgqkcon = value;
	}

	public java.util.Date getWgsj() {
		return this.wgsj;
	}

	public void setWgsj(java.util.Date value) {
		this.wgsj = value;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}
	public String getScbz() {
		return this.scbz;
	}

	public void setScbz(String value) {
		this.scbz = value;
	}
	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
	}

	public java.util.Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String value) {
		this.operator = value;
	}
	public String getClzt() {
		return this.clzt;
	}

	public void setClzt(String value) {
		this.clzt = value;
	}

	public java.util.Date getSavetime() {
		return this.savetime;
	}

	public void setSavetime(java.util.Date value) {
		this.savetime = value;
	}

	public String getSaveuser() {
		return this.saveuser;
	}

	public void setSaveuser(String value) {
		this.saveuser = value;
	}
	public String getUpdator() {
		return this.updator;
	}

	public void setUpdator(String value) {
		this.updator = value;
	}

	public java.util.Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(java.util.Date value) {
		this.updatetime = value;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String value) {
		this.creator = value;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
}

