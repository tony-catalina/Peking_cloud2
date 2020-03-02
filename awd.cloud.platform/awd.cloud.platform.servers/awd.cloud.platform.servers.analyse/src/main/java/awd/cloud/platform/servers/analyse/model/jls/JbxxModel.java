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
import java.util.HashMap;
import java.util.Map;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class JbxxModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Jbxx";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_SNBH = "所内编号";
	public static final String ALIAS_YWLCID = "业务流程ID";
	public static final String ALIAS_JSH = "拘室号";
	public static final String ALIAS_GCBH = "过程编号";
	public static final String ALIAS_WBRYBH = "网办人员编号";
	public static final String ALIAS_XM = "姓名";
	public static final String ALIAS_XMPY = "姓名拼音";
	public static final String ALIAS_XMPYSZM = "姓名拼音首字母";
	public static final String ALIAS_BM = "别名";
	public static final String ALIAS_BMTY = "别名同音";
	public static final String ALIAS_XB = "性别(XB)";
	public static final String ALIAS_CSRQ = "出生日期";
	public static final String ALIAS_ZJLX = "证件类型(ZJLX)";
	public static final String ALIAS_ZJH = "证件号";
	public static final String ALIAS_HYZK = "婚姻状况(HYZK)";
	public static final String ALIAS_MZ = "民族(MZ)";
	public static final String ALIAS_GJ = "国籍(GJ)";
	public static final String ALIAS_HJD = "户籍地(XZQH)";
	public static final String ALIAS_HJDXZ = "户籍地详址";
	public static final String ALIAS_XZD = "现住地(XZQH)";
	public static final String ALIAS_XZDXZ = "现住地详址";
	public static final String ALIAS_WHCD = "文化程度(WHCD)";
	public static final String ALIAS_ZY = "职业(ZY)";
	public static final String ALIAS_GZDW = "工作单位";
	public static final String ALIAS_SF = "身份(SF)";
	public static final String ALIAS_TSSF = "特殊身份(TSSF)";
	public static final String ALIAS_DH = "联系电话";
	public static final String ALIAS_RSRQ = "入所日期";
	public static final String ALIAS_RSXZ = "入所性质(RSXZ)";
	public static final String ALIAS_DWLX = "办案单位类型";
	public static final String ALIAS_BADW = "办案单位";
	public static final String ALIAS_SYPZWSH = "收押凭证文书号";
	public static final String ALIAS_AY = "案由(AJLX)";
	public static final String ALIAS_JYAQ = "简要案情";
	public static final String ALIAS_SXZM = "涉嫌罪名(SXZM)";
	public static final String ALIAS_ZXF = "重刑犯(ZXF)";
	public static final String ALIAS_SYPZ = "收押凭证(SYPZ)";
	public static final String ALIAS_GYKSRQ = "关押开始日期";
	public static final String ALIAS_AQDJ = "安全等级(AQDJ)";
	public static final String ALIAS_JYRQ = "羁押日期";
	public static final String ALIAS_GYQX = "关押期限";
	public static final String ALIAS_ZRDW = "转入单位";
	public static final String ALIAS_SHID = "手环ID";
	public static final String ALIAS_SYDW = "送押单位";
	public static final String ALIAS_SYR = "送押人";
	public static final String ALIAS_CYLX = "成员类型(CYLX)";
	public static final String ALIAS_BAHJ = "办案环节(BAJD)";
	public static final String ALIAS_BAR = "办案人 ";
	public static final String ALIAS_BARDH = "办案民警电话";
	public static final String ALIAS_CZDH = "办案传真电话";
	public static final String ALIAS_ZZQSRQ = "暂住起始日期";
	public static final String ALIAS_RYGLLB = "人员管理类别(RYGLLB)";
	public static final String ALIAS_ZSZT = "在所状态(ZSZT) 12,13都属于已决";
	public static final String ALIAS_LSCSYY = "临时出所原因(LSCS)";
	public static final String ALIAS_LSCSSJ = "上一次临时出所时间";
	public static final String ALIAS_LSCSHSSJ = "上一次临时出所回所时间";
	public static final String ALIAS_CRJBJ = "出入监标记(CJBJ)";
	public static final String ALIAS_RKBHGBJ = "入库合格不合格标记(SHFO)";
	public static final String ALIAS_RKBHGYY = "不合格入库原因";
	public static final String ALIAS_LRSFJS = "是否及时录入标记(SHFO)";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_DAH = "档案号";
	public static final String ALIAS_CSSJ = "出所时间";
	public static final String ALIAS_CSYY = "出所原因(CSYY)";
	public static final String ALIAS_SFFLWSH = "法律文书号";
	public static final String ALIAS_TSBJ = "特殊标记";
	public static final String ALIAS_EMLX = "耳目类型(EMLX)";
	public static final String ALIAS_RSJCLX = "妊娠检测类型(RSJCLX)";
	public static final String ALIAS_ZZMM = "政治面貌(ZZMM)";
	public static final String ALIAS_XQ = "刑期";
	public static final String ALIAS_HX = "缓刑";
	public static final String ALIAS_HXQX = "缓刑期限";
	public static final String ALIAS_CLJG = "处理结果(CLJG)";
	public static final String ALIAS_FJX = "附加刑(FJX)";
	public static final String ALIAS_ZWBH = "指纹编号";
	public static final String ALIAS_JCQK = "奖惩情况";
	public static final String ALIAS_YKSS = "原看守所";
	public static final String ALIAS_LSYY = "留所原因(LSYY)";
	public static final String ALIAS_JG = "结果";
	public static final String ALIAS_CSQX = "出所去向(CSQX)";
	public static final String ALIAS_FZJL = "犯罪经历";
	public static final String ALIAS_CAAJ = "从案类型(AJLB)";
	public static final String ALIAS_BHLX = "病号类型(BHLX)";
	public static final String ALIAS_AZB = "艾滋病(SHFO)";
	public static final String ALIAS_SYMJ = "收押民警";
	public static final String ALIAS_JLRQ = "拘留日期";
	public static final String ALIAS_DBRQ = "逮捕日期";
	public static final String ALIAS_SCQSRQ = "审查起诉日期";
	public static final String ALIAS_YSFYRQ = "移送法院日期";
	public static final String ALIAS_PWH = "入库合格不合格标记";
	public static final String ALIAS_LSFH = "不合格入库原因";
	public static final String ALIAS_ZC = "专长(ZC)";
	public static final String ALIAS_WXDJ = "危险等级(FXDJ)";
	public static final String ALIAS_ZYRYXGQK = "在押人员相关情况 (ZYRYXGQK)";
	public static final String ALIAS_BYZD = "收押非拘捕人员";
	public static final String ALIAS_JKZK = "健康状况";
	public static final String ALIAS_ZUC = "足长";
	public static final String ALIAS_SG = "身高";
	public static final String ALIAS_JJ = "戒具标志位(JJ)";
	public static final String ALIAS_JB = "禁闭标志位(JB)";
	public static final String ALIAS_ZDZY = "重点在押";
	public static final String ALIAS_SAVETIME = "保存时间";
	public static final String ALIAS_SJHSQK = "数据核实情况";
	public static final String ALIAS_HSSJMJ = "核实数据民警姓名";
	public static final String ALIAS_SCBZ = "上传标志";
	public static final String ALIAS_OPERATOR = "操作人";
	public static final String ALIAS_STATE = "状态(YWSTATE)";
	public static final String ALIAS_JSHJ = "家属会见";
	public static final String ALIAS_LDF = "劳动犯标志位(LDF)";
	public static final String ALIAS_YGRY = "严管人员标志位(YGRY)";
	public static final String ALIAS_BARJH = "办案人警号 多个以逗号或空格分开";
	public static final String ALIAS_JSLY = "拒收理由(入所健康检查结果)";
	public static final String ALIAS_XHAY = "细化案由(XHAY)";
	public static final String ALIAS_JY = "就医";
	public static final String ALIAS_GYJSRQ = "关押结束日期";
	public static final String ALIAS_GYTS = "羁押天数";
	public static final String ALIAS_JLJDJG = "jljdjg";
	public static final String ALIAS_RYZWBJ = "人员指纹标记(RYZWBJ)";
	public static final String ALIAS_CSZWYZ = "出所指纹验证(SHFO)";
	public static final String ALIAS_DZDJ = "dzdj";
	public static final String ALIAS_SDNJCCJG = "涉毒尿检初查结果";
	public static final String ALIAS_SDNJDW = "涉毒尿检单位";
	public static final String ALIAS_SDNJCJSJ = "涉毒尿检初检时间";
	public static final String ALIAS_SDNJCJJCR = "涉毒尿检初检检查人";
	public static final String ALIAS_HZFLWSH = "回执法律文书";
	public static final String ALIAS_FWH = "附物号";
	public static final String ALIAS_WFSJ = "违法时间";
	public static final String ALIAS_WFDD = "违法地点";
	public static final String ALIAS_JLQX = "拘留期限";
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

	private String ywlcid;

	private String jsbh;

	private String rybh;

	private String snbh;

	private String jsh;

	private String gcbh;

	private String wbrybh;

	private String xm;

	private String xmpy;

	private String xmpyszm;

	private String bm;

	private String bmty;

	private String xb;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date csrq;

	private String zjlx;

	private String zjh;

	private String hyzk;

	private String mz;

	private String gj;

	private String hjd;

	private String hjdxz;

	private String xzd;

	private String xzdxz;

	private String whcd;

	private String zy;

	private String gzdw;

	private String sf;

	private String tssf;

	private String dh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date rsrq;

	private String rsxz;

	private String dwlx;

	private String badw;

	private String sypzwsh;

	private String ay;

	private String jyaq;

	private String sxzm;

	private String zxf;

	private String sypz;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date gyksrq;

	private String aqdj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jyrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date gyqx;

	private String zrdw;

	private String shid;

	private String sydw;

	private String syr;

	private String cylx;

	private String bahj;

	private String bar;

	private String bardh;

	private String czdh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date zzqsrq;

	private String rygllb;

	private String zszt;

	private String lscsyy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lscssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lscshssj;

	private String crjbj;

	private String rkbhgbj;

	private String rkbhgyy;

	private String lrsfjs;

	private String bz;

	private String dah;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date cssj;

	private String csyy;

	private String sfflwsh;

	private String tsbj;

	private String emlx;

	private String rsjclx;

	private String zzmm;

	private String xq;

	private String hx;

	private String hxqx;

	private String cljg;

	private String fjx;

	private String zwbh;

	private String jcqk;

	private String ykss;

	private String lsyy;

	private String jg;

	private String csqx;

	private String fzjl;

	private String caaj;

	private String bhlx;

	private String azb;

	private String symj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jlrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date dbrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date scqsrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ysfyrq;

	private String pwh;

	private String lsfh;

	private String zc;

	private String wxdj;

	private String zyryxgqk;

	private String byzd;

	private String jkzk;

	private Short zuc;

	private Short sg;

	private String jj;

	private String jb;

	private String zdzy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date savetime;

	private String sjhsqk;

	private String hssjmj;

	private String scbz;

	private String operator;

	private String state;

	private String jshj;

	private String ldf;

	private String ygry;

	private String barjh;

	private String jsly;

	private String xhay;

	private String jy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date gyjsrq;

	private String gyts;

	private String jljdjg;

	private String ryzwbj;

	private String cszwyz;

	private String dzdj;

	private String sdnjccjg;

	private String sdnjdw;

	private String sdnjcjsj;

	private String sdnjcjjcr;

	private String hzflwsh;

	private String fwh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date wfsj;

	private String wfdd;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jlqx;

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

    //字典
	private String mzString;
	private String dwlxString;
	private String tssfString;
	private String zyString;
	private String gjString;
	private String xbString;
	private String zjlxString;
	private String zzmmString;
	private String hyzkString;
	private String jgString;
	private String xzdString;
	private String hjdString;
	private String whcdString;
	private String zcString;
	private String sfString;
	private String bhlxString;
	private String azbString;
	private String rsxzString;
	private String ayString;
	private String zhayString;
	private String cylxString;
	private String bahjString;
	private String badwString;
	private String csyyString;
	private String csqxString;
	private String cszwyzString;
	private String rygllbString;
	private String cljgString;
	private String zsztString;
	private String lscsString;
	private String crjbjString;
	private String rkbhgbjString;
	private String rkbhgyyString;
	private String lrsfjsString;
	private String zyryxgqkString;
	private String emlxString;
	private String rsjclxString;
	private String jjString;
	private String jbString;
	private String ldfString;
	private String ygryString;
	private String zdryString;
	private String zyafString;
	private String fxdjString;
	private String slajString;
	private String sypzString;
	private String isjdwmString;
	private String cypjfzString;
	private String sfypjString;
	private String sfcxcyString;
	private String sffyString;
	private String wcnbjString;
	private String stateString;
	private String csrqString;
	private String rsrqString;
	private String jyrqString;
	private String gyqxString;
	private String zzqsrqString;
	private String jlrqString;
	private String dbrqString;
	private String cssjString;
	private String lscssjString;
	private String lscshssjString;
	private String sykzrqString;
	private String createtimeString;
	private String updatetimeString;
	private String ywlcname;

	public String getMzString() {
		return mzString;
	}

	public String getDwlxString() {
		return dwlxString;
	}

	public String getTssfString() {
		return tssfString;
	}

	public String getZyString() {
		return zyString;
	}

	public String getGjString() {
		return gjString;
	}

	public String getXbString() {
		return xbString;
	}

	public String getZjlxString() {
		return zjlxString;
	}

	public String getZzmmString() {
		return zzmmString;
	}

	public String getHyzkString() {
		return hyzkString;
	}

	public String getJgString() {
		return jgString;
	}

	public String getXzdString() {
		return xzdString;
	}

	public String getHjdString() {
		return hjdString;
	}

	public String getWhcdString() {
		return whcdString;
	}

	public String getZcString() {
		return zcString;
	}

	public String getSfString() {
		return sfString;
	}

	public String getBhlxString() {
		return bhlxString;
	}

	public String getAzbString() {
		return azbString;
	}

	public String getRsxzString() {
		return rsxzString;
	}

	public String getAyString() {
		return ayString;
	}

	public String getZhayString() {
		return zhayString;
	}

	public String getCylxString() {
		return cylxString;
	}

	public String getBahjString() {
		return bahjString;
	}

	public String getBadwString() {
		return badwString;
	}

	public String getCsyyString() {
		return csyyString;
	}

	public String getCsqxString() {
		return csqxString;
	}

	public String getCszwyzString() {
		return cszwyzString;
	}

	public String getRygllbString() {
		return rygllbString;
	}

	public String getCljgString() {
		return cljgString;
	}

	public String getZsztString() {
		return zsztString;
	}

	public String getLscsString() {
		return lscsString;
	}

	public String getCrjbjString() {
		return crjbjString;
	}

	public String getRkbhgbjString() {
		return rkbhgbjString;
	}

	public String getRkbhgyyString() {
		return rkbhgyyString;
	}

	public String getLrsfjsString() {
		return lrsfjsString;
	}

	public String getZyryxgqkString() {
		return zyryxgqkString;
	}

	public String getEmlxString() {
		return emlxString;
	}

	public String getRsjclxString() {
		return rsjclxString;
	}

	public String getJjString() {
		return jjString;
	}

	public String getJbString() {
		return jbString;
	}

	public String getLdfString() {
		return ldfString;
	}

	public String getYgryString() {
		return ygryString;
	}

	public String getZdryString() {
		return zdryString;
	}

	public String getZyafString() {
		return zyafString;
	}

	public String getFxdjString() {
		return fxdjString;
	}

	public String getSlajString() {
		return slajString;
	}

	public String getSypzString() {
		return sypzString;
	}

	public String getIsjdwmString() {
		return isjdwmString;
	}

	public String getCypjfzString() {
		return cypjfzString;
	}

	public String getSfypjString() {
		return sfypjString;
	}

	public String getSfcxcyString() {
		return sfcxcyString;
	}

	public String getSffyString() {
		return sffyString;
	}

	public String getWcnbjString() {
		return wcnbjString;
	}

	public String getStateString() {
		return stateString;
	}

	public String getCsrqString() {
		return csrqString;
	}

	public String getRsrqString() {
		return rsrqString;
	}

	public String getJyrqString() {
		return jyrqString;
	}

	public String getGyqxString() {
		return gyqxString;
	}

	public String getZzqsrqString() {
		return zzqsrqString;
	}

	public String getJlrqString() {
		return jlrqString;
	}

	public String getDbrqString() {
		return dbrqString;
	}

	public String getCssjString() {
		return cssjString;
	}

	public String getLscssjString() {
		return lscssjString;
	}

	public String getLscshssjString() {
		return lscshssjString;
	}

	public String getSykzrqString() {
		return sykzrqString;
	}

	public String getCreatetimeString() {
		return createtimeString;
	}

	public String getUpdatetimeString() {
		return updatetimeString;
	}

	public String getYwlcname() {
		return ywlcname;
	}

	//columns END


	private String taskid;

	private Map<String, String> ldpsxx;

	public Map<String, String> getLdpsxx() {
		return ldpsxx;
	}

	public JbxxModel(){
		this.ldpsxx = new HashMap<String, String>();
	}

	public String getTaskid(){
		return taskid;
	}
	public void setTaskid(String taskid){
		this.taskid = taskid;
	}



	public JbxxModel(String id) {
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

	public void setYwlcid(String value) {
		this.ywlcid = value;
	}

	public String getYwlcid() {
		return this.ywlcid;
	}

	public String getRybh() {
		return this.rybh;
	}

	public void setRybh(String value) {
		this.rybh = value;
	}
	public String getSnbh() {
		return this.snbh;
	}

	public void setSnbh(String value) {
		this.snbh = value;
	}
	public String getJsh() {
		return this.jsh;
	}

	public void setJsh(String value) {
		this.jsh = value;
	}
	public String getGcbh() {
		return this.gcbh;
	}

	public void setGcbh(String value) {
		this.gcbh = value;
	}
	public String getWbrybh() {
		return this.wbrybh;
	}

	public void setWbrybh(String value) {
		this.wbrybh = value;
	}
	public String getXm() {
		return this.xm;
	}

	public void setXm(String value) {
		this.xm = value;
	}
	public String getXmpy() {
		return this.xmpy;
	}

	public void setXmpy(String value) {
		this.xmpy = value;
	}
	public String getXmpyszm() {
		return this.xmpyszm;
	}

	public void setXmpyszm(String value) {
		this.xmpyszm = value;
	}
	public String getBm() {
		return this.bm;
	}

	public void setBm(String value) {
		this.bm = value;
	}
	public String getBmty() {
		return this.bmty;
	}

	public void setBmty(String value) {
		this.bmty = value;
	}
	public String getXb() {
		return this.xb;
	}

	public void setXb(String value) {
		this.xb = value;
	}

	public java.util.Date getCsrq() {
		return this.csrq;
	}

	public void setCsrq(java.util.Date value) {
		this.csrq = value;
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
	public String getHyzk() {
		return this.hyzk;
	}

	public void setHyzk(String value) {
		this.hyzk = value;
	}
	public String getMz() {
		return this.mz;
	}

	public void setMz(String value) {
		this.mz = value;
	}
	public String getGj() {
		return this.gj;
	}

	public void setGj(String value) {
		this.gj = value;
	}
	public String getHjd() {
		return this.hjd;
	}

	public void setHjd(String value) {
		this.hjd = value;
	}
	public String getHjdxz() {
		return this.hjdxz;
	}

	public void setHjdxz(String value) {
		this.hjdxz = value;
	}
	public String getXzd() {
		return this.xzd;
	}

	public void setXzd(String value) {
		this.xzd = value;
	}
	public String getXzdxz() {
		return this.xzdxz;
	}

	public void setXzdxz(String value) {
		this.xzdxz = value;
	}
	public String getWhcd() {
		return this.whcd;
	}

	public void setWhcd(String value) {
		this.whcd = value;
	}
	public String getZy() {
		return this.zy;
	}

	public void setZy(String value) {
		this.zy = value;
	}
	public String getGzdw() {
		return this.gzdw;
	}

	public void setGzdw(String value) {
		this.gzdw = value;
	}
	public String getSf() {
		return this.sf;
	}

	public void setSf(String value) {
		this.sf = value;
	}
	public String getTssf() {
		return this.tssf;
	}

	public void setTssf(String value) {
		this.tssf = value;
	}
	public String getDh() {
		return this.dh;
	}

	public void setDh(String value) {
		this.dh = value;
	}

	public java.util.Date getRsrq() {
		return this.rsrq;
	}

	public void setRsrq(java.util.Date value) {
		this.rsrq = value;
	}

	public String getRsxz() {
		return this.rsxz;
	}

	public void setRsxz(String value) {
		this.rsxz = value;
	}
	public String getDwlx() {
		return this.dwlx;
	}

	public void setDwlx(String value) {
		this.dwlx = value;
	}
	public String getBadw() {
		return this.badw;
	}

	public void setBadw(String value) {
		this.badw = value;
	}
	public String getSypzwsh() {
		return this.sypzwsh;
	}

	public void setSypzwsh(String value) {
		this.sypzwsh = value;
	}
	public String getAy() {
		return this.ay;
	}

	public void setAy(String value) {
		this.ay = value;
	}
	public String getJyaq() {
		return this.jyaq;
	}

	public void setJyaq(String value) {
		this.jyaq = value;
	}
	public String getSxzm() {
		return this.sxzm;
	}

	public void setSxzm(String value) {
		this.sxzm = value;
	}
	public String getZxf() {
		return this.zxf;
	}

	public void setZxf(String value) {
		this.zxf = value;
	}
	public String getSypz() {
		return this.sypz;
	}

	public void setSypz(String value) {
		this.sypz = value;
	}

	public java.util.Date getGyksrq() {
		return this.gyksrq;
	}

	public void setGyksrq(java.util.Date value) {
		this.gyksrq = value;
	}

	public String getAqdj() {
		return this.aqdj;
	}

	public void setAqdj(String value) {
		this.aqdj = value;
	}

	public java.util.Date getJyrq() {
		return this.jyrq;
	}

	public void setJyrq(java.util.Date value) {
		this.jyrq = value;
	}


	public java.util.Date getGyqx() {
		return this.gyqx;
	}

	public void setGyqx(java.util.Date value) {
		this.gyqx = value;
	}

	public String getZrdw() {
		return this.zrdw;
	}

	public void setZrdw(String value) {
		this.zrdw = value;
	}
	public String getShid() {
		return this.shid;
	}

	public void setShid(String value) {
		this.shid = value;
	}
	public String getSydw() {
		return this.sydw;
	}

	public void setSydw(String value) {
		this.sydw = value;
	}
	public String getSyr() {
		return this.syr;
	}

	public void setSyr(String value) {
		this.syr = value;
	}
	public String getCylx() {
		return this.cylx;
	}

	public void setCylx(String value) {
		this.cylx = value;
	}
	public String getBahj() {
		return this.bahj;
	}

	public void setBahj(String value) {
		this.bahj = value;
	}
	public String getBar() {
		return this.bar;
	}

	public void setBar(String value) {
		this.bar = value;
	}
	public String getBardh() {
		return this.bardh;
	}

	public void setBardh(String value) {
		this.bardh = value;
	}
	public String getCzdh() {
		return this.czdh;
	}

	public void setCzdh(String value) {
		this.czdh = value;
	}

	public java.util.Date getZzqsrq() {
		return this.zzqsrq;
	}

	public void setZzqsrq(java.util.Date value) {
		this.zzqsrq = value;
	}

	public String getRygllb() {
		return this.rygllb;
	}

	public void setRygllb(String value) {
		this.rygllb = value;
	}
	public String getZszt() {
		return this.zszt;
	}

	public void setZszt(String value) {
		this.zszt = value;
	}
	public String getLscsyy() {
		return this.lscsyy;
	}

	public void setLscsyy(String value) {
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

	public String getCrjbj() {
		return this.crjbj;
	}

	public void setCrjbj(String value) {
		this.crjbj = value;
	}
	public String getRkbhgbj() {
		return this.rkbhgbj;
	}

	public void setRkbhgbj(String value) {
		this.rkbhgbj = value;
	}
	public String getRkbhgyy() {
		return this.rkbhgyy;
	}

	public void setRkbhgyy(String value) {
		this.rkbhgyy = value;
	}
	public String getLrsfjs() {
		return this.lrsfjs;
	}

	public void setLrsfjs(String value) {
		this.lrsfjs = value;
	}
	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}
	public String getDah() {
		return this.dah;
	}

	public void setDah(String value) {
		this.dah = value;
	}

	public java.util.Date getCssj() {
		return this.cssj;
	}

	public void setCssj(java.util.Date value) {
		this.cssj = value;
	}

	public String getCsyy() {
		return this.csyy;
	}

	public void setCsyy(String value) {
		this.csyy = value;
	}
	public String getSfflwsh() {
		return this.sfflwsh;
	}

	public void setSfflwsh(String value) {
		this.sfflwsh = value;
	}
	public String getTsbj() {
		return this.tsbj;
	}

	public void setTsbj(String value) {
		this.tsbj = value;
	}
	public String getEmlx() {
		return this.emlx;
	}

	public void setEmlx(String value) {
		this.emlx = value;
	}
	public String getRsjclx() {
		return this.rsjclx;
	}

	public void setRsjclx(String value) {
		this.rsjclx = value;
	}
	public String getZzmm() {
		return this.zzmm;
	}

	public void setZzmm(String value) {
		this.zzmm = value;
	}
	public String getXq() {
		return this.xq;
	}

	public void setXq(String value) {
		this.xq = value;
	}
	public String getHx() {
		return this.hx;
	}

	public void setHx(String value) {
		this.hx = value;
	}
	public String getHxqx() {
		return this.hxqx;
	}

	public void setHxqx(String value) {
		this.hxqx = value;
	}
	public String getCljg() {
		return this.cljg;
	}

	public void setCljg(String value) {
		this.cljg = value;
	}
	public String getFjx() {
		return this.fjx;
	}

	public void setFjx(String value) {
		this.fjx = value;
	}
	public String getZwbh() {
		return this.zwbh;
	}

	public void setZwbh(String value) {
		this.zwbh = value;
	}
	public String getJcqk() {
		return this.jcqk;
	}

	public void setJcqk(String value) {
		this.jcqk = value;
	}
	public String getYkss() {
		return this.ykss;
	}

	public void setYkss(String value) {
		this.ykss = value;
	}
	public String getLsyy() {
		return this.lsyy;
	}

	public void setLsyy(String value) {
		this.lsyy = value;
	}
	public String getJg() {
		return this.jg;
	}

	public void setJg(String value) {
		this.jg = value;
	}
	public String getCsqx() {
		return this.csqx;
	}

	public void setCsqx(String value) {
		this.csqx = value;
	}
	public String getFzjl() {
		return this.fzjl;
	}

	public void setFzjl(String value) {
		this.fzjl = value;
	}
	public String getCaaj() {
		return this.caaj;
	}

	public void setCaaj(String value) {
		this.caaj = value;
	}
	public String getBhlx() {
		return this.bhlx;
	}

	public void setBhlx(String value) {
		this.bhlx = value;
	}
	public String getAzb() {
		return this.azb;
	}

	public void setAzb(String value) {
		this.azb = value;
	}
	public String getSymj() {
		return this.symj;
	}

	public void setSymj(String value) {
		this.symj = value;
	}

	public java.util.Date getJlrq() {
		return this.jlrq;
	}

	public void setJlrq(java.util.Date value) {
		this.jlrq = value;
	}


	public java.util.Date getDbrq() {
		return this.dbrq;
	}

	public void setDbrq(java.util.Date value) {
		this.dbrq = value;
	}


	public java.util.Date getScqsrq() {
		return this.scqsrq;
	}

	public void setScqsrq(java.util.Date value) {
		this.scqsrq = value;
	}


	public java.util.Date getYsfyrq() {
		return this.ysfyrq;
	}

	public void setYsfyrq(java.util.Date value) {
		this.ysfyrq = value;
	}

	public String getPwh() {
		return this.pwh;
	}

	public void setPwh(String value) {
		this.pwh = value;
	}
	public String getLsfh() {
		return this.lsfh;
	}

	public void setLsfh(String value) {
		this.lsfh = value;
	}
	public String getZc() {
		return this.zc;
	}

	public void setZc(String value) {
		this.zc = value;
	}
	public String getWxdj() {
		return this.wxdj;
	}

	public void setWxdj(String value) {
		this.wxdj = value;
	}
	public String getZyryxgqk() {
		return this.zyryxgqk;
	}

	public void setZyryxgqk(String value) {
		this.zyryxgqk = value;
	}
	public String getByzd() {
		return this.byzd;
	}

	public void setByzd(String value) {
		this.byzd = value;
	}
	public String getJkzk() {
		return this.jkzk;
	}

	public void setJkzk(String value) {
		this.jkzk = value;
	}

	public Short getZuc() {
		return this.zuc;
	}

	public void setZuc(Short value) {
		this.zuc = value;
	}
	public Short getSg() {
		return this.sg;
	}

	public void setSg(Short value) {
		this.sg = value;
	}

	public String getJj() {
		return this.jj;
	}

	public void setJj(String value) {
		this.jj = value;
	}
	public String getJb() {
		return this.jb;
	}

	public void setJb(String value) {
		this.jb = value;
	}
	public String getZdzy() {
		return this.zdzy;
	}

	public void setZdzy(String value) {
		this.zdzy = value;
	}

	public java.util.Date getSavetime() {
		return this.savetime;
	}

	public void setSavetime(java.util.Date value) {
		this.savetime = value;
	}

	public String getSjhsqk() {
		return this.sjhsqk;
	}

	public void setSjhsqk(String value) {
		this.sjhsqk = value;
	}
	public String getHssjmj() {
		return this.hssjmj;
	}

	public void setHssjmj(String value) {
		this.hssjmj = value;
	}
	public String getScbz() {
		return this.scbz;
	}

	public void setScbz(String value) {
		this.scbz = value;
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
	public String getJshj() {
		return this.jshj;
	}

	public void setJshj(String value) {
		this.jshj = value;
	}
	public String getLdf() {
		return this.ldf;
	}

	public void setLdf(String value) {
		this.ldf = value;
	}
	public String getYgry() {
		return this.ygry;
	}

	public void setYgry(String value) {
		this.ygry = value;
	}
	public String getBarjh() {
		return this.barjh;
	}

	public void setBarjh(String value) {
		this.barjh = value;
	}
	public String getJsly() {
		return this.jsly;
	}

	public void setJsly(String value) {
		this.jsly = value;
	}
	public String getXhay() {
		return this.xhay;
	}

	public void setXhay(String value) {
		this.xhay = value;
	}
	public String getJy() {
		return this.jy;
	}

	public void setJy(String value) {
		this.jy = value;
	}

	public java.util.Date getGyjsrq() {
		return this.gyjsrq;
	}

	public void setGyjsrq(java.util.Date value) {
		this.gyjsrq = value;
	}

	public String getGyts() {
		return this.gyts;
	}

	public void setGyts(String value) {
		this.gyts = value;
	}
	public String getJljdjg() {
		return this.jljdjg;
	}

	public void setJljdjg(String value) {
		this.jljdjg = value;
	}
	public String getRyzwbj() {
		return this.ryzwbj;
	}

	public void setRyzwbj(String value) {
		this.ryzwbj = value;
	}
	public String getCszwyz() {
		return this.cszwyz;
	}

	public void setCszwyz(String value) {
		this.cszwyz = value;
	}
	public String getDzdj() {
		return this.dzdj;
	}

	public void setDzdj(String value) {
		this.dzdj = value;
	}
	public String getSdnjccjg() {
		return this.sdnjccjg;
	}

	public void setSdnjccjg(String value) {
		this.sdnjccjg = value;
	}
	public String getSdnjdw() {
		return this.sdnjdw;
	}

	public void setSdnjdw(String value) {
		this.sdnjdw = value;
	}
	public String getSdnjcjsj() {
		return this.sdnjcjsj;
	}

	public void setSdnjcjsj(String value) {
		this.sdnjcjsj = value;
	}
	public String getSdnjcjjcr() {
		return this.sdnjcjjcr;
	}

	public void setSdnjcjjcr(String value) {
		this.sdnjcjjcr = value;
	}
	public String getHzflwsh() {
		return this.hzflwsh;
	}

	public void setHzflwsh(String value) {
		this.hzflwsh = value;
	}
	public String getFwh() {
		return this.fwh;
	}

	public void setFwh(String value) {
		this.fwh = value;
	}

	public java.util.Date getWfsj() {
		return this.wfsj;
	}

	public void setWfsj(java.util.Date value) {
		this.wfsj = value;
	}

	public String getWfdd() {
		return this.wfdd;
	}

	public void setWfdd(String value) {
		this.wfdd = value;
	}

	public java.util.Date getJlqx() {
		return this.jlqx;
	}

	public void setJlqx(java.util.Date value) {
		this.jlqx = value;
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

