/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */


package awd.cloud.platform.webs.charts.modal.jls;

import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.bj.base.model.Model;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class JbxxModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Jbxx";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_TBR = "填表人";
	public static final String ALIAS_TBRQ = "填表日期";
	public static final String ALIAS_WBRYBH = "网办人员编号";
	public static final String ALIAS_GCBH = "过程编号";
	public static final String ALIAS_YWLCID = "业务流程ID";
	public static final String ALIAS_TASKID = "业务进程ID";
	public static final String ALIAS_SNBH = "所内编号";
	public static final String ALIAS_JSH = "拘室号";
	public static final String ALIAS_XM = "姓名";
	public static final String ALIAS_XMPY = "姓名拼音";
	public static final String ALIAS_XMPYSZM = "姓名拼音首字母";
	public static final String ALIAS_BM = "别名";
	public static final String ALIAS_BMTY = "别名同音";
	public static final String ALIAS_XB = "性别(XB)";
	public static final String ALIAS_RSRQ = "入所日期";
	public static final String ALIAS_ZJLX = "证件类型(ZJLX)";
	public static final String ALIAS_ZJH = "证件号";
	public static final String ALIAS_GJ = "国籍(GJ)";
	public static final String ALIAS_WHCD = "文化程度(WHCD)";
	public static final String ALIAS_SF = "身份(SF)";
	public static final String ALIAS_TSSF = "特殊身份(TSSF)";
	public static final String ALIAS_MZ = "民族(MZ)";
	public static final String ALIAS_ZZMM = "政治面貌(ZZMM)";
	public static final String ALIAS_CSRQ = "出生日期";
	public static final String ALIAS_HYZK = "婚姻状况(HYZK)";
	public static final String ALIAS_HJD = "户籍地(XZQH)";
	public static final String ALIAS_HJDXZ = "户籍地详址";
	public static final String ALIAS_JG = "籍贯(XZQH)";
	public static final String ALIAS_XZD = "现住地(XZQH)";
	public static final String ALIAS_XZDXZ = "现住地详址";
	public static final String ALIAS_GZDW = "工作单位";
	public static final String ALIAS_ZY = "职业(ZY)";
	public static final String ALIAS_JLJDJG = "拘留决定机关";
	public static final String ALIAS_BADW = "办案单位（送案单位）";
	public static final String ALIAS_BAR = "办案人";
	public static final String ALIAS_RSXZ = "入所性质(JLSRSYY)（收拘类别）";
	public static final String ALIAS_SYR = "收押人（收拘民警）";
	public static final String ALIAS_SYPZ = "收押凭证(SYPZ)";
	public static final String ALIAS_SYPZWSH = "收押凭证文书号（收拘法律文书号）";
	public static final String ALIAS_AY = "案由(JLSAJLB)";
	public static final String ALIAS_BARJH = "办案人警号";
	public static final String ALIAS_BARDH = "办案民警电话";
	public static final String ALIAS_CZDH = "办案传真电话";
	public static final String ALIAS_GYTS = "关押天数（日）";
	public static final String ALIAS_JLRQ = "拘留日期（拘留开始日期）";
	public static final String ALIAS_GYQX = "关押期限（拘留结束日期）";
	public static final String ALIAS_SDNJCCJG = "涉毒尿检初查结果";
	public static final String ALIAS_SDNJDW = "涉毒尿检单位";
	public static final String ALIAS_SDNJCJSJ = "涉毒尿检初检时间";
	public static final String ALIAS_SDNJJCR = "涉毒尿检检查人";
	public static final String ALIAS_JYAQ = "简要案情";
	public static final String ALIAS_DAH = "档案号";
	public static final String ALIAS_HZFLWSH = "回执文书号";
	public static final String ALIAS_PZRYBH = "派综人员编号";
	public static final String ALIAS_PZAJBH = "派综案件编号";
	public static final String ALIAS_PZFLWSH = "派综法律文书号";
	public static final String ALIAS_CZZT = "操作状态(CZZT)";
	public static final String ALIAS_JSLY = "拒收理由（健康检查结果）";
	public static final String ALIAS_BYJSWSH = "不宜拘留文书号";
	public static final String ALIAS_JSRQ = "拒收日期";
	public static final String ALIAS_ZXF = "重刑犯(ZXF)";
	public static final String ALIAS_AQDJ = "安全等级(AQDJ)";
	public static final String ALIAS_RYGLLB = "人员管理类别(RYGLLB)";
	public static final String ALIAS_LSCSYY = "临时出所原因(LSCS)";
	public static final String ALIAS_LSCSSJ = "上一次临时出所时间";
	public static final String ALIAS_LSCSHSSJ = "上一次临时出所回所时间";
	public static final String ALIAS_CRJBJ = "出入监标记(CJBJ)";
	public static final String ALIAS_RKBHGBJ = "入库合格不合格标记(SHFO)";
	public static final String ALIAS_RKBHGYY = "不合格入库原因";
	public static final String ALIAS_LRSFJS = "是否及时录入标记(SHFO)";
	public static final String ALIAS_CSSJ = "出所时间";
	public static final String ALIAS_CSYY = "出所原因(JLSCSYY)";
	public static final String ALIAS_CSQX = "出所去向(CSQX)";
	public static final String ALIAS_JJ = "戒具标志位(JJ)";
	public static final String ALIAS_JB = "禁闭标志位(JB)";
	public static final String ALIAS_ZDZY = "重点在押(ZDRY)";
	public static final String ALIAS_YGRY = "严管人员标志位(YGRY)";
	public static final String ALIAS_YFH = "衣服号";
	public static final String ALIAS_CWH = "床位号";
	public static final String ALIAS_FWH = "附物号";
	public static final String ALIAS_RSLDSP = "入所领导审批(PSBZ)";
	public static final String ALIAS_RSLDSPNR = "入所领导审批内容";
	public static final String ALIAS_RSLDQM = "入所领导签名";
	public static final String ALIAS_RSLDQMSJ = "入所领导签名时间";
	public static final String ALIAS_STATE = "人员状态(STATE)";
	public static final String ALIAS_SCBZ = "上传标志(SHFO)";
	public static final String ALIAS_DWLX = "单位类型(DWLX)";
	public static final String ALIAS_WFDD = "违法地点";
	public static final String ALIAS_SYDW = "送押单位";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_BHLX = "病号类型";
	public static final String ALIAS_CSD = "出生地";
	public static final String ALIAS_KY = "口音";
	public static final String ALIAS_LXDH = "联系电话";
	public static final String ALIAS_HJDSZPCS = "户籍地所在派出所";
	public static final String ALIAS_TMTBTZ = "体貌体表特征";
	public static final String ALIAS_DWDZ = "单位地址";
	public static final String ALIAS_BJLRJL = "被拘留人简历";
	public static final String ALIAS_BJLRJSCFQK = "被拘留人接受处罚情况";
	public static final String ALIAS_WFSJ = "违法时间";
	public static final String ALIAS_FXDJ = "风险等级";
	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String tbr;
	@JSONField(format = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date tbrq;
		
	
	private java.lang.String wbrybh;
	
	private java.lang.String gcbh;
	
	private java.lang.String ywlcid;
	
	private java.lang.String taskid;
	
	private java.lang.String snbh;
	
	private java.lang.String jsh;
	
	private java.lang.String xm;
	
	private java.lang.String xmpy;
	
	private java.lang.String xmpyszm;
	
	private java.lang.String bm;
	
	private java.lang.String bmty;
	
	private java.lang.String xb;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date rsrq;
		
	
	private java.lang.String zjlx;
	
	private java.lang.String zjh;
	
	private java.lang.String gj;
	
	private java.lang.String whcd;
	
	private java.lang.String sf;
	
	private java.lang.String tssf;
	
	private java.lang.String mz;
	
	private java.lang.String fxdj;
	
	private java.lang.String zzmm;
	@JSONField(format = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date csrq;
		
	
	private java.lang.String hyzk;
	
	private java.lang.String hjd;
	
	private java.lang.String hjdxz;
	
	private java.lang.String jg;
	
	private java.lang.String xzd;
	
	private java.lang.String xzdxz;
	
	private java.lang.String gzdw;
	
	private java.lang.String zy;
	
	private java.lang.String jljdjg;
	
	private java.lang.String badw;
	
	private java.lang.String bar;
	
	private java.lang.String rsxz;
	
	private java.lang.String syr;
	
	private java.lang.String sypz;
	
	private java.lang.String sypzwsh;
	
	private java.lang.String ay;
	
	private java.lang.String bhlx;
	
	private java.lang.String barjh;
	
	private java.lang.String bardh;
	
	private java.lang.String czdh;
	
	private java.lang.String gyts;
	@JSONField(format = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date jlrq;
		
	@JSONField(format = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date gyqx;
		
	
	private java.lang.String sdnjccjg;
	
	private java.lang.String sdnjdw;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date sdnjcjsj;
		
	
	private java.lang.String sdnjjcr;
	
	private java.lang.String jyaq;
	
	private java.lang.String dah;
	
	private java.lang.String hzflwsh;
	
	private java.lang.String pzrybh;
	
	private java.lang.String pzajbh;
	
	private java.lang.String pzflwsh;
	
	private java.lang.String czzt;
	
	private java.lang.String jsly;
	
	private java.lang.String byjswsh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jsrq;
		
	
	private java.lang.String zxf;
	
	private java.lang.String aqdj;
	
	private java.lang.String rygllb;
	
	private java.lang.String lscsyy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lscssj;
		
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lscshssj;
		
	
	private java.lang.String crjbj;
	
	private java.lang.String rkbhgbj;
	
	private java.lang.String rkbhgyy;
	
	private java.lang.String lrsfjs;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date cssj;
		
	
	private java.lang.String csyy;
	
	private java.lang.String csqx;
	
	private java.lang.String jj;
	
	private java.lang.String jb;
	
	private java.lang.String zdzy;
	
	private java.lang.String ygry;
	
	private java.lang.String yfh;
	
	private java.lang.String cwh;
	
	private java.lang.String fwh;
	
	private java.lang.String rsldsp;
	
	private java.lang.String rsldspnr;
	
	private java.lang.String rsldqm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date rsldqmsj;
		
	
	private java.lang.String state;
	
	private java.lang.String scbz;
	
	private java.lang.String dwlx;

	private java.lang.String sydw;

	private java.lang.String wfdd;
	
	private java.lang.String creator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
		
	
	private java.lang.String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date wfsj;
		
	private java.lang.String bjlrjscfqk;
	
	private java.lang.String bjlrjl;
	
	private java.lang.String dwdz;
	
	private java.lang.String tmtbtz;
	
	private java.lang.String hjdszpcs;
	
	private java.lang.String lxdh;
	
	private java.lang.String ky;
	
	private java.lang.String csd;
	//columns END
	//开始生成字典的String属性
	private java.lang.String jsbhString;
	
		
	private java.lang.String xbString;
		
	private java.lang.String zjlxString;
		
	private java.lang.String gjString;
		
	private java.lang.String whcdString;
		
	private java.lang.String sfString;
		
	private java.lang.String tssfString;
		
	private java.lang.String mzString;
		
	private java.lang.String zzmmString;
		
	private java.lang.String hyzkString;
		
	private java.lang.String hjdString;
		
	private java.lang.String jgString;
		
	private java.lang.String xzdString;
		
	private java.lang.String zyString;
		
	private java.lang.String rsxzString;
		
	private java.lang.String sypzString;
		
	private java.lang.String ayString;
		
	private java.lang.String czztString;
		
	private java.lang.String zxfString;
		
	private java.lang.String aqdjString;
		
	private java.lang.String rygllbString;
		
	private java.lang.String lscsyyString;
		
	private java.lang.String crjbjString;
		
	private java.lang.String rkbhgbjString;
		
	private java.lang.String lrsfjsString;
		
	private java.lang.String csyyString;
		
	private java.lang.String csqxString;
		
	private java.lang.String jjString;
		
	private java.lang.String jbString;
		
	private java.lang.String zdzyString;
		
	private java.lang.String ygryString;
		
	private java.lang.String rsldspString;
		
	private java.lang.String stateString;
		
	private java.lang.String scbzString;
	
	private java.lang.String dwlxString;
	private java.lang.String fxdjString;
	//字典的get方法生成结束

	private String ywlcname;
	
	private Map<String, String> ldpsxx;
	
	public String getYwlcname() {
		return ywlcname;
	}
	public void setYwlcname(String ywlcname) {
		this.ywlcname = ywlcname;
	}
	public Map<String, String> getLdpsxx() {
		return ldpsxx;
	}
	public void setLdpsxx(Map<String, String> ldpsxx) {
		this.ldpsxx = ldpsxx;
	}
	public JbxxModel(){
	}
	public JbxxModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	public java.lang.String getRybh() {
		return this.rybh;
	}
	
	public void setRybh(java.lang.String value) {
		this.rybh = value;
	}
	public java.lang.String getTbr() {
		return this.tbr;
	}
	
	public void setTbr(java.lang.String value) {
		this.tbr = value;
	}
	
	public java.util.Date getTbrq() {
		return this.tbrq;
	}
	
	public void setTbrq(java.util.Date value) {
		this.tbrq = value;
	}
	
	public java.lang.String getWbrybh() {
		return this.wbrybh;
	}
	
	public void setWbrybh(java.lang.String value) {
		this.wbrybh = value;
	}
	public java.lang.String getGcbh() {
		return this.gcbh;
	}
	
	public void setGcbh(java.lang.String value) {
		this.gcbh = value;
	}
	
	
	public java.lang.String getFxdj() {
		return fxdj;
	}
	public void setFxdj(java.lang.String fxdj) {
		this.fxdj = fxdj;
	}
	public java.lang.String getYwlcid() {
		return this.ywlcid;
	}
	
	public void setYwlcid(java.lang.String value) {
		this.ywlcid = value;
	}
	public java.lang.String getTaskid() {
		return this.taskid;
	}
	
	public void setTaskid(java.lang.String value) {
		this.taskid = value;
	}
	public java.lang.String getSnbh() {
		return this.snbh;
	}
	
	public void setSnbh(java.lang.String value) {
		this.snbh = value;
	}
	public java.lang.String getJsh() {
		return this.jsh;
	}
	
	public void setJsh(java.lang.String value) {
		this.jsh = value;
	}
	public java.lang.String getXm() {
		return this.xm;
	}
	
	public void setXm(java.lang.String value) {
		this.xm = value;
	}
	public java.lang.String getXmpy() {
		return this.xmpy;
	}
	
	public void setXmpy(java.lang.String value) {
		this.xmpy = value;
	}
	public java.lang.String getXmpyszm() {
		return this.xmpyszm;
	}
	
	public void setXmpyszm(java.lang.String value) {
		this.xmpyszm = value;
	}
	public java.lang.String getBm() {
		return this.bm;
	}
	
	public void setBm(java.lang.String value) {
		this.bm = value;
	}
	public java.lang.String getBmty() {
		return this.bmty;
	}
	
	public void setBmty(java.lang.String value) {
		this.bmty = value;
	}
	public java.lang.String getXb() {
		return this.xb;
	}
	
	public void setXb(java.lang.String value) {
		this.xb = value;
	}
	
	public java.util.Date getRsrq() {
		return this.rsrq;
	}
	
	public void setRsrq(java.util.Date value) {
		this.rsrq = value;
	}
	
	public java.lang.String getZjlx() {
		return this.zjlx;
	}
	
	public void setZjlx(java.lang.String value) {
		this.zjlx = value;
	}
	public java.lang.String getZjh() {
		return this.zjh;
	}
	
	public void setZjh(java.lang.String value) {
		this.zjh = value;
	}
	public java.lang.String getGj() {
		return this.gj;
	}
	
	public void setGj(java.lang.String value) {
		this.gj = value;
	}
	public java.lang.String getWhcd() {
		return this.whcd;
	}
	
	public void setWhcd(java.lang.String value) {
		this.whcd = value;
	}
	public java.lang.String getSf() {
		return this.sf;
	}
	
	public void setSf(java.lang.String value) {
		this.sf = value;
	}
	public java.lang.String getTssf() {
		return this.tssf;
	}
	
	public void setTssf(java.lang.String value) {
		this.tssf = value;
	}
	public java.lang.String getMz() {
		return this.mz;
	}
	
	public void setMz(java.lang.String value) {
		this.mz = value;
	}
	public java.lang.String getZzmm() {
		return this.zzmm;
	}
	
	public void setZzmm(java.lang.String value) {
		this.zzmm = value;
	}
	
	public java.util.Date getCsrq() {
		return this.csrq;
	}
	
	public void setCsrq(java.util.Date value) {
		this.csrq = value;
	}
	
	public java.lang.String getHyzk() {
		return this.hyzk;
	}
	
	public void setHyzk(java.lang.String value) {
		this.hyzk = value;
	}
	public java.lang.String getHjd() {
		return this.hjd;
	}
	
	public void setHjd(java.lang.String value) {
		this.hjd = value;
	}
	public java.lang.String getHjdxz() {
		return this.hjdxz;
	}
	
	public void setHjdxz(java.lang.String value) {
		this.hjdxz = value;
	}
	public java.lang.String getJg() {
		return this.jg;
	}
	
	public void setJg(java.lang.String value) {
		this.jg = value;
	}
	public java.lang.String getXzd() {
		return this.xzd;
	}
	
	public void setXzd(java.lang.String value) {
		this.xzd = value;
	}
	public java.lang.String getXzdxz() {
		return this.xzdxz;
	}
	
	public void setXzdxz(java.lang.String value) {
		this.xzdxz = value;
	}
	public java.lang.String getGzdw() {
		return this.gzdw;
	}
	
	public void setGzdw(java.lang.String value) {
		this.gzdw = value;
	}
	public java.lang.String getZy() {
		return this.zy;
	}
	
	public void setZy(java.lang.String value) {
		this.zy = value;
	}
	public java.lang.String getJljdjg() {
		return this.jljdjg;
	}
	
	public void setJljdjg(java.lang.String value) {
		this.jljdjg = value;
	}
	public java.lang.String getBadw() {
		return this.badw;
	}
	
	public void setBadw(java.lang.String value) {
		this.badw = value;
	}
	public java.lang.String getBar() {
		return this.bar;
	}
	
	public void setBar(java.lang.String value) {
		this.bar = value;
	}
	public java.lang.String getRsxz() {
		return this.rsxz;
	}
	
	public void setRsxz(java.lang.String value) {
		this.rsxz = value;
	}
	public java.lang.String getSyr() {
		return this.syr;
	}
	
	public void setSyr(java.lang.String value) {
		this.syr = value;
	}
	public java.lang.String getSypz() {
		return this.sypz;
	}
	
	public void setSypz(java.lang.String value) {
		this.sypz = value;
	}
	public java.lang.String getSypzwsh() {
		return this.sypzwsh;
	}
	
	public void setSypzwsh(java.lang.String value) {
		this.sypzwsh = value;
	}
	public java.lang.String getAy() {
		return this.ay;
	}
	
	public void setAy(java.lang.String value) {
		this.ay = value;
	}
	public java.lang.String getBarjh() {
		return this.barjh;
	}
	
	public void setBarjh(java.lang.String value) {
		this.barjh = value;
	}
	public java.lang.String getBardh() {
		return this.bardh;
	}
	
	public void setBardh(java.lang.String value) {
		this.bardh = value;
	}
	public java.lang.String getCzdh() {
		return this.czdh;
	}
	
	public void setCzdh(java.lang.String value) {
		this.czdh = value;
	}
	public java.lang.String getGyts() {
		return this.gyts;
	}
	
	public void setGyts(java.lang.String value) {
		this.gyts = value;
	}
	
	public java.util.Date getJlrq() {
		return this.jlrq;
	}
	
	public void setJlrq(java.util.Date value) {
		this.jlrq = value;
	}
	
	
	public java.util.Date getGyqx() {
		return this.gyqx;
	}
	
	public void setGyqx(java.util.Date value) {
		this.gyqx = value;
	}
	
	public java.lang.String getSdnjccjg() {
		return this.sdnjccjg;
	}
	
	public void setSdnjccjg(java.lang.String value) {
		this.sdnjccjg = value;
	}
	public java.lang.String getSdnjdw() {
		return this.sdnjdw;
	}
	
	public void setSdnjdw(java.lang.String value) {
		this.sdnjdw = value;
	}
	
	public java.util.Date getSdnjcjsj() {
		return this.sdnjcjsj;
	}
	
	public void setSdnjcjsj(java.util.Date value) {
		this.sdnjcjsj = value;
	}
	
	public java.lang.String getSdnjjcr() {
		return this.sdnjjcr;
	}
	
	public void setSdnjjcr(java.lang.String value) {
		this.sdnjjcr = value;
	}
	public java.lang.String getJyaq() {
		return this.jyaq;
	}
	
	public void setJyaq(java.lang.String value) {
		this.jyaq = value;
	}
	public java.lang.String getDah() {
		return this.dah;
	}
	
	public void setDah(java.lang.String value) {
		this.dah = value;
	}
	public java.lang.String getHzflwsh() {
		return this.hzflwsh;
	}
	
	public void setHzflwsh(java.lang.String value) {
		this.hzflwsh = value;
	}
	public java.lang.String getPzrybh() {
		return this.pzrybh;
	}
	
	public void setPzrybh(java.lang.String value) {
		this.pzrybh = value;
	}
	public java.lang.String getPzajbh() {
		return this.pzajbh;
	}
	
	public void setPzajbh(java.lang.String value) {
		this.pzajbh = value;
	}
	public java.lang.String getPzflwsh() {
		return this.pzflwsh;
	}
	
	public void setPzflwsh(java.lang.String value) {
		this.pzflwsh = value;
	}
	public java.lang.String getCzzt() {
		return this.czzt;
	}
	
	public void setCzzt(java.lang.String value) {
		this.czzt = value;
	}
	public java.lang.String getJsly() {
		return this.jsly;
	}
	
	public void setJsly(java.lang.String value) {
		this.jsly = value;
	}
	public java.lang.String getByjswsh() {
		return this.byjswsh;
	}
	
	public void setByjswsh(java.lang.String value) {
		this.byjswsh = value;
	}
	
	public java.util.Date getJsrq() {
		return this.jsrq;
	}
	
	public void setJsrq(java.util.Date value) {
		this.jsrq = value;
	}
	
	public java.lang.String getZxf() {
		return this.zxf;
	}
	
	public void setZxf(java.lang.String value) {
		this.zxf = value;
	}
	public java.lang.String getAqdj() {
		return this.aqdj;
	}
	
	public void setAqdj(java.lang.String value) {
		this.aqdj = value;
	}
	public java.lang.String getRygllb() {
		return this.rygllb;
	}
	
	public void setRygllb(java.lang.String value) {
		this.rygllb = value;
	}
	public java.lang.String getLscsyy() {
		return this.lscsyy;
	}
	
	public void setLscsyy(java.lang.String value) {
		this.lscsyy = value;
	}
	
	public java.util.Date getLscssj() {
		return this.lscssj;
	}
	
	public void setLscssj(java.util.Date value) {
		this.lscssj = value;
	}
	
	
	public java.util.Date getLscshssj() {
		return this.lscshssj;
	}
	
	public void setLscshssj(java.util.Date value) {
		this.lscshssj = value;
	}
	
	public java.lang.String getCrjbj() {
		return this.crjbj;
	}
	
	public void setCrjbj(java.lang.String value) {
		this.crjbj = value;
	}
	public java.lang.String getRkbhgbj() {
		return this.rkbhgbj;
	}
	
	public void setRkbhgbj(java.lang.String value) {
		this.rkbhgbj = value;
	}
	public java.lang.String getRkbhgyy() {
		return this.rkbhgyy;
	}
	
	public void setRkbhgyy(java.lang.String value) {
		this.rkbhgyy = value;
	}
	public java.lang.String getLrsfjs() {
		return this.lrsfjs;
	}
	
	public void setLrsfjs(java.lang.String value) {
		this.lrsfjs = value;
	}
	
	public java.util.Date getCssj() {
		return this.cssj;
	}
	
	public void setCssj(java.util.Date value) {
		this.cssj = value;
	}
	
	public java.lang.String getCsyy() {
		return this.csyy;
	}
	
	public void setCsyy(java.lang.String value) {
		this.csyy = value;
	}
	public java.lang.String getCsqx() {
		return this.csqx;
	}
	
	public void setCsqx(java.lang.String value) {
		this.csqx = value;
	}
	public java.lang.String getJj() {
		return this.jj;
	}
	
	public void setJj(java.lang.String value) {
		this.jj = value;
	}
	public java.lang.String getJb() {
		return this.jb;
	}
	
	public void setJb(java.lang.String value) {
		this.jb = value;
	}
	public java.lang.String getZdzy() {
		return this.zdzy;
	}
	
	public void setZdzy(java.lang.String value) {
		this.zdzy = value;
	}
	public java.lang.String getYgry() {
		return this.ygry;
	}
	
	public void setYgry(java.lang.String value) {
		this.ygry = value;
	}
	public java.lang.String getYfh() {
		return this.yfh;
	}
	
	public void setYfh(java.lang.String value) {
		this.yfh = value;
	}
	public java.lang.String getCwh() {
		return this.cwh;
	}
	
	public void setCwh(java.lang.String value) {
		this.cwh = value;
	}
	public java.lang.String getFwh() {
		return this.fwh;
	}
	
	public void setFwh(java.lang.String value) {
		this.fwh = value;
	}
	public java.lang.String getRsldsp() {
		return this.rsldsp;
	}
	
	public void setRsldsp(java.lang.String value) {
		this.rsldsp = value;
	}
	public java.lang.String getRsldspnr() {
		return this.rsldspnr;
	}
	
	public void setRsldspnr(java.lang.String value) {
		this.rsldspnr = value;
	}
	public java.lang.String getRsldqm() {
		return this.rsldqm;
	}
	
	public void setRsldqm(java.lang.String value) {
		this.rsldqm = value;
	}
	
	public java.util.Date getRsldqmsj() {
		return this.rsldqmsj;
	}
	
	public void setRsldqmsj(java.util.Date value) {
		this.rsldqmsj = value;
	}
	
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	public java.lang.String getScbz() {
		return this.scbz;
	}
	
	public void setScbz(java.lang.String value) {
		this.scbz = value;
	}
	
	public java.lang.String getDwlx() {
		return dwlx;
	}
	
	public void setDwlx(java.lang.String dwlx) {
		this.dwlx = dwlx;
	}

	public java.lang.String getBhlx() {
		return bhlx;
	}
	public void setBhlx(java.lang.String bhlx) {
		this.bhlx = bhlx;
	}
	public String getSydw() {
		return sydw;
	}

	public void setSydw(String sydw) {
		this.sydw = sydw;
	}

	public String getWfdd() {
		return wfdd;
	}

	public void setWfdd(String wfdd) {
		this.wfdd = wfdd;
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
	
	
	
	public java.util.Date getWfsj() {
		return wfsj;
	}
	public void setWfsj(java.util.Date wfsj) {
		this.wfsj = wfsj;
	}
	public java.lang.String getBjlrjscfqk() {
		return bjlrjscfqk;
	}
	public void setBjlrjscfqk(java.lang.String bjlrjscfqk) {
		this.bjlrjscfqk = bjlrjscfqk;
	}
	public java.lang.String getBjlrjl() {
		return bjlrjl;
	}
	public void setBjlrjl(java.lang.String bjlrjl) {
		this.bjlrjl = bjlrjl;
	}
	public java.lang.String getDwdz() {
		return dwdz;
	}
	public void setDwdz(java.lang.String dwdz) {
		this.dwdz = dwdz;
	}
	public java.lang.String getTmtbtz() {
		return tmtbtz;
	}
	public void setTmtbtz(java.lang.String tmtbtz) {
		this.tmtbtz = tmtbtz;
	}
	public java.lang.String getHjdszpcs() {
		return hjdszpcs;
	}
	public void setHjdszpcs(java.lang.String hjdszpcs) {
		this.hjdszpcs = hjdszpcs;
	}
	public java.lang.String getLxdh() {
		return lxdh;
	}
	public void setLxdh(java.lang.String lxdh) {
		this.lxdh = lxdh;
	}
	public java.lang.String getKy() {
		return ky;
	}
	public void setKy(java.lang.String ky) {
		this.ky = ky;
	}
	public java.lang.String getCsd() {
		return csd;
	}
	public void setCsd(java.lang.String csd) {
		this.csd = csd;
	}
	//开始生成字典的getString方法
	public String getJsbhString() {
		return this.jsbhString;
	}
		
	public void setJsbhString(java.lang.String jsbhString) {
		this.jsbhString = jsbhString;
	}
	public java.lang.String getXbString() {
		return this.xbString;
	}

	public void setXbString(java.lang.String value) {
		this.xbString = value;
	}
		
	public java.lang.String getZjlxString() {
		return this.zjlxString;
	}

	public void setZjlxString(java.lang.String value) {
		this.zjlxString = value;
	}
		
	public java.lang.String getGjString() {
		return this.gjString;
	}

	public void setGjString(java.lang.String value) {
		this.gjString = value;
	}
		
	public java.lang.String getWhcdString() {
		return this.whcdString;
	}

	public void setWhcdString(java.lang.String value) {
		this.whcdString = value;
	}
		
	public java.lang.String getSfString() {
		return this.sfString;
	}

	public void setSfString(java.lang.String value) {
		this.sfString = value;
	}
		
	public java.lang.String getTssfString() {
		return this.tssfString;
	}

	public void setTssfString(java.lang.String value) {
		this.tssfString = value;
	}
		
	public java.lang.String getMzString() {
		return this.mzString;
	}

	public void setMzString(java.lang.String value) {
		this.mzString = value;
	}
		
	public java.lang.String getZzmmString() {
		return this.zzmmString;
	}

	public void setZzmmString(java.lang.String value) {
		this.zzmmString = value;
	}
		
	public java.lang.String getHyzkString() {
		return this.hyzkString;
	}

	public void setHyzkString(java.lang.String value) {
		this.hyzkString = value;
	}
		
	public java.lang.String getHjdString() {
		return this.hjdString;
	}

	public void setHjdString(java.lang.String value) {
		this.hjdString = value;
	}
		
	public java.lang.String getJgString() {
		return this.jgString;
	}

	public void setJgString(java.lang.String value) {
		this.jgString = value;
	}
		
	public java.lang.String getXzdString() {
		return this.xzdString;
	}

	public void setXzdString(java.lang.String value) {
		this.xzdString = value;
	}
		
	public java.lang.String getZyString() {
		return this.zyString;
	}

	public void setZyString(java.lang.String value) {
		this.zyString = value;
	}
		
	public java.lang.String getRsxzString() {
		return this.rsxzString;
	}

	public void setRsxzString(java.lang.String value) {
		this.rsxzString = value;
	}
		
	public java.lang.String getSypzString() {
		return this.sypzString;
	}

	public void setSypzString(java.lang.String value) {
		this.sypzString = value;
	}
		
	public java.lang.String getAyString() {
		return this.ayString;
	}

	public void setAyString(java.lang.String value) {
		this.ayString = value;
	}
		
	public java.lang.String getCzztString() {
		return this.czztString;
	}

	public void setCzztString(java.lang.String value) {
		this.czztString = value;
	}
		
	public java.lang.String getZxfString() {
		return this.zxfString;
	}

	public void setZxfString(java.lang.String value) {
		this.zxfString = value;
	}
		
	public java.lang.String getAqdjString() {
		return this.aqdjString;
	}

	public void setAqdjString(java.lang.String value) {
		this.aqdjString = value;
	}
		
	public java.lang.String getRygllbString() {
		return this.rygllbString;
	}

	public void setRygllbString(java.lang.String value) {
		this.rygllbString = value;
	}
		
	public java.lang.String getLscsyyString() {
		return this.lscsyyString;
	}

	public void setLscsyyString(java.lang.String value) {
		this.lscsyyString = value;
	}
		
	public java.lang.String getCrjbjString() {
		return this.crjbjString;
	}

	public void setCrjbjString(java.lang.String value) {
		this.crjbjString = value;
	}
		
	public java.lang.String getRkbhgbjString() {
		return this.rkbhgbjString;
	}

	public void setRkbhgbjString(java.lang.String value) {
		this.rkbhgbjString = value;
	}
		
	public java.lang.String getLrsfjsString() {
		return this.lrsfjsString;
	}

	public void setLrsfjsString(java.lang.String value) {
		this.lrsfjsString = value;
	}
		
	public java.lang.String getCsyyString() {
		return this.csyyString;
	}

	public void setCsyyString(java.lang.String value) {
		this.csyyString = value;
	}
		
	public java.lang.String getCsqxString() {
		return this.csqxString;
	}

	public void setCsqxString(java.lang.String value) {
		this.csqxString = value;
	}
		
	public java.lang.String getJjString() {
		return this.jjString;
	}

	public void setJjString(java.lang.String value) {
		this.jjString = value;
	}
		
	public java.lang.String getJbString() {
		return this.jbString;
	}

	public void setJbString(java.lang.String value) {
		this.jbString = value;
	}
		
	public java.lang.String getZdzyString() {
		return this.zdzyString;
	}

	public void setZdzyString(java.lang.String value) {
		this.zdzyString = value;
	}
		
	public java.lang.String getYgryString() {
		return this.ygryString;
	}

	public void setYgryString(java.lang.String value) {
		this.ygryString = value;
	}
		
	public java.lang.String getRsldspString() {
		return this.rsldspString;
	}

	public void setRsldspString(java.lang.String value) {
		this.rsldspString = value;
	}
		
	public java.lang.String getStateString() {
		return this.stateString;
	}

	public void setStateString(java.lang.String value) {
		this.stateString = value;
	}
		
	public java.lang.String getScbzString() {
		return this.scbzString;
	}

	public void setScbzString(java.lang.String value) {
		this.scbzString = value;
	}
	
	public java.lang.String getDwlxString() {
		return dwlxString;
	}
	
	public void setDwlxString(java.lang.String dwlxString) {
		this.dwlxString = dwlxString;
	}
	public java.lang.String getFxdjString() {
		return fxdjString;
	}
	public void setFxdjString(java.lang.String fxdjString) {
		this.fxdjString = fxdjString;
	}
	
	
	//字典的get方法生成结束
	 
}

