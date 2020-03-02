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
public class ClcsModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Clcs";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_CSYY = "出所原因(CSYY)";
	public static final String ALIAS_QJLY = "请假理由";
	public static final String ALIAS_QJRDH = "请假人电话";
	public static final String ALIAS_ZJQX = "准假期限";
	public static final String ALIAS_CSSJ = "出所时间";
	public static final String ALIAS_CSQX = "处所去向(CSQX)";
	public static final String ALIAS_PZDW = "批准单位";
	public static final String ALIAS_PZR = "批准人";
	public static final String ALIAS_SCBZ = "上传标志";
	public static final String ALIAS_OPERATOR = "操作人";
	public static final String ALIAS_BLR = "办理人";
	public static final String ALIAS_DBR = "担保人";
	public static final String ALIAS_DBRDW = "担保人单位";
	public static final String ALIAS_DBRZW = "担保人职务";
	public static final String ALIAS_YDDH = "移动电话";
	public static final String ALIAS_DBRGH = "担保人固话";
	public static final String ALIAS_DBRZJH = "担保人证件号";
	public static final String ALIAS_YBDBRGX = "与被担保人关系(GX)";
	public static final String ALIAS_DBRDZ = "担保人地址";
	public static final String ALIAS_JDDW = "监督单位";
	public static final String ALIAS_ZCDWSZD = "转出单位所在地";
	public static final String ALIAS_SAVETIME = "保存时间";
	public static final String ALIAS_SJZL_JSBZ = "及时标记";
	public static final String ALIAS_KSSJ = "开始时间";
	public static final String ALIAS_JSRQ = "结束时间";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_STATE = "状态";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_PZJG = "批准机关";
	public static final String ALIAS_SNBX = "所内表现";
	public static final String ALIAS_HSSJ = "回所时间";
	public static final String ALIAS_ZBMJ = "值班民警";
	public static final String ALIAS_GJQM = "管教签名";
	public static final String ALIAS_GJYJ = "管教意见";
	public static final String ALIAS_JCLY = "解除理由";
	public static final String ALIAS_TQJCJLQXZ = "提前解除拘留期限至";
	public static final String ALIAS_GJQMRQ = "管教签名日期";
	public static final String ALIAS_DQWGDJR = "到期违规登记人";
	public static final String ALIAS_DQWGDJRQ = "到期违规登记日期";
	public static final String ALIAS_DQWGCLQK = "到期违规处理情况";
	
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

	private String csyy;

	private String qjly;

	private String qjrdh;

	private String zjqx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date cssj;

	private String csqx;

	private String pzdw;

	private String pzr;

	private String scbz;

	private String operator;

	private String blr;

	private String dbr;

	private String dbrdw;

	private String dbrzw;

	private String yddh;

	private String dbrgh;

	private String dbrzjh;

	private String ybdbrgx;

	private String dbrdz;

	private String jddw;

	private String zcdwszd;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date savetime;

	private String sjzlJsbz;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date kssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jsrq;

	private String bz;

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

	private String pzjg;

	private String snbx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hssj;

	private String zbmj;

	private String gjqm;

	private String gjyj;

	private String jcly;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date tqjcjlqxz;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date gjqmrq;

	private String dqwgdjr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date dqwgdjrq;

	private String dqwgclqk;
	//columns END


	//字典
	private String csyyString;
	private String csqxString;
	private String ybdbrgxString;
	private String stateString;

	public String getCsyyString() {
		return csyyString;
	}

	public String getCsqxString() {
		return csqxString;
	}

	public String getYbdbrgxString() {
		return ybdbrgxString;
	}

	public String getStateString() {
		return stateString;
	}

	public ClcsModel(){
	}
	public ClcsModel(String id) {
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
	public String getCsyy() {
		return this.csyy;
	}

	public void setCsyy(String value) {
		this.csyy = value;
	}
	public String getQjly() {
		return this.qjly;
	}

	public void setQjly(String value) {
		this.qjly = value;
	}
	public String getQjrdh() {
		return this.qjrdh;
	}

	public void setQjrdh(String value) {
		this.qjrdh = value;
	}
	public String getZjqx() {
		return this.zjqx;
	}

	public void setZjqx(String value) {
		this.zjqx = value;
	}

	public java.util.Date getCssj() {
		return this.cssj;
	}

	public void setCssj(java.util.Date value) {
		this.cssj = value;
	}

	public String getCsqx() {
		return this.csqx;
	}

	public void setCsqx(String value) {
		this.csqx = value;
	}
	public String getPzdw() {
		return this.pzdw;
	}

	public void setPzdw(String value) {
		this.pzdw = value;
	}
	public String getPzr() {
		return this.pzr;
	}

	public void setPzr(String value) {
		this.pzr = value;
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
	public String getBlr() {
		return this.blr;
	}

	public void setBlr(String value) {
		this.blr = value;
	}
	public String getDbr() {
		return this.dbr;
	}

	public void setDbr(String value) {
		this.dbr = value;
	}
	public String getDbrdw() {
		return this.dbrdw;
	}

	public void setDbrdw(String value) {
		this.dbrdw = value;
	}
	public String getDbrzw() {
		return this.dbrzw;
	}

	public void setDbrzw(String value) {
		this.dbrzw = value;
	}
	public String getYddh() {
		return this.yddh;
	}

	public void setYddh(String value) {
		this.yddh = value;
	}
	public String getDbrgh() {
		return this.dbrgh;
	}

	public void setDbrgh(String value) {
		this.dbrgh = value;
	}
	public String getDbrzjh() {
		return this.dbrzjh;
	}

	public void setDbrzjh(String value) {
		this.dbrzjh = value;
	}
	public String getYbdbrgx() {
		return this.ybdbrgx;
	}

	public void setYbdbrgx(String value) {
		this.ybdbrgx = value;
	}
	public String getDbrdz() {
		return this.dbrdz;
	}

	public void setDbrdz(String value) {
		this.dbrdz = value;
	}
	public String getJddw() {
		return this.jddw;
	}

	public void setJddw(String value) {
		this.jddw = value;
	}
	public String getZcdwszd() {
		return this.zcdwszd;
	}

	public void setZcdwszd(String value) {
		this.zcdwszd = value;
	}

	public java.util.Date getSavetime() {
		return this.savetime;
	}

	public void setSavetime(java.util.Date value) {
		this.savetime = value;
	}

	public String getSjzlJsbz() {
		return this.sjzlJsbz;
	}

	public void setSjzlJsbz(String value) {
		this.sjzlJsbz = value;
	}

	public java.util.Date getKssj() {
		return this.kssj;
	}

	public void setKssj(java.util.Date value) {
		this.kssj = value;
	}


	public java.util.Date getJsrq() {
		return this.jsrq;
	}

	public void setJsrq(java.util.Date value) {
		this.jsrq = value;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
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

	public String getPzjg() {
		return this.pzjg;
	}

	public void setPzjg(String value) {
		this.pzjg = value;
	}
	public String getSnbx() {
		return this.snbx;
	}

	public void setSnbx(String value) {
		this.snbx = value;
	}

	public java.util.Date getHssj() {
		return this.hssj;
	}

	public void setHssj(java.util.Date value) {
		this.hssj = value;
	}

	public String getZbmj() {
		return this.zbmj;
	}

	public void setZbmj(String value) {
		this.zbmj = value;
	}
	public String getGjqm() {
		return this.gjqm;
	}

	public void setGjqm(String value) {
		this.gjqm = value;
	}
	public String getGjyj() {
		return this.gjyj;
	}

	public void setGjyj(String value) {
		this.gjyj = value;
	}
	public String getJcly() {
		return this.jcly;
	}

	public void setJcly(String value) {
		this.jcly = value;
	}

	public java.util.Date getTqjcjlqxz() {
		return this.tqjcjlqxz;
	}

	public void setTqjcjlqxz(java.util.Date value) {
		this.tqjcjlqxz = value;
	}


	public java.util.Date getGjqmrq() {
		return this.gjqmrq;
	}

	public void setGjqmrq(java.util.Date value) {
		this.gjqmrq = value;
	}

	public String getDqwgdjr() {
		return this.dqwgdjr;
	}

	public void setDqwgdjr(String value) {
		this.dqwgdjr = value;
	}

	public java.util.Date getDqwgdjrq() {
		return this.dqwgdjrq;
	}

	public void setDqwgdjrq(java.util.Date value) {
		this.dqwgdjrq = value;
	}

	public String getDqwgclqk() {
		return this.dqwgclqk;
	}

	public void setDqwgclqk(String value) {
		this.dqwgclqk = value;
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

