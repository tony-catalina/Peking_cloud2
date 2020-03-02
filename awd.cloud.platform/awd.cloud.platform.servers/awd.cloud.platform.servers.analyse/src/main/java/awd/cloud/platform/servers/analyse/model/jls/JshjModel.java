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
public class JshjModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Jshj";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "监室编号";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_LY = "会见事由(JSHJSY)";
	public static final String ALIAS_HJSJ = "会见时间";
	public static final String ALIAS_JSSJ = "结束时间";
	public static final String ALIAS_DCMJ = "带出民警";
	public static final String ALIAS_SJMJ = "收监民警";
	public static final String ALIAS_SQR = "申请人";
	public static final String ALIAS_HJSJCD = "会见时间长度";
	public static final String ALIAS_BLSJ = "办理时间";
	public static final String ALIAS_YYHJSJ = "预约会见时间";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_SQSJ = "申请时间";
	public static final String ALIAS_CXLY = "撤销理由";
	public static final String ALIAS_CXR = "撤销人";
	public static final String ALIAS_CXSJ = "撤销时间";
	public static final String ALIAS_SJYCQK = "收监异常情况，有无伤痕及违禁物品情况";
	public static final String ALIAS_YCXXQK = "异常详细情况说明";
	public static final String ALIAS_PSBZ = "批示标志";
	public static final String ALIAS_YWLCSYID = "业务流程ID";
	public static final String ALIAS_FLAG = "完成状态 1  办理  2 执行  3  收回";
	public static final String ALIAS_PASTABLE = "是否有效(SHFO)";
	public static final String ALIAS_BLLX = "办理类型(JSHJSY)";
	public static final String ALIAS_SCBZ = "上传标志";
	public static final String ALIAS_STATE = "状态";
	public static final String ALIAS_OPERATOR = "操作人";
	public static final String ALIAS_LDYJ = "领导意见";
	public static final String ALIAS_SJZL_JSBZ = "数据质量—及时标记";
	public static final String ALIAS_SAVETIME = "保存时间";
	public static final String ALIAS_SFCX = "是否初吸";
	public static final String ALIAS_HJLX = "家属会见类型";
	public static final String ALIAS_JSXM = "姓名（家属）";
	public static final String ALIAS_JSZJLX = "证件类型(ZJLX)";
	public static final String ALIAS_JSZJH = "家属证件号";
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

	private String ly;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hjsj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jssj;

	private String dcmj;

	private String sjmj;

	private String sqr;

	private String hjsjcd;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date blsj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date yyhjsj;

	private String bz;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date sqsj;

	private String cxly;

	private String cxr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date cxsj;

	private String sjycqk;

	private String ycxxqk;

	private String psbz;

	private String ywlcsyid;

	private String flag;

	private String pastable;

	private String bllx;

	private String scbz;

	private String state;

	private String operator;

	private String ldyj;

	private String sjzlJsbz;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date savetime;

	private String sfcx;

	private String hjlx;

	private String jsxm;

	private String jszjlx;

	private String jszjh;

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
	//columns END

	//字典
	private String lyString;
	private String stateString;
	private String bllxString;
	private String pastableString;
	private String jszjlxString;

	public String getLyString() {
		return lyString;
	}

	public String getStateString() {
		return stateString;
	}

	public String getBllxString() {
		return bllxString;
	}

	public String getPastableString() {
		return pastableString;
	}

	public String getJszjlxString() {
		return jszjlxString;
	}




	public JshjModel(){
	}
	public JshjModel(String id) {
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
	public String getLy() {
		return this.ly;
	}

	public void setLy(String value) {
		this.ly = value;
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

	public String getDcmj() {
		return this.dcmj;
	}

	public void setDcmj(String value) {
		this.dcmj = value;
	}
	public String getSjmj() {
		return this.sjmj;
	}

	public void setSjmj(String value) {
		this.sjmj = value;
	}
	public String getSqr() {
		return this.sqr;
	}

	public void setSqr(String value) {
		this.sqr = value;
	}
	public String getHjsjcd() {
		return this.hjsjcd;
	}

	public void setHjsjcd(String value) {
		this.hjsjcd = value;
	}

	public java.util.Date getBlsj() {
		return this.blsj;
	}

	public void setBlsj(java.util.Date value) {
		this.blsj = value;
	}


	public java.util.Date getYyhjsj() {
		return this.yyhjsj;
	}

	public void setYyhjsj(java.util.Date value) {
		this.yyhjsj = value;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}

	public java.util.Date getSqsj() {
		return this.sqsj;
	}

	public void setSqsj(java.util.Date value) {
		this.sqsj = value;
	}

	public String getCxly() {
		return this.cxly;
	}

	public void setCxly(String value) {
		this.cxly = value;
	}
	public String getCxr() {
		return this.cxr;
	}

	public void setCxr(String value) {
		this.cxr = value;
	}

	public java.util.Date getCxsj() {
		return this.cxsj;
	}

	public void setCxsj(java.util.Date value) {
		this.cxsj = value;
	}

	public String getSjycqk() {
		return this.sjycqk;
	}

	public void setSjycqk(String value) {
		this.sjycqk = value;
	}
	public String getYcxxqk() {
		return this.ycxxqk;
	}

	public void setYcxxqk(String value) {
		this.ycxxqk = value;
	}
	public String getPsbz() {
		return this.psbz;
	}

	public void setPsbz(String value) {
		this.psbz = value;
	}
	public String getYwlcsyid() {
		return this.ywlcsyid;
	}

	public void setYwlcsyid(String value) {
		this.ywlcsyid = value;
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
	public String getBllx() {
		return this.bllx;
	}

	public void setBllx(String value) {
		this.bllx = value;
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
	public String getLdyj() {
		return this.ldyj;
	}

	public void setLdyj(String value) {
		this.ldyj = value;
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

	public String getSfcx() {
		return this.sfcx;
	}

	public void setSfcx(String value) {
		this.sfcx = value;
	}
	public String getHjlx() {
		return this.hjlx;
	}

	public void setHjlx(String value) {
		this.hjlx = value;
	}
	public String getJsxm() {
		return this.jsxm;
	}

	public void setJsxm(String value) {
		this.jsxm = value;
	}
	public String getJszjlx() {
		return this.jszjlx;
	}

	public void setJszjlx(String value) {
		this.jszjlx = value;
	}
	public String getJszjh() {
		return this.jszjh;
	}

	public void setJszjh(String value) {
		this.jszjh = value;
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

