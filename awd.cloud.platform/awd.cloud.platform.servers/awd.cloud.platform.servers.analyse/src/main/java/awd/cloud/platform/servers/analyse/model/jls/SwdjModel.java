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
public class SwdjModel implements Model{
	public static final String TABLE_ALIAS = "Swdj";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_TBR = "填表人";
	public static final String ALIAS_TBRQ = "填表日期";
	public static final String ALIAS_SWSJ = "死亡时间";
	public static final String ALIAS_SWDD = "死亡地点";
	public static final String ALIAS_SFJD = "死亡登记标识";
	public static final String ALIAS_SWYY = "死亡原因";
	public static final String ALIAS_JBR = "经办人";
	public static final String ALIAS_JBRQ = "经办日期";
	public static final String ALIAS_SWJDJL = "死亡鉴定结论";
	public static final String ALIAS_SWJDDW = "死亡鉴定单位";
	public static final String ALIAS_STCLQK = "尸体处理情况";
	public static final String ALIAS_SWTZR = "死亡通知人";
	public static final String ALIAS_TZSJ = "通知时间";
	public static final String ALIAS_FLWSH = "法律文书号";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_STATE = "删除状态(YWSTATE)";
	public static final String ALIAS_SCBZ = "上传标志(SHFO)";
	public static final String ALIAS_OPERATOR = "操作人";
	public static final String ALIAS_SJZLJSBZ = "及时标记(JSBZ)";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	private String id;
	private String jsbh;
	private String rybh;
	private String tbr;
	@JSONField(
			format = "yyyy-MM-dd"
	)
	@JsonFormat(
			pattern = "yyyy-MM-dd",
			timezone = "GMT+8"
	)
	@DateTimeFormat(
			pattern = "yyyy-MM-dd"
	)
	private Date tbrq;
	@JSONField(
			format = "yyyy-MM-dd HH:mm:ss"
	)
	@JsonFormat(
			pattern = "yyyy-MM-dd HH:mm:ss",
			timezone = "GMT+8"
	)
	@DateTimeFormat(
			pattern = "yyyy-MM-dd HH:mm:ss"
	)
	private Date swsj;
	private String swdd;
	private String sfjd;
	private String swyy;
	private String jbr;
	@JSONField(
			format = "yyyy-MM-dd"
	)
	@JsonFormat(
			pattern = "yyyy-MM-dd",
			timezone = "GMT+8"
	)
	@DateTimeFormat(
			pattern = "yyyy-MM-dd"
	)
	private Date jbrq;
	private String swjdjl;
	private String swjddw;
	private String stclqk;
	private String swtzr;
	@JSONField(
			format = "yyyy-MM-dd HH:mm:ss"
	)
	@JsonFormat(
			pattern = "yyyy-MM-dd HH:mm:ss",
			timezone = "GMT+8"
	)
	@DateTimeFormat(
			pattern = "yyyy-MM-dd HH:mm:ss"
	)
	private Date tzsj;
	private String flwsh;
	private String bz;
	private String state;
	private String scbz;
	private String operator;
	private String sjzljsbz;
	private String creator;
	@JSONField(
			format = "yyyy-MM-dd HH:mm:ss"
	)
	@JsonFormat(
			pattern = "yyyy-MM-dd HH:mm:ss",
			timezone = "GMT+8"
	)
	@DateTimeFormat(
			pattern = "yyyy-MM-dd HH:mm:ss"
	)
	private Date createtime;
	private String updator;
	@JSONField(
			format = "yyyy-MM-dd HH:mm:ss"
	)
	@JsonFormat(
			pattern = "yyyy-MM-dd HH:mm:ss",
			timezone = "GMT+8"
	)
	@DateTimeFormat(
			pattern = "yyyy-MM-dd HH:mm:ss"
	)
	private Date updatetime;
	private String jsbhString;
	private String stateString;
	private String scbzString;
	private String sjzljsbzString;

	public SwdjModel() {
	}

	public SwdjModel(String id) {
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

	public Date getTbrq() {
		return this.tbrq;
	}

	public void setTbrq(Date value) {
		this.tbrq = value;
	}

	public Date getSwsj() {
		return this.swsj;
	}

	public void setSwsj(Date value) {
		this.swsj = value;
	}

	public String getSwdd() {
		return this.swdd;
	}

	public void setSwdd(String value) {
		this.swdd = value;
	}

	public String getSfjd() {
		return this.sfjd;
	}

	public void setSfjd(String value) {
		this.sfjd = value;
	}

	public String getSwyy() {
		return this.swyy;
	}

	public void setSwyy(String value) {
		this.swyy = value;
	}

	public String getJbr() {
		return this.jbr;
	}

	public void setJbr(String value) {
		this.jbr = value;
	}

	public Date getJbrq() {
		return this.jbrq;
	}

	public void setJbrq(Date value) {
		this.jbrq = value;
	}

	public String getSwjdjl() {
		return this.swjdjl;
	}

	public void setSwjdjl(String value) {
		this.swjdjl = value;
	}

	public String getSwjddw() {
		return this.swjddw;
	}

	public void setSwjddw(String value) {
		this.swjddw = value;
	}

	public String getStclqk() {
		return this.stclqk;
	}

	public void setStclqk(String value) {
		this.stclqk = value;
	}

	public String getSwtzr() {
		return this.swtzr;
	}

	public void setSwtzr(String value) {
		this.swtzr = value;
	}

	public Date getTzsj() {
		return this.tzsj;
	}

	public void setTzsj(Date value) {
		this.tzsj = value;
	}

	public String getFlwsh() {
		return this.flwsh;
	}

	public void setFlwsh(String value) {
		this.flwsh = value;
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

	public String getSjzljsbz() {
		return this.sjzljsbz;
	}

	public void setSjzljsbz(String value) {
		this.sjzljsbz = value;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String value) {
		this.creator = value;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date value) {
		this.createtime = value;
	}

	public String getUpdator() {
		return this.updator;
	}

	public void setUpdator(String value) {
		this.updator = value;
	}

	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date value) {
		this.updatetime = value;
	}

	public String getJsbhString() {
		return this.jsbhString;
	}

	public String getStateString() {
		return this.stateString;
	}

	public void setStateString(String value) {
		this.stateString = value;
	}

	public String getScbzString() {
		return this.scbzString;
	}

	public void setScbzString(String value) {
		this.scbzString = value;
	}

	public String getSjzljsbzString() {
		return this.sjzljsbzString;
	}

	public void setSjzljsbzString(String value) {
		this.sjzljsbzString = value;
	}
}

