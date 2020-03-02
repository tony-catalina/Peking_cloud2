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
public class LshjModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Lshj";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_LSXM = "律师姓名";
	public static final String ALIAS_ZJLX = "证件类型(ZJLX)";
	public static final String ALIAS_ZJH = "证件号";
	public static final String ALIAS_DW = "单位";
	public static final String ALIAS_RS = "人数";
	public static final String ALIAS_HJSY = "会见事由";
	public static final String ALIAS_HJSJ = "会见时间";
	public static final String ALIAS_JSSJ = "结束时间";
	public static final String ALIAS_SCBZ = "上传标志";
	public static final String ALIAS_PZR = "批准人";
	public static final String ALIAS_FZMJ = "带出民警";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_JSR = "收监民警";
	public static final String ALIAS_YWSHWJWPQK = "有无伤病或带入物品情况";
	public static final String ALIAS_YCCON = "异常检查情况说明";
	public static final String ALIAS_OPERATOR = "操作人";
	public static final String ALIAS_STATE = "删除状态";
	public static final String ALIAS_LRMJ = "录入民警";
	public static final String ALIAS_FLAG = "标志";
	public static final String ALIAS_PASTABLE = "是否有效(SHFO)";
	public static final String ALIAS_SJZL_JSBZ = "数据质量—及时标记";
	public static final String ALIAS_SAVETIME = "保存时间";
	public static final String ALIAS_YWLCSYID = "业务流程id";
	public static final String ALIAS_LXFS = "联系方式";
	public static final String ALIAS_LSZYZM = "律师执业证明是否到期(SHFO)";
	public static final String ALIAS_LSSWSBH = "律师事务所介绍信编号";
	public static final String ALIAS_DZ = "地址";
	public static final String ALIAS_WTSLX = "委托书类型";
	public static final String ALIAS_WTRXM = "委托人姓名";
	public static final String ALIAS_HJYXQ = "会见有效期";
	public static final String ALIAS_QTRY = "其他人员";
	public static final String ALIAS_ZBMJ = "值班民警";
	public static final String ALIAS_DJSJ = "登记时间";
	public static final String ALIAS_HJDD = "会见地点";
	public static final String ALIAS_APHJSJ = "安排会见时间";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	
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

	private String rybh;

	private String lsxm;

	private String zjlx;

	private String zjh;

	private String dw;

	private String rs;

	private String hjsy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hjsj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jssj;

	private String scbz;

	private String pzr;

	private String fzmj;

	private String bz;

	private String jsr;

	private String ywshwjwpqk;

	private String yccon;

	private String operator;

	private String state;

	private String lrmj;

	private String flag;

	private String pastable;

	private String sjzlJsbz;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date savetime;

	private String ywlcsyid;

	private String lxfs;

	private String lszyzm;

	private String lsswsbh;

	private String dz;

	private String wtslx;

	private String wtrxm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hjyxq;

	private String qtry;

	private String zbmj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date djsj;

	private String hjdd;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date aphjsj;

	private String creator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;

	private String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;

	private String stateString;
	private String zjlxString;
	private String lszyzmString;
	private String pastableString;
	//columns END



	public LshjModel(){
	}
	public LshjModel(String id) {
		this.id = id;
	}

	public String getStateString() {
		return stateString;
	}

	public String getZjlxString() {
		return zjlxString;
	}

	public String getLszyzmString() {
		return lszyzmString;
	}

	public String getPastableString() {
		return pastableString;
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
	public String getRybh() {
		return this.rybh;
	}

	public void setRybh(String value) {
		this.rybh = value;
	}
	public String getLsxm() {
		return this.lsxm;
	}

	public void setLsxm(String value) {
		this.lsxm = value;
	}
	public String getZjlx() {
		return this.zjlx;
	}

	public void setZjlx(String value) {
		this.zjlx = value;
	}
	public String getZjh() {
		return this.zjh;
	}

	public void setZjh(String value) {
		this.zjh = value;
	}
	public String getDw() {
		return this.dw;
	}

	public void setDw(String value) {
		this.dw = value;
	}
	public String getRs() {
		return this.rs;
	}

	public void setRs(String value) {
		this.rs = value;
	}
	public String getHjsy() {
		return this.hjsy;
	}

	public void setHjsy(String value) {
		this.hjsy = value;
	}

	public java.util.Date getHjsj() {
		return this.hjsj;
	}

	public void setHjsj(java.util.Date value) {
		this.hjsj = value;
	}


	public java.util.Date getJssj() {
		return this.jssj;
	}

	public void setJssj(java.util.Date value) {
		this.jssj = value;
	}

	public String getScbz() {
		return this.scbz;
	}

	public void setScbz(String value) {
		this.scbz = value;
	}
	public String getPzr() {
		return this.pzr;
	}

	public void setPzr(String value) {
		this.pzr = value;
	}
	public String getFzmj() {
		return this.fzmj;
	}

	public void setFzmj(String value) {
		this.fzmj = value;
	}
	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}
	public String getJsr() {
		return this.jsr;
	}

	public void setJsr(String value) {
		this.jsr = value;
	}
	public String getYwshwjwpqk() {
		return this.ywshwjwpqk;
	}

	public void setYwshwjwpqk(String value) {
		this.ywshwjwpqk = value;
	}
	public String getYccon() {
		return this.yccon;
	}

	public void setYccon(String value) {
		this.yccon = value;
	}
	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String value) {
		this.operator = value;
	}
	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
	}
	public String getLrmj() {
		return this.lrmj;
	}

	public void setLrmj(String value) {
		this.lrmj = value;
	}
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String value) {
		this.flag = value;
	}
	public String getPastable() {
		return this.pastable;
	}

	public void setPastable(String value) {
		this.pastable = value;
	}
	public String getSjzlJsbz() {
		return this.sjzlJsbz;
	}

	public void setSjzlJsbz(String value) {
		this.sjzlJsbz = value;
	}

	public java.util.Date getSavetime() {
		return this.savetime;
	}

	public void setSavetime(java.util.Date value) {
		this.savetime = value;
	}

	public String getYwlcsyid() {
		return this.ywlcsyid;
	}

	public void setYwlcsyid(String value) {
		this.ywlcsyid = value;
	}
	public String getLxfs() {
		return this.lxfs;
	}

	public void setLxfs(String value) {
		this.lxfs = value;
	}
	public String getLszyzm() {
		return this.lszyzm;
	}

	public void setLszyzm(String value) {
		this.lszyzm = value;
	}
	public String getLsswsbh() {
		return this.lsswsbh;
	}

	public void setLsswsbh(String value) {
		this.lsswsbh = value;
	}
	public String getDz() {
		return this.dz;
	}

	public void setDz(String value) {
		this.dz = value;
	}
	public String getWtslx() {
		return this.wtslx;
	}

	public void setWtslx(String value) {
		this.wtslx = value;
	}
	public String getWtrxm() {
		return this.wtrxm;
	}

	public void setWtrxm(String value) {
		this.wtrxm = value;
	}

	public java.util.Date getHjyxq() {
		return this.hjyxq;
	}

	public void setHjyxq(java.util.Date value) {
		this.hjyxq = value;
	}

	public String getQtry() {
		return this.qtry;
	}

	public void setQtry(String value) {
		this.qtry = value;
	}
	public String getZbmj() {
		return this.zbmj;
	}

	public void setZbmj(String value) {
		this.zbmj = value;
	}

	public java.util.Date getDjsj() {
		return this.djsj;
	}

	public void setDjsj(java.util.Date value) {
		this.djsj = value;
	}

	public String getHjdd() {
		return this.hjdd;
	}

	public void setHjdd(String value) {
		this.hjdd = value;
	}

	public java.util.Date getAphjsj() {
		return this.aphjsj;
	}

	public void setAphjsj(java.util.Date value) {
		this.aphjsj = value;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String value) {
		this.creator = value;
	}

	public java.util.Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
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

