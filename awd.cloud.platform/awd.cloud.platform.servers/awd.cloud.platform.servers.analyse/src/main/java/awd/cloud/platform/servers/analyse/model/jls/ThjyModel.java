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
public class ThjyModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Thjy";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_TBR = "填表人";
	public static final String ALIAS_TBRQ = "填表日期";
	public static final String ALIAS_THYY = "谈话原因(THYY)";
	public static final String ALIAS_KSSJ = "开始时间";
	public static final String ALIAS_JSSJ = "结束时间";
	public static final String ALIAS_THKSSJ = "谈话开始时间";
	public static final String ALIAS_THJSSJ = "谈话结束时间";
	public static final String ALIAS_ZRGJ = "责任管教";
	public static final String ALIAS_THR = "谈话人";
	public static final String ALIAS_LY = "录音";
	public static final String ALIAS_FZMJ = "负责民警";
	public static final String ALIAS_QXYCQK = "情绪异常情况";
	public static final String ALIAS_YWQXYC = "有无情绪异常 0 无 1 有";
	public static final String ALIAS_THNR = "谈话内容";
	public static final String ALIAS_JYNR = "教育内容";
	public static final String ALIAS_YWZSSB = "有无自述伤病";
	public static final String ALIAS_SCBZ = "上传标志";
	public static final String ALIAS_STATE = "删除状态(STATE)";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_OPERATOR = "操作人";
	public static final String ALIAS_SBQK = "主要症状";
	public static final String ALIAS_SFLJJS = "是否接受";
	public static final String ALIAS_BLJJSYY = "不接受原因";
	public static final String ALIAS_BLLBZ = "补录入标志";
	public static final String ALIAS_SJZL_JSBZ = "及时标记";
	public static final String ALIAS_SAVETIME = "创建时间";
	public static final String ALIAS_SAVEUSER = "保存人";
	public static final String ALIAS_QXZT = "情绪状态";
	public static final String ALIAS_LJJSCD = "理解接受程度";
	public static final String ALIAS_ZSSQ = "自述伤情";
	public static final String ALIAS_NOZHIWENYY = "nozhiwenyy";
	
	//columns START

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date starttime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date endtime;



	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date starttime1;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date endtime1;



	private String id;


	private String rybh;

	private String jsbh;

	private String tbr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date tbrq;

	private String thyy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date kssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date thkssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date thjssj;

	private String zrgj;

	private String thr;

	private String ly;

	private String fzmj;

	private String qxycqk;

	private String ywqxyc;

	private String thnr;

	private String jynr;

	private String ywzssb;

	private String scbz;

	private String state;

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

	private String bz;

	private String operator;

	private String sbqk;

	private String sfljjs;

	private String bljjsyy;

	private String bllbz;

	private String sjzlJsbz;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date savetime;

	private String saveuser;

	private String qxzt;

	private String ljjscd;

	private String zssq;

	private String nozhiwenyy;

	private String thyyString;
	private String stateString;
	//columns END



	public ThjyModel(){
	}
	public ThjyModel(String id) {
		this.id = id;
	}

	public String getThyyString() {
		return thyyString;
	}

	public String getStateString() {
		return stateString;
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
	public String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(String value) {
		this.jsbh = value;
	}
	public String getTbr() {
		return this.tbr;
	}

	public void setTbr(String value) {
		this.tbr = value;
	}

	public java.util.Date getTbrq() {
		return this.tbrq;
	}

	public void setTbrq(java.util.Date value) {
		this.tbrq = value;
	}

	public String getThyy() {
		return this.thyy;
	}

	public void setThyy(String value) {
		this.thyy = value;
	}

	public java.util.Date getKssj() {
		return this.kssj;
	}

	public void setKssj(java.util.Date value) {
		this.kssj = value;
	}


	public java.util.Date getJssj() {
		return this.jssj;
	}

	public void setJssj(java.util.Date value) {
		this.jssj = value;
	}


	public java.util.Date getThkssj() {
		return this.thkssj;
	}

	public void setThkssj(java.util.Date value) {
		this.thkssj = value;
	}


	public java.util.Date getThjssj() {
		return this.thjssj;
	}

	public void setThjssj(java.util.Date value) {
		this.thjssj = value;
	}

	public String getZrgj() {
		return this.zrgj;
	}

	public void setZrgj(String value) {
		this.zrgj = value;
	}
	public String getThr() {
		return this.thr;
	}

	public void setThr(String value) {
		this.thr = value;
	}
	public String getLy() {
		return this.ly;
	}

	public void setLy(String value) {
		this.ly = value;
	}
	public String getFzmj() {
		return this.fzmj;
	}

	public void setFzmj(String value) {
		this.fzmj = value;
	}
	public String getQxycqk() {
		return this.qxycqk;
	}

	public void setQxycqk(String value) {
		this.qxycqk = value;
	}
	public String getYwqxyc() {
		return this.ywqxyc;
	}

	public void setYwqxyc(String value) {
		this.ywqxyc = value;
	}
	public String getThnr() {
		return this.thnr;
	}

	public void setThnr(String value) {
		this.thnr = value;
	}
	public String getJynr() {
		return this.jynr;
	}

	public void setJynr(String value) {
		this.jynr = value;
	}
	public String getYwzssb() {
		return this.ywzssb;
	}

	public void setYwzssb(String value) {
		this.ywzssb = value;
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

	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}
	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String value) {
		this.operator = value;
	}
	public String getSbqk() {
		return this.sbqk;
	}

	public void setSbqk(String value) {
		this.sbqk = value;
	}
	public String getSfljjs() {
		return this.sfljjs;
	}

	public void setSfljjs(String value) {
		this.sfljjs = value;
	}
	public String getBljjsyy() {
		return this.bljjsyy;
	}

	public void setBljjsyy(String value) {
		this.bljjsyy = value;
	}
	public String getBllbz() {
		return this.bllbz;
	}

	public void setBllbz(String value) {
		this.bllbz = value;
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

	public String getSaveuser() {
		return this.saveuser;
	}

	public void setSaveuser(String value) {
		this.saveuser = value;
	}
	public String getQxzt() {
		return this.qxzt;
	}

	public void setQxzt(String value) {
		this.qxzt = value;
	}
	public String getLjjscd() {
		return this.ljjscd;
	}

	public void setLjjscd(String value) {
		this.ljjscd = value;
	}
	public String getZssq() {
		return this.zssq;
	}

	public void setZssq(String value) {
		this.zssq = value;
	}
	public String getNozhiwenyy() {
		return this.nozhiwenyy;
	}

	public void setNozhiwenyy(String value) {
		this.nozhiwenyy = value;
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

	public Date getStarttime1() {
		return starttime1;
	}

	public void setStarttime1(Date starttime1) {
		this.starttime1 = starttime1;
	}

	public Date getEndtime1() {
		return endtime1;
	}

	public void setEndtime1(Date endtime1) {
		this.endtime1 = endtime1;
	}
}

