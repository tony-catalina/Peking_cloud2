/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.model.jls;

import awd.framework.common.model.Model;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class TsdjModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Tsdj";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "监室编号";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_DW = "提审单位(BADW)";
	public static final String ALIAS_RY = "提审人员";
	public static final String ALIAS_ZJLX = "证件类型(TSZJLX)";
	public static final String ALIAS_ZJH = "证件号";
	public static final String ALIAS_KSSJ = "开始时间";
	public static final String ALIAS_TSZBR = "提审值班人";
	public static final String ALIAS_JSSJ = "结束时间";
	public static final String ALIAS_SCBZ = "上传标志";
	public static final String ALIAS_JSR = "收监民警";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_DCMJ = "带出民警";
	public static final String ALIAS_YWSHWJWPQK = "有无伤痕及违禁物品情况";
	public static final String ALIAS_YCCON = "抄身检查情况说明";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_OPERATOR = "创建人";
	public static final String ALIAS_STATE = "删除状态(STATE)";
	public static final String ALIAS_FLAG = "完成状态(FLAG)";
	public static final String ALIAS_PSBZ = "批示标识(PSBZ)";
	public static final String ALIAS_PASTABLE = "是否有效(SHFO)";
	public static final String ALIAS_BLLX = "办理类型(TSBLLX)";
	public static final String ALIAS_SJZLJSBZ = "及时标志";
	public static final String ALIAS_SAVETIME = "保存时间";
	public static final String ALIAS_TSS = "提审室";
	public static final String ALIAS_YWLCSYID = "业务流程使用ID";
	public static final String ALIAS_BAJGLXFS = "办案机关联系方式";
	public static final String ALIAS_BAJG = "办案机关";
	public static final String ALIAS_BARXM1 = "办案人姓名1";
	public static final String ALIAS_LXFS1 = "联系方式1";
	public static final String ALIAS_GZZJHM1 = "工作证件号码1";
	public static final String ALIAS_BARXM2 = "办案人姓名2";
	public static final String ALIAS_LXFS2 = "联系方式2";
	public static final String ALIAS_GZZJHM2 = "工作证件号码2";
	public static final String ALIAS_AY = "案由";
	public static final String ALIAS_JSXBH = "介绍信编号";
	public static final String ALIAS_DJR = "登记人";
	public static final String ALIAS_DJSJ = "登记时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_CREATOR = "创建人";
	
	//columns START
	
	private String id;


	private String jsbh;

	private String rybh;

	private String dw;

	private String ry;

	private String zjlx;

	private String zjh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date kssj;

	private String tszbr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jssj;

	private String scbz;

	private String jsr;

	private String bz;

	private String dcmj;

	private String ywshwjwpqk;

	private String yccon;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;

	private String operator;

	private String state;

	private String flag;

	private String psbz;

	private String pastable;

	private String bllx;

	private String sjzljsbz;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date savetime;

	private String tss;

	private String ywlcsyid;

	private String bajglxfs;

	private String bajg;

	private String barxm1;

	private String lxfs1;

	private String gzzjhm1;

	private String barxm2;

	private String lxfs2;

	private String gzzjhm2;

	private String ay;

	private String jsxbh;

	private String djr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date djsj;

	private String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;

	private String creator;
	//columns END



	public TsdjModel(){
	}
	public TsdjModel(String id) {
		this.id = id;
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
	public String getDw() {
		return this.dw;
	}

	public void setDw(String value) {
		this.dw = value;
	}
	public String getRy() {
		return this.ry;
	}

	public void setRy(String value) {
		this.ry = value;
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

	public java.util.Date getKssj() {
		return this.kssj;
	}

	public void setKssj(java.util.Date value) {
		this.kssj = value;
	}

	public String getTszbr() {
		return this.tszbr;
	}

	public void setTszbr(String value) {
		this.tszbr = value;
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
	public String getJsr() {
		return this.jsr;
	}

	public void setJsr(String value) {
		this.jsr = value;
	}
	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}
	public String getDcmj() {
		return this.dcmj;
	}

	public void setDcmj(String value) {
		this.dcmj = value;
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
	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
	}
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String value) {
		this.flag = value;
	}
	public String getPsbz() {
		return this.psbz;
	}

	public void setPsbz(String value) {
		this.psbz = value;
	}
	public String getPastable() {
		return this.pastable;
	}

	public void setPastable(String value) {
		this.pastable = value;
	}
	public String getBllx() {
		return this.bllx;
	}

	public void setBllx(String value) {
		this.bllx = value;
	}
	public String getSjzljsbz() {
		return this.sjzljsbz;
	}

	public void setSjzljsbz(String value) {
		this.sjzljsbz = value;
	}

	public java.util.Date getSavetime() {
		return this.savetime;
	}

	public void setSavetime(java.util.Date value) {
		this.savetime = value;
	}

	public String getTss() {
		return this.tss;
	}

	public void setTss(String value) {
		this.tss = value;
	}
	public String getYwlcsyid() {
		return this.ywlcsyid;
	}

	public void setYwlcsyid(String value) {
		this.ywlcsyid = value;
	}
	public String getBajglxfs() {
		return this.bajglxfs;
	}

	public void setBajglxfs(String value) {
		this.bajglxfs = value;
	}
	public String getBajg() {
		return this.bajg;
	}

	public void setBajg(String value) {
		this.bajg = value;
	}
	public String getBarxm1() {
		return this.barxm1;
	}

	public void setBarxm1(String value) {
		this.barxm1 = value;
	}
	public String getLxfs1() {
		return this.lxfs1;
	}

	public void setLxfs1(String value) {
		this.lxfs1 = value;
	}
	public String getGzzjhm1() {
		return this.gzzjhm1;
	}

	public void setGzzjhm1(String value) {
		this.gzzjhm1 = value;
	}
	public String getBarxm2() {
		return this.barxm2;
	}

	public void setBarxm2(String value) {
		this.barxm2 = value;
	}
	public String getLxfs2() {
		return this.lxfs2;
	}

	public void setLxfs2(String value) {
		this.lxfs2 = value;
	}
	public String getGzzjhm2() {
		return this.gzzjhm2;
	}

	public void setGzzjhm2(String value) {
		this.gzzjhm2 = value;
	}
	public String getAy() {
		return this.ay;
	}

	public void setAy(String value) {
		this.ay = value;
	}
	public String getJsxbh() {
		return this.jsxbh;
	}

	public void setJsxbh(String value) {
		this.jsxbh = value;
	}
	public String getDjr() {
		return this.djr;
	}

	public void setDjr(String value) {
		this.djr = value;
	}

	public java.util.Date getDjsj() {
		return this.djsj;
	}

	public void setDjsj(java.util.Date value) {
		this.djsj = value;
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
	 
}

