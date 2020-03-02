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
public class JypzModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Jypz";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_XM = "姓名";
	public static final String ALIAS_ZDRQ = "诊断日期";
	public static final String ALIAS_YS = "医生";
	public static final String ALIAS_LY = "来源";
	public static final String ALIAS_ZS = "自述";
	public static final String ALIAS_ZD = "诊断";
	public static final String ALIAS_JZYY = "就诊医院";
	public static final String ALIAS_CLJG = "处理结果";
	public static final String ALIAS_SWJY = "所外就医 0  不是  1 是";
	public static final String ALIAS_SCBZ = "上传标志";
	public static final String ALIAS_PSBZ = "批示标识  0 未批示  1 批示成功  2 批示失败";
	public static final String ALIAS_OPERATOR = "操作人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_STATE = "删除状态";
	public static final String ALIAS_YYZDSJ = "医院诊断时间";
	public static final String ALIAS_YYZDJG = "医院诊断结果";
	public static final String ALIAS_YYZLCS = "医院治疗措施";
	public static final String ALIAS_CSJYLX = "出所就医类型  1 门诊  2 住院";
	public static final String ALIAS_PTMJ = "陪同民警";
	public static final String ALIAS_FY = "费用";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_PASTABLE = "过期标志";
	public static final String ALIAS_YWLCSYID = "业务流程ID";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_HSSJ = "回所时间";
	public static final String ALIAS_BHLX = "病号类型  0 不是  1 重点病号  2 普通病号";
	public static final String ALIAS_LDYJ = "领导意见";
	public static final String ALIAS_YPFF = "药品发放";
	public static final String ALIAS_FYKSRQ = "服药开始日期";
	public static final String ALIAS_FYJSRQ = "服药结束日期";
	public static final String ALIAS_SJZL_JSBZ = "及时标志";
	public static final String ALIAS_SAVETIME = "保存时间";
	public static final String ALIAS_CREATOR = "创建人";
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


	private String rybh;

	private String xm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date zdrq;

	private String ys;

	private String ly;

	private String zs;

	private String zd;

	private String jzyy;

	private String cljg;

	private String swjy;

	private String scbz;

	private String psbz;

	private String operator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;

	private String state;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date yyzdsj;

	private String yyzdjg;

	private String yyzlcs;

	private String csjylx;

	private String ptmj;

	private java.math.BigDecimal fy;

	private String bz;

	private String pastable;

	private String ywlcsyid;

	private String jsbh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hssj;

	private String bhlx;

	private String ldyj;

	private String ypff;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date fyksrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date fyjsrq;

	private String sjzlJsbz;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date savetime;

	private String creator;

	private String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
	//columns END

	//字典
	private String swjyString;
	private String psbzString;
	private String stateString;
	private String csjylxString;
	private String bhlxString;

	public String getSwjyString() {
		return swjyString;
	}

	public String getPsbzString() {
		return psbzString;
	}

	public String getStateString() {
		return stateString;
	}

	public String getCsjylxString() {
		return csjylxString;
	}

	public String getBhlxString() {
		return bhlxString;
	}

	public JypzModel(){
	}
	public JypzModel(String id) {
		this.id = id;
	}


	public void setId(String value) {
		this.id = value;
	}

	public String getId() {
		return this.id;
	}

	public String getRybh() {
		return this.rybh;
	}

	public void setRybh(String value) {
		this.rybh = value;
	}
	public String getXm() {
		return this.xm;
	}

	public void setXm(String value) {
		this.xm = value;
	}

	public java.util.Date getZdrq() {
		return this.zdrq;
	}

	public void setZdrq(java.util.Date value) {
		this.zdrq = value;
	}

	public String getYs() {
		return this.ys;
	}

	public void setYs(String value) {
		this.ys = value;
	}
	public String getLy() {
		return this.ly;
	}

	public void setLy(String value) {
		this.ly = value;
	}
	public String getZs() {
		return this.zs;
	}

	public void setZs(String value) {
		this.zs = value;
	}
	public String getZd() {
		return this.zd;
	}

	public void setZd(String value) {
		this.zd = value;
	}
	public String getJzyy() {
		return this.jzyy;
	}

	public void setJzyy(String value) {
		this.jzyy = value;
	}
	public String getCljg() {
		return this.cljg;
	}

	public void setCljg(String value) {
		this.cljg = value;
	}
	public String getSwjy() {
		return this.swjy;
	}

	public void setSwjy(String value) {
		this.swjy = value;
	}
	public String getScbz() {
		return this.scbz;
	}

	public void setScbz(String value) {
		this.scbz = value;
	}
	public String getPsbz() {
		return this.psbz;
	}

	public void setPsbz(String value) {
		this.psbz = value;
	}
	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String value) {
		this.operator = value;
	}

	public java.util.Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
	}

	public java.util.Date getYyzdsj() {
		return this.yyzdsj;
	}

	public void setYyzdsj(java.util.Date value) {
		this.yyzdsj = value;
	}

	public String getYyzdjg() {
		return this.yyzdjg;
	}

	public void setYyzdjg(String value) {
		this.yyzdjg = value;
	}
	public String getYyzlcs() {
		return this.yyzlcs;
	}

	public void setYyzlcs(String value) {
		this.yyzlcs = value;
	}
	public String getCsjylx() {
		return this.csjylx;
	}

	public void setCsjylx(String value) {
		this.csjylx = value;
	}
	public String getPtmj() {
		return this.ptmj;
	}

	public void setPtmj(String value) {
		this.ptmj = value;
	}
	public java.math.BigDecimal getFy() {
		return this.fy;
	}

	public void setFy(java.math.BigDecimal value) {
		this.fy = value;
	}
	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}
	public String getPastable() {
		return this.pastable;
	}

	public void setPastable(String value) {
		this.pastable = value;
	}
	public String getYwlcsyid() {
		return this.ywlcsyid;
	}

	public void setYwlcsyid(String value) {
		this.ywlcsyid = value;
	}
	public String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(String value) {
		this.jsbh = value;
	}

	public java.util.Date getHssj() {
		return this.hssj;
	}

	public void setHssj(java.util.Date value) {
		this.hssj = value;
	}

	public String getBhlx() {
		return this.bhlx;
	}

	public void setBhlx(String value) {
		this.bhlx = value;
	}
	public String getLdyj() {
		return this.ldyj;
	}

	public void setLdyj(String value) {
		this.ldyj = value;
	}
	public String getYpff() {
		return this.ypff;
	}

	public void setYpff(String value) {
		this.ypff = value;
	}

	public java.util.Date getFyksrq() {
		return this.fyksrq;
	}

	public void setFyksrq(java.util.Date value) {
		this.fyksrq = value;
	}


	public java.util.Date getFyjsrq() {
		return this.fyjsrq;
	}

	public void setFyjsrq(java.util.Date value) {
		this.fyjsrq = value;
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

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String value) {
		this.creator = value;
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

