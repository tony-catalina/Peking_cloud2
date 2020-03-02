/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.entity;


import awd.cloud.platform.utils.CacheUtils;
import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.service.web.group.CreateGroup;
import awd.framework.common.service.web.group.UpdateGroup;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class JstzEntity extends SimpleGenericEntity<String> implements JbxxInfo {
	// alias
	public static final String TABLE_ALIAS = "监室调整";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_TZSJ = "调整时间";
	public static final String ALIAS_YJSH = "原监室";
	public static final String ALIAS_XJSH = "现监室";
	public static final String ALIAS_YY = "原因";
	public static final String ALIAS_TJR = "调监人";
	public static final String ALIAS_LDXM = "领导姓名";
	public static final String ALIAS_LDPSSJ = "领导批示时间";
	public static final String ALIAS_LDYJ = "领导意见";
	public static final String ALIAS_PSBZ = "批示标识";
	public static final String ALIAS_STATE = "删除状态";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_YWLCID = "业务流程ID";
	public static final String ALIAS_GJXM = "管教姓名";
	public static final String ALIAS_DJSJ = "登记时间";
	public static final String ALIAS_ZDZXM = "中队长姓名";
	public static final String ALIAS_ZDZPSSJ = "中队长批示时间";
	 //所有组
    @GroupSequence({CreateGroup.class, UpdateGroup.class})
    public interface All {
    }

	// columns START

	@NotNull(message = "监所编号不能为空", groups = CreateGroup.class)
	@Length(max = 9, message = "监所编号最大长度9位", groups = CreateGroup.class)
	private String jsbh;

	@NotNull(message="人员编号不能为空",groups=CreateGroup.class)
	@Length(max=21,message="人员编号最大长度21位" ,groups=CreateGroup.class)
	private String rybh;

	@NotNull(message = "调整时间不能为空")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date tzsj;

	@NotNull(message = "原监室不能为空", groups = CreateGroup.class)
	@Length(max = 4, message = "原监室最大长度4位", groups = CreateGroup.class)
	private String yjsh;

	@NotNull(message = "现监室不能为空", groups = CreateGroup.class)
	@Length(max = 4, message = "现监室最大长度4位", groups = CreateGroup.class)
	private String xjsh;

	@NotNull(message = "原因不能为空", groups = CreateGroup.class)
	@Length(max = 100, message = "原因最大长度100位", groups = CreateGroup.class)
	private String yy;

	private String yyString;
	@NotNull(message = "调监人不能为空", groups = CreateGroup.class)
	@Length(max = 40, message = "调监人最大长度40位", groups = CreateGroup.class)
	private String tjr;

	@Length(max = 30, message = "领导姓名最大长度30位", groups = CreateGroup.class)
	private String ldxm;

	// 领导批示时间
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date ldpssj;

	@Length(max = 500, message = "领导意见最大长度500位", groups = CreateGroup.class)
	private String ldyj;

	@Length(max = 4, message = "批示标识最大长度4位", groups = CreateGroup.class)
	private String psbz;

	@Length(max = 2, message = "删除状态最大长度2位", groups = CreateGroup.class)
	private String state;

	@NotNull(message = "创建人不能为空", groups = CreateGroup.class)
	@Length(max = 30, message = "创建人最大长度30位", groups = CreateGroup.class)
	private String creator;

	@NotNull(message = "创建时间不能为空", groups = CreateGroup.class)
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createtime;

	@Length(max = 30, message = "更新人最大长度30位", groups = CreateGroup.class)
	private String updator;

	@Length(max = 30, message = "管教姓名最大长度30位", groups = CreateGroup.class)
	private String gjxm;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updatetime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date djsj;

	@Length(max = 500, message = "备注最大长度500位", groups = CreateGroup.class)
	private String bz;

	@Length(max = 21, message = "业务流程ID最大长度21位", groups = CreateGroup.class)
	private String ywlcid;

	@Length(max=30,message="中队长姓名最大长度30位" ,groups=CreateGroup.class)
	private String zdzxm;



	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date zdzpssj;

	private String xmpy;
	private String bm;
	private String bmty;

	public String getZdzxm() {
		return zdzxm;
	}

	public void setZdzxm(String zdzxm) {
		this.zdzxm = zdzxm;
	}

	public Date getZdzpssj() {
		return zdzpssj;
	}

	public void setZdzpssj(Date zdzpssj) {
		this.zdzpssj = zdzpssj;
	}
	public String getXmpy() {
		return xmpy;
	}

	public void setXmpy(String xmpy) {
		this.xmpy = xmpy;
	}

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getBmty() {
		return bmty;
	}

	public void setBmty(String bmty) {
		this.bmty = bmty;
	}

	public String getYwlcid() {
		return ywlcid;
	}

	public void setYwlcid(String ywlcid) {
		this.ywlcid = ywlcid;
	}

	// columns END
	public JstzEntity() {
	}

	public JstzEntity(String id) {
		super.setId(id);
	}

	public String getJsbh() {
		return this.jsbh;
	}

	public String getJsbhString() {
		return CacheUtils.get().getJsbhString(this.jsbh);
	}

	public String getGjxm() {
		return gjxm;
	}

	public void setGjxm(String gjxm) {
		this.gjxm = gjxm;
	}

	public Date getDjsj() {
		return djsj;
	}

	public void setDjsj(Date djsj) {
		this.djsj = djsj;
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

	public Date getTzsj() {
		return this.tzsj;
	}

	public void setTzsj(Date value) {
		this.tzsj = value;
	}

	public String getYjsh() {
		return this.yjsh;
	}

	public void setYjsh(String value) {
		this.yjsh = value;
	}

	public String getXjsh() {
		return this.xjsh;
	}

	public void setXjsh(String value) {
		this.xjsh = value;
	}

	public String getYy() {
		return this.yy;
	}

	public void setYy(String value) {
		this.yy = value;
	}

	public String getYyString() {
		return CacheUtils.get().getDictionary("TJYY", this.getYy());
	}

	public String getTjr() {
		return this.tjr;
	}

	public void setTjr(String value) {
		this.tjr = value;
	}

	public String getLdxm() {
		return this.ldxm;
	}

	public void setLdxm(String value) {
		this.ldxm = value;
	}

	public Date getLdpssj() {
		return this.ldpssj;
	}

	public void setLdpssj(Date value) {
		this.ldpssj = value;
	}

	public String getLdyj() {
		return this.ldyj;
	}

	public void setLdyj(String value) {
		this.ldyj = value;
	}

	public String getPsbz() {
		return this.psbz;
	}

	public void setPsbz(String value) {
		this.psbz = value;
	}

	public String getState() {
		return this.state;
	}


	public String getStateString() {
		return CacheUtils.get().getDictionary("YWSTATE", this.state);
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

	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}

	@Override
	public String getJbxxSnbh() {
		// if(CacheUtils.get().getJbxx(this.rybh)==null) {
		// 	return "";
		// }
		// return CacheUtils.get().getJbxx(this.rybh).getString("snbh")==null?"":CacheUtils.get().getJbxx(this.rybh).getString("snbh");
		return null;
	}

	@Override
	public String getJbxxXm() {
		// if(CacheUtils.get().getJbxx(this.rybh)==null) {
		// 	return "";
		// }
		// return CacheUtils.get().getJbxx(this.rybh).getString("xm")==null?"":CacheUtils.get().getJbxx(this.rybh).getString("xm");
		return null;
	}

	@Override
	public String getJbxxJsh() {
		return null;
	}

	@Override
	public String getJbxxRsrq() {
		return null;
	}

	@Override
	public String getJbxxCsrq() {
		return null;
	}

	@Override
	public String getJbxxHjd() {
		return null;
	}


}
