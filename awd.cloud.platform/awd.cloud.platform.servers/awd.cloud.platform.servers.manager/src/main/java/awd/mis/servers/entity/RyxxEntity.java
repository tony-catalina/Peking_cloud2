/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.entity;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.utils.ApplicationContextHolder;
import awd.framework.expands.redisclient.RedisUtils;



/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class RyxxEntity extends SimpleGenericEntity<String> {
	//alias
	public static final String TABLE_ALIAS = "人员信息";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_GCBH = "过程编号";
	public static final String ALIAS_SNBH = "所内编号";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_JSH = "监室号";
	public static final String ALIAS_XM = "姓名";
	public static final String ALIAS_XMPY = "姓名拼音";
	public static final String ALIAS_BM = "别名";
	public static final String ALIAS_BMTY = "别名同音";
	public static final String ALIAS_XB = "性别";
	public static final String ALIAS_CSRQ = "出生日期";
	public static final String ALIAS_ZJLX = "证件类型(ZJLX)";
	public static final String ALIAS_ZJH = "证件号";
	public static final String ALIAS_HYZK = "婚姻状况(HYZK)";
	public static final String ALIAS_MZ = "民族(MZ)";
	public static final String ALIAS_GJ = "国籍(GJ)";
	public static final String ALIAS_JG = "籍贯";
	public static final String ALIAS_HJD = "户籍地(XZQH)";
	public static final String ALIAS_HJDXZ = "户籍地详址";
	public static final String ALIAS_XZD = "现住地(XZQH)";
	public static final String ALIAS_XZDXZ = "现住地详址";
	public static final String ALIAS_WHCD = "文化程度(WHCD)";
	public static final String ALIAS_ZY = "职业(ZY)";
	public static final String ALIAS_ZC = "专长";
	public static final String ALIAS_GZDW = "工作单位";
	public static final String ALIAS_SF = "身份(SF)";
	public static final String ALIAS_TSSF = "特殊身份(TSSF)";
	public static final String ALIAS_DAH = "档案编号";
	public static final String ALIAS_ZZMM = "政治面貌(ZZMM)";
	public static final String ALIAS_ZWBH = "指纹编号";
	public static final String ALIAS_JKZK = "健康情况(JKZK)";
	public static final String ALIAS_ZUC = "足长";
	public static final String ALIAS_SG = "身高";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_STATE = "在所状态(RYZT)";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	
	//date formats
	public static final String FORMAT_CSRQ = DATE_FORMAT;
	
	//保存组（不需要id验证）
	public static interface SaveGroup {};  
	//新增组（需要id验证）
	public static interface UpdateGroup {};
	//所有组
	@GroupSequence({SaveGroup.class, UpdateGroup.class})  
	public interface All {       
	} 

	//columns START
	@NotNull(message="监所编号不能为空",groups=SaveGroup.class)
    @Length(max=9,message="监所编号最大长度9位" ,groups=SaveGroup.class)
	private java.lang.String jsbh;
    @Length(max=30,message="过程编号最大长度30位" ,groups=SaveGroup.class)
	private java.lang.String gcbh;
    @Length(max=8,message="所内编号最大长度8位" ,groups=SaveGroup.class)
	private java.lang.String snbh;
	@NotNull(message="人员编号不能为空",groups=SaveGroup.class)
    @Length(max=21,message="人员编号最大长度21位" ,groups=SaveGroup.class)
	private java.lang.String rybh;
	@NotNull(message="监室号不能为空",groups=SaveGroup.class)
    @Length(max=4,message="监室号最大长度4位" ,groups=SaveGroup.class)
	private java.lang.String jsh;
	@NotNull(message="姓名不能为空",groups=SaveGroup.class)
    @Length(max=30,message="姓名最大长度30位" ,groups=SaveGroup.class)
	private java.lang.String xm;
    @Length(max=100,message="姓名拼音最大长度100位" ,groups=SaveGroup.class)
	private java.lang.String xmpy;
    @Length(max=30,message="别名最大长度30位" ,groups=SaveGroup.class)
	private java.lang.String bm;
    @Length(max=160,message="别名同音最大长度160位" ,groups=SaveGroup.class)
	private java.lang.String bmty;
	@NotNull(message="性别不能为空",groups=SaveGroup.class)
    @Length(max=1,message="性别最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String xb;
	
	@NotNull(message="出生日期不能为空")
	@JSONField(format = "yyyy-MM-dd")  
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date csrq;

	@NotNull(message="证件类型(ZJLX)不能为空",groups=SaveGroup.class)
    @Length(max=2,message="证件类型(ZJLX)最大长度2位" ,groups=SaveGroup.class)
	private java.lang.String zjlx;
    @Length(max=18,message="证件号最大长度18位" ,groups=SaveGroup.class)
	private java.lang.String zjh;
	@NotNull(message="婚姻状况(HYZK)不能为空",groups=SaveGroup.class)
    @Length(max=1,message="婚姻状况(HYZK)最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String hyzk;
	@NotNull(message="民族(MZ)不能为空",groups=SaveGroup.class)
    @Length(max=2,message="民族(MZ)最大长度2位" ,groups=SaveGroup.class)
	private java.lang.String mz;
	@NotNull(message="国籍(GJ)不能为空",groups=SaveGroup.class)
    @Length(max=3,message="国籍(GJ)最大长度3位" ,groups=SaveGroup.class)
	private java.lang.String gj;
	@NotNull(message="籍贯不能为空",groups=SaveGroup.class)
    @Length(max=6,message="籍贯最大长度6位" ,groups=SaveGroup.class)
	private java.lang.String jg;
	@NotNull(message="户籍地(XZQH)不能为空",groups=SaveGroup.class)
    @Length(max=6,message="户籍地(XZQH)最大长度6位" ,groups=SaveGroup.class)
	private java.lang.String hjd;
    @Length(max=120,message="户籍地详址最大长度120位" ,groups=SaveGroup.class)
	private java.lang.String hjdxz;
	@NotNull(message="现住地(XZQH)不能为空",groups=SaveGroup.class)
    @Length(max=6,message="现住地(XZQH)最大长度6位" ,groups=SaveGroup.class)
	private java.lang.String xzd;
    @Length(max=120,message="现住地详址最大长度120位" ,groups=SaveGroup.class)
	private java.lang.String xzdxz;
	@NotNull(message="文化程度(WHCD)不能为空",groups=SaveGroup.class)
    @Length(max=2,message="文化程度(WHCD)最大长度2位" ,groups=SaveGroup.class)
	private java.lang.String whcd;
    @Length(max=4,message="职业(ZY)最大长度4位" ,groups=SaveGroup.class)
	private java.lang.String zy;
    @Length(max=2,message="专长最大长度2位" ,groups=SaveGroup.class)
	private java.lang.String zc;
    @Length(max=40,message="工作单位最大长度40位" ,groups=SaveGroup.class)
	private java.lang.String gzdw;
	@NotNull(message="身份(SF)不能为空",groups=SaveGroup.class)
    @Length(max=2,message="身份(SF)最大长度2位" ,groups=SaveGroup.class)
	private java.lang.String sf;
    @Length(max=10,message="特殊身份(TSSF)最大长度10位" ,groups=SaveGroup.class)
	private java.lang.String tssf;
    @Length(max=20,message="档案编号最大长度20位" ,groups=SaveGroup.class)
	private java.lang.String dah;
	@NotNull(message="政治面貌(ZZMM)不能为空",groups=SaveGroup.class)
    @Length(max=2,message="政治面貌(ZZMM)最大长度2位" ,groups=SaveGroup.class)
	private java.lang.String zzmm;
    @Length(max=30,message="指纹编号最大长度30位" ,groups=SaveGroup.class)
	private java.lang.String zwbh;
	@NotNull(message="健康情况(JKZK)不能为空",groups=SaveGroup.class)
    @Length(max=1,message="健康情况(JKZK)最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String jkzk;
	@NotNull(message="足长不能为空",groups=SaveGroup.class)
    @Length(max=6,message="足长最大长度6位" ,groups=SaveGroup.class)
	private java.lang.String zuc;
	@NotNull(message="身高不能为空",groups=SaveGroup.class)
    @Length(max=5,message="身高最大长度5位" ,groups=SaveGroup.class)
	private java.lang.String sg;
    @Length(max=250,message="备注最大长度250位" ,groups=SaveGroup.class)
	private java.lang.String bz;
	@NotNull(message="在所状态(RYZT)不能为空",groups=SaveGroup.class)
    @Length(max=2,message="在所状态(RYZT)最大长度2位" ,groups=SaveGroup.class)
	private java.lang.String state;
	@NotNull(message="创建人不能为空",groups=SaveGroup.class)
    @Length(max=50,message="创建人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String creator;
	@NotNull(message="创建时间不能为空",groups=SaveGroup.class)
    @Length(max=11,message="创建时间最大长度11位" ,groups=SaveGroup.class)
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
    @Length(max=50,message="更新人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String updator;
    @Length(max=11,message="更新时间最大长度11位" ,groups=SaveGroup.class)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
	//columns END


	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	
	public java.lang.String getGcbh() {
		return this.gcbh;
	}
	
	public void setGcbh(java.lang.String value) {
		this.gcbh = value;
	}
	
	public java.lang.String getSnbh() {
		return this.snbh;
	}
	
	public void setSnbh(java.lang.String value) {
		this.snbh = value;
	}
	
	public java.lang.String getRybh() {
		return this.rybh;
	}
	
	public void setRybh(java.lang.String value) {
		this.rybh = value;
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
	
	public java.util.Date getCsrq() {
		return this.csrq;
	}
	
	public void setCsrq(java.util.Date value) {
		this.csrq = value;
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
	
	public java.lang.String getHyzk() {
		return this.hyzk;
	}
	
	public void setHyzk(java.lang.String value) {
		this.hyzk = value;
	}
	
	public java.lang.String getMz() {
		return this.mz;
	}
	
	public void setMz(java.lang.String value) {
		this.mz = value;
	}
	
	public java.lang.String getGj() {
		return this.gj;
	}
	
	public void setGj(java.lang.String value) {
		this.gj = value;
	}
	
	public java.lang.String getJg() {
		return this.jg;
	}
	
	public void setJg(java.lang.String value) {
		this.jg = value;
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
	
	public java.lang.String getWhcd() {
		return this.whcd;
	}
	
	public void setWhcd(java.lang.String value) {
		this.whcd = value;
	}
	
	public java.lang.String getZy() {
		return this.zy;
	}
	
	public void setZy(java.lang.String value) {
		this.zy = value;
	}
	
	public java.lang.String getZc() {
		return this.zc;
	}
	
	public void setZc(java.lang.String value) {
		this.zc = value;
	}
	
	public java.lang.String getGzdw() {
		return this.gzdw;
	}
	
	public void setGzdw(java.lang.String value) {
		this.gzdw = value;
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
	
	public java.lang.String getDah() {
		return this.dah;
	}
	
	public void setDah(java.lang.String value) {
		this.dah = value;
	}
	
	public java.lang.String getZzmm() {
		return this.zzmm;
	}
	
	public void setZzmm(java.lang.String value) {
		this.zzmm = value;
	}
	
	public java.lang.String getZwbh() {
		return this.zwbh;
	}
	
	public void setZwbh(java.lang.String value) {
		this.zwbh = value;
	}
	
	public java.lang.String getJkzk() {
		return this.jkzk;
	}
	
	public void setJkzk(java.lang.String value) {
		this.jkzk = value;
	}
	
	public java.lang.String getZuc() {
		return this.zuc;
	}
	
	public void setZuc(java.lang.String value) {
		this.zuc = value;
	}
	
	public java.lang.String getSg() {
		return this.sg;
	}
	
	public void setSg(java.lang.String value) {
		this.sg = value;
	}
	
	public java.lang.String getBz() {
		return this.bz;
	}
	
	public void setBz(java.lang.String value) {
		this.bz = value;
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
	
	
}

