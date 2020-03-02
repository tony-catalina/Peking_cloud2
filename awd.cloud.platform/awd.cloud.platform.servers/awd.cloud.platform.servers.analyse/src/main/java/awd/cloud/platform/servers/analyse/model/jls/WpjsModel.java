/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.model.jls;

import awd.framework.common.model.Model;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class WpjsModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Wpjs";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_TBR = "填表人";
	public static final String ALIAS_TBRQ = "填表日期";
	public static final String ALIAS_JSCXBH = "接收次序编号";
	public static final String ALIAS_MC = "物品名称";
	public static final String ALIAS_LQR = "领取人";
	public static final String ALIAS_XJCD = "新旧程度(XJCD)";
	public static final String ALIAS_WPLX = "物品类型( 0 日常物品 1 贵重物品 )";
	public static final String ALIAS_YS = "颜色(WPYS)";
	public static final String ALIAS_DW = "单位";
	public static final String ALIAS_SQJE = "送钱金额";
	public static final String ALIAS_WPXX = "物品信息";
	public static final String ALIAS_DZ = "地址";
	public static final String ALIAS_SL = "数量";
	public static final String ALIAS_DJRQ = "登记日期";
	public static final String ALIAS_DJR = "登记人";
	public static final String ALIAS_LQRQ = "领取日期";
	public static final String ALIAS_LQZT = "领取状态(WPLQZT)";
	public static final String ALIAS_GX = "与被送物品人关系(GX)";
	public static final String ALIAS_GZDW = "工作单位";
	public static final String ALIAS_LXDH = "联系电话";
	public static final String ALIAS_SWRXM = "送物人姓名";
	public static final String ALIAS_XB = "送物人性别(XB)";
	public static final String ALIAS_ZJLX = "证件类型(ZJLX)";
	public static final String ALIAS_ZJHM = "送物人证件号码";
	public static final String ALIAS_SWSJ = "送物时间";
	public static final String ALIAS_ZQMJ = "执勤民警";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_WBWPBH = "网办物品编号";
	public static final String ALIAS_JJRXM = "接济人姓名";
	public static final String ALIAS_JJRZJLX = "接济人联系证件类型";
	public static final String ALIAS_JJRZJH = "接济人证件号";
	public static final String ALIAS_JJRLXDH = "接济人联系电话";
	public static final String ALIAS_JJRLXDZ = "接济人联系地址";
	public static final String ALIAS_YBJJRGX = "与被接济人关系";
	public static final String ALIAS_RSQSFYJB = "入所前是否有疾病";
	public static final String ALIAS_RSQJBXXQK = "入所前疾病详细情况";
	public static final String ALIAS_SFYJZBS = "是否有家族病史";
	public static final String ALIAS_JZBSXXQK = "家族病史详细情况";
	public static final String ALIAS_SFCQFYHFYZMYW = "是否长期服药或服用专门药物";
	public static final String ALIAS_CQFYHFYZMYWXXQK = "长期服药或服用专门药物详细情况";
	public static final String ALIAS_SCBZ = "上传标志";
	public static final String ALIAS_STATE = "删除状态(STATE)";
	public static final String ALIAS_OPERATOR = "操作人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_FZDSL = "封装袋数量";
	public static final String ALIAS_FZDBH = "封装袋编号";
	public static final String ALIAS_DJWPSL = "大件物品数量";
	public static final String ALIAS_DJWPBH = "大件物品编号";
	public static final String ALIAS_XH = "序号";
	public static final String ALIAS_TZ = "特征";
	public static final String ALIAS_GGYS = "规格样式";
	public static final String ALIAS_PH = "牌号";
	public static final String ALIAS_WPBH = "物品编号";
	public static final String ALIAS_JSR = "接受人";
	public static final String ALIAS_WPLY = "物品来源";
	public static final String ALIAS_JZR = "见证人";
	public static final String ALIAS_TWRQ = "退物日期";
	public static final String ALIAS_JZRQ = "见证日期";
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

	private String rybh;

	private String tbr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date tbrq;

	private String jscxbh;

	private String mc;

	private String lqr;

	private String xjcd;

	private String wplx;

	private String ys;

	private String dw;

	private String sqje;

	private String wpxx;

	private String dz;

	private String sl;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date djrq;

	private String djr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lqrq;

	private String lqzt;

	private String gx;

	private String gzdw;

	private String lxdh;

	private String swrxm;

	private String xb;

	private String zjlx;

	private String zjhm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date swsj;

	private String zqmj;

	private String bz;

	private String wbwpbh;

	private String jjrxm;

	private String jjrzjlx;

	private String jjrzjh;

	private String jjrlxdh;

	private String jjrlxdz;

	private String ybjjrgx;

	private String rsqsfyjb;

	private String rsqjbxxqk;

	private String sfyjzbs;

	private String jzbsxxqk;

	private String sfcqfyhfyzmyw;

	private String cqfyhfyzmywxxqk;

	private String scbz;

	private String state;

	private String operator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;

	private String fzdsl;

	private String fzdbh;

	private String djwpsl;

	private String djwpbh;

	private String xh;

	private String tz;

	private String ggys;

	private String ph;

	private String wpbh;

	private String jsr;

	private String wply;

	private String jzr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date twrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jzrq;

	private String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;

	private String creator;
	//columns END



	public WpjsModel(){
	}
	public WpjsModel(String id) {
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

	public String getJscxbh() {
		return this.jscxbh;
	}

	public void setJscxbh(String value) {
		this.jscxbh = value;
	}
	public String getMc() {
		return this.mc;
	}

	public void setMc(String value) {
		this.mc = value;
	}
	public String getLqr() {
		return this.lqr;
	}

	public void setLqr(String value) {
		this.lqr = value;
	}
	public String getXjcd() {
		return this.xjcd;
	}

	public void setXjcd(String value) {
		this.xjcd = value;
	}
	public String getWplx() {
		return this.wplx;
	}

	public void setWplx(String value) {
		this.wplx = value;
	}
	public String getYs() {
		return this.ys;
	}

	public void setYs(String value) {
		this.ys = value;
	}
	public String getDw() {
		return this.dw;
	}

	public void setDw(String value) {
		this.dw = value;
	}
	public String getSqje() {
		return this.sqje;
	}

	public void setSqje(String value) {
		this.sqje = value;
	}
	public String getWpxx() {
		return this.wpxx;
	}

	public void setWpxx(String value) {
		this.wpxx = value;
	}
	public String getDz() {
		return this.dz;
	}

	public void setDz(String value) {
		this.dz = value;
	}
	public String getSl() {
		return this.sl;
	}

	public void setSl(String value) {
		this.sl = value;
	}

	public java.util.Date getDjrq() {
		return this.djrq;
	}

	public void setDjrq(java.util.Date value) {
		this.djrq = value;
	}

	public String getDjr() {
		return this.djr;
	}

	public void setDjr(String value) {
		this.djr = value;
	}

	public java.util.Date getLqrq() {
		return this.lqrq;
	}

	public void setLqrq(java.util.Date value) {
		this.lqrq = value;
	}

	public String getLqzt() {
		return this.lqzt;
	}

	public void setLqzt(String value) {
		this.lqzt = value;
	}
	public String getGx() {
		return this.gx;
	}

	public void setGx(String value) {
		this.gx = value;
	}
	public String getGzdw() {
		return this.gzdw;
	}

	public void setGzdw(String value) {
		this.gzdw = value;
	}
	public String getLxdh() {
		return this.lxdh;
	}

	public void setLxdh(String value) {
		this.lxdh = value;
	}
	public String getSwrxm() {
		return this.swrxm;
	}

	public void setSwrxm(String value) {
		this.swrxm = value;
	}
	public String getXb() {
		return this.xb;
	}

	public void setXb(String value) {
		this.xb = value;
	}
	public String getZjlx() {
		return this.zjlx;
	}

	public void setZjlx(String value) {
		this.zjlx = value;
	}
	public String getZjhm() {
		return this.zjhm;
	}

	public void setZjhm(String value) {
		this.zjhm = value;
	}

	public java.util.Date getSwsj() {
		return this.swsj;
	}

	public void setSwsj(java.util.Date value) {
		this.swsj = value;
	}

	public String getZqmj() {
		return this.zqmj;
	}

	public void setZqmj(String value) {
		this.zqmj = value;
	}
	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}
	public String getWbwpbh() {
		return this.wbwpbh;
	}

	public void setWbwpbh(String value) {
		this.wbwpbh = value;
	}
	public String getJjrxm() {
		return this.jjrxm;
	}

	public void setJjrxm(String value) {
		this.jjrxm = value;
	}
	public String getJjrzjlx() {
		return this.jjrzjlx;
	}

	public void setJjrzjlx(String value) {
		this.jjrzjlx = value;
	}
	public String getJjrzjh() {
		return this.jjrzjh;
	}

	public void setJjrzjh(String value) {
		this.jjrzjh = value;
	}
	public String getJjrlxdh() {
		return this.jjrlxdh;
	}

	public void setJjrlxdh(String value) {
		this.jjrlxdh = value;
	}
	public String getJjrlxdz() {
		return this.jjrlxdz;
	}

	public void setJjrlxdz(String value) {
		this.jjrlxdz = value;
	}
	public String getYbjjrgx() {
		return this.ybjjrgx;
	}

	public void setYbjjrgx(String value) {
		this.ybjjrgx = value;
	}
	public String getRsqsfyjb() {
		return this.rsqsfyjb;
	}

	public void setRsqsfyjb(String value) {
		this.rsqsfyjb = value;
	}
	public String getRsqjbxxqk() {
		return this.rsqjbxxqk;
	}

	public void setRsqjbxxqk(String value) {
		this.rsqjbxxqk = value;
	}
	public String getSfyjzbs() {
		return this.sfyjzbs;
	}

	public void setSfyjzbs(String value) {
		this.sfyjzbs = value;
	}
	public String getJzbsxxqk() {
		return this.jzbsxxqk;
	}

	public void setJzbsxxqk(String value) {
		this.jzbsxxqk = value;
	}
	public String getSfcqfyhfyzmyw() {
		return this.sfcqfyhfyzmyw;
	}

	public void setSfcqfyhfyzmyw(String value) {
		this.sfcqfyhfyzmyw = value;
	}
	public String getCqfyhfyzmywxxqk() {
		return this.cqfyhfyzmywxxqk;
	}

	public void setCqfyhfyzmywxxqk(String value) {
		this.cqfyhfyzmywxxqk = value;
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

	public String getFzdsl() {
		return this.fzdsl;
	}

	public void setFzdsl(String value) {
		this.fzdsl = value;
	}
	public String getFzdbh() {
		return this.fzdbh;
	}

	public void setFzdbh(String value) {
		this.fzdbh = value;
	}
	public String getDjwpsl() {
		return this.djwpsl;
	}

	public void setDjwpsl(String value) {
		this.djwpsl = value;
	}
	public String getDjwpbh() {
		return this.djwpbh;
	}

	public void setDjwpbh(String value) {
		this.djwpbh = value;
	}
	public String getXh() {
		return this.xh;
	}

	public void setXh(String value) {
		this.xh = value;
	}
	public String getTz() {
		return this.tz;
	}

	public void setTz(String value) {
		this.tz = value;
	}
	public String getGgys() {
		return this.ggys;
	}

	public void setGgys(String value) {
		this.ggys = value;
	}
	public String getPh() {
		return this.ph;
	}

	public void setPh(String value) {
		this.ph = value;
	}
	public String getWpbh() {
		return this.wpbh;
	}

	public void setWpbh(String value) {
		this.wpbh = value;
	}
	public String getJsr() {
		return this.jsr;
	}

	public void setJsr(String value) {
		this.jsr = value;
	}
	public String getWply() {
		return this.wply;
	}

	public void setWply(String value) {
		this.wply = value;
	}
	public String getJzr() {
		return this.jzr;
	}

	public void setJzr(String value) {
		this.jzr = value;
	}

	public java.util.Date getTwrq() {
		return this.twrq;
	}

	public void setTwrq(java.util.Date value) {
		this.twrq = value;
	}


	public java.util.Date getJzrq() {
		return this.jzrq;
	}

	public void setJzrq(java.util.Date value) {
		this.jzrq = value;
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

